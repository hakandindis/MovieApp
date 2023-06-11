package org.hakandindis.movieapp.data.remote.model.peopledetail


import com.google.gson.annotations.SerializedName

data class PeopleDetailResponse(
    @SerializedName("biography") val biography: String?,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("profile_path") val profilePath: String?
)