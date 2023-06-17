package org.hakandindis.movieapp.data.remote.model.moviedetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductionCompany(
    @SerializedName("name") val name: String?,
)