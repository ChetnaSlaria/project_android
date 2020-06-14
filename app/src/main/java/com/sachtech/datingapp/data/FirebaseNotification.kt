package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class FirebaseNotification(
    val to:String,
    val collapse_key:String="type_a",
    val notification: Notification
)
@Keep
data class Notification(
    val body:String,
    val title:String
)