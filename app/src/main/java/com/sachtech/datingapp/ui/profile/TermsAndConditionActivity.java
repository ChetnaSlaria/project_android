package com.sachtech.datingapp.ui.profile;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public final class TermsAndConditionActivity extends AppCompatActivity {

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_terms_n_conditions);
      WebView webView=findViewById(id.terms_view);
      webView.loadUrl("http://stsmentor.com/privacy_policy/rnikah/terms_and_conditions.html");
   }

}
