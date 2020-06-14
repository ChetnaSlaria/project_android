package com.sachtech.datingapp.ui.chat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.BaseMessage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.cometChat.CometChatMessaging;
import com.sachtech.datingapp.cometChat.CometConstants;
import com.sachtech.datingapp.cometChat.StringContract.IntentString;
import com.sachtech.datingapp.cometChat.StringContract.RequestCode;
import com.sachtech.datingapp.cometChat.helper.CCPermissionHelper;
import com.sachtech.datingapp.helper.CometChatAudioHelper;
import com.sachtech.datingapp.helper.OutgoingAudioHelper;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;


public class CallActivity extends AppCompatActivity implements OnClickListener/*, OnMessageUpdate*/ {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CallActivity.class), "cometMessage", "getCometMessage()Lcom/sachtech/datingapp/cometChat/CometChatMessaging;"))};
   private String name;
   private String id;
   private String imageUrl;
   private String sessionID;
   private Uri notification;
   private boolean isOutGoing;
   private CometChatAudioHelper cometChatAudioHelper;
   @NotNull
   private HashMap _$_findViewCache;
   CometChatMessaging cometChatMessaging;

   public void onUpdate(@NotNull BaseMessage messageList) {
   }

   public void onError(@Nullable CometChatException error) {
   }

   String[] cometPermissions={new CCPermissionHelper().getREQUEST_PERMISSION_CAMERA(),new CCPermissionHelper().getREQUEST_PERMISSION_RECORD_AUDIO()};
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_call);
      this.cometChatAudioHelper = new CometChatAudioHelper((Context)this);
      cometChatMessaging=new CometChatMessaging();
      CometChatAudioHelper chatAudioHelper = this.cometChatAudioHelper;
      if (chatAudioHelper != null) {
         chatAudioHelper.initAudio();
      }

     notification = RingtoneManager.getDefaultUri(1);
