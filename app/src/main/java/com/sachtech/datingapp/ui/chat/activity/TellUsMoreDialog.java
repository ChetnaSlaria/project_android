package com.sachtech.datingapp.ui.chat.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.core.Repo;
import com.google.firebase.firestore.DocumentReference;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.Report;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;

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


public final class TellUsMoreDialog extends DialogFragment {
   private final FirebaseHelper firebaseHelper = new FirebaseHelper();
   @NotNull
   public User reportedUser;


   @NotNull
   public final User getReportedUser() {
      User var10000 = this.reportedUser;
      return var10000;
   }

   public final void setReportedUser(@NotNull User var1) {
      this.reportedUser = var1;
   }

   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.dialog_tell_us_more, container, false);
   }
com.sachtech.datingapp.data.User userData;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      String user= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      userData=new Gson().fromJson(user, com.sachtech.datingapp.data.User.class);
      Dialog var10000 = this.getDialog();
      if (var10000 != null) {
         Window var3 = var10000.getWindow();
         if (var3 != null) {
            var3.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }

      var10000 = this.getDialog();
      if (var10000 != null) {
         var10000.setCanceledOnTouchOutside(false);
      }

      (view.findViewById(id.send)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {

            String var7 = userData.getUid();

            String var8 = userData.getName();
            String var10004 = TellUsMoreDialog.this.getReportedUser().getUid();
            EditText var10005 = (EditText)view.findViewById(id.message);
            String var6 = var10005.getText().toString();
            String var10006 = TellUsMoreDialog.this.getReportedUser().getName();
            String var3 = var10006;
            String var4 = var6;
            String var5 = var10004;
            Report report=new Report(var7,var8,var6,var10004,var10006);
            TellUsMoreDialog.this.firebaseHelper.addReport(report).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
               // $FF: synthetic method
               // $FF: bridge method
               public void onSuccess(Object var1) {
                  this.onSuccess((DocumentReference)var1);
               }

               public final void onSuccess(DocumentReference it) {
                  CommonsKt.showToast("reported successfully");
                  TellUsMoreDialog.this.dismiss();
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

   public final void getReportedUserData(@NotNull User data) {
      this.reportedUser = data;
   }

}
