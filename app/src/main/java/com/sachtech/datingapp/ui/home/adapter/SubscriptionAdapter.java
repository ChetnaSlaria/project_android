package com.sachtech.datingapp.ui.home.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.android.billingclient.api.SkuDetails;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.home.fragment.OnSubscriptionClicked;

import gurtek.mrgurtekbase.ExtenstionsKt;
import gurtek.mrgurtekbase.adapter.BaseAdapter;
import gurtek.mrgurtekbase.adapter.BaseAdapter.IViewHolder;
import java.util.ArrayList;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {
   @NotNull
   private final ArrayList<SkuDetails> updatedList;
   private final OnSubscriptionClicked onProductClicked;

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription,parent,false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
      View view = holder.itemView;
      TextView var3 = (TextView)view.findViewById(id.text_price);
      Object var10001 = this.updatedList.get(position);
      var3.setText((CharSequence)((SkuDetails)var10001).getPrice());
      var3 = (TextView)view.findViewById(id.text_get);
      var10001 = this.updatedList.get(position);
      var3.setText((CharSequence)((SkuDetails)var10001).getDescription());
      var3 = (TextView)view.findViewById(id.text_off);
      var10001 = this.updatedList.get(position);
      var3.setText((CharSequence)((SkuDetails)var10001).getTitle());
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            onProductClicked.getSkuDetails(updatedList.get(position));
         }
      }));
      if (position == 3) {
         view = holder.itemView;
         Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
         var3 = (TextView)view.findViewById(id.popular);
         Intrinsics.checkExpressionValueIsNotNull(var3, "holder.itemView.popular");
         ExtenstionsKt.visible((View)var3);
      }

   }

   public int getItemCount() {
      return this.updatedList.size();
   }

   @NotNull
   public final ArrayList getUpdatedList() {
      return this.updatedList;
   }

   public SubscriptionAdapter(@NotNull ArrayList<SkuDetails> updatedList, OnSubscriptionClicked onProductClicked) {
      super();
      this.updatedList = updatedList;
      this.onProductClicked = onProductClicked;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
