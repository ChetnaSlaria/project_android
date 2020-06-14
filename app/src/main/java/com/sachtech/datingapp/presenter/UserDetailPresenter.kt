package com.sachtech.datingapp.presenter

import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.model.SignUpModel
import com.sachtech.datingapp.view.UserDetailView

class UserDetailPresenter(val userDetailView: UserDetailView) {

    val signUpModel by lazy { SignUpModel() }
    fun addUserDetailsToDatabase(user: User)
    {
        signUpModel.addUserDetailsToDatabase(user,userDetailView)
    }

    fun createChatUser(uid:String,name:String,email:String,avatar:String)
    {
        signUpModel.createCometUser(uid, name, email,avatar,userDetailView)
    }
}