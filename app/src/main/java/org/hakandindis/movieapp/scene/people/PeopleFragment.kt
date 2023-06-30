package org.hakandindis.movieapp.scene.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.movieapp.databinding.FragmentPeopleBinding

@AndroidEntryPoint
class PeopleFragment : Fragment(), PeopleClickListener {
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.listener = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        observeEvents()
    }

    private fun initializeListeners() {
        binding.fragmentPeopleSearchbar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchPeopleByText(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query?.length == 0) {
                    viewModel.getPopularPeople()
                    binding.fragmentPeopleSearchbar.clearFocus()
                } else {
                    query?.let { viewModel.searchPeopleByText(it) }
                }
                return true
            }
        })
    }

    private fun observeEvents() {
        viewModel.errorMessages.observe(viewLifecycleOwner) {
            if (viewModel.peopleList.value.isNullOrEmpty()) {
                binding.fragmentPeopleErrorText.text = it
                binding.fragmentPeopleErrorText.isVisible = true
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragmentPeopleProgressbar.isVisible = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPeopleClick(peopleId: Int?) {
        peopleId?.let {
            val action = PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailFragment(it)
            findNavController().navigate(action)
        }
    }
}