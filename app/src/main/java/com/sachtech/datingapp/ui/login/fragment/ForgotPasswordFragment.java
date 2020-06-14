package com.sachtech.datingapp.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import gurtek.mrgurtekbase.KotlinBaseFragment;
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


public final class ForgotPasswordFragment extends Fragment {

FirebaseHelper firebaseHelper;
   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
     firebaseHelper=new FirebaseHelper();
     return firebaseHelper;
   }

Button forgotBtn;
   EditText forgotEmail;
   TextView email;
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_forgot_password,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      forgotBtn=view.findViewById(id.forgotSendBtn);
      forgotEmail=view.findViewById(id.forgotEmail);
      email= view.findViewById(id.email);

      setOnFocusChange();
      forgotBtn.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
           sendVerificationEmail();
         }
      });
   }

   private  void sendVerificationEmail() {
      String email = forgotEmail.getText().toString();
      if (CommonsKt.validateEmail(email)) {
        // this.showProgress();
        getFirebaseHelper().getFirebaseAuth().sendPasswordResetEmail(email).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
            public final void onFailure(@NotNull Exception it) {
             //  ForgotPasswordFragment.this.hideProgress();
               String message = it.getLocalizedMessage();
               CommonsKt.showToast(message);
            }
         })).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
               this.onSuccess((Void)var1);
            }

            public final void onSuccess(Void it) {
               //ForgotPasswordFragment.this.hideProgress();
               CommonsKt.showToast("Reset password link will be send to your email,please check it.");
               //ForgotPasswordFragment.this.onBackPressed();
            }
         }));
      } else {
         CommonsKt.showToast("Enter your correct email");
      }

   }

   private  void setOnFocusChange() {
      CommonsKt.onFocusChange(forgotEmail, email, "Email");
   }

}
