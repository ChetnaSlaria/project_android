package com.mgazetti.android.util

import com.facebook.login.LoginResult
import com.sachtech.datingapp.listener.FailureListener

interface FacebookCallbackListener:FailureListener
{
   fun onFacebookLoginSuccess(result: LoginResult?)

}