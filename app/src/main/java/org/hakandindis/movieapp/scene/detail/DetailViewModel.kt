package org.hakandindis.movieapp.scene.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.movieapp.BuildConfig
import org.hakandindis.movieapp.data.remote.model.moviedetail.MovieDetailResponse
import org.hakandindis.movieapp.data.remote.service.MovieService
import org.hakandindis.movieapp.util.Languages
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieService: MovieService) : ViewModel() {

    val movieDetailResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()


    fun getMovieDetailById(movieId: Int) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieService.getSelectedMovieById(
                    movieId = movieId.toString(),
                    token = BuildConfig.BEARER_TOKEN,
                    language = Languages.ENGLISH.languageName
                )

                if (response.isSuccessful) {
                    movieDetailResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occurred"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}