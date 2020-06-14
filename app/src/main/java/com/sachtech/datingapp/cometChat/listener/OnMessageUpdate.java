package com.sachtech.datingapp.cometChat.listener;

import com.cometchat.pro.models.BaseMessage;

import org.jetbrains.annotations.NotNull;

public interface OnMessageUpdate extends OnErrorListener {
   void onUpdate(@NotNull BaseMessage var1);
}