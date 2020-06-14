package com.sachtech.datingapp.networking;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.data.GoogleUser;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;


public final class Google {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Google.class), "firestoreHelper", "getFirestoreHelper()Lcom/sachtech/datingapp/networking/FirebaseHelper;"))};
   private int RC_SIGN_IN = 901;
   private Fragment fragment;
   private GoogleSignInClient googleSignInClient;
   @Nullable
   private GoogleCallbackListener googleCallbackListener;

   public final int getRC_SIGN_IN() {
      return this.RC_SIGN_IN;
   }

   public final void setRC_SIGN_IN(int var1) {
      this.RC_SIGN_IN = var1;
   }

   private final FirebaseHelper getFirestoreHelper() {
    /*  Lazy var1 = this.firestoreHelper$delegate;
      KProperty var3 = $$delegatedProperties[0];
      boolean var4 = false;*/
      return new FirebaseHelper();
   }

   @Nullable
   public final GoogleCallbackListener getGoogleCallbackListener() {
      return this.googleCallbackListener;
   }

   public final void setGoogleCallbackListener(@Nullable GoogleCallbackListener var1) {
      this.googleCallbackListener = var1;
   }

   public final void init() {
      Builder var10000 = new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
      Fragment var10001 = this.fragment;
      GoogleSignInOptions gso = var10000.requestIdToken(var10001 != null ? var10001.getString(R.string.default_web_client_id) : null).requestEmail().build();
    //  Context var2 = var10001 != null ? var10001.getContext() : null;
      googleSignInClient = GoogleSignIn.getClient(fragment.getContext(),gso);
   }

   public final void signInWithGoogle() {
      GoogleSignInClient var10000 = this.googleSignInClient;
      Intent signInIntent = var10000.getSignInIntent();
         fragment.startActivityForResult(signInIntent, this.RC_SIGN_IN);

   }

   private final void handleSignInResult(Task task) {
      GoogleCallbackListener var10000;
      try {
         GoogleSignInAccount account = task != null ? (GoogleSignInAccount)task.getResult(ApiException.class) : null;
         GoogleUser googleUser = new GoogleUser(account != null ? account.getDisplayName() : null, account != null ? account.getEmail() : null, String.valueOf(account != null ? account.getPhotoUrl() : null), account != null ? account.getIdToken() : null);
         Preferences var4 = Preferences.INSTANCE;
         PrefKeys var9 = PrefKeys.INSTANCE;
         Intrinsics.checkExpressionValueIsNotNull(var9, "PrefKeys.INSTANCE");
         String var10 = var9.getGOOGLEUSER();
         Intrinsics.checkExpressionValueIsNotNull(var10, "PrefKeys.INSTANCE.googleuser");
         String key$iv = var10;
         if (CommonsKt.isNull(googleUser)) {
            var4.clearPrefKey(key$iv);
         } else {
            var4.setPref(key$iv, (new Gson()).toJson(googleUser));
         }

         AuthCredential var11 = GoogleAuthProvider.getCredential(account != null ? account.getIdToken() : null, (String)null);
         Intrinsics.checkExpressionValueIsNotNull(var11, "GoogleAuthProvider.getCr…l(account?.idToken, null)");
         AuthCredential credential = var11;
         var10000 = this.googleCallbackListener;
         if (var10000 != null) {
            var10000.onGoogleSignInSuccess(credential);
         }
      } catch (ApiException var7) {
         var10000 = this.googleCallbackListener;
         if (var10000 != null) {
            String var10001 = var7.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(var10001, "e.localizedMessage");
            var10000.onFailure(var10001);
         }

         Log.e("e", var7.toString());
      } catch (Throwable throwable) {
         throwable.printStackTrace();
      }

   }

   private final void firebaseAuthWithGoogle(final GoogleSignInAccount account, final Function0 body) {
      AuthCredential var10000 = GoogleAuthProvider.getCredential(account != null ? account.getIdToken() : null, (String)null);
      Intrinsics.checkExpressionValueIsNotNull(var10000, "GoogleAuthProvider.getCr…l(account?.idToken, null)");
      AuthCredential credential = var10000;
      this.getFirestoreHelper().getFirebaseAuth().signInWithCredential(credential).addOnCompleteListener((OnCompleteListener)(new OnCompleteListener() {
         public final void onComplete(@NotNull Task it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            if (it.isSuccessful()) {
               GoogleSignInAccount var10000 = account;
               String name = var10000 != null ? var10000.getDisplayName() : null;
               var10000 = account;
               String email = var10000 != null ? var10000.getEmail() : null;
               var10000 = account;
               Uri photourl = var10000 != null ? var10000.getPhotoUrl() : null;
               body.invoke();
            } else {
               Exception var10001 = it.getException();
               Log.e("googleexception", var10001 != null ? var10001.getMessage() : null);
            }

         }
      }));
   }

   public final void signOutFromGoogle() {
      GoogleSignInClient var10000 = this.googleSignInClient;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("googleSignInClient");
      }

      var10000.signOut();
   }

   public final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      if (requestCode == this.RC_SIGN_IN) {
         Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
         this.handleSignInResult(task);
      }

   }

   public Google(@Nullable Fragment fragment, @Nullable GoogleCallbackListener googleCallbackListener) {
      this.fragment = fragment;
      this.googleCallbackListener = googleCallbackListener;
   }

   public Google(@Nullable Fragment fragment) {
      this.fragment = fragment;
   }
}
