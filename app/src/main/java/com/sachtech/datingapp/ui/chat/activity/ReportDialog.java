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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public final class ReportDialog extends DialogFragment {
   private final FirebaseHelper firebaseHelper = new FirebaseHelper();
   @NotNull
   public User anotherUser;
   private HashMap _$_findViewCache;



   @NotNull
   public final User getAnotherUser() {
      User var10000 = this.anotherUser;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("anotherUser");
      }

      return var10000;
   }


com.sachtech.datingapp.data.User userdata;
   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(inflater, "inflater");
      return inflater.inflate(R.layout.dialog_report, container, false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(view, "view");
      super.onViewCreated(view, savedInstanceState);
    String user= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
    userdata=new Gson().fromJson(user, com.sachtech.datingapp.data.User.class);
      Dialog var10000 = this.getDialog();
      if (var10000 != null) {
         Window var3 = var10000.getWindow();
         if (var3 != null) {
            var3.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }

      TellUsMoreDialog var4 =new TellUsMoreDialog();
      User var10001 = this.anotherUser;
      var4.getReportedUserData(var10001);
      ((TextView)view.findViewById(id.other)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            var4.show(getActivity().getSupportFragmentManager(), "tell");
            ReportDialog.this.dismiss();
         }
      }));
      ((TextView)view.findViewById(id.scam)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ReportDialog.this.sendReportData("Scam or spam");
         }
      }));
      ((TextView)view.findViewById(id.inappropriate_message)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ReportDialog.this.sendReportData("Inappropriate message");
         }
      }));
      ((TextView)view.findViewById(id.inappropriate_profile)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ReportDialog.this.sendReportData("Inappropriate profile");
         }
      }));
   }

   private final void sendReportData(String message) {
      String var7 = userdata.getUid();
      String var8 = userdata.getName();
      User var10004 = this.anotherUser;

      String var9 = var10004.getUid();

      String var6 = var10004.getName();
      String var3 = var6;
      String var5 = var9;
      Report var10000 = new Report(var7, var8, message, var5, var3);
      Report reportData = var10000;
      this.firebaseHelper.addReport(reportData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
         @Override
         public void onSuccess(DocumentReference documentReference) {
            CommonsKt.showToast("reported successfully");
            ReportDialog.this.dismiss();  
         }
      }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            CommonsKt.showToast(e.getLocalizedMessage());
         }
      });
   }

   public final void getChatUserData(@NotNull User chatUserData) {
      anotherUser = chatUserData;
   }
   
}
