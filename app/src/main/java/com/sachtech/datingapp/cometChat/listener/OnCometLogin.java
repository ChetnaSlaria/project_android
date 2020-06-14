package com.sachtech.datingapp.cometChat.listener;

import androidx.annotation.Nullable;


import com.cometchat.pro.models.User;

import org.jetbrains.annotations.NotNull;

public interface OnCometLogin {
   void onCometLoginSuccess(@Nullable User user);
   void onCometLoginFailure(@NotNull String message);
}
