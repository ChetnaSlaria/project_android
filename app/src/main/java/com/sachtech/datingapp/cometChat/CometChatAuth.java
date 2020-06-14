package com.sachtech.datingapp.cometChat;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.CometChat.CallbackListener;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.cometChat.listener.OnCometLogin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class CometChatAuth {
   @NotNull
   private final Context context;

   public final void initializeCometChat() {
      AppSettings appSettings=new AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion("us").build();
      CometChat.init(context,context.getResources().getString(R.string.comet_app_id), appSettings, new CallbackListener<String>() {
         @Override
         public void onSuccess(String s) {
            Log.e("success",s);
          //  this.onSuccess((String)s);
         }

         @Override
         public void onError(CometChatException e) {
            Toast.makeText(CometChatAuth.this.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

         }
      });

   }

   public final void loginToCometChat(@NotNull String uid, @NotNull final OnCometLogin onCometLogin) {
      CometChat.login(uid, this.context.getResources().getString(R.string.comet_api_key), (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable User user) {
            OnCometLogin cometLogin = onCometLogin;
if(user!=null)
            cometLogin.onCometLoginSuccess(user);
         }

         public void onSuccess(Object var1) {
            this.onSuccess((User)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            OnCometLogin cometLogin = onCometLogin;
            String error = p0 != null ? p0.getLocalizedMessage() : null;
            cometLogin.onCometLoginFailure(error);
         }
      }));
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   public CometChatAuth(@NotNull Context context) {
      this.context = context;
   }
}
