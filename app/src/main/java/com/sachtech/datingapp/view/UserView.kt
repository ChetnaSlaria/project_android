package com.sachtech.datingapp.view

import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.listener.FailureListener

interface UserView:FailureListener {
    fun onGettingAllUsers(it: QuerySnapshot)
    fun onLikeUser(user: User)
    fun onUnlikeUser(user: User)
    fun onSuccess()
    fun onRequestUpdateSuccess(isAccepted: Boolean) {}
    fun onMatchUser(uid: String) {}
}