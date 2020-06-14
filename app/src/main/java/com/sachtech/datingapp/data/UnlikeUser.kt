package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
class UnlikeUser {
    var userId: List<String>? = null

    constructor()
    constructor(userId: List<String>) {
        this.userId = userId
    }

}