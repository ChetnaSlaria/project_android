package com.sachtech.datingapp.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Friend(
    @SerializedName("accepted")
    val accepted: List<String>
)