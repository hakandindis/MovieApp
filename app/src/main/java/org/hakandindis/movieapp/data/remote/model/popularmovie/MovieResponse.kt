package org.hakandindis.movieapp.data.remote.model.popularmovie


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val movieItems: List<MovieItem?>?
    //video 12:21
)