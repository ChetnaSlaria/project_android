package com.sachtech.datingapp.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Keep
data class CreateUserResponse(
    @SerializedName("data") var data: Data? = null
)
@Keep
data class Data(

    @SerializedName("uid") var uid: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("createdAt") var createdAt: Int? = null,
    @SerializedName("createdBy") var createdBy: String? = null
    )

