// ProfileItemListAdapter.java
package com.sachtech.datingapp.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.profile.listener.OnItemSelected;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.ProfileItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;


public final class ProfileItemListAdapter extends RecyclerView.Adapter<ProfileItemListAdapter.ViewHolder> {
   private int selected_position;
   @Nullable
   private ArrayList itemList;
   @NotNull
   private final Context context;
   @NotNull
   private final ArrayList stringlist;
   @Nullable
   private final String item;
   @Nullable
   private final String selectedType;
   @NotNull
   private final OnItemSelected onItemSelected;

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_items,parent,false
      ));
   }

   public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {
      this.itemList = new ArrayList();
      View view = holder.itemView;
      TextView item_profile = (TextView)view.findViewById(id.item_profile);
      item_profile.setText((CharSequence)this.stringlist.get(position));
      switch (this.selectedType)
      {
         case "single":{

         }
      }
      if (Intrinsics.areEqual(this.selectedType, "single")) {
         if (holder.getAdapterPosition() == this.selected_position) {
            ((TextView)view.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_background);
            ((TextView)view.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorWhite));
         } else {
            ((TextView)view.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_unselected);
            ((TextView)view.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorGrey));
         }

         holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
               String var2 = ProfileItemListAdapter.this.getItem();
               if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getORIGIN())) {
                  Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getORIGIN(), ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getCOLORCOMPLEXION())) {
                  Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getCOLORCOMPLEXION(), ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHAIRCOLOR())) {
                  Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getHAIRCOLOR(), ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEYECOLOR())) {
                  Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getEYECOLOR(), ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPROFESSION())) {
                  Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getPROFESSION(), ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEDUCATION())) {
                  Preferences.INSTANCE.setPref("education", ProfileItemListAdapter.this.getStringlist().get(position));
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getNATIONALITY())) {
                  Preferences.INSTANCE.setPref("nationality", ProfileItemListAdapter.this.getStringlist().get(position));
               }

               OnItemSelected var10000 = ProfileItemListAdapter.this.getOnItemSelected();
               Object var10001 = ProfileItemListAdapter.this.getStringlist().get(position);
               var10000.selectedItem((String)var10001);
               ProfileItemListAdapter.this.selected_position = holder.getAdapterPosition();
               ProfileItemListAdapter.this.notifyDataSetChanged();
            }
         }));
      } else if (Intrinsics.areEqual(this.selectedType, "multiple")) {
         view = holder.itemView;
         ((TextView)view.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_unselected);
         view = holder.itemView;
         ((TextView)view.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorGrey));
         holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
               View var10000 = holder.itemView;
               Intrinsics.checkExpressionValueIsNotNull(var10000, "holder.itemView");
               ((TextView)var10000.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_background);
               var10000 = holder.itemView;
               Intrinsics.checkExpressionValueIsNotNull(var10000, "holder.itemView");
               ((TextView)var10000.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(ProfileItemListAdapter.this.getContext(), R.color.colorWhite));
               ArrayList var3 = itemList;
               View var10001 = holder.itemView;
               TextView var5 = (TextView)var10001.findViewById(id.item_profile);
               var3.add(var5.getText().toString());
               String var2 = ProfileItemListAdapter.this.getItem();
               String var4;
               ArrayList var6;
               if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getCOLORCOMPLEXION())) {
                  var4 = PrefKeys.INSTANCE.getPREF_COLORCOMPLEXION();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHAIRCOLOR())) {
                  var4 = PrefKeys.INSTANCE.getPREF_HAIRCOLOR();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEYECOLOR())) {
                  var4 = PrefKeys.INSTANCE.getPREF_EYECOLOR();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPROFESSION())) {
                  var4 = PrefKeys.INSTANCE.getPREF_PROFESSION();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEDUCATION())) {
                  var4 = PrefKeys.INSTANCE.getPREF_EDUCATION();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getQURAN())) {
                  var4 = PrefKeys.INSTANCE.getPREF_QURAN();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPRAYS())) {
                  var4 = PrefKeys.INSTANCE.getPREF_PRAYS();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHALAL())) {
                  var4 = PrefKeys.INSTANCE.getPREF_HALAL();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getWEAR())) {
                  var4 = PrefKeys.INSTANCE.getPREF_WEAR();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE())) {
                  var4 = PrefKeys.INSTANCE.getPREF_LIFE_AFTER_MARRIAGE();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getMARRIAGEPLANS())) {
                  var4 = PrefKeys.INSTANCE.getPREF_MARRIAGE_PLANS();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getREVERT())) {
                  var4 = PrefKeys.INSTANCE.getPREF_REVERT();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getBEARD())) {
                  var4 = PrefKeys.INSTANCE.getPREF_BEARD();
                  var6 = ProfileItemListAdapter.this.itemList;
                  Preferences.INSTANCE.setPref(var4, var6 != null ? joinToString(var6) : null);
               }

               OnItemSelected var7 = ProfileItemListAdapter.this.getOnItemSelected();
               var6 = ProfileItemListAdapter.this.itemList;
               String var8 = var6 != null ? joinToString(var6) : null;
               if (var8 == null) {
                  Intrinsics.throwNpe();
               }

               var7.selectedItem(var8);
            }
         }));
      }

   }

   public String joinToString(ArrayList arrayList) {
      StringBuilder builder = new StringBuilder("");
      for (int i = 0; i < arrayList.size(); i++) {
         builder.append(arrayList).append(",");
      }
      String strList = builder.toString();
      if (strList.length() > 0)
         strList = strList.substring(0, strList.length() - 1);
      return strList;
   }

   public int getItemCount() {
      return this.stringlist.size();
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   @NotNull
   public final ArrayList getStringlist() {
      return this.stringlist;
   }

   @Nullable
   public final String getItem() {
      return this.item;
   }

   @Nullable
   public final String getSelectedType() {
      return this.selectedType;
   }

   @NotNull
   public final OnItemSelected getOnItemSelected() {
      return this.onItemSelected;
   }

   public ProfileItemListAdapter(@NotNull Context context, @NotNull ArrayList stringlist, @Nullable String item, @Nullable String selectedType, @NotNull OnItemSelected onItemSelected) {
      super();
      this.context = context;
      this.stringlist = stringlist;
      this.item = item;
      this.selectedType = selectedType;
      this.onItemSelected = onItemSelected;
      this.selected_position = -1;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}


