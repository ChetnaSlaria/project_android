/*
package com.sachtech.datingapp.presenter;

import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.model.UserModel;
import com.sachtech.datingapp.view.UserView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class UserPresenter {
   @NotNull
   private final UserView userView;

   @NotNull
   public final UserModel getUserModel() {
      return new UserModel();
   }

   public final void getAllUsers() {
      this.getUserModel().getAllUsers(this.userView);
   }

   public final void addToLikeList(@NotNull User user) {
      this.getUserModel().addUserToLikeList(user, this.userView);
   }

   public final void addToVisitList(@NotNull User user) {
      this.getUserModel().addToVisitList(user, this.userView);
   }

   public final void notifyLikedUser(@NotNull User user) {
      this.getUserModel().notifyLikedUser(user);
   }

   public final void addToChatFriendList(@NotNull User user, @NotNull OnFriendAdd onFreindAdd) {
      this.getUserModel().addUserToFriendList(user, onFreindAdd);
   }

   void OnFriendAdd(){

   }
   public final void addToUnlikeList(@NotNull User user) {
      this.getUserModel().addUserToUnlikeList(user, this.userView);
   }

   public final void removeFromUnlikeList(@NotNull User user) {
      Intrinsics.checkParameterIsNotNull(user, "user");
      this.getUserModel().removeUserFromUnliekeList(user);
   }

   public final void removeFromLikeList(@Nullable String docId) {
      this.getUserModel().removeUserFromLikeList(docId);
   }

   @NotNull
   public final UserView getUserView() {
      return this.userView;
   }

   public UserPresenter(@NotNull UserView userView) {
      super();
      this.userView = userView;
   }
}
*/
