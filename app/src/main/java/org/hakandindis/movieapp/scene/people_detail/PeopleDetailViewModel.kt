package org.hakandindis.movieapp.scene.people_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.movieapp.BuildConfig
import org.hakandindis.movieapp.data.remote.model.peopledetail.PeopleDetailResponse
import org.hakandindis.movieapp.data.remote.service.PeopleService
import org.hakandindis.movieapp.util.Languages
import javax.inject.Inject


@HiltViewModel
class PeopleDetailViewModel @Inject constructor(private val peopleService: PeopleService) :
    ViewModel() {

    val peopleDetailResponse: MutableLiveData<PeopleDetailResponse> = MutableLiveData()


    fun loadPeopleDetailById(peopleId: Int) {

        viewModelScope.launch {
            try {
                val response = peopleService.getSelectedPersonById(
                    personId = peopleId,
                    token = BuildConfig.BEARER_TOKEN,
                    language = Languages.ENGLISH.languageName
                )

                if (response.isSuccessful) {
                    peopleDetailResponse.postValue(response.body())
                } else {
                    //
                }
            } catch (e: Exception) {
                println(e.message)
            } finally {

            }
        }
    }
}