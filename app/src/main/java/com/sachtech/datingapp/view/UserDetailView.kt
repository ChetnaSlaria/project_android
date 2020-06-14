package com.sachtech.datingapp.view

import com.google.android.gms.tasks.Task
import com.sachtech.datingapp.data.CreateUserResponse
import com.sachtech.datingapp.listener.FailureListener

interface UserDetailView:FailureListener{
    fun onSuccess(result: Task<Void>)
fun onCreateChatUser(it: CreateUserResponse)

}