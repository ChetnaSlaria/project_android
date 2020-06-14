package com.sachtech.datingapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UnblockData {

    @SerializedName("superhero3")
    @Expose
    var superhero3: Superhero3? = null
    @SerializedName("superhero4")
    @Expose
    var superhero4: Superhero4? = null

}

class Superhero3 {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}

class Superhero4 {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}

class UnblockResponse {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}