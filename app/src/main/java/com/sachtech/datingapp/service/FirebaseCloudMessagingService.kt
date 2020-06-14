package com.sachtech.datingapp.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.sachtech.datingapp.R
import com.sachtech.datingapp.ui.home.HomeActivity


class FirebaseCloudMessagingService: FirebaseMessagingService(){

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        Log.e("notification", p0.toString())

        intent.putExtra("fcm_notification", "Y")
        intent.putExtra("user_id", p0.from)

        val uniqueInt = (System.currentTimeMillis() and 0xff).toInt()
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, uniqueInt, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
        notificationBuilder.setSmallIcon(R.drawable.app_icon)
            .setContentText(p0.notification?.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}
