package com.sachtech.datingapp.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.cometChat.network.ApiInterface;
import com.sachtech.datingapp.cometChat.network.ApiKt;
import com.sachtech.datingapp.data.ChatUser;
import com.sachtech.datingapp.data.UpdateResponse;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.home.activity.EditProfileActivity;
import com.sachtech.datingapp.ui.home.fragment.SubscriptionActivity;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.OnSelectImageListener;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.SelectImageUtils;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;


public final class ProfileFragment extends Fragment implements OnSelectImageListener {
   @Nullable
   private OnFragmentChange onFragmentChange;
 
   @Nullable
   private ChatUser cometUser;
   @NotNull
   public User newuser;

   public void onImageSelected(@NotNull Uri path, @Nullable ImageView view) {
      this.uploadImage(path);
   }

   private final void uploadImage(Uri path) {
      this.getFirebaseHelper().uploadImageToStorage(path).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((TaskSnapshot)var1);
         }

         public final void onSuccess(TaskSnapshot it) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            StorageMetadata var10000 = it.getMetadata();
            if (var10000 == null) {
               Intrinsics.throwNpe();
            }

            Intrinsics.checkExpressionValueIsNotNull(var10000, "it.metadata!!");
            StorageReference var2 = var10000.getReference();
            if (var2 == null) {
               Intrinsics.throwNpe();
            }

            Intrinsics.checkExpressionValueIsNotNull(var2, "it.metadata!!.reference!!");
            var2.getDownloadUrl().addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
               // $FF: synthetic method
               // $FF: bridge method
               public void onSuccess(Object var1) {
                  this.onSuccess((Uri)var1);
               }

               public final void onSuccess(Uri it)
               {
                  ProfileFragment.this.updateUserDetail(it);
               }
            })).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                  CommonsKt.showToast(e.getLocalizedMessage());
               }
            });
         }
      })).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            CommonsKt.showToast(e.getLocalizedMessage());
         }
      });
   }

   private final void updateUserDetail(final Uri it) {
      Picasso.get().load(it).into((RoundedImageView)view.findViewById(id.profile_image));
      newuser.setProfilePic(it.toString());
      FirebaseHelper var2 = this.getFirebaseHelper();

      var2.updateUserDetails(newuser).addOnCompleteListener((OnCompleteListener)(new OnCompleteListener() {
         public final void onComplete(@NotNull Task task) {
            ProfileFragment.this.updateOnCometChat(String.valueOf(it));
         }
      })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
         public final void onFailure(@NotNull Exception it) {
            String var10000 = it.getLocalizedMessage();
            CommonsKt.showToast(var10000);
         }
      }));
   }

   private final void updateOnCometChat(String uri) {
      ChatUser var10000 = this.cometUser;
      if (var10000 != null) {
         var10000.setAvatar(uri);
      }

      ApiInterface var7 = ApiKt.apiHitter();
      String var10003 = this.getResources().getString(R.string.comet_api_key);
      String var10004 = this.getResources().getString(R.string.comet_app_id);
      ChatUser var10005 = this.cometUser;
      String var6 = var10005 != null ? var10005.getUid() : null;

      ChatUser var10006 = this.cometUser;

      ChatUser var2 = var10006;
      String var3 = var6;
      String var4 = var10004;
      String var5 = var10003;
      var7.updateUserOnCometChat( "application/json", "application/json",var10004,var10003,var6,var10006).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UpdateResponse>() {
         @Override
         public void accept(UpdateResponse updateResponse) throws Exception {
            Preferences preferences = Preferences.INSTANCE;
            String user = PrefKeys.INSTANCE.getUSER();
            String key$iv = user;
            preferences.setPref(user, new Gson().toJson(newuser));
            String cometUserKey = PrefKeys.COMETUSER;
            preferences.setPref(cometUserKey, new Gson().toJson(cometUser));

         }

      }, new Consumer<Throwable>() {
         @Override
         public void accept(Throwable throwable) throws Exception {
            CommonsKt.showToast(throwable.getLocalizedMessage());
         }
      });
   }

   @Nullable
   public final OnFragmentChange getOnFragmentChange() {
      return this.onFragmentChange;
   }

   public final void setOnFragmentChange(@Nullable OnFragmentChange var1) {
      this.onFragmentChange = var1;
   }

   public void onAttach(@NotNull Context context) {
      super.onAttach(context);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(4);
      }

   }


   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   @Nullable
   public final ChatUser getCometUser() {
      return this.cometUser;
   }

   public final void setCometUser(@Nullable ChatUser var1) {
      this.cometUser = var1;
   }

   public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
     imageUtils.onActivityResult(requestCode, resultCode, data);
   }



   SelectImageUtils imageUtils;
   public final void setNewuser(@NotNull User var1) {
      this.newuser = var1;
   }

   View view;
   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile,container,false);
   }
