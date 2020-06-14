package com.sachtech.datingapp.ui.home.adapter.listener;

import com.android.billingclient.api.SkuDetails;

import org.jetbrains.annotations.NotNull;

public interface OnSubscriptionSelected {
      void selectedSubscription(@NotNull SkuDetails var1);
   }