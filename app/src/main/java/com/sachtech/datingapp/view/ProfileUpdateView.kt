package com.sachtech.datingapp.view

import com.sachtech.datingapp.listener.FailureListener

interface ProfileUpdateView:FailureListener{
    fun onUpdateProfile()
}