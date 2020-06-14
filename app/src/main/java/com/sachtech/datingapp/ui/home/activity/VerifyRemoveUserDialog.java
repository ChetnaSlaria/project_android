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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.extensions.CommonsKt;
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


public final class VerifyRemoveUserDialog extends DialogFragment {

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {

      return new FirebaseHelper();
   }

   public boolean adjustdisplay() {
      return true;
   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.dialog_verify_remove_user,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
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
            var5.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }

      ((TextView)view.findViewById(id.cancelLike)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            VerifyRemoveUserDialog.this.dismissAllowingStateLoss();
         }
      }));
      ((TextView)view.findViewById(id.confirm_unlike)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            FirebaseHelper var10000 = VerifyRemoveUserDialog.this.getFirebaseHelper();
            Bundle var10001 = VerifyRemoveUserDialog.this.getArguments();
            String var2 = var10001 != null ? var10001.getString("documentId") : null;
            if (var2 == null) {
               Intrinsics.throwNpe();
            }

            var10000.deleteLikedUser(var2).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
               // $FF: synthetic method
               // $FF: bridge method
               public void onSuccess(Object var1) {
                  this.onSuccess((Void)var1);
               }

               public final void onSuccess(Void it) {
                  CommonsKt.showToast("User unliked");
                  VerifyRemoveUserDialog.this.dismissAllowingStateLoss();
               }
            })).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                  Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
               }
            });
         }
      }));
   }

   private final void unlikeUser(String uid) {
   }

}
