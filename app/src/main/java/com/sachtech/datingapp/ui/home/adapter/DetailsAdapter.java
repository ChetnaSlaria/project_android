package com.sachtech.datingapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import gurtek.mrgurtekbase.adapter.BaseAdapter;
import gurtek.mrgurtekbase.adapter.BaseAdapter.IViewHolder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
   @NotNull
   private final ArrayList details;

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about, parent, false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      View itemView = holder.itemView;
      TextView var3 = (TextView)itemView.findViewById(id.about_name);
      var3.setText((CharSequence)this.details.get(position));
   }

   public int getItemCount() {
      return this.details.size();
   }

   @NotNull
   public final ArrayList getDetails() {
      return this.details;
   }

   public DetailsAdapter(@NotNull ArrayList details) {
      super();
      this.details = details;
   }

   public static class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
