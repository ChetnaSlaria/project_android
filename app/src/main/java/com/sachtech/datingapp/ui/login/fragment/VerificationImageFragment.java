package com.sachtech.datingapp.ui.login.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.cometChat.CometChatAuth;
import com.sachtech.datingapp.data.CreateUserResponse;
import com.sachtech.datingapp.data.FacebookUser;
import com.sachtech.datingapp.data.GoogleUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.presenter.UserDetailPresenter;
import com.sachtech.datingapp.presenter.VerificationImagePresenter;
import com.sachtech.datingapp.utils.CircleTransform;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.Constants;
import com.sachtech.datingapp.utils.IntentString;
import com.sachtech.datingapp.utils.OnSelectImageListener;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.SelectImageUtils;
import com.sachtech.datingapp.view.UserDetailView;
import com.sachtech.datingapp.view.VerificationImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import gurtek.mrgurtekbase.ExtenstionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;


public final class VerificationImageFragment extends Fragment implements VerificationImageView, OnSelectImageListener, UserDetailView {
    SelectImageUtils imageUtils;

    @Nullable
    private Uri verificationImageUri;
    @NotNull
    private String uri = "";
    @NotNull
    private String name = "";
    @NotNull
    private String email = "";

    @Nullable
    public final CometChatAuth getCometUser() {
        return new CometChatAuth(getContext());
    }

    @NotNull
    public final UserDetailPresenter getUserDetailPresenter() {

        return new UserDetailPresenter(this);
    }

    @Nullable
    public final Uri getVerificationImageUri() {
        return this.verificationImageUri;
    }

    public final void setVerificationImageUri(@Nullable Uri var1) {
        this.verificationImageUri = var1;
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }

    public final void setUri(@NotNull String var1) {
        this.uri = var1;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String var1) {
        this.name = var1;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(@NotNull String var1) {
        this.email = var1;
    }

    @NotNull
    public final VerificationImagePresenter getProfileImagePresenter() {

        return new VerificationImagePresenter(this);
    }




    public void onCreateChatUser(@NotNull CreateUserResponse it) {
        new Commons(getContext()).hideProgress();
        Preferences var2 = Preferences.INSTANCE;
        PrefKeys var10000 = PrefKeys.INSTANCE;
        String var6 = var10000.getUSER();
        String key$iv = var6;
        String var10001 = PrefDataKt.getUid();
        Object obj$iv = this.getUserDetails(var10001);
        if (obj$iv != null) {
            var2.clearPrefKey(key$iv);
        } else var2.setPref(key$iv, new Gson().toJson(obj$iv));
        if (CommonsKt.isNull(obj$iv)) {
            var2.clearPrefKey(key$iv);
        } else {
            var2.setPref(key$iv, (new Gson()).toJson(obj$iv));
        }
        BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new NotVerifiedStatusFragment(), null);
    }

    public void onVerificationImageUpload(@NotNull Uri it) {
        new Commons(getContext()).hideProgress();
        VerificationImagePresenter var10000 = this.getProfileImagePresenter();
        String var10001 = it.toString();
this.uri=it.toString();
        String email = this.getUserDetails(PrefDataKt.getUid()).getEmail();
        String name = this.getUserDetails(PrefDataKt.getUid()).getName();
        String var2 = PrefDataKt.getUid();
        String var3 = name;
        String var4 = email;
        String var5 = var10001;
        var10000.sendVerifyEmail(var3, var5, var4, var2);
        LinearLayout layout = view.findViewById(id.image_layout);
        ExtenstionsKt.visible((View) layout);
        ImageView var1 = (ImageView) view.findViewById(id.verify_camera);
        ExtenstionsKt.gone((View) var1);
        ImageView var10 = (ImageView) view.findViewById(id.verification_image);
        Picasso.get().load(verificationImageUri).transform(new CircleTransform()).into(var10);
    }
    public void onVerifyEmail() {
        LinearLayout var10000 = getView().findViewById(id.image_layout);
        ExtenstionsKt.visible((View) var10000);
        ImageView var1 = (ImageView) getView().findViewById(id.verify_camera);
        ExtenstionsKt.gone((View) var1);
    }

