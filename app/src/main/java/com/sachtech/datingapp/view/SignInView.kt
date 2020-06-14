package com.sachtech.datingapp.view

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.listener.FailureListener
import java.io.InputStream

interface SignInView : FailureListener{
    fun onSignIn(result: AuthResult)
    fun onUserDetails(it: Task<DocumentSnapshot>)
    fun onFacebookUserDetails(fbuser: FacebookUser)
    fun onFbImageDownload(
        byteStream: InputStream,
        fbUser: FacebookUser
    )

}