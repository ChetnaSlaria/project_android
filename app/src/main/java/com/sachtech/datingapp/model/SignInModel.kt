package com.sachtech.datingapp.model

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.firebase.auth.AuthCredential
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.view.SignInView
import org.json.JSONException

class SignInModel {
    val firebaseHelper by lazy { FirebaseHelper() }

    fun signInWithEmailPassword(email: String, password: String, signInView: SignInView) {
        firebaseHelper.loginWithEmail(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                signInView.onSignIn(it.result!!)
            }
        }.addOnFailureListener {
            signInView.onFailure(it.localizedMessage)
        }
    }

    fun getUserDetails(uid: String?, signInView: SignInView) {
        firebaseHelper.getUserDetailsFromDatabase(uid!!).addOnCompleteListener {
            if (it.isSuccessful) {
                signInView.onUserDetails(it)
            }
        }.addOnFailureListener {
            signInView.onFailure(it.localizedMessage)
        }
    }

    fun signInwithCredentials(credential: AuthCredential, signInView: SignInView) {
        firebaseHelper.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                signInView.onSignIn(it.result!!)
                Log.e("credential",it.result.toString())
            }
        }.addOnFailureListener {
            Log.e("credential failure",it.message)
            signInView.onFailure(it.localizedMessage)
        }
    }

    fun getFacebookUserDetails(accessToken: AccessToken, signInView: SignInView) {
            var email_id: String? = null
            val dataRequest = GraphRequest.newMeRequest(accessToken) { `object`, response ->
                try {
                    val first_name = `object`.getString("name")
                    var picUrl=""
                    val picture = `object`.optJSONObject("picture")
                    if(picture!=null) {

                        val data = picture.optJSONObject("data")
                        if(data!=null) {
                            picUrl = data.getString("url")
                        }

                    }
                    email_id = `object`.getString("email")?:""
                    val token = accessToken.token
                    Log.e("fb url",""+picUrl)
                    if(picUrl.isEmpty())
                     picUrl = "https://graph.facebook.com/me/picture?type=normal&access_token=$token"
                   val fbuser=FacebookUser(first_name,email_id,picUrl,token)
                    signInView.onFacebookUserDetails(fbuser)
                } catch (e: JSONException) {
                    signInView.onFailure(e.localizedMessage)
                   // showToast(e.localizedMessage)
                }
            }
            val permission_param = Bundle()
            permission_param.putString("fields", "id,name,email,picture.type(large)")
            dataRequest.parameters = permission_param
            dataRequest.executeAsync()
    }

    @SuppressLint("CheckResult")
    fun downloadFbImage(
        profilePic: String,
        fbUser: FacebookUser,
        signInView: SignInView
    ) {

        apiHitter().downloadFbPicture(profilePic).subscribe({

                      signInView.onFbImageDownload(it.byteStream(),fbUser)


            },{
            showToast(it.localizedMessage?:"")
            })

    }


}


