package org.hakandindis.movieapp.data.remote.service

import org.hakandindis.movieapp.data.remote.model.moviedetail.MovieDetailResponse
import org.hakandindis.movieapp.data.remote.model.popularmovie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Response<MovieResponse>

    @GET("movie/{movieId}")
    suspend fun getSelectedMovieById(
        @Path("movieId") movieId: String,
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Response<MovieDetailResponse>

    @GET("search/movie")
    suspend fun searchMovieByText(
        @Header("Authorization") token: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<MovieResponse>
}