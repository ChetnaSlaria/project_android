package com.sachtech.datingapp.ui.profile;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;

import org.jetbrains.annotations.Nullable;


public final class PrivacyActivity extends AppCompatActivity {

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_privacy_policy);
      WebView webView=findViewById(id.privacy_view);
      webView.loadUrl("http://stsmentor.com/privacy_policy/rnikah/privacy_policy.html");
   }

}
