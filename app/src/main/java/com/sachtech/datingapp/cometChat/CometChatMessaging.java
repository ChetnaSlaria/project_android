package com.sachtech.datingapp.cometChat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.Call;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.CometChat.CallListener;
import com.cometchat.pro.core.CometChat.CallbackListener;
import com.cometchat.pro.core.CometChat.MessageListener;
import com.cometchat.pro.core.CometChat.OngoingCallListener;
import com.cometchat.pro.core.MessagesRequest;
import com.cometchat.pro.core.MessagesRequest.MessagesRequestBuilder;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.AppEntity;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.models.CustomMessage;
import com.cometchat.pro.models.MediaMessage;
import com.cometchat.pro.models.TextMessage;
import com.cometchat.pro.models.TypingIndicator;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.app.DatingApp;
import com.sachtech.datingapp.cometChat.listener.OnGettingMessage;
import com.sachtech.datingapp.cometChat.listener.OnMessageUpdate;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.ui.chat.activity.CallActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


public final class CometChatMessaging {
   @Nullable
   private OnMessageUpdate onMessageUpdate;

   @Nullable
   public final OnMessageUpdate getOnMessageUpdate() {
      return this.onMessageUpdate;
   }

   public final void setOnMessageUpdate(@Nullable OnMessageUpdate var1) {
      this.onMessageUpdate = var1;
   }

   public final void fetchAllMessage(@NotNull String uid, @NotNull final OnGettingMessage ongettingMessage) {

      MessagesRequest messagesRequest = (MessagesRequest)null;
      boolean limit = true;
      messagesRequest = (new MessagesRequestBuilder()).setUID(uid).build();
      if (messagesRequest != null) {
         messagesRequest.fetchPrevious((CallbackListener)(new CallbackListener() {
            public void onSuccess(@Nullable List p0) {
               Collection var2 = (Collection)p0;
               boolean var3 = false;
               boolean var4 = false;
               if (var2 != null && !var2.isEmpty()) {
                  Iterator var6 = p0.iterator();

                  while(var6.hasNext()) {
                     BaseMessage baseMessage = (BaseMessage)var6.next();
                     ongettingMessage.onGettingMessage(baseMessage);
                  }
               }

            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
               this.onSuccess((List)var1);
            }

            public void onError(@Nullable CometChatException p0) {
               Log.d("ContentValues", "Message fetching failed with exception: " + (p0 != null ? p0.getMessage() : null));
            }
         }));
      }

   }

   public final void removeMessageListener(@NotNull String listener) {
      CometChat.removeMessageListener(listener);
   }

   public final void removeCallListener(@NotNull String listener) {
      CometChat.removeCallListener(listener);
   }

