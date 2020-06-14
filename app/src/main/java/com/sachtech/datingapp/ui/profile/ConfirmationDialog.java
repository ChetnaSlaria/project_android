package com.sachtech.datingapp.ui.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.network.ApiInterface;
import com.sachtech.datingapp.cometChat.network.ApiKt;
import com.sachtech.datingapp.data.DeleteUser;
import com.sachtech.datingapp.data.FacebookUser;
import com.sachtech.datingapp.data.GoogleUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.login.LoginActivity;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import gurtek.mrgurtekbase.KotlinBaseDialogFragment;
import gurtek.mrgurtekbase.adapter.RecycleItemViewCallback;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;


public final class ConfirmationDialog extends DialogFragment {
   @Nullable
   private AuthCredential credential;
   @Nullable
   private FacebookUser facebookUser;
   @Nullable
   private GoogleUser googleUser;
   private HashMap _$_findViewCache;
User user;
Context fragment;
   public ConfirmationDialog(Context settingFragment) {
      this.fragment=settingFragment;
   }

   public boolean adjustdisplay() {
      return true;
   }


   @Nullable
   public final AuthCredential getCredential() {
      return this.credential;
   }

   public final void setCredential(@Nullable AuthCredential var1) {
      this.credential = var1;
   }

   @Nullable
   public final FacebookUser getFacebookUser() {
      return this.facebookUser;
   }

   public final void setFacebookUser(@Nullable FacebookUser var1) {
      this.facebookUser = var1;
   }

   @Nullable
   public final GoogleUser getGoogleUser() {
      return this.googleUser;
   }

   public final void setGoogleUser(@Nullable GoogleUser var1) {
      this.googleUser = var1;
   }
FirebaseHelper firebaseHelper;

   @Nullable
   @Override
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view=LayoutInflater.from(fragment).inflate(R.layout.confirmation_dialog,container,false);
      return view;
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      Dialog var10000 = this.getDialog();
      if (var10000 != null) {
         Window var14 = var10000.getWindow();
         if (var14 != null) {
            var14.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }
      firebaseHelper=new FirebaseHelper();
String userString=Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      user=new Gson().fromJson(userString,User.class);
    String fb=  Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getFACEBOOKUSER(),"");
     facebookUser=new Gson().fromJson(fb,FacebookUser.class);
    String google=Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getGOOGLEUSER(),"");
    googleUser=new Gson().fromJson(google, GoogleUser.class);
      ((TextView)view.findViewById(id.yes)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            new Commons(getContext()).showProgress();
            if (var10000 != null) {
               if (!user.isSubscribed()) {
                  String var10001 = PrefDataKt.getUid();

                  firebaseHelper.deleteUserFromFirebase(var10001).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                        new Commons(getContext()).hideProgress();
                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }
                  }).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {
                        FirebaseAuth var10000 = FirebaseAuth.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "FirebaseAuth.getInstance()");
                        final FirebaseUser user1 = var10000.getCurrentUser();
                        ConfirmationDialog var14;
                        if (ConfirmationDialog.this.getFacebookUser() != null) {
                           var14 = ConfirmationDialog.this;
                           FacebookUser var10001 = ConfirmationDialog.this.getFacebookUser();
                           String var16 = var10001 != null ? var10001.getAccessToken() : null;
                           var14.setCredential(FacebookAuthProvider.getCredential(var16));
                        } else if (ConfirmationDialog.this.getGoogleUser() != null) {
                           var14 = ConfirmationDialog.this;
                           GoogleUser var18 = ConfirmationDialog.this.getGoogleUser();
                           var14.setCredential(GoogleAuthProvider.getCredential(var18 != null ? var18.getIdToken() : null, (String) null));
                        } else {

                           String email = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getEMAIL(), "");
                           String password = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPASSWORD(), "");
                           credential = EmailAuthProvider
                                   .getCredential(email, password);
                        }

                        if (user1 != null) {
                           AuthCredential var20 = ConfirmationDialog.this.getCredential();
                           if (var20 == null) {
                              Intrinsics.throwNpe();
                           }

                           Task var25 = user1.reauthenticate(var20);
                           if (var25 != null) {
                              var25 = var25.addOnCompleteListener((OnCompleteListener) (new OnCompleteListener() {
                                 public final void onComplete(@NotNull Task it) {
                                    user1.delete().addOnCompleteListener((OnCompleteListener) (new OnCompleteListener() {
                                       public final void onComplete(@NotNull Task task) {
                                          if (task.isSuccessful()) {
                                             ApiInterface var10000 = ApiKt.apiHitter();
                                             String var10003 = ConfirmationDialog.this.getResources().getString(R.string.comet_app_id);
                                             String var10004 = ConfirmationDialog.this.getResources().getString(R.string.comet_api_key);
                                             String var10005 = user1.getUid();
                                             var10000.deleteUser("application/json", "application/json", var10003, var10004, var10005).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeleteUser>() {
                                                @Override
                                                public void accept(DeleteUser deleteUser) throws Exception {
                                                   new Commons(getContext()).hideProgress();
                                                   Preferences.INSTANCE.clearPref();
                                                   ConfirmationDialog.this.dismiss();
                                                   Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   getActivity().startActivity(intent);
                                                   getActivity().finish();

                                                }
                                             }, new Consumer<Throwable>() {
                                                @Override
                                                public void accept(Throwable throwable) throws Exception {
                                                   new Commons(getContext()).hideProgress();
                                                   String var10000 = throwable.getLocalizedMessage();
                                                   CommonsKt.showToast(var10000);
                                                }
                                             });
                                          }

                                       }
                                    }));
                                 }
                              }));
                              if (var25 != null) {
                                 var25.addOnFailureListener((OnFailureListener) (new OnFailureListener() {
                                    public final void onFailure(@NotNull Exception it) {
                                       Intrinsics.checkParameterIsNotNull(it, "it");
                                      new Commons(getContext()).hideProgress();
                                       String var10000 = it.getLocalizedMessage();
                                       Intrinsics.checkExpressionValueIsNotNull(var10000, "it.localizedMessage");
                                       CommonsKt.showToast(var10000);
                                    }
                                 }));
                              }
                           }
                        }
                     }
                  });
               }
            }
         }
      }));
            ((TextView) view.findViewById(id.no)).setOnClickListener((OnClickListener) (new OnClickListener() {
               public final void onClick(View it) {
                  ConfirmationDialog.this.dismiss();
               }
            }));
         }

      }

