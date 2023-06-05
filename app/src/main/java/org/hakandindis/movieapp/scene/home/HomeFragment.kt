package org.hakandindis.movieapp.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.movieapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        observeEvents()
    }

    private fun initializeViews() {
        movieAdapter = MovieAdapter(object : MovieClickListener {
            override fun onMovieClick(movieId: Int?) {
                movieId?.let {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(action)
                }
            }
        })
        binding.fragmentHomeMovieList.adapter = movieAdapter
    }

    private fun observeEvents() {
        viewModel.movieList.observe(viewLifecycleOwner) { list ->
            if(list.isNullOrEmpty()) {
                binding.fragmentHomeErrorTextView.text = "There is any movie"
                binding.fragmentHomeErrorTextView.isVisible = true
            } else {
                movieAdapter.submitList(list)
                binding.fragmentHomeErrorTextView.isVisible = false
            }
        }

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
}