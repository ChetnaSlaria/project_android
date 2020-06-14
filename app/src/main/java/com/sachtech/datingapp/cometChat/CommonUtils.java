package com.sachtech.datingapp.cometChat;

import android.content.Context;
import android.content.Intent;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.ui.chat.activity.CallActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class CommonUtils {
 //  public static final CommonUtils.Companion Companion = new CommonUtils.Companion((DefaultConstructorMarker)null);

      public final void startCallIntent(@NotNull String receiverType, @NotNull Context context, @NotNull User user, @NotNull String type, boolean isOutgoing, @NotNull String sessionId) {
         Intent callIntent = new Intent(context, CallActivity.class);
         callIntent.putExtra("user_name", user.getName());
         callIntent.putExtra("user_id", user.getUid());
         callIntent.putExtra("session_id", sessionId);
         callIntent.putExtra("user_avatar", user.getAvatar());
         callIntent.putExtra("receiver_type", receiverType);
         callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         callIntent.setAction(type);
         if (isOutgoing) {
            callIntent.setType("outgoing");
         } else {
            callIntent.setType("incoming");
         }

         context.startActivity(callIntent);
      }

   }

