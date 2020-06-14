package com.sachtech.datingapp.view


import com.google.firebase.auth.AuthResult
import com.sachtech.datingapp.listener.FailureListener

interface SignUpView :FailureListener{
    fun onSignUpSuccess(result: AuthResult?)
}