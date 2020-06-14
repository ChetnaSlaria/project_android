package com.sachtech.datingapp.data

import androidx.annotation.Keep


@Keep
data class ChatUser(
    var uid:String,
    var name:String,
    var email:String?=null,
    var avatar:String
   // val metadata: String
)
