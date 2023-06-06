package org.hakandindis.movieapp.data.remote.model.people


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("profile_path") val profilePath: String?
)