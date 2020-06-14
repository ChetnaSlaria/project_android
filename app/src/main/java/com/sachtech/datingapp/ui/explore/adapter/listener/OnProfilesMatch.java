package com.sachtech.datingapp.ui.explore.adapter.listener;

import com.sachtech.datingapp.data.User;

import org.jetbrains.annotations.NotNull;

public interface OnProfilesMatch {
   void matchedProfile(@NotNull User var1);

   void OnMessageClick(@NotNull User var1);
}