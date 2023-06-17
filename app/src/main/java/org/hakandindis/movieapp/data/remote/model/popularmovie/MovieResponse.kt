package org.hakandindis.movieapp.data.remote.model.popularmovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieResponse(
    @SerializedName("results") val movies: List<Movie?>?
)