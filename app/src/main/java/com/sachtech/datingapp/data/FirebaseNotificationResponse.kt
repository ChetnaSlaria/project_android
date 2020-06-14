package com.sachtech.datingapp.data


import com.google.gson.annotations.SerializedName

data class FirebaseNotificationResponse (
    @SerializedName("multicast_id") var multicastId: Int? = null,
    @SerializedName("success") var success: Int? = null,
    @SerializedName("failure") var failure: Int? = null,
    @SerializedName("canonical_ids") var canonicalIds: Int? = null,
    @SerializedName("results") var results: List<Result>? = null
    ){
    data class Result(
        @SerializedName("error") var error: String? = null
    )
}



