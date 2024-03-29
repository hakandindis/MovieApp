package org.hakandindis.movieapp.scene.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.movieapp.BuildConfig
import org.hakandindis.movieapp.data.remote.model.popularmovie.Movie
import org.hakandindis.movieapp.data.remote.service.MovieService
import org.hakandindis.movieapp.util.Languages
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieService: MovieService) : ViewModel() {
    val movieList: MutableLiveData<List<Movie?>?> = MutableLiveData()
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
                    token = BuildConfig.BEARER_TOKEN,
                    language = Languages.ENGLISH.languageName
                )

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movies)
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
                    token = BuildConfig.BEARER_TOKEN,
                    language = Languages.ENGLISH.languageName,
                    query = query
                )

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movies)
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