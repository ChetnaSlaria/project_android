package com.sachtech.datingapp.model;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

@Keep
public final class LikeUsers {
   @Nullable
   private String liked_by_user_id;
   @Nullable
   private String liked_to_user_id;
   @NotNull
   private String like_status;

   @Nullable
   public final String getLiked_by_user_id() {
      return this.liked_by_user_id;
   }

   public final void setLiked_by_user_id(@Nullable String var1) {
      this.liked_by_user_id = var1;
   }

   @Nullable
   public final String getLiked_to_user_id() {
      return this.liked_to_user_id;
   }

   public final void setLiked_to_user_id(@Nullable String var1) {
      this.liked_to_user_id = var1;
   }

   @NotNull
   public final String getLike_status() {
      return this.like_status;
   }

   public final void setLike_status(@NotNull String var1) {
      this.like_status = var1;
   }

   public LikeUsers(@Nullable String liked_by_user_id, @Nullable String liked_to_user_id, @NotNull String like_status) {
      super();
      this.liked_by_user_id = liked_by_user_id;
      this.liked_to_user_id = liked_to_user_id;
      this.like_status = like_status;
   }




   @NotNull
   public String toString() {
      return "LikeUsers(liked_by_user_id=" + this.liked_by_user_id + ", liked_to_user_id=" + this.liked_to_user_id + ", like_status=" + this.like_status + ")";
   }
}