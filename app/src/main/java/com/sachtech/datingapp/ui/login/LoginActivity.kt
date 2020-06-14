/*
package com.sachtech.datingapp.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.sachtech.datingapp.R
import com.sachtech.datingapp.base.BaseFragment
import com.sachtech.datingapp.cometChat.CometChatUser
import com.sachtech.datingapp.cometChat.listener.OnCometLogin
import com.sachtech.datingapp.data.ChatUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.location.MyLocationLIstener
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.home.HomeActivity
import com.sachtech.datingapp.ui.login.fragment.ProfileImageFragment
import com.sachtech.datingapp.ui.login.fragment.SignInFragment
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.Preferences.setPref
import com.sachtech.datingapp.utils.Preferences.setprefObject
import cool.rishab.gallerydemo.withPermissions
import gurtek.mrgurtekbase.KotlinBaseActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity(R.id.login_container),MyLocationLIstener.OnLocationChanged, OnCometLogin {
    override fun onCometLoginSuccess(p0: com.cometchat.pro.models.User?) {
        val chatUser= ChatUser(avatar = p0?.avatar!!,uid = p0?.uid!!,name = p0?.name!!,email = p0?.email!!)
        setprefObject(PrefKeys.COMETUSER, chatUser)
        showToast(p0.uid!!)
    }

    override fun onCometLoginFailure(localizedMessage: String) {
        showToast(localizedMessage)
    }

    var location: String = ""
    var latitude = 0.0
    var longitude = 0.0
    val firebaseHelper by lazy { FirebaseHelper() }
    val myLocationListener by lazy { MyLocationLIstener(this, this) }
    override fun onLocationUpdated(location: Location) {
        if (location != null) {
            latitude = location.latitude
            longitude = location.longitude
            setPref(PrefKeys.LATITUDE, latitude.toString())
            setPref(PrefKeys.LONGITUDE, longitude.toString())
            getLocationFromLatLng(latitude, longitude)
        } else {
            latitude = 0.0
            longitude = 0.0
            setPref(PrefKeys.LATITUDE, "0.0")
            setPref(PrefKeys.LONGITUDE, "0.0")
            getCurrentLocation()
        }

    }

    private fun getLocationFromLatLng(lat: Double, long: Double) {
        val geocoder = Geocoder(this)
        try {
            val addresses = geocoder.getFromLocation(lat, long, 1)
            if (addresses.size > 0) {
                val address = addresses.get(0)?.countryName
                setPref(PrefKeys.LOCATION, address)
            }
        } catch (ex: java.lang.Exception) {
            setPref(PrefKeys.LOCATION, "India")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       // printHashKey(this)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            val fcm = it.token
            Log.e("fcm", fcm)
            setPref(PrefKeys.FCM, fcm)
        }

        getUserStatus()
 if (getUid()!! != "")
             CometChatUser().getLoggedInUser()

        Handler().postDelayed({
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).withPermissions(activity = this)
            {
                try {
                    getCurrentLocation()
                } catch (ex: Exception) {

                }
            }
        }, 3000)


    }
 fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("hash",
                    "printHashKey() Hash Key: $hashKey"
                )
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("exc", "printHashKey()", e)
        } catch (e: java.lang.Exception) {
            Log.e("exc", "printHashKey()", e)
        }
    }

    private fun getUserStatus() {

            if (getprefObject<User>(PrefKeys.INSTANCE.user) == null && getUid() == "")
               // navigateToFragment<SignInFragment>()
            BaseFragment.replaceFragment(R.id.login_container,SignInFragment(),null)
            else if (getprefObject<User>(PrefKeys.INSTANCE.user) == null && getUid() != "") {
                //navigateToFragment<ProfileImageFragment>()
                BaseFragment.replaceFragment(R.id.login_container,ProfileImageFragment(),null)

            } else {
                startActivity(Intent(this, HomeActivity::class.java))

        }
    }

    private fun getCurrentLocation() {
        myLocationListener.init()
    }
}
*/
