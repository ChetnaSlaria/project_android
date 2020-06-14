package com.sachtech.datingapp.ui.home.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.networking.FirebaseHelper;
import gurtek.mrgurtekbase.KotlinBaseDialogFragment;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ConfirmDialog extends DialogFragment {

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   TextView cancelLikes;

   public boolean adjustdisplay() {
      return true;
   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.dialog_confrim, container, false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      cancelLikes = view.findViewById(id.cancelLike);
      Bundle var10000 = this.getArguments();
      if (var10000 != null) {
         var10000.getString("uid");
      } else {
         var10000 = null;
      }

      Dialog var4 = this.getDialog();
      if (var4 != null) {
         Window var5 = var4.getWindow();
         if (var5 != null) {
            var5.setBackgroundDrawable((Drawable) (new ColorDrawable(0)));
         }
      }
      cancelLikes.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            dismissAllowingStateLoss();
         }
      });
    /*  ((TextView)this._$_findCachedViewById(id.cancelLike)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ConfirmDialog.this.dismissAllowingStateLoss();
         }
      }));
   }
*/

  /* public ConfirmDialog() {
      super(1300113);
      this.firebaseHelper$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   public View _$_findCachedViewById(int var1) {
      if (this._$_findViewCache == null) {
         this._$_findViewCache = new HashMap();
      }

      View var2 = (View)this._$_findViewCache.get(var1);
      if (var2 == null) {
         View var10000 = this.getView();
         if (var10000 == null) {
            return null;
         }

         var2 = var10000.findViewById(var1);
         this._$_findViewCache.put(var1, var2);
      }

      return var2;
   }

   public void _$_clearFindViewByIdCache() {
      if (this._$_findViewCache != null) {
         this._$_findViewCache.clear();
      }

   }

   // $FF: synthetic method
   public void onDestroyView() {
      super.onDestroyView();
      this._$_clearFindViewByIdCache();
   }
*/
   }
}
