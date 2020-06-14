package com.sachtech.datingapp.data;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Keep
public final class RemoveFriendList {
   @SerializedName("friends")
   @NotNull
   private final List accepted;


   @NotNull
   public final List getAccepted() {
      return this.accepted;
   }

   public RemoveFriendList(@NotNull List accepted) {
      super();
      this.accepted = accepted;
   }




}