User user;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      this.view=view;
      imageUtils=new SelectImageUtils(getContext());
      imageUtils.selectImage(this::onImageSelected);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(4);
      }
      Preferences preferences = Preferences.INSTANCE;
      String userkey = PrefKeys.INSTANCE.getUSER();
      String userString=preferences.getPrefs().getString(userkey,"");
    user=new Gson().fromJson(userString,User.class);

      String cometuserString=preferences.getPrefs().getString(PrefKeys.COMETUSER,"");
      cometUser=new Gson().fromJson(cometuserString,ChatUser.class);
      newuser=user;
      this.setAccount(user);
      if(user!=null) {
         if(!user.getProfilePic().isEmpty()) {
            Picasso.get().load(user.getProfilePic()).into((RoundedImageView) view.findViewById(id.profile_image));
         }
      }
      TextView var22 = (TextView)view.findViewById(id.profile_name);
      var22.setText((CharSequence)user.getName());
      ((TextView)view.findViewById(id.profile_edit)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            getActivity().startActivity(new Intent(getContext(),EditProfileActivity.class));
         }
      }));
      ((TextView)view.findViewById(id.profile_settings)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.home_container,new SettingFragment(),null);
         }
      }));
      ((TextView)view.findViewById(id.profile_partner_preferences)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.home_container,new PartnerPreferenceFragment(),null);

         }
      }));
      ((TextView)view.findViewById(id.profile_privacy)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            FragmentActivity var10000 = ProfileFragment.this.getActivity();
            if (var10000 != null) {
               var10000.startActivity(new Intent((Context)ProfileFragment.this.getActivity(), PrivacyActivity.class));
            }

         }
      }));
      ((TextView)view.findViewById(id.profile_terms)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            FragmentActivity var10000 = ProfileFragment.this.getActivity();
            if (var10000 != null) {
               var10000.startActivity(new Intent((Context)ProfileFragment.this.getActivity(), TermsAndConditionActivity.class));
            }

         }
      }));
      ((ImageView)view.findViewById(id.add_image)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Fragment var10001 = (Fragment)ProfileFragment.this;
            RoundedImageView var10002 = (RoundedImageView)view.findViewById(id.profile_image);
            imageUtils.openChooser(ProfileFragment.this, (ImageView)var10002);
         }
      }));
      ((TextView)view.findViewById(id.profile_subscription)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            getActivity().startActivity(new Intent(getActivity(),SubscriptionActivity.class));
         }
      }));
     /* ((ImageView)view.findViewById(id.back)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
           getActivity().onBackPressed();
         }
      }));
*/   }

   private void setAccount(User user) {
      TextView var10000;
      if (Intrinsics.areEqual(user.getAccountStatus(), "verified")) {
         var10000 = (TextView) getView().findViewById(id.profile_subscription);
         Intrinsics.checkExpressionValueIsNotNull(var10000, "profile_subscription");
         var10000.setEnabled(true);
         ((TextView)view.findViewById(id.profile_subscription)).setTextColor(this.getResources().getColor(R.color.colorWhite));
      } else {
         var10000 = (TextView)view.findViewById(id.profile_subscription);
         Intrinsics.checkExpressionValueIsNotNull(var10000, "profile_subscription");
         var10000.setEnabled(false);
         ((TextView)view.findViewById(id.profile_subscription)).setTextColor(this.getResources().getColor(R.color.colorGrey));
      }

   }

  }
