package org.hakandindis.movieapp.data.remote.model.people

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class PeopleResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val people: List<People?>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)