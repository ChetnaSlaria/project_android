package com.sachtech.datingapp.ui.home.adapter.listener;

import com.sachtech.datingapp.data.User;

import org.jetbrains.annotations.NotNull;

public interface OnCardAction {
   void onCardLike(@NotNull User var1);

   void onCardUnlike(@NotNull User var1);

   void sendMessage(@NotNull User var1, int var2);
}