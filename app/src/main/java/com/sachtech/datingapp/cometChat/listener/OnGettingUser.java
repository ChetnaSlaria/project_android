package com.sachtech.datingapp.cometChat.listener;

import androidx.annotation.Nullable;

import com.cometchat.pro.exceptions.CometChatException;

import java.util.List;

public interface OnGettingUser {
   void onGettingUser(@Nullable List var1);

   void onFailure(@Nullable CometChatException var1);
}