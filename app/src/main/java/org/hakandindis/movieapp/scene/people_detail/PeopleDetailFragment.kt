package org.hakandindis.movieapp.scene.people_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.movieapp.MainActivity
import org.hakandindis.movieapp.databinding.FragmentPeopleDetailBinding
import org.hakandindis.movieapp.extension.loadImage

@AndroidEntryPoint
class PeopleDetailFragment : Fragment() {
    private var _binding: FragmentPeopleDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleDetailViewModel by viewModels()
    private val args by navArgs<PeopleDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPeopleDetailById(args.peopleId)

        observeEvents()
    }


    private fun observeEvents() {
        viewModel.peopleDetailResponse.observe(viewLifecycleOwner) { people ->
            binding.fragmentDetailThumbnail.loadImage(people.profilePath)
            binding.fragmentDetailVote.text = people.popularity.toString()
            binding.fragmentDetailStudio.text = people.placeOfBirth
            binding.fragmentDetailLanguage.text = people.birthday
            binding.fragmentDetailOverview.text = people.biography

            binding.fragmentDetailGroup.isVisible = true

            (requireActivity() as MainActivity).supportActionBar?.title = people.name
        }
    }

}