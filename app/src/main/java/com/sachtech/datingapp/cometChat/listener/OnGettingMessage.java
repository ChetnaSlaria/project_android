package com.sachtech.datingapp.cometChat.listener;

import com.cometchat.pro.models.BaseMessage;

import org.jetbrains.annotations.NotNull;

public interface OnGettingMessage {
   void onGettingMessage(@NotNull BaseMessage var1);
}