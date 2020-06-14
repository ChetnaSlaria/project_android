package com.sachtech.datingapp.data


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Country(
    @SerializedName("data")
    var `data`: List<CountryData>,
    @SerializedName("Message")
    var message: String,
    @SerializedName("Status")
    var status: Boolean
)
    @Keep
    data class CountryData(
        @SerializedName("id")
        var id: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("phonecode")
        var phonecode: String,
        @SerializedName("sortname")
        var sortname: String
    )
