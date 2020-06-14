package com.sachtech.datingapp.ui.explore.adapter.listener;

import org.jetbrains.annotations.NotNull;

public interface CallTypeListener {
   void onAudioCallSelected(@NotNull String var1);

   void onVideoCallSelected(@NotNull String var1);
}