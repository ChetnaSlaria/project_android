package com.sachtech.datingapp.cometChat;

import android.util.Log;

import androidx.annotation.Nullable;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.UsersRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.cometChat.listener.OnGettingUser;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.ui.chat.activity.listener.OnGettingUserDetails;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Ref;

public final class CometChatUser {
   @Nullable
   public final User getLoggedInUser() {
      return CometChat.getLoggedInUser();
   }

   public final void getUserList(@NotNull final OnGettingUser onGettingUser) {
    //  Intrinsics.checkParameterIsNotNull(onGettingUser, "onGettingUser");
      UsersRequest usersRequest = (UsersRequest)null;
      int limit = 30;
      usersRequest = (new UsersRequest.UsersRequestBuilder()).setLimit(limit).build();
      if (usersRequest != null) {
         usersRequest.fetchNext((CometChat.CallbackListener)(new CometChat.CallbackListener() {
            public void onSuccess(@Nullable List p0) {
               onGettingUser.onGettingUser(p0);
            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
               this.onSuccess((List)var1);
            }

            public void onError(@Nullable CometChatException p0) {
               onGettingUser.onFailure(p0);
            }
         }));
      }

   }

   @NotNull
   public final ArrayList getOnlineUsers() {
      final Ref.ObjectRef list = new Ref.ObjectRef();
      list.element = new ArrayList();
      UsersRequest usersRequest = (new UsersRequest.UsersRequestBuilder()).setUserStatus("available").build();
      if (usersRequest != null) {
         usersRequest.fetchNext((CometChat.CallbackListener)(new CometChat.CallbackListener() {
            @Contract("null -> fail")
            public void onSuccess(@Nullable List p0) {
               Ref.ObjectRef var10000 = list;
               if (p0 == null) {
                  throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.cometchat.pro.models.User> /* = java.util.ArrayList<com.cometchat.pro.models.User> */");
               } else {
                  var10000.element = (ArrayList)p0;
               }
            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
               this.onSuccess((List)var1);
            }

            public void onError(@Nullable CometChatException p0) {
               String var10000 = p0 != null ? p0.getLocalizedMessage() : null;


               CommonsKt.showToast(var10000);
            }
         }));
      }

      return (ArrayList)list.element;
   }

   @NotNull
   public final User getUserDetails(@NotNull String uid, @NotNull final OnGettingUserDetails body) {

      final Ref.ObjectRef user = new Ref.ObjectRef();
      user.element = new User();
      CometChat.getUser(uid, (CometChat.CallbackListener)(new CometChat.CallbackListener() {
         public void onSuccess(@Nullable User p0) {
            Ref.ObjectRef var10000 = user;
                       body.userDetails(p0);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((User)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            Log.e("exception", p0 != null ? p0.getCode() : null);
         }
      }));
      return (User)user.element;
   }

   public final void getUserEvents(@NotNull String listenerId) {
     // Intrinsics.checkParameterIsNotNull(listenerId, "listenerId");
      CometChat.addUserListener(listenerId, (CometChat.UserListener)(new CometChat.UserListener() {
      }));
   }

   public final void addUserListener() {
      CometChat.addUserListener("user", (CometChat.UserListener)(new CometChat.UserListener() {
         public void onUserOnline(@Nullable User user) {
            StringBuilder var10001 = new StringBuilder();
          /*  if (user == null) {
               Intrinsics.throwNpe();
            }*/

            Log.d("user", var10001.append(user.getName()).append("is online.").toString());
         }

         public void onUserOffline(@Nullable User user) {
            StringBuilder var10001 = new StringBuilder();
          /*  if (user == null) {
               Intrinsics.throwNpe();
            }
*/
            Log.d("user", var10001.append(user.getName()).append("is offline.").toString());
         }
      }));
   }
}
