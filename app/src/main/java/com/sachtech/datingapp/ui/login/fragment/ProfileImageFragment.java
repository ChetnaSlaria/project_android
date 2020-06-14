package com.sachtech.datingapp.ui.login.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.data.FacebookUser;
import com.sachtech.datingapp.data.GoogleUser;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.presenter.ProfileImagePresenter;
import com.sachtech.datingapp.utils.CircleTransform;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.IntentString;
import com.sachtech.datingapp.utils.OnSelectImageListener;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.SelectImageUtils;
import com.sachtech.datingapp.view.ProfileImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;


public final class ProfileImageFragment extends Fragment implements OnSelectImageListener, ProfileImageView {
    @Nullable
    private Uri uri;
    @Nullable
    private String uploadedUri;
    @Nullable
    private ArrayList imageArray;


    public void onAdditionalImagesUpload(@NotNull Uri it, @Nullable ImageView imageView) {
        new Commons(getContext()).hideProgress();
        ArrayList var10000 = this.imageArray;
        var10000.add(it.toString());
        if (imageView.getDrawable() != null) {
            Picasso.get().load(it).transform(new CircleTransform()).into(imageView);
        } else {
            if (profileImage1.getDrawable() == null) {
                Picasso.get().load(it).transform(new CircleTransform()).into(profileImage1);
            } else if (profileImage2.getDrawable() == null) {
                Picasso.get().load(it).transform(new CircleTransform()).into(profileImage2);

            } else if (profileImage3.getDrawable() == null) {
                Picasso.get().load(it).transform(new CircleTransform()).into(profileImage3);

            } else if (profileImage4.getDrawable() == null) {
                Picasso.get().load(it).transform(new CircleTransform()).into(profileImage4);

            } else if (profileImage5.getDrawable() == null) {
                Picasso.get().load(it).transform(new CircleTransform()).into(profileImage5);

            }
        }
    }

    public void onFailure(@NotNull String message) {
        new Commons(getContext()).hideProgress();
        CommonsKt.showToast(message);
    }

    public void onUploadSuccessful(@NotNull Uri it) {
        new Commons(getContext()).hideProgress();
        Log.e("data", "" + it);
        this.uploadedUri = it.toString();
        Picasso.get().load(it).transform(new CircleTransform()).into(profileImage);

    }

    public void onImageSelected(@NotNull Uri path, @Nullable ImageView imageView) {
        this.uri = path;
        new Commons(getContext()).showProgress();
        if (imageView == profileImage) {
            this.getProfileImagePresenter().uploadProfilePicture(path);
        } else {
            this.getProfileImagePresenter().uploadAdditionalImages(path, imageView);
        }

    }

    @Nullable
    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(@Nullable Uri var1) {
        this.uri = var1;
    }

    @Nullable
    public final String getUploadedUri() {
        return this.uploadedUri;
    }

    public final void setUploadedUri(@Nullable String var1) {
        this.uploadedUri = var1;
    }

    @Nullable
    public final ArrayList getImageArray() {
        return this.imageArray;
    }

    public final void setImageArray(@Nullable ArrayList var1) {
        this.imageArray = var1;
    }

    @NotNull
    public final ProfileImagePresenter getProfileImagePresenter() {
        return new ProfileImagePresenter(this);
    }

    @NotNull
    public final SelectImageUtils getSelectImageUtils() {
        return new SelectImageUtils(getContext());
    }

    ImageView profileImage, profileImage1, profileImage2, profileImage3, profileImage4, profileImage5;
    SelectImageUtils selectImageUtils;

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context var10000 = this.getContext();
        return LayoutInflater.from(var10000).inflate(R.layout.fragment_select_profile, container, false);
    }

    Bundle bundle;

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.imageArray = new ArrayList();
        bundle = new Bundle();
        selectImageUtils = new SelectImageUtils(getContext());
        selectImageUtils.selectImage(this::onImageSelected);
        profileImage = view.findViewById(id.profile_image);
        profileImage1 = view.findViewById(id.profile_image1);
        profileImage2 = view.findViewById(id.profile_image2);
        profileImage3 = view.findViewById(id.profile_image3);
        profileImage4 = view.findViewById(id.profile_image4);
        profileImage5 = view.findViewById(id.profile_image5);
        this.setOnImageClickListener();
        ProfileImagePresenter imagePresenter = new ProfileImagePresenter(this);
        Preferences var4 = Preferences.INSTANCE;
        PrefKeys var10000 = PrefKeys.INSTANCE;
        String fbString = var4.getPrefs().getString(var10000.getFACEBOOKUSER(), "");
        FacebookUser fbUser = new Gson().fromJson(fbString, FacebookUser.class);
        String googleString = var4.getPrefs().getString(var10000.getFACEBOOKUSER(), "");
        GoogleUser googleUser = new Gson().fromJson(googleString, GoogleUser.class);
        if ((fbUser != null ? fbUser.getProfilePic() : null) != null) {
            uploadedUri = fbUser.getProfilePic();
            Picasso.get().load(uploadedUri).transform(new CircleTransform()).into(profileImage);
            imagePresenter.uploadProfilePicture(Uri.fromFile(new File(fbUser.getProfilePic())));
        } else if ((googleUser != null ? googleUser.getPhotoUrl() : null) != null) {
            uploadedUri = googleUser.getPhotoUrl();
            Picasso.get().load(uploadedUri).transform(new CircleTransform()).into(profileImage);
        } else {
            Picasso.get().load(R.drawable.user).transform(new CircleTransform()).into(profileImage);

        }
        ((TextView) view.findViewById(id.profile_next)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                ProfileImageFragment.this.checkProfileImage();
            }
        }));
    }

    private final void setOnImageClickListener() {
        profileImage.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage);
            }
        }));
        profileImage1.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage1);
            }
        }));
        profileImage2.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage2);
            }
        }));
        profileImage3.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage3);
            }
        }));
        profileImage4.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage4);
            }
        }));
        profileImage5.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                selectImageUtils.openChooser(ProfileImageFragment.this, profileImage5);
            }
        }));
    }

    private final void checkProfileImage() {
        if (this.uploadedUri != null) {
            bundle.putString(IntentString.INSTANCE.getEMAIL(), getArguments() != null ? getArguments().getString(IntentString.INSTANCE.getEMAIL()) :Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getEMAIL(),""));
            bundle.putString(IntentString.INSTANCE.getNAME(),getArguments()!=null?getArguments().getString(IntentString.INSTANCE.getNAME()):Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSERNAME(),""));
            bundle.putStringArrayList(IntentString.INSTANCE.getIMAGELIST(), imageArray);
            bundle.putString(IntentString.INSTANCE.getPROFILEIMAGE(), this.uploadedUri);

            BaseFragment.replaceFragment((AppCompatActivity) this.getActivity(), id.login_container, (Fragment) (new GenderDobFragment()), bundle);
        } else {
            Context var5 = this.getContext();
            if (var5 == null) {
                Intrinsics.throwNpe();
            }

            Toast.makeText(var5, (CharSequence) "Please add Profile Picture before proceeding..", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectImageUtils.onActivityResult(requestCode, resultCode, data);
        // this.getSelectImageUtils().onActivityResult(requestCode, resultCode, data);
    }


}
