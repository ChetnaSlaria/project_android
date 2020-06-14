package com.sachtech.datingapp.ui.login.fragment;

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

import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.presenter.ProfileImagePresenter;
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter;
import com.sachtech.datingapp.presenter.VerificationImagePresenter;
import com.sachtech.datingapp.utils.CircleTransform;
import com.sachtech.datingapp.utils.OnSelectImageListener;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.SelectImageUtils;
import com.sachtech.datingapp.view.ProfileImageView;
import com.sachtech.datingapp.view.ProfileUpdateView;
import com.sachtech.datingapp.view.VerificationImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;


public final class NotVerifiedStatusFragment extends Fragment implements ProfileImageView, VerificationImageView, OnSelectImageListener, ProfileUpdateView {

    SelectImageUtils imageUtils;

    @Nullable
    private Uri uri;
    @Nullable
    private String profileUri;
    @Nullable
    private String verificationUri;
    @Nullable
    private User user;
    private HashMap _$_findViewCache;

    public void onUpdateProfile() {
        // this.hideProgress();
        BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.login_container, new SignInFragment(), null);
    }

    VerificationImagePresenter imagePresenter;
    ProfileImagePresenter profileImagePresenter;

    public VerificationImagePresenter getImagePresenter() {
        imagePresenter = new VerificationImagePresenter(this);
        return imagePresenter;
    }

    public ProfileImagePresenter getProfilePresenter() {
        profileImagePresenter = new ProfileImagePresenter(this);
        return profileImagePresenter;
    }

    public void onImageSelected(@NotNull Uri path, @Nullable ImageView view) {
        this.uri = path;
        if (view == notVerifyVrificationImage) {
            getImagePresenter().uploadVerificationPicture(path);
        } else if (view == notVerifyProfileImage) {
            getProfilePresenter().uploadProfilePicture(path);
        }
    }

    public void onVerificationImageUpload(@NotNull Uri it) {
        Picasso.get().load(it).transform(new CircleTransform()).into(notVerifyVrificationImage);
        this.verificationUri = it.toString();
    }

    public void onVerifyEmail() {
    }

    public void onUploadSuccessful(@NotNull Uri it) {
        Picasso.get().load(it).transform(new CircleTransform()).into(notVerifyProfileImage);
        this.profileUri = it.toString();
    }

    public void onAdditionalImagesUpload(@NotNull Uri it, @Nullable ImageView imageView) {
    }

    public void onFailure(@NotNull String message) {
        CommonsKt.showToast(message);
    }

    @Nullable
    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(@Nullable Uri var1) {
        this.uri = var1;
    }

    @Nullable
    public final String getProfileUri() {
        return this.profileUri;
    }

    public final void setProfileUri(@Nullable String var1) {
        this.profileUri = var1;
    }

    @Nullable
    public final String getVerificationUri() {
        return this.verificationUri;
    }

    public final void setVerificationUri(@Nullable String var1) {
        this.verificationUri = var1;
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User var1) {
        this.user = var1;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_not_verified_status, container, false);
    }
TextView notVerifiedImage,profileImage;
    ImageView notVerifyProfileImage,notVerifyVrificationImage;
ProfileUpdatePresenter updatePresenter;
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notVerifiedImage = view.findViewById(id.notVerify_change_verification);
        profileImage = view.findViewById(id.notVerify_change_profile);
        notVerifyProfileImage=view.findViewById(id.notVerify_profile_image);
        notVerifyVrificationImage=view.findViewById(id.notVerify_verification_image);
        imageUtils = new SelectImageUtils(getContext());
        imageUtils.selectImage(this::onAdditionalImagesUpload);
       updatePresenter= new ProfileUpdatePresenter((ProfileUpdateView) this);

        String var10001 = PrefKeys.INSTANCE.getUSER();
        String key$iv = var10001;
        Gson var10000 = new Gson();
        String var9 = Preferences.INSTANCE.getPrefs().getString(key$iv, "");
        User var11 = new Gson().fromJson(var9, User.class);
        this.user = var11;
        if (this.user != null) {
            ImageView var22 = (ImageView) view.findViewById(id.notVerify_profile_image);
            Picasso.get().load(user.getProfilePic()).into(var22);
            var22 = (ImageView) view.findViewById(id.notVerify_verification_image);
            Picasso.get().load(user.getVerificationImage()).into(var22);

            ((TextView) view.findViewById(id.notVerify_change_profile)).setOnClickListener((OnClickListener) (new OnClickListener() {
                public final void onClick(View it) {
                    Fragment var10001 = (Fragment) NotVerifiedStatusFragment.this;
                    ImageView var10002 = (ImageView)view.findViewById(id.notVerify_profile_image);
                    imageUtils.openChooser(var10001, var10002);
                }
            }));
            ((TextView) view.findViewById(id.notVerify_change_verification)).setOnClickListener((OnClickListener) (new OnClickListener() {
                public final void onClick(View it) {
                    imageUtils.selectImageFromCamera((Fragment) NotVerifiedStatusFragment.this, (ImageView) view.findViewById(id.notVerify_verification_image));
                }
            }));
            ((TextView) view.findViewById(id.notVerify_ok)).setOnClickListener((OnClickListener) (new OnClickListener() {
                public final void onClick(View it) {
                    if (NotVerifiedStatusFragment.this.getProfileUri() == null && NotVerifiedStatusFragment.this.getVerificationUri() == null) {
                        BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.login_container, new SignInFragment(), null);
                    } else {
                        User var10000;
                        String var10001;
                        if (NotVerifiedStatusFragment.this.getProfileUri() != null) {
                            var10000 = NotVerifiedStatusFragment.this.getUser();
                            if (var10000 != null) {
                                var10001 = NotVerifiedStatusFragment.this.getProfileUri();

                                var10000.setProfilePic(var10001);
                            }
                        }

                        if (NotVerifiedStatusFragment.this.getVerificationUri() != null) {
                            var10000 = NotVerifiedStatusFragment.this.getUser();
                            if (var10000 != null) {
                                var10001 = NotVerifiedStatusFragment.this.getVerificationUri();

                                var10000.setVerificationImage(var10001);
                            }
                        }

                        User var3 = NotVerifiedStatusFragment.this.getUser();

                        updatePresenter.updateProfileOnDatabase(var3);
                    }

                }
            }));
        }
    }
        public void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            imageUtils.onActivityResult(requestCode, resultCode, data);
        }
    }