try{
   if(getIntent().getAction().equals(CometChatConstants.CALL_TYPE_VIDEO)){
      if(!new CCPermissionHelper().hasPermissions(CallActivity.this,cometPermissions))
      {
         new  CCPermissionHelper().requestPermissions(
                 this,
                cometPermissions,
                 RequestCode.Companion.getVIDEO_CALL()
         );
      }
   }
   else if(getIntent().getAction().equals(CometChatConstants.CALL_TYPE_AUDIO))
   {
      if(!new CCPermissionHelper().hasPermissions(CallActivity.this,new String[]{new CCPermissionHelper().getREQUEST_PERMISSION_RECORD_AUDIO()}))
      {
         new  CCPermissionHelper().requestPermissions(
                 this,
                 new String[]{new CCPermissionHelper().getREQUEST_PERMISSION_RECORD_AUDIO()},
                 RequestCode.Companion.getVIDEO_CALL()
         );
      }
   }
} catch (NullPointerException var6) {
         var6.printStackTrace();
      }
      Intent intent = this.getIntent();

      TextView var13 = (TextView)findViewById(com.sachtech.datingapp.R.id.tvUserName);
      var13.setText((CharSequence)(intent != null ? intent.getStringExtra(IntentString.Companion.getUSER_NAME()) : null));
      intent.getStringExtra(IntentString.Companion.getUSER_ID());
      intent.getStringExtra(IntentString.Companion.getSESSION_ID());
      CircleImageView var14 = (CircleImageView)findViewById(com.sachtech.datingapp.R.id.circular_userPic);
      Picasso.get().load(intent.getStringExtra(IntentString.Companion.getUSER_AVATAR())).into(var14);
      ((RippleBackground)findViewById(R.id.content)).startRippleAnimation();
      ImageView var16 = (ImageView)findViewById(com.sachtech.datingapp.R.id.user_image);
      Picasso.get().load(intent.getStringExtra(IntentString.Companion.getUSER_AVATAR())).into(var16);
      String var12;
      if (this.getIntent().hasExtra(IntentString.Companion.getRECIVER_TYPE())) {
         if (this.getIntent().getStringExtra(IntentString.Companion.getRECIVER_TYPE()).equals("group")) {
            var12 = this.getIntent().getStringExtra(IntentString.Companion.getGROUP_NAME());
            this.name = var12;
            var12 = this.getIntent().getStringExtra(IntentString.Companion.getGROUP_ID());
            this.id = var12;
            intent = this.getIntent();
            this.imageUrl = intent != null ? intent.getStringExtra(IntentString.Companion.getGROUP_ICON()) : null;
         } else {
            var12 = this.getIntent().getStringExtra(IntentString.Companion.getUSER_NAME());
            this.name = var12;
            var12 = this.getIntent().getStringExtra(IntentString.Companion.getUSER_ID());
            this.id = var12;
            intent = this.getIntent();
            this.imageUrl = intent != null ? intent.getStringExtra(IntentString.Companion.getUSER_AVATAR()) : null;
         }
      }
      if(intent.getType().equals(IntentString.Companion.getINCOMING()))
      {
         chatAudioHelper.startIncomingAudio(notification,true);
         isOutGoing=false;
      }
      else if(intent.getType().equals(IntentString.Companion.getOUTGOING()))
      {
         cometChatAudioHelper.startOutgoingAudio(OutgoingAudioHelper.Type.IN_COMMUNICATION);
  /* LayoutParams rl = new LayoutParams(  RelativeLayout.LayoutParams.WRAP_CONTENT,
           RelativeLayout.LayoutParams.WRAP_CONTENT);
   rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
   rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
   rl.bottomMargin = 56;*/
         this.isOutGoing = true;
         FloatingActionButton var17 = (FloatingActionButton)findViewById(com.sachtech.datingapp.R.id.hangUp);
//   var17.setLayoutParams((android.view.ViewGroup.LayoutParams)rl);
         // var17 =findViewById(com.sachtech.datingapp.R.id.acceptCall);
         // var17.setVisibility(View.GONE);
         var13 = (TextView)findViewById(com.sachtech.datingapp.R.id.tvCallText);
         var13.setText((CharSequence)"Ringing...");
      }
      ((FloatingActionButton)findViewById(com.sachtech.datingapp.R.id.acceptCall)).setOnClickListener((OnClickListener)this);
      ((FloatingActionButton)findViewById(com.sachtech.datingapp.R.id.hangUp)).setOnClickListener((OnClickListener)this);
      var12 = this.getIntent().getStringExtra(IntentString.Companion.getSESSION_ID());
      this.sessionID = var12;
   }

   public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
      boolean var6;
      boolean var8;
      if (requestCode == RequestCode.Companion.getVOICE_CALL()) {
         var6 = false;
         var8 = false;
         if (grantResults.length == 0 || grantResults[0] != 0) {
            Toast.makeText((Context)this, (CharSequence)"Voice Call", Toast.LENGTH_SHORT).show();
         }
      } else if (requestCode == RequestCode.Companion.getVIDEO_CALL()) {
         var6 = false;
         var8 = false;
         if (grantResults.length == 0 || grantResults[0] != 0 || grantResults[1] != 0) {
            Toast.makeText((Context)this, (CharSequence)"Video Call", Toast.LENGTH_SHORT).show();
         }
      }

   }

   public void onClick(@Nullable View p0) {
      Integer var2 = p0 != null ? p0.getId() : null;
      int var3 = R.id.acceptCall;
      CometChatAudioHelper var10000;
      String var10001;
      CometChatMessaging var4;
      if (var2 != null) {
         if (var2 == var3) {
            var10000 = this.cometChatAudioHelper;
            if (var10000 != null) {
               var10000.stop(false);
            }

            var4 = cometChatMessaging;
            var10001 = this.sessionID;
           /* if (var10001 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("sessionID");
            }
*/
            RelativeLayout var10002 = (RelativeLayout)findViewById(com.sachtech.datingapp.R.id.main_call_view);
            //  Intrinsics.checkExpressionValueIsNotNull(var10002, "main_call_view");
            var4.acceptCall(var10001, var10002, (Activity) this);
            return;
         }
      }

      var3 = R.id.hangUp;
      if (var2 != null) {
         if (var2 == var3) {
            var10000 = this.cometChatAudioHelper;
            if (var10000 != null) {
               var10000.stop(false);
            }

            if (this.isOutGoing) {
               var4 = cometChatMessaging;
               var10001 = this.sessionID;
               if (var10001 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("sessionID");
               }

               var4.rejectCall(var10001, "cancelled", (Activity) this);
            } else {
               var4 = cometChatMessaging;
               var10001 = this.sessionID;
               if (var10001 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("sessionID");
               }

               var4.rejectCall(var10001, "rejected", (Activity) this);
            }
         }
      }
   }

   protected void onStart() {
      super.onStart();
      cometChatMessaging.addCallListener((RelativeLayout)findViewById(com.sachtech.datingapp.R.id.main_call_view), (Activity)this, CometConstants.INSTANCE.getCallListener());
   }

   protected void onDestroy() {
      super.onDestroy();
      cometChatMessaging.removeCallListener(CometConstants.INSTANCE.getCallListener());
      CometChatAudioHelper var10000 = this.cometChatAudioHelper;
      if (var10000 != null) {
         var10000.stop(false);
      }

   }
}
