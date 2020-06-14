package com.sachtech.datingapp.ui.profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.home.adapter.ProfileItemListAdapter;
import com.sachtech.datingapp.ui.profile.listener.EditInformation;
import com.sachtech.datingapp.ui.profile.listener.OnItemSelected;
import com.sachtech.datingapp.utils.ProfileItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;


public final class ProfileItemFragment extends DialogFragment implements OnItemSelected {
   @Nullable
   private TextView textView;
   @Nullable
   private EditInformation onEditInformation;
   @Nullable
   private ArrayList list;
   @Nullable
   private String selectedItem;
   @Nullable
   private ProfileItemListAdapter adapter;
   @Nullable
   private String selectedType;
   @NotNull
   private String[] booleanArray = new String[]{"Yes", "No"};
   @NotNull
   private String item = "";


   @NotNull
   public final String getItem() {
      return this.item;
   }

   public final void setItem(@NotNull String var1) {
      this.item = var1;
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      Bundle bundle = this.getArguments();
      this.selectedItem = bundle.getString("item_name");
      this.selectedType =  bundle.getString("item_type");
      TextView itemName = view.findViewById(id.item_name);
      itemName.setText(selectedItem);
      this.list = new ArrayList();
      RecyclerView rv = view.findViewById(id.profile_item_rv);

      FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this.getContext());
      layoutManager.setFlexDirection(FlexDirection.ROW);
      layoutManager.setJustifyContent(JustifyContent.CENTER);
      rv.setLayoutManager((LayoutManager)layoutManager);
       adapter =new ProfileItemListAdapter(getContext(), list, selectedItem, selectedType,this);

      list=new ArrayList();
     // ArrayList var10004 = this.list;
     if(this.selectedItem==ProfileItem.INSTANCE.getEDUCATION())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.education)));
     }
     else if(selectedItem== ProfileItem.INSTANCE.getPROFESSION())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.profession)));
     }
     else if(selectedItem== ProfileItem.INSTANCE.getEYECOLOR())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.eyeColor)));
     }
     else if(selectedItem== ProfileItem.INSTANCE.getHAIRCOLOR())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.hairColor)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getCOLORCOMPLEXION())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.colorComplexion)));

     } else if(selectedItem==ProfileItem.INSTANCE.getQURAN())
     {
        list.addAll(Arrays.asList(booleanArray));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getPRAYS())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.prays)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getHALAL())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.halal)));
      }
     else if(selectedItem==ProfileItem.INSTANCE.getWEAR())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.wear)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.after_marriage_plans)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.after_marriage_plans)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getMARRIAGEPLANS())
     {
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.marriage_plans)));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getREVERT())
     {
        list.addAll(Arrays.asList(booleanArray));
     }
     else if(selectedItem==ProfileItem.INSTANCE.getBEARD())
     {
        list.addAll(Arrays.asList(booleanArray));
     }
     TextView tv=view.findViewById(id.profile_item_save);
     tv.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            onEditInformation.editinformation(item,textView);
            dismiss();
           }
     });

   }

   public final void infoSelectListener(@NotNull EditInformation onEditInformation, @NotNull TextView textView) {
      this.onEditInformation = onEditInformation;
      this.textView = textView;
   }

   public void selectedItem(@NotNull String item) {
      this.item = item;
   }
}
