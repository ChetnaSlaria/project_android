package com.sachtech.datingapp.ui.profile.listener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.Metadata;


public interface OnPrefChange {
   void selectedPref(@NotNull String var1, @NotNull ArrayList<String> var2);
}