    public void onImageSelected(@NotNull Uri path, @Nullable ImageView view) {
        this.verificationImageUri = path;
        new Commons(getContext()).showProgress();
        this.getProfileImagePresenter().uploadVerificationPicture(path);
    }

    public void onSuccess(@NotNull Task result) {
        new Commons(getContext()).hideProgress();
        String var7;
        if (result.isSuccessful()) {
            Preferences var2 = Preferences.INSTANCE;
            PrefKeys var10000 = PrefKeys.INSTANCE;
            var7 = var10000.getUSER();
            String key$iv = var7;
           // User user= (User) result.getResult();
            String var10001 = PrefDataKt.getUid();
            Object obj$iv = this.getUserDetails(var10001);
            if (CommonsKt.isNull(obj$iv)) {
                var2.clearPrefKey(key$iv);
            } else {
                var2.setPref(key$iv, (new Gson()).toJson(obj$iv));
            }

            String var10002 = PrefDataKt.getUid();

            User userDetails = this.getUserDetails(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUID(),""));
            this.getUserDetailPresenter().createChatUser(userDetails.getUid(), userDetails.getName(), userDetails.getEmail(), userDetails.getProfilePic());
          /*  Preferences var2 = Preferences.INSTANCE;
            PrefKeys var10000 = PrefKeys.INSTANCE;
            String var6 = var10000.getUSER();
            String key$iv = var6;
            String var10001 = PrefDataKt.getUid();
            Object obj$iv = this.getUserDetails(var10001);
            if (obj$iv != null) {
                var2.clearPrefKey(key$iv);
            } else var2.setPref(key$iv, new Gson().toJson(obj$iv));
            if (CommonsKt.isNull(obj$iv)) {
                var2.clearPrefKey(key$iv);
            } else {
                var2.setPref(key$iv, (new Gson()).toJson(obj$iv));
            }*/
          //  BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new NotVerifiedStatusFragment(), null);
        } else {
            FragmentActivity var8 = this.getActivity();
            if (var8 != null) {
                FragmentManager var9 = var8.getSupportFragmentManager();
                if (var9 != null) {
                    var9.popBackStack();
                }
            }

            Exception var10 = result.getException();
            var7 = var10 != null ? var10.getLocalizedMessage() : null;

            CommonsKt.showToast(var7);
        }

    }

    public void onFailure(@NotNull String localizedMessage) {
        new Commons(getContext()).hideProgress();
      //  Toast.makeText(getContext(), localizedMessage, Toast.LENGTH_SHORT).show();
        ;
    }

    private User getUserDetails(String uid) {
        String key$iv;
        String defaultValue$iv;
        String var15;
        String var10002;
        SharedPreferences var23;
        String var24;
        String var25;
        Integer var26;
        Boolean var28;
        Float var30;
        Long var31;
        if (PrefDataKt.facebookUser() != null) {
            FacebookUser var10001 = PrefDataKt.facebookUser();
            var25 = var10001 != null ? var10001.getName() : null;
            this.name = var25;
            var10001 = PrefDataKt.facebookUser();
            var25 = var10001 != null ? var10001.getEmail() : null;
            this.email = var25;
        } else if (PrefDataKt.googleUser() != null) {
            GoogleUser var27 = PrefDataKt.googleUser();
            var25 = var27 != null ? var27.getDisplayName() : null;
            this.name = var25;
            var27 = PrefDataKt.googleUser();
            var25 = var27 != null ? var27.getEmail() : null;
            this.email = var25;
        } else {
            Preferences var2;
            boolean $i$f$get;
            KClass var6;
            VerificationImageFragment var10000;
            label390:
            {
                if(getArguments().getString(IntentString.INSTANCE.getNAME())!=null) {
                    name = getArguments().getString(IntentString.INSTANCE.getNAME());
                }
                else name="abc";
                if( getArguments().getString(IntentString.INSTANCE.getEMAIL())!=null) {
                    email = getArguments().getString(IntentString.INSTANCE.getEMAIL());
                }
                else email="abc@gmail.com";

                if (name == null) {
                    name = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSERNAME(), "");
                }
                if (email == null) {
                    email = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getEMAIL(), "");
                }
                ArrayList imageArray = bundle != null ? getArguments().getStringArrayList(IntentString.INSTANCE.getIMAGELIST()) : null;
                var24 = bundle != null ? getArguments().getString(IntentString.INSTANCE.getPROFILEIMAGE()) : null;

                key$iv = var24;
                defaultValue$iv = bundle != null ? getArguments().getString(IntentString.INSTANCE.getGENDER()) : null;
                Long dob = bundle != null ? getArguments().getLong(IntentString.INSTANCE.getDOB()) : null;
                String age = bundle != null ? getArguments().getString(IntentString.INSTANCE.getAGE()) : null;
                String verificationImage = this.uri;
                User user = new User();
                User var35 = user;
                boolean var10;
                if (uid != null) {
                    var10 = false;
                    var24 = uid.toLowerCase();
                    var15 = var24;
                    var35 = user;
                    var25 = var15;
                } else {
                    var25 = null;
                }

                var35.setUid(String.valueOf(var25));
                var25 = this.name;
                user.setName(var25);
                var25 = this.email;
                user.setEmail(var25);
                var25 = PrefDataKt.getFcm();
                user.setFcm(var25);
                user.setProfilePic(key$iv);
                user.setVerificationImage(verificationImage.toString());
                user.setGender(defaultValue$iv);
                user.setAge(age);
                user.setDob(dob);
                user.setAccountStatus(Constants.INSTANCE.getNOT_VERIFIED());
                user.setColorComplexion("");
                var25 = PrefDataKt.getLatitude();
                String var9;
                double var22;
                Double var32;
                if (var25 != null) {
                    var9 = var25;
                    var10 = false;
                    var22 = Double.parseDouble(var9);
                    var35 = user;
                    var32 = var22;
                } else {
                    var32 = null;
                }
                var35.setLatitude(var32);
                var35 = user;
                var25 = PrefDataKt.getLongitude();
                if (var25 != null) {
                    var9 = var25;
                    var10 = false;
                    var22 = Double.parseDouble(var9);
                    var35 = user;
                    var32 = var22;
                } else {
                    var32 = null;
                }

                var35.setLongitude(var32);
                Preferences var20 = Preferences.INSTANCE;
                var25 = var20.getPrefs().getString(PrefKeys.INSTANCE.getLOCATION(), "");
                user.setLocation(var25);
                user.setImages(imageArray);
                try {
                    user.setCreatedOn(getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean var33 = privateAccount.isChecked();
                user.setPrivate(var33);
                return user;
            }

        }
        return null;
    }


    public long getDate() throws ParseException {
        long str_date = (new Date()).getTime();
        String dateFormatted = CommonsKt.getDateInformat(str_date);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = formatter.parse(dateFormatted);
        Date d = date;
        return date.getTime() / (long) 1000;
    }

    Switch privateAccount;
    ImageView verifyCamera;
    TextView changeVerifyImage, verificationDone;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_verify_image, container, false);
    }
