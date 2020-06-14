package com.sachtech.datingapp.view

import com.sachtech.datingapp.listener.FailureListener

interface MatchUserView : FailureListener {
    fun onMatchUser(uid: String)
}
