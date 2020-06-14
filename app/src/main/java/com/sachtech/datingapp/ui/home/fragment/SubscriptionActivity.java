package com.sachtech.datingapp.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.networking.GooglePlayBilling;
import com.sachtech.datingapp.networking.GooglePlayBilling.OnLoadingSubscriptionProducts;
import com.sachtech.datingapp.ui.home.adapter.SubscriptionAdapter;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.Function;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SubscriptionActivity extends AppCompatActivity implements OnLoadingSubscriptionProducts, PurchasesUpdatedListener,OnSubscriptionClicked {

   @Nullable
   private User user;
   @NotNull
   public ArrayList updatedList;
   @NotNull
   public SubscriptionAdapter subscriptionAdapter;

   @NotNull
   public final GooglePlayBilling getGooglePlayBilling() {
    return new GooglePlayBilling(this,this::onLoadingSku,this::onPurchasesUpdated);
   }

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   @Nullable
   public final User getUser() {
      return this.user;
   }

   public final void setUser(@Nullable User var1) {
      this.user = var1;
   }

   @NotNull
   public final ArrayList getUpdatedList() {
      ArrayList var10000 = this.updatedList;
      return var10000;
   }

   public final void setUpdatedList(@NotNull ArrayList var1) {
      this.updatedList = var1;
   }

   @NotNull
   public final SubscriptionAdapter getSubscriptionAdapter() {
      SubscriptionAdapter var10000 = this.subscriptionAdapter;
      return var10000;
   }

   public final void setSubscriptionAdapter(@NotNull SubscriptionAdapter var1) {
      this.subscriptionAdapter = var1;
   }

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.fragment_subscription);
      this.getGooglePlayBilling().initGooglePlay();
      String userString=Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      user=new Gson().fromJson(userString,User.class);
      ((ImageView)findViewById(id.subscription_back)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            SubscriptionActivity.this.onBackPressed();
         }
      }));
   }

   public void onLoadingSku(@NotNull List skuDetailsList) {
      subscriptionAdapter=new SubscriptionAdapter((ArrayList) skuDetailsList,this::getSkuDetails);
      RecyclerView rv=findViewById(id.subscription_rv);
      rv.setAdapter(subscriptionAdapter);
   }

   public void onPurchasesUpdated(int responseCode, @Nullable List purchases) {
      if (responseCode == 0) {
         User var10000 = this.user;
         if (var10000 != null) {
            var10000.setSubscribed(true);
         }

         if (purchases != null) {
            Iterable $this$forEach$iv = (Iterable)purchases;
            Iterator var5 = $this$forEach$iv.iterator();

            while(var5.hasNext()) {
               Object element$iv = var5.next();
               Purchase it = (Purchase)element$iv;
               var10000 = this.user;
               if (var10000 != null) {
                  var10000.setSubscriptionFrom(it.getPurchaseTime());
               }

               var10000 = this.user;
               if (var10000 != null) {
                  String var10001 = it.getSku();
                  Intrinsics.checkExpressionValueIsNotNull(var10001, "it.sku");
                  var10000.setSubscriptionType(var10001);
               }
            }
         }

         User var9 = this.user;
         if (var9 == null) {
            Intrinsics.throwNpe();
         }

         this.updateUser(var9);
      } else if (responseCode == 7) {
      }

   }

   private final void updateUser(User user) {
      this.getFirebaseHelper().updateUserDetails(user);
   }


   @Override
   public void getSkuDetails(SkuDetails skuDetails) {
        if (user.isSubscribed()) {
        CommonsKt.showToast("Already Subscribed.");
        } else {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build();
        GooglePlayBilling var4 = SubscriptionActivity.this.getGooglePlayBilling();
        var4.launchBillingFlow(this, billingFlowParams);
        }

   }
}
