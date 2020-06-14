package com.sachtech.datingapp.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.google.firebase.FirebaseApp;
import com.sachtech.datingapp.cometChat.CometChatAuth;

import org.jetbrains.annotations.Nullable;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;


public final class DatingApp extends Application {
   @Nullable
   public static Context application;
  // public static final DatingApp app= new DatingApp();

   public void onCreate() {
      super.onCreate();
      application = this;
      Fabric.with((Context) this, new Kit[]{(Kit) (new Crashlytics())});
      FirebaseApp.initializeApp((Context) this);
      FacebookSdk.sdkInitialize((Context) this);
      new CometChatAuth((Context) this).initializeCometChat();

   }

   }

