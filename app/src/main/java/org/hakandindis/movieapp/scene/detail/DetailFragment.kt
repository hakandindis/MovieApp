package org.hakandindis.movieapp.scene.detail

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
import org.hakandindis.movieapp.databinding.FragmentDetailBinding
import org.hakandindis.movieapp.extension.loadImage

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDetailById(args.movieId)

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.movieDetailResponse.observe(viewLifecycleOwner) { movie ->
            binding.fragmentDetailThumbnail.loadImage(movie.backdropPath)
            binding.fragmentDetailVote.text = movie.voteAverage.toString()
            binding.fragmentDetailStudio.text = movie.productionCompanies?.first()?.name
            binding.fragmentDetailLanguage.text = movie.spokenLanguages?.first()?.englishName
            binding.fragmentDetailOverview.text = movie.overview

            binding.fragmentDetailGroup.isVisible = true

            (requireActivity() as MainActivity).supportActionBar?.title = movie.title
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragmentDetailProgressbar.isVisible = it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.fragmentDetailErrorText.text = it
            binding.fragmentDetailErrorText.isVisible = true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}