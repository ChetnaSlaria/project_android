package com.sachtech.datingapp.networking

import com.google.firebase.auth.AuthCredential
import com.sachtech.datingapp.listener.FailureListener

interface GoogleCallbackListener:FailureListener {
    fun onGoogleSignInSuccess(account: AuthCredential)

}