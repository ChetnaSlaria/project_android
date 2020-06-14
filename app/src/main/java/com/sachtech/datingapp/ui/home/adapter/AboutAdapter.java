// AboutAdapter.java
package com.sachtech.datingapp.ui.home.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.ui.home.adapter.listener.OnCardAction;
import com.sachtech.datingapp.ui.home.fragment.AboutFragment;
import com.sachtech.datingapp.ui.home.fragment.HomeFragment;
import com.sachtech.datingapp.utils.Constants;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;


public final class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyStreamingAdapter> {
   private ArrayList arraylist;
   private final HomeFragment context;
   @NotNull
   private final OnCardAction onCardAction;
   @NotNull
   private final Activity baselistener;


   @NotNull
   public AboutAdapter.MyStreamingAdapter onCreateViewHolder(@NotNull ViewGroup p0, int p1) {
      View view = LayoutInflater.from(p0.getContext()).inflate(R.layout.item_streaming, p0, false);
      return new AboutAdapter.MyStreamingAdapter(view);
   }

   public int getItemCount() {
      return this.arraylist.size();
   }

   public final void setNewList(@NotNull ArrayList list) {
      this.arraylist.clear();
      this.arraylist.addAll((Collection)list);
      this.notifyDataSetChanged();
   }

   public void onBindViewHolder(@NotNull final AboutAdapter.MyStreamingAdapter holder, final int position) {
      final IntRef pos = new IntRef();
      pos.element = holder.getAdapterPosition();
      View var10000;
      View var10001;
      ImageView var4;
      TextView var5;
      RequestCreator var6;
      if (((User)this.arraylist.get(position)).getPrivate()) {
         var10000 = holder.itemView;
         var4 = (ImageView)var10000.findViewById(id.lock);
         var4.setVisibility(View.VISIBLE);
         var10000 = holder.itemView;
         var5 = (TextView)var10000.findViewById(id.text_private);
         var5.setVisibility(View.VISIBLE);
         var6 = Picasso.get().load(((User)this.arraylist.get(pos.element)).getProfilePic());
         View var10003 = holder.itemView;
         LinearLayout var7 = (LinearLayout)var10003.findViewById(id.image_view_lay);
         var6 = var6.transform((Transformation)(new BlurTransformation(var7.getContext(), 25, 1)));
         var10001 = holder.itemView;
         var6.into((ImageView)var10001.findViewById(id.holder_image));
      } else {
         var10000 = holder.itemView;
         var4 = (ImageView)var10000.findViewById(id.lock);
         var4.setVisibility(View.GONE);
         var10000 = holder.itemView;
         var5 = (TextView)var10000.findViewById(id.text_private);
         var5.setVisibility(View.GONE);
         var6 = Picasso.get().load(((User)this.arraylist.get(pos.element)).getProfilePic());
         var10001 = holder.itemView;
         var6.into((ImageView)var10001.findViewById(id.holder_image));
      }

      var5 = (TextView)var10000.findViewById(id.address);
      var5.setText((CharSequence)((User)this.arraylist.get(position)).getProfileHighlight());
      var5 = (TextView)var10000.findViewById(id.user_name);
      var5.setText((CharSequence)(((User)this.arraylist.get(position)).getName() + " , " + ((User)this.arraylist.get(position)).getAge()));
      if (Intrinsics.areEqual(((User)this.arraylist.get(position)).getGender(), "Male")) {
         ((ImageView)var10000.findViewById(id.symbol)).setBackgroundResource(R.drawable.male);
      } else {
         ((ImageView)var10000.findViewById(id.symbol)).setBackgroundResource(R.drawable.female_color);
      }

      var10000 = holder.itemView;
      Intrinsics.checkExpressionValueIsNotNull(var10000, "holder.itemView");
      ((ImageView)var10000.findViewById(id.like)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            pos.element = position;
            Constants.INSTANCE.setType(Constants.INSTANCE.getMESSAGEWITHOUTLIKE());
            OnCardAction var10000 = AboutAdapter.this.getOnCardAction();
            Object var10001 = AboutAdapter.this.arraylist.get(holder.getAdapterPosition());
            Intrinsics.checkExpressionValueIsNotNull(var10001, "arraylist[holder.adapterPosition]");
            var10000.onCardLike((User)var10001);
         }
      }));
      var10000 = holder.itemView;
      Intrinsics.checkExpressionValueIsNotNull(var10000, "holder.itemView");
      ((ImageView)var10000.findViewById(id.message)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            String userString=Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
            User var10000 =new Gson().fromJson(userString,User.class);
          //  if (Intrinsics.areEqual(var10000 != null ? var10000.getAccountStatus() : null, "verified")) {
               pos.element = position;
               Preferences var2 = Preferences.INSTANCE;
               String var6 = PrefKeys.INSTANCE.getSELECTEDCHATUSER();
               Intrinsics.checkExpressionValueIsNotNull(var6, "PrefKeys.SELECTEDCHATUSER");
               String key$iv = var6;
               Object obj$iv = AboutAdapter.this.arraylist.get(holder.getAdapterPosition());
               if (CommonsKt.isNull(obj$iv)) {
                  var2.clearPrefKey(key$iv);
               } else {
                  var2.setPref(key$iv, (new Gson()).toJson(obj$iv));
               }

               Constants.INSTANCE.setType(Constants.INSTANCE.getMESSAGEWITHLIKE());
               OnCardAction var7 = AboutAdapter.this.getOnCardAction();
               Object var10001 = AboutAdapter.this.arraylist.get(holder.getAdapterPosition());
               var7.sendMessage((User)var10001, holder.getAdapterPosition());
           /* } else {
               CommonsKt.showToast("Your account is Not verified yet");
            }*/

         }
      }));
      var10000 = holder.itemView;
      ((ImageView)var10000.findViewById(id.dislike)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            OnCardAction var10000 = AboutAdapter.this.getOnCardAction();
            Object var10001 = AboutAdapter.this.arraylist.get(holder.getAdapterPosition());
            var10000.onCardUnlike((User)var10001);
         }
      }));
      var10000 = holder.itemView;
      ((TextView)var10000.findViewById(id.expand)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Bundle bundle = new Bundle();
            bundle.putString("uid", ((User)AboutAdapter.this.arraylist.get(position)).getUid());
            AboutFragment aboutFragment = new AboutFragment();
            aboutFragment.setArguments(bundle);
            FragmentManager var10001 = AboutAdapter.this.context.getFragmentManager();
            aboutFragment.show(var10001, aboutFragment.getTag());
         }
      }));
   }


   public int getItemViewType(int position) {
      return position;
   }

   @NotNull
   public final OnCardAction getOnCardAction() {
      return this.onCardAction;
   }

   @NotNull
   public final Activity getBaselistener() {
      return this.baselistener;
   }

   public AboutAdapter(@NotNull HomeFragment context, @NotNull OnCardAction onCardAction, @NotNull Activity baselistener) {
      super();
      this.context = context;
      this.onCardAction = onCardAction;
      this.baselistener = baselistener;
      ArrayList var6 = new ArrayList();
      this.arraylist = var6;
   }

   public final class MyStreamingAdapter extends ViewHolder {
      public MyStreamingAdapter(@NotNull View view) {
         super(view);
      }
   }
}