   public final void initiateCall(@NotNull String receiverId, @NotNull String callType) {
      final String receiverType = CometChatConstants.RECEIVER_TYPE_USER;
      Call call = new Call(receiverId, receiverType, callType);
      CometChat.initiateCall(call, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable Call p0) {
            CommonUtils utils=new CommonUtils();
          //  Companion var10000 = CommonUtils.Companion;
            String var10001 = receiverType;
          /*  Context var10002 = ;
            if (var10002 == null) {
               Intrinsics.throwNpe();
            }*/

            AppEntity var10003 = p0 != null ? p0.getCallReceiver() : null;
            if (var10003 == null) {
               throw new TypeCastException("null cannot be cast to non-null type com.cometchat.pro.models.User");
            } else {
               User var3 = (User)var10003;
               String var10004 = p0.getType();
               String var10006 = p0.getSessionId();
               utils.startCallIntent(var10001, DatingApp.application, var3, var10004, true, var10006);
               OnMessageUpdate var2 = onMessageUpdate;
               if (var2 != null) {
                  var2.onUpdate((BaseMessage)p0);
               }

            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((Call)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onError(p0);
            }

         }
      }));
   }

   public final void startCall(@NotNull final Activity activity, @NotNull RelativeLayout view, @NotNull String sessionId) {
      Intrinsics.checkParameterIsNotNull(activity, "activity");
      Intrinsics.checkParameterIsNotNull(view, "view");
      Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
      CometChat.startCall(activity, sessionId, view, (OngoingCallListener)(new OngoingCallListener() {
         public void onUserJoined(@Nullable User p0) {
            Log.d("ContentValues", "User joined call: " + (p0 != null ? p0.getName() : null));
         }

         public void onUserLeft(@Nullable User p0) {
            activity.finish();
            Log.d("ContentValues", "User left call: " + (p0 != null ? p0.getName() : null));
         }

         public void onError(@Nullable CometChatException p0) {
            activity.finish();
            CommonsKt.showToast("Call connection error");
         }

         public void onCallEnded(@Nullable Call p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (p0 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)p0);
            }

            if (activity instanceof CallActivity) {
               activity.onBackPressed();
            }

            CommonsKt.showToast("Call ended ");
         }
      }));
   }

   public final void acceptCall(@NotNull String sessionID, @NotNull final RelativeLayout view, @NotNull final Activity activity) {
     /* Intrinsics.checkParameterIsNotNull(sessionID, "sessionID");
      Intrinsics.checkParameterIsNotNull(view, "view");
      Intrinsics.checkParameterIsNotNull(activity, "activity");*/
      CometChat.acceptCall(sessionID, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable Call p0) {
            if (p0 != null) {
               OnMessageUpdate var10000 = onMessageUpdate;
               if (var10000 != null) {
                  var10000.onUpdate((BaseMessage)p0);
               }

               CometChatMessaging var2 = CometChatMessaging.this;
               Activity var10001 = activity;
               RelativeLayout var10002 = view;
               String var10003 = p0.getSessionId();
               Intrinsics.checkExpressionValueIsNotNull(var10003, "p0.sessionId");
               var2.startCall(var10001, var10002, var10003);
            }

         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((Call)var1);
         }

         public void onError(@Nullable CometChatException p0) {
         }
      }));
   }

   public final void rejectCall(@NotNull String sessionID, @NotNull String call_status, @NotNull final Activity activity) {
      CometChat.rejectCall(sessionID, call_status, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable Call p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (p0 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)p0);
            }

            activity.finish();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((Call)var1);
         }

         public void onError(@Nullable CometChatException p0) {
         }
      }));
   }

   public final void addCallListener(@Nullable final RelativeLayout view, @NotNull final Activity activity, @NotNull String listener) {
      CometChat.addCallListener(listener, (CallListener)(new CallListener() {
         public void onOutgoingCallAccepted(@Nullable Call p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (p0 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)p0);
            }

            CometChatMessaging var2 = CometChatMessaging.this;
            Activity var10001 = activity;
            RelativeLayout var10002 = view;
            if (var10002 == null) {
               Intrinsics.throwNpe();
            }

            String var10003 = p0 != null ? p0.getSessionId() : null;
            if (var10003 == null) {
               Intrinsics.throwNpe();
            }

            var2.startCall(var10001, var10002, var10003);
         }

         public void onIncomingCallReceived(@Nullable Call p0) {
            if (p0 != null) {
               OnMessageUpdate var10000 = onMessageUpdate;
               if (var10000 != null) {
                  var10000.onUpdate((BaseMessage)p0);
               }

               if (p0.getReceiverType().equals(CometChatConstants.RECEIVER_TYPE_USER)) {
                  CommonUtils var2 =new  CommonUtils();
                  Context var10002 =new  DatingApp().getApplicationContext();
                  if (var10002 == null) {
                     Intrinsics.throwNpe();
                  }

                  AppEntity var10003 = p0.getCallInitiator();
                  if (var10003 == null) {
                     throw new TypeCastException("null cannot be cast to non-null type com.cometchat.pro.models.User");
                  }

                  User var3 = (User)var10003;
                  String var10004 = p0.getType();
                  Intrinsics.checkExpressionValueIsNotNull(var10004, "p0.type");
                  String var10006 = p0.getSessionId();
                  Intrinsics.checkExpressionValueIsNotNull(var10006, "p0.sessionId");
                  var2.startCallIntent("user", var10002, var3, var10004, false, var10006);
               }
            }

         }

         public void onIncomingCallCancelled(@Nullable Call p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (p0 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)p0);
            }

            activity.finish();
         }

         public void onOutgoingCallRejected(@Nullable Call p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {


               var10000.onUpdate((BaseMessage)p0);
            }

            activity.finish();
         }
      }));
   }

   public final void sendTextMessage(@NotNull String receiverID, @NotNull String messageText) {
      String messageType = CometChatConstants.MESSAGE_TYPE_TEXT;
      String receiverType = CometChatConstants.RECEIVER_TYPE_USER;
      TextMessage textMessage = new TextMessage(receiverID, messageText,receiverType);
      CometChat.sendMessage(textMessage, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable TextMessage p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (p0 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)p0);
            }

         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((TextMessage)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onError(p0);
            }

         }
      }));
   }

   public final void sendMediaMessage(@NotNull String receiverID, @NotNull String message) {
      String messageType = CometChatConstants.MESSAGE_TYPE_IMAGE;
      String receiverType = CometChatConstants.RECEIVER_TYPE_USER;
      MediaMessage mediaMessage = new MediaMessage(receiverID, new File(message), messageType, receiverType);
      CometChat.sendMediaMessage(mediaMessage, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable MediaMessage p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onUpdate((BaseMessage)p0);
            }

         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((MediaMessage)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onError(p0);
            }

         }
      }));
   }

   public final void addMessageListener(@NotNull String listener) {
      Intrinsics.checkParameterIsNotNull(listener, "listener");
      CometChat.addMessageListener(listener, (MessageListener)(new MessageListener() {
         public void onTextMessageReceived(@Nullable TextMessage message) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (message == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)message);
            }

         }

         public void onMediaMessageReceived(@Nullable MediaMessage message) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (message == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)message);
            }

         }

         public void onCustomMessageReceived(@Nullable CustomMessage message) {
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               if (message == null) {
                  Intrinsics.throwNpe();
               }

               var10000.onUpdate((BaseMessage)message);
            }

         }
      }));
   }

   public final void deleteMessage() {
      CometChat.deleteMessage(12345, (CallbackListener)(new CallbackListener() {
         public void onSuccess(@NotNull BaseMessage message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onUpdate(message);
            }

         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((BaseMessage)var1);
         }

         public void onError(@NotNull CometChatException e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            OnMessageUpdate var10000 = onMessageUpdate;
            if (var10000 != null) {
               var10000.onError(e);
            }

         }
      }));
   }

   public final void startTyping(@NotNull String uid) {
      Intrinsics.checkParameterIsNotNull(uid, "uid");
      TypingIndicator typingIndicator = new TypingIndicator(uid, CometChatConstants.RECEIVER_TYPE_USER);
      CometChat.startTyping(typingIndicator);
   }

   public final void markAsRead(@NotNull BaseMessage message) {
      Intrinsics.checkParameterIsNotNull(message, "message");
      if (message.getReadByMeAt() == 0L) {
         User var10000 = message.getSender();
         Intrinsics.checkExpressionValueIsNotNull(var10000, "message.sender");
         String var2 = var10000.getUid();
         User var10001 = CometChat.getLoggedInUser();
         Intrinsics.checkExpressionValueIsNotNull(var10001, "CometChat.getLoggedInUser()");
         if (!StringsKt.equals(var2, var10001.getUid(), true)) {
           // CometChat.markMessageAsRead(message);
         }
      }

   }

   public final void addListner(@NotNull String listener) {
      this.addMessageListener(listener);
   }

   public final void removeListener(@NotNull String listener) {
      CometChat.removeMessageListener(listener);
      CometChat.removeCallListener(listener);
   }

   public CometChatMessaging(@NotNull OnMessageUpdate onMessageUpdate) {
      this.onMessageUpdate = onMessageUpdate;
   }
   public CometChatMessaging(){

   }

 
}
