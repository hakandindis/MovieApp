package org.hakandindis.movieapp.scene.people

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.movieapp.BuildConfig
import org.hakandindis.movieapp.data.remote.model.people.People
import org.hakandindis.movieapp.data.remote.service.PeopleService
import org.hakandindis.movieapp.util.Languages
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val peopleService: PeopleService) : ViewModel() {

    val peopleList: MutableLiveData<List<People?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessages: MutableLiveData<String?> = MutableLiveData()


    init {
        getPopularPeople()
    }

    fun getPopularPeople() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response =
                    peopleService.getPopularPeople(
                        BuildConfig.BEARER_TOKEN,
                        Languages.ENGLISH.languageName
                    )
                if (response.isSuccessful) {
                    peopleList.postValue(response.body()?.people)
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

    fun searchPeopleByText(query: String) {
        viewModelScope.launch {
            try {
                val response =
                    peopleService.searchMovieByText(
                        token = BuildConfig.BEARER_TOKEN,
                        language = Languages.ENGLISH.languageName,
                        query = query
                    )
                if (response.isSuccessful) {
                    peopleList.postValue(response.body()?.people)
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
}