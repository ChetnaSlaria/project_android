// PrefAdapter.java
package com.sachtech.datingapp.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.profile.listener.OnPrefChange;
import com.sachtech.datingapp.utils.PrefType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;


public final class PrefAdapter extends RecyclerView.Adapter<PrefAdapter.ViewHolder> {
   private ArrayList itemList;
   @NotNull
   private ArrayList<Integer> maritalStatusIconList;
   @NotNull
   private ArrayList<Integer> buildIconList;
   @NotNull
   private ArrayList<Integer> currentLivingIconList;
   @NotNull
   private ArrayList<Integer> sectIconList;
   @NotNull
   private final ArrayList<String> arraylist;
   @NotNull
   private final Context context;
   @NotNull
   private final String pref_type;
   private ArrayList selectedItems;
   @NotNull
   private final com.sachtech.datingapp.ui.profile.listener.OnPrefChange onPrefChange;

   @Nullable
   public final ArrayList<String> getItemList() {
      return this.itemList;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pref,parent,false));
   }

   public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {
      this.itemList = new ArrayList();
      selectedItems=new ArrayList();
      View itemView = holder.itemView;
      TextView itemName = (TextView)itemView.findViewById(id.pref_name);
      itemName.setText((CharSequence)this.arraylist.get(position));
       RelativeLayout view= ((RelativeLayout)itemView.findViewById(id.view));
      String var3 = this.pref_type;
      Object var10001;
      if (Intrinsics.areEqual(var3, PrefType.INSTANCE.getMARITALSTATUS())) {
         var10001 = this.maritalStatusIconList.get(position);
         itemName.setCompoundDrawablesWithIntrinsicBounds(((Number)var10001).intValue(), 0, 0, 0);
      } else if (Intrinsics.areEqual(var3, PrefType.INSTANCE.getCURRENTLIVINGARRANGEMENT())) {
         var10001 = this.currentLivingIconList.get(position);
         itemName.setCompoundDrawablesWithIntrinsicBounds(((Number)var10001).intValue(), 0, 0, 0);
      } else if (Intrinsics.areEqual(var3, PrefType.INSTANCE.getSECT())) {
         var10001 = this.sectIconList.get(position);
         Intrinsics.checkExpressionValueIsNotNull(var10001, "sectIconList[position]");
         itemName.setCompoundDrawablesWithIntrinsicBounds(((Number)var10001).intValue(), 0, 0, 0);
      } else if (Intrinsics.areEqual(var3, PrefType.INSTANCE.getBUILD())) {
         var10001 = this.buildIconList.get(position);
         Intrinsics.checkExpressionValueIsNotNull(var10001, "buildIconList[position]");
         itemName.setCompoundDrawablesWithIntrinsicBounds(((Number)var10001).intValue(), 0, 0, 0);
      }

      if (selectedItems.contains(arraylist.get(position))) {
        view.setBackgroundResource(R.drawable.selected);
         itemName.setTextColor(ContextCompat.getColor(this.context, R.color.colorWhite));
         ArrayList arraylist = this.itemList;
         if (arraylist != null) {
            arraylist.addAll((Collection)this.selectedItems);
         }
      } else {
        view.setBackgroundResource(R.drawable.unselected);
       itemName.setTextColor(ContextCompat.getColor(this.context, R.color.colorGrey));
      }

      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ArrayList var10000 = PrefAdapter.this.getItemList();
            Boolean var2 = var10000 != null ? var10000.contains(PrefAdapter.this.getArraylist().get(position)) : null;


            if (var2) {
             view.setBackgroundResource(R.drawable.unselected);
              itemName.setTextColor(ContextCompat.getColor(PrefAdapter.this.getContext(), R.color.colorGrey));
               var10000 = PrefAdapter.this.getItemList();
               if (var10000 != null) {
                  var10000.remove(itemName.getText().toString());
               }
            } else {
             itemName.setTextColor(ContextCompat.getColor(PrefAdapter.this.getContext(), R.color.colorWhite));
               var10000 = PrefAdapter.this.getItemList();
               if (var10000 != null) {
                  var10000.add(PrefAdapter.this.getArraylist().get(position));
               }

               com.sachtech.datingapp.ui.profile.listener.OnPrefChange var6 = PrefAdapter.this.getOnPrefChange();
               String var5 = PrefAdapter.this.getPref_type();
               ArrayList var10002 = PrefAdapter.this.getItemList();
               var6.selectedPref(var5, var10002);
            }

         }
      }));
   }



   public int getItemCount() {
      return this.arraylist.size();
   }

   @NotNull
   public final ArrayList getArraylist() {
      return this.arraylist;
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   @NotNull
   public final String getPref_type() {
      return this.pref_type;
   }

   @NotNull
   public final ArrayList getSelectedItems() {
      return this.selectedItems;
   }

   @NotNull
   public final com.sachtech.datingapp.ui.profile.listener.OnPrefChange getOnPrefChange() {
      return this.onPrefChange;
   }

   public PrefAdapter(@NotNull ArrayList arraylist, @NotNull Context context, @NotNull String pref_type, @NotNull ArrayList selectedItems, @NotNull OnPrefChange onPrefChange) {
      super();
      this.arraylist = arraylist;
      this.context = context;
      this.pref_type = pref_type;
      this.selectedItems = selectedItems;
      this.onPrefChange = onPrefChange;
      this.maritalStatusIconList = CollectionsKt.arrayListOf(new Integer[]{R.drawable.single,
              R.drawable.divorced,R.drawable.windowed,
              R.drawable.annulled});
      this.buildIconList = CollectionsKt.arrayListOf(new Integer[]{R.drawable.athletic,
              R.drawable.large,R.drawable.medium,
              R.drawable.slim});
      this.currentLivingIconList = CollectionsKt.arrayListOf(new Integer[]{ R.drawable.living_alone,
              R.drawable.living_with_family,R.drawable.living_with_friend,
              R.drawable.other});
      this.sectIconList = CollectionsKt.arrayListOf(new Integer[]{ R.drawable.sunni,
              R.drawable.shia,R.drawable.other2});
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}


