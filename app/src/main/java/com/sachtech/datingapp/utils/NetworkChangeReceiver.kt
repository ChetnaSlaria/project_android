package com.sachtech.datingapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.sachtech.datingapp.app.DatingApp.application

class NetworkChangeReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        isOnline()
    }

    private fun isOnline(): Boolean {

        val connectivityManager =
            application?.applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return (null != networkInfo && networkInfo.isConnected)
    }
}