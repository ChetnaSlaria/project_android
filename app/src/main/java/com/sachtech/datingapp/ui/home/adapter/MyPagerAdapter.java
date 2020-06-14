package com.sachtech.datingapp.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.sachtech.datingapp.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import kotlin.TypeCastException;


public final class MyPagerAdapter extends PagerAdapter {
   @Nullable
   private final Activity context;
   @NotNull
   private final ArrayList arrayList;
   @NotNull
   private LayoutInflater mLayoutInflater;

   @Nullable
   public final Activity getContext() {
      return this.context;
   }


   @NotNull
   public final LayoutInflater getMLayoutInflater$app() {
      return this.mLayoutInflater;
   }



   public boolean isViewFromObject(@NotNull View p0, @NotNull Object p1) {
      return p0 == (RelativeLayout)p1;
   }

   public int getCount() {
      return arrayList.size();
   }

   public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
      container.removeView((View)((RelativeLayout)object));
   }

   @NotNull
   public Object instantiateItem(@NotNull ViewGroup container, int position) {
      View itemView = this.mLayoutInflater.inflate(R.layout.pager_item, container, false);
      ImageView var10000 = itemView.findViewById(R.id.imageView);
         Picasso.get().load((String)this.arrayList.get(position)).into(var10000);
         container.addView(itemView);
         return itemView;
   }

   public MyPagerAdapter(@Nullable Activity activity, @NotNull ArrayList arraylist) {
      super();
      this.context = activity;
      this.arrayList = arraylist;
      Object var10001 = activity != null ? activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) : null;
      if (var10001 == null) {
         throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
      } else {
         this.mLayoutInflater = (LayoutInflater)var10001;
      }
   }
}
