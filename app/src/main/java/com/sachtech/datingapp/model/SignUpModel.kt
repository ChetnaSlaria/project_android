package com.sachtech.datingapp.model

import android.util.Log
import com.google.gson.Gson
import com.sachtech.datingapp.R
import com.sachtech.datingapp.app.DatingApp
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.ChatUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.view.SignUpView
import com.sachtech.datingapp.view.UserDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpModel {
    val firebaseHelper by lazy { FirebaseHelper() }

    fun signUp(email: String, password: String, signUpView: SignUpView) {
        firebaseHelper.signUpUserWithEmail(email, password).addOnCompleteListener {
            if(it.isSuccessful)
            signUpView.onSignUpSuccess(it.result)
        }.addOnFailureListener {
            signUpView.onFailure(it.localizedMessage)
        }
    }

    fun addUserDetailsToDatabase(user: User, userView: UserDetailView) {
        firebaseHelper.addUserDetailsToDatabase(user.uid, user).addOnCompleteListener {
            if (it.isSuccessful)
                Log.e("firebaseuser", it.toString())
                userView.onSuccess(it)

        }.addOnFailureListener {
            Log.e("firebasefailure", it.toString())
            userView.onFailure(it.localizedMessage)
        }
    }

    fun createCometUser(
        uid: String,
        name: String,
        email: String,
        avatar: String,
        userDetailView: UserDetailView
    ) {
        val chatUser=ChatUser(uid,name,email,avatar/*, metadata = Gson().toJson(uid)*/)
        val appId= DatingApp.application?.resources?.getString(R.string.comet_app_id)
        val apikey=DatingApp.application?.resources?.getString(R.string.comet_api_key)
        Log.e("cometuser",Gson().toJson(chatUser.toString()))
        apiHitter().createCometUser(apikey = apikey!!,appId = appId!!,chatUser = chatUser).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                userDetailView.onCreateChatUser(it)
                Log.e("cometUserapi", it.data.toString())
            },{
                userDetailView.onFailure(it.localizedMessage)
                Log.e("cometuserapi", "failure:${it.localizedMessage}")

            }
        )
    }

}