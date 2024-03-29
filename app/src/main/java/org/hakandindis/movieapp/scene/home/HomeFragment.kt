package org.hakandindis.movieapp.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.movieapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

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
        binding.fragmentHomeSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchMovieByText(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query?.length == 0) {
                    viewModel.getPopularMovies()
                    binding.fragmentHomeSearchView.clearFocus()
                } else {
                    query?.let { viewModel.searchMovieByText(it) }
                }
                return true
            }
        })
    }

    private fun observeEvents() {

        viewModel.errorMessages.observe(viewLifecycleOwner) {
            binding.fragmentHomeErrorTextView.text = it
            binding.fragmentHomeErrorTextView.isVisible = true
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragmentHomeProgressBar.isVisible = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movieId: Int?) {
        movieId?.let {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }
}