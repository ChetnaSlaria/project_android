package com.sachtech.datingapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Accepted

class AddFriendResponse {

    @SerializedName("data")
    var data: AddFriendData? = null

}

class AddFriendData {

    @SerializedName("accepted")
    var accepted: Accepted? = null

}

