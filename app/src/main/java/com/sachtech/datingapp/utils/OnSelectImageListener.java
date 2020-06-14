package com.sachtech.datingapp.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public interface OnSelectImageListener {
      void onImageSelected(@NotNull Uri var1, @Nullable ImageView var2);
   }