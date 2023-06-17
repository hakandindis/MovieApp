package org.hakandindis.movieapp.data.remote.model.moviedetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String?,
)