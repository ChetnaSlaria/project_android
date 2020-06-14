package com.sachtech.datingapp.networking

import android.content.Intent
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.mgazetti.android.util.FacebookCallbackListener

import java.util.*


class Facebook :
    FacebookCallback<LoginResult> {

    lateinit var fragment: Fragment
    lateinit var facebookCallbackListener: FacebookCallbackListener

    constructor(fragment: Fragment, facebookCallbackListener: FacebookCallbackListener) {
        this.fragment = fragment
        this.facebookCallbackListener = facebookCallbackListener
    }

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }

    val helper by lazy { FirebaseHelper() }
    val fbInstace = LoginManager.getInstance()
    var callbackManager: CallbackManager? = null

    fun init() {
        initCallback()
        registerCallback(initCallback())
    }

    private fun initCallback(): CallbackManager? {
        callbackManager = CallbackManager.Factory.create()
        return callbackManager
    }

    private fun registerCallback(callback: CallbackManager?) {
        fbInstace.logInWithReadPermissions(
            fragment,
            Arrays.asList("public_profile", "email")
        )
        fbInstace.registerCallback(callback, this)
    }

    fun onActivityResult(requestCode: Int, resultcode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultcode, data)
    }

    fun logout() {
        LoginManager.getInstance().logOut()
    }

    override fun onSuccess(result: LoginResult?) {
        facebookCallbackListener.onFacebookLoginSuccess(result)
    }

    override fun onCancel() {
        facebookCallbackListener.onFailure("Cancelled")
    }

    override fun onError(error: FacebookException?) {
        facebookCallbackListener.onFailure(error?.message!!)
    }

}