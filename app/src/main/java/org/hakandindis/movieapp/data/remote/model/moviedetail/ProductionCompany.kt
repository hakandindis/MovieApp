package org.hakandindis.movieapp.data.remote.model.moviedetail


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("name") val name: String?,
)