package com.sachtech.datingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        window.decorView.postDelayed({
          //  CometChatAuth(this).initializeCometChat()
        }, 10000)
    }
}



