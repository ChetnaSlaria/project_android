// SplashActivity.java
package com.sachtech.datingapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.ui.login.LoginActivity;

import org.jetbrains.annotations.Nullable;


public final class SplashActivity extends AppCompatActivity {

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_splash);
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
         @Override
         public void run() {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
         }
      },2000);
   }


}
