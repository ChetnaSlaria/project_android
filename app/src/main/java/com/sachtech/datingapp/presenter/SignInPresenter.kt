package com.sachtech.datingapp.presenter


import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.model.SignInModel
import com.sachtech.datingapp.view.SignInView

class SignInPresenter(val signInView: SignInView) {

    val signInModel by lazy { SignInModel() }
    fun signIn(email: String, password: String) {
       signInModel.signInWithEmailPassword(email,password, signInView)
    }

    fun signInWithCredentials(credential: AuthCredential) {
        signInModel.signInwithCredentials(credential,signInView)
    }

    fun getUserDetails(uid: String?) {
        signInModel.getUserDetails(uid, signInView)
    }

    fun getFacebookUserDetails(accessToken: AccessToken) {
        signInModel.getFacebookUserDetails(accessToken,signInView)
    }

    fun downloadFbImage(profilePic: String, fbuser: FacebookUser) {
        signInModel.downloadFbImage(profilePic,fbuser,signInView)

    }

}