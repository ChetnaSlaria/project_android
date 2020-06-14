package com.sachtech.datingapp.data


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DeleteFriend(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("message")
        val message: String,
        @SerializedName("success")
        val success: Boolean
    )
}