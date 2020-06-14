/*
package com.sachtech.datingapp.ui.splash

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.github.florent37.kotlin.pleaseanimate.please
import com.sachtech.datingapp.R
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.login.LoginActivity
import gurtek.mrgurtekbase.KotlinBaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : KotlinBaseActivity() {

    val firebaseHelper by lazy { FirebaseHelper() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //getHashKey()
        please(duration = 1000) {
            animate(welcome_text) toBe {
                aboveOf(view1)
                visible()
            }
                animate(welcome_text) toBe {
                    centerInParent(true, true)
                    visible()
                }
        }.start()

        Handler().apply {
            this.postDelayed(
                Runnable {
                    openA(LoginActivity::class)
                    finish()
                }, 2000
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.FROYO)
    @SuppressLint("PackageManagerGetSignatures")
    fun getHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }
}
*/