Bundle bundle;
    View view;
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle=new Bundle();
        this.view=view;
        imageUtils = new SelectImageUtils(getContext());
        imageUtils.selectImage(this::onImageSelected);
        privateAccount = view.findViewById(id.private_account);
        verifyCamera = view.findViewById(id.verify_camera);
        changeVerifyImage = view.findViewById(id.change_verify_image);
        verificationDone = view.findViewById(id.verification_done);
        verifyCamera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUtils.selectImageFromCamera((Fragment) VerificationImageFragment.this, (ImageView) getView().findViewById(id.verification_image));
            }
        });
        changeVerifyImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUtils.selectImageFromCamera((Fragment) VerificationImageFragment.this, (ImageView) getView().findViewById(id.verification_image));

            }
        });
        verificationDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    VerificationImageFragment.this.verifyImage();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        privateAccount.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    private final void verifyImage() throws ParseException {
        if (Intrinsics.areEqual(this.uri, "") ^ true) {
            UserDetailPresenter var10000 = this.getUserDetailPresenter();
            String var10002 = PrefDataKt.getUid();
            new Commons(getContext()).showProgress();
            var10000.addUserDetailsToDatabase(this.getUserDetails(var10002));
        } else {
            Toast.makeText(getContext(), "You cannot skip this step", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUtils.onActivityResult(requestCode, resultCode, data);
    }
}

