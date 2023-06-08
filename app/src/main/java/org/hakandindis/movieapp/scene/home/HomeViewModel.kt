package org.hakandindis.movieapp.scene.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.movieapp.data.remote.model.popularmovie.MovieItem
import org.hakandindis.movieapp.data.remote.service.MovieService
import org.hakandindis.movieapp.util.ApiConstants
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieService: MovieService) : ViewModel() {
    val movieList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessages: MutableLiveData<String?> = MutableLiveData()

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieService.getPopularMovies(
                    token = ApiConstants.BEARER_TOKEN,
                    ApiConstants.TURKISH
                )

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movieItems)
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessages.value = "An unknown error occured"
                    } else {
                        errorMessages.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessages.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

    fun searchMovieByText(query: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = movieService.searchMovieByText(
                    token = ApiConstants.BEARER_TOKEN,
                    language = ApiConstants.TURKISH,
                    query = query
                )

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movieItems)
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessages.value = "An unknown error occurred"
                    } else {
                        errorMessages.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessages.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}