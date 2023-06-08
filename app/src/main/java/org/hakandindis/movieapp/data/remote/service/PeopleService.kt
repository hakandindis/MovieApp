package org.hakandindis.movieapp.data.remote.service

import org.hakandindis.movieapp.data.remote.model.people.PeopleResponse
import org.hakandindis.movieapp.data.remote.model.peopledetail.PeopleDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("trending/person/week")
    suspend fun getPopularPeople(
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Response<PeopleResponse>

    @GET("person/{personId}")
    suspend fun getSelectedPersonById(
        @Path("personId") personId: Int,
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Response<PeopleDetailResponse>


    @GET("search/person")
    suspend fun searchMovieByText(
        @Header("Authorization") token: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<PeopleResponse>


}