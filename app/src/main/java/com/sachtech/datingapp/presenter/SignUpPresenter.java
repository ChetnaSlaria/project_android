package com.sachtech.datingapp.presenter;

import com.sachtech.datingapp.model.SignUpModel;
import com.sachtech.datingapp.view.SignUpView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public  class SignUpPresenter {
   @NotNull
   private final SignUpView signUpView;

   @NotNull
   public final SignUpModel getSignUpModel() {
      return new SignUpModel();
   }

   public final void signUpUser(@NotNull String email, @NotNull String password) {
      this.getSignUpModel().signUp(email, password, this.signUpView);
   }

   @NotNull
   public final SignUpView getSignUpView() {
      return this.signUpView;
   }

   public SignUpPresenter(@NotNull SignUpView signUpView) {
      super();
      this.signUpView = signUpView;
   }
}
