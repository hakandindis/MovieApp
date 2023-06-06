package org.hakandindis.movieapp.data.remote.service

import org.hakandindis.movieapp.data.remote.model.people.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PeopleService {

    @GET("trending/person/week")
    suspend fun getPopularPeople(@Header("Authorization") token: String): Response<PeopleResponse>

}