package com.sachtech.datingapp.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
class FriendStatusData {
    @SerializedName("uid")
    var uid: String = ""
    @SerializedName("isFriend")
    var requestStatus: Int = RequestSatus.pending
    @SerializedName("isRequested")
    var requestedBy: Int = RequestBy.Me
}

object RequestSatus {
    var pending = 0
    var accept = 1
    var decline = 2
}

object RequestBy {
    var Me = 0
    var Other = 1
}