/*
package com.mgazetti.android.util

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.GoogleUser
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.networking.GoogleCallbackListener
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.setprefObject


class Google {

    var RC_SIGN_IN = 901
    private var fragment: Fragment? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    private val firestoreHelper by lazy { FirebaseHelper() }
    var googleCallbackListener:GoogleCallbackListener?=null

    constructor(fragment: Fragment?,googleCallbackListener: GoogleCallbackListener?) {
        this.fragment = fragment
        this.googleCallbackListener=googleCallbackListener
    }
    constructor(fragment: Fragment?)
    {
        this.fragment=fragment
    }

  fun init() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(fragment?.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(fragment?.context!!, gso)
    }

    fun signInWithGoogle() {
        val signinIntent = googleSignInClient.signInIntent
        fragment?.startActivityForResult(signinIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(
        task: Task<GoogleSignInAccount>?
    ) {
        try {
            val account = task?.getResult(ApiException::class.java)
            val googleUser= GoogleUser(account?.displayName,account?.email,account?.photoUrl.toString(),account?.idToken)
            setprefObject(PrefKeys.INSTANCE.googleuser,googleUser)
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            googleCallbackListener?.onGoogleSignInSuccess(credential)

        } catch (e: ApiException) {
            googleCallbackListener?.onFailure(e.localizedMessage)
            Log.e("e", e.toString())
        }
    }

    private fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount?,
        body: () -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firestoreHelper.firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val name = account?.displayName
                val email = account?.email
                val photourl = account?.photoUrl
                body()
                //addUserDataToFirebase(firestoreHelper.firebaseAuth?.currentUser?.uid, account, body)
            } else {
                Log.e("googleexception", it.exception?.message)
            }
        }
    }

    */
/* private fun addUserDataToFirebase(
         uid: String?,
         account: GoogleSignInAccount?,
         body: () -> Unit
     ) {
        )
         firestoreHelper.addUserDetailsToDatabase(uid!!, user).addOnCompleteListener {
             if (it.isSuccessful) {
                 setprefObject("user", user)
                 body()
             } else
                 showToast("failed")
         }

     }*//*


    fun signOutFromGoogle() {
        googleSignInClient.signOut()
    }


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
}


*/
