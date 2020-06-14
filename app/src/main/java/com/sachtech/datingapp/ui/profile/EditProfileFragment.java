package com.sachtech.datingapp.ui.profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.gson.Gson;
import com.hendraanggrian.appcompat.countrypicker.Country;
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog;
import com.hendraanggrian.appcompat.widget.CountryPicker.OnSelectedListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.network.ApiInterface;
import com.sachtech.datingapp.cometChat.network.ApiKt;
import com.sachtech.datingapp.data.ChatUser;
import com.sachtech.datingapp.data.UpdateResponse;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.ProfileImagePresenter;
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter;
import com.sachtech.datingapp.ui.profile.listener.EditInformation;
import com.sachtech.datingapp.ui.profile.listener.OnEditInformation;
import com.sachtech.datingapp.ui.profile.listener.OnLanguageSelect;
import com.sachtech.datingapp.utils.Beard;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.CompletedQuran;
import com.sachtech.datingapp.utils.Halal;
import com.sachtech.datingapp.utils.LifeAfterMarriage;
import com.sachtech.datingapp.utils.LivingArrangement;
import com.sachtech.datingapp.utils.MaritalStatus;
import com.sachtech.datingapp.utils.OnSelectImageListener;
import com.sachtech.datingapp.utils.Prays;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.ProfileItem;
import com.sachtech.datingapp.utils.Revert;
import com.sachtech.datingapp.utils.Sect;
import com.sachtech.datingapp.utils.SelectImageUtils;
import com.sachtech.datingapp.utils.Wear;
import com.sachtech.datingapp.utils.Weight;
import com.sachtech.datingapp.view.ProfileImageView;
import com.sachtech.datingapp.view.ProfileUpdateView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import gurtek.mrgurtekbase.ExtenstionsKt;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;

public  class EditProfileFragment extends Fragment implements OnSelectImageListener, OnEditInformation, ProfileImageView, ProfileUpdateView, OnLanguageSelect, EditInformation {
    @NotNull
    private String wear;
    @NotNull
    private String prays;
    @NotNull
    private String beard;
    @NotNull
    private String halal;
    @NotNull
    private String revert;
    @NotNull
    private String completedQuran;
    private long dob;
    private int age;
    @NotNull
    private ArrayList languages;
    @Nullable
    private CountryPickerDialog originDialog;
    @Nullable
    private CountryPickerDialog nationalityDialog;
    @Nullable
    private ChatUser cometUser;
    private int scrollX;
    private int scrollY;
    @NotNull
    private ArrayList images;
    @NotNull
    private String profilePic;
    @Nullable
    private Uri uri;

    public void editinformation(@NotNull String info, @NotNull TextView textView) {
        CharSequence var3 = (CharSequence) info;
        boolean var4 = false;
        if (var3.length() > 0) {
            textView.setText((CharSequence) info);
        }

    }

    @NotNull
    public final String getWear() {
        return this.wear;
    }

    public final void setWear(@NotNull String var1) {
        this.wear = var1;
    }

    @NotNull
    public final String getPrays() {
        return this.prays;
    }

    public final void setPrays(@NotNull String var1) {
        this.prays = var1;
    }

    @NotNull
    public final String getBeard() {
        return this.beard;
    }

    public final void setBeard(@NotNull String var1) {
        this.beard = var1;
    }

    @NotNull
    public final String getHalal() {
        return this.halal;
    }

    public final void setHalal(@NotNull String var1) {
        this.halal = var1;
    }

    @NotNull
    public final String getRevert() {
        return this.revert;
    }

    public final void setRevert(@NotNull String var1) {
        this.revert = var1;
    }

    @NotNull
    public final String getCompletedQuran() {
        return this.completedQuran;
    }

    public final void setCompletedQuran(@NotNull String var1) {
        this.completedQuran = var1;
    }

    public final long getDob() {
        return this.dob;
    }

    public final void setDob(long var1) {
        this.dob = var1;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int var1) {
        this.age = var1;
    }

    @NotNull
    public final ArrayList getLanguages() {
        return this.languages;
    }

    public final void setLanguages(@NotNull ArrayList var1) {
        this.languages = var1;
    }

    @Nullable
    public final CountryPickerDialog getOriginDialog() {
        return this.originDialog;
    }

    public final void setOriginDialog(@Nullable CountryPickerDialog var1) {
        this.originDialog = var1;
    }

    @Nullable
    public final CountryPickerDialog getNationalityDialog() {
        return this.nationalityDialog;
    }

    public final void setNationalityDialog(@Nullable CountryPickerDialog var1) {
        this.nationalityDialog = var1;
    }

    private final FirebaseHelper getFirebaseHelper() {
        return new FirebaseHelper();
    }

    private final User getUser() {
       String userString= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      User user=new Gson().fromJson(userString,User.class);
        return user;
    }

    User user;
    public void getLanguage(@Nullable ArrayList language) {
        ArrayList var10000 = this.languages;
        var10000.addAll((Collection) language);
        TextView var2 = (TextView) view.findViewById(id.edit_languages);
        var2.setText(joinToString(var10000));
    }

    public void onUpdateProfile() {
        this.getFirebaseHelper().getUserDetailsFromDatabase(this.getUser().getUid()).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((DocumentSnapshot) var1);
            }

            public final void onSuccess(DocumentSnapshot it) {
                new Commons(getContext()).hideProgress();

                Preferences var2 = Preferences.INSTANCE;
                PrefKeys var10000 = PrefKeys.INSTANCE;
                String var6 = var10000.getUSER();
                Intrinsics.checkExpressionValueIsNotNull(var6, "PrefKeys.INSTANCE.user");
                String key$iv = var6;
                Object obj$iv = it.toObject(User.class);
                if (CommonsKt.isNull(obj$iv)) {
                    var2.clearPrefKey(key$iv);
                } else {
                    var2.setPref(key$iv, (new Gson()).toJson(obj$iv));
                }

                EditProfileFragment.this.updateOnCometChat();
            }
        })).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
            public final void onFailure(@NotNull Exception it) {
                new Commons(getContext()).hideProgress();
                String var10000 = it.getLocalizedMessage();
                CommonsKt.showToast(var10000);
            }
        }));
    }

    private final void updateOnCometChat() {
        ChatUser var10000 = this.cometUser;
        if (var10000 != null) {
            var10000.setAvatar(this.profilePic);
        }

        var10000 = this.cometUser;
        if (var10000 != null) {
            var10000.setEmail(null);
        }

        var10000 = this.cometUser;
        if (var10000 != null) {
            var10000.setName(this.getUser().getName());
        }

        var10000 = this.cometUser;
        if (var10000 != null) {
            var10000.setUid(this.getUser().getUid());
        }

        ApiInterface var6 = ApiKt.apiHitter();
        String api_key = this.getResources().getString(R.string.comet_api_key);
        String app_id = this.getResources().getString(R.string.comet_app_id);
        ChatUser var10005 = this.cometUser;
        String var5 = var10005 != null ? var10005.getUid() : null;

        ChatUser var10006 = this.cometUser;

        ChatUser var1 = var10006;
        String var2 = var5;
        var6.updateUserOnCometChat("application/json", "application/json", app_id, api_key, var2, var10006).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UpdateResponse>() {
            @Override
            public void accept(UpdateResponse updateResponse) throws Exception {
                Preferences var2 = Preferences.INSTANCE;
                var2.setPref(PrefKeys.COMETUSER, new Gson().toJson(getCometUser()));
                Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
           getActivity().finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                new Commons(getContext()).hideProgress();
                Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onAdditionalImagesUpload(@NotNull Uri it, @Nullable ImageView imageView) {
        new Commons(getContext()).hideProgress();
        if (CommonsKt.isNotNull(imageView != null ? imageView.getDrawable() : null)) {
            if (imageView != null) {
                imageView.setImageURI(this.uri);
            }

            if (Intrinsics.areEqual(imageView, (RoundedImageView) view.findViewById(id.image2))) {
                this.images.set(0, it.toString());
            } else if (Intrinsics.areEqual(imageView, (RoundedImageView) view.findViewById(id.image3))) {
                this.images.set(1, it.toString());
            } else if (Intrinsics.areEqual(imageView, (RoundedImageView) view.findViewById(id.image4))) {
                this.images.set(2, it.toString());
            } else if (Intrinsics.areEqual(imageView, (RoundedImageView) view.findViewById(id.image5))) {
                this.images.set(3, it.toString());
            } else if (Intrinsics.areEqual(imageView, (RoundedImageView) view.findViewById(id.image6))) {
                this.images.set(4, it.toString());
            }
        } else {
            RoundedImageView var10000 = (RoundedImageView) view.findViewById(id.image2);
            if (CommonsKt.isNull(var10000.getDrawable())) {
                this.images.add(0, it.toString());
                ((RoundedImageView) view.findViewById(id.image2)).setImageURI(this.uri);
            } else {
                var10000 = (RoundedImageView) view.findViewById(id.image3);
                if (CommonsKt.isNull(var10000.getDrawable())) {
                    this.images.add(1, it.toString());
                    ((RoundedImageView) view.findViewById(id.image3)).setImageURI(this.uri);
                } else {
                    var10000 = (RoundedImageView) view.findViewById(id.image4);
                    if (CommonsKt.isNull(var10000.getDrawable())) {
                        this.images.add(2, it.toString());
                        ((RoundedImageView) view.findViewById(id.image4)).setImageURI(this.uri);
                    } else {
                        var10000 = (RoundedImageView) view.findViewById(id.image5);
                        if (CommonsKt.isNull(var10000.getDrawable())) {
                            this.images.add(3, it.toString());
                            ((RoundedImageView) view.findViewById(id.image5)).setImageURI(this.uri);
                        } else {
                            var10000 = (RoundedImageView) view.findViewById(id.image6);
                            if (CommonsKt.isNull(var10000.getDrawable())) {
                                this.images.add(4, it.toString());
                                ((RoundedImageView) view.findViewById(id.image6)).setImageURI(this.uri);
                            }
                        }
                    }
                }
            }
        }

    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_profile,container,false);
    }

    public void onUploadSuccessful(@NotNull Uri it) {
        new Commons(getContext()).hideProgress();
        String var10001 = it.toString();
        this.profilePic = var10001;
        ((RoundedImageView) view.findViewById(id.image1)).setImageURI(this.uri);
    }

    public void onFailure(@NotNull String message) {
        CommonsKt.showToast(message);
    }

    public void onImageSelected(@NotNull Uri path, @Nullable ImageView view) {
        this.uri = path;
        new Commons(getContext()).showProgress();
        if (Intrinsics.areEqual(view, (RoundedImageView) view.findViewById(id.image1))) {
            this.getProfileImagePresenter().uploadProfilePicture(path);
        } else {
            this.getProfileImagePresenter().uploadAdditionalImages(path, view);
        }

    }

    public void information(@NotNull String info, @NotNull TextView textView) {
        CharSequence var3 = (CharSequence) info;
        boolean var4 = false;
        if (var3.length() > 0) {
            ExtenstionsKt.visible((View) textView);
        }

        textView.setText((CharSequence) info);
    }

    @Nullable
    public final ChatUser getCometUser() {
        return this.cometUser;
    }

    public final void setCometUser(@Nullable ChatUser var1) {
        this.cometUser = var1;
    }

    public final int getScrollX() {
        return this.scrollX;
    }

    public final void setScrollX(int var1) {
        this.scrollX = var1;
    }

    public final int getScrollY() {
        return this.scrollY;
    }

    public final void setScrollY(int var1) {
        this.scrollY = var1;
    }

    @NotNull
    public final SelectImageUtils getSelectImageUtils() {
        return new SelectImageUtils(getContext());
    }

    @NotNull
    public final ArrayList getImages() {
        return this.images;
    }

    public final void setImages(@NotNull ArrayList var1) {
        this.images = var1;
    }

    @NotNull
    public final String getProfilePic() {
        return this.profilePic;
    }

    public final void setProfilePic(@NotNull String var1) {
        this.profilePic = var1;
    }

    @Nullable
    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(@Nullable Uri var1) {
        this.uri = var1;
    }

    @NotNull
    public final ProfileImagePresenter getProfileImagePresenter() {
        return new ProfileImagePresenter(this);
    }

    @NotNull
    public final ProfileUpdatePresenter getProfileUpdatePresenter() {

        return new ProfileUpdatePresenter(this);
    }

    View view;
SelectImageUtils imageUtils;
String profileImage;
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        this.getRetainInstance();
        imageUtils=new SelectImageUtils(getActivity());
        imageUtils.selectImage(this);
        Preferences var3 = Preferences.INSTANCE;
        String key$iv = "comet_user";
        Gson var10000 = new Gson();
        String val = var3.getPrefs().getString(PrefKeys.COMETUSER, "");
        cometUser = var10000.fromJson(val, ChatUser.class);
        nationalityDialog=new CountryPickerDialog(getContext());
        originDialog = new CountryPickerDialog(getContext());
        this.setUserDataInProfile();
        this.selectOnImageClickListener();
        this.setOnClick();
        this.setOnRadioCheckListener();
        this.setValues();
        ((Switch)view.findViewById(id.editProfile_private)).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    private final void setValues() {
        CharSequence var1 = (CharSequence) this.getUser().getAboutFamily();
        TextView var10000;
        if (var1 != null && var1.length() != 0) {
            var10000 = (TextView) view.findViewById(id.edit_about_family);
            ExtenstionsKt.visible((View) var10000);
        }

        var1 = (CharSequence) this.getUser().getAboutMe();
        if (var1 != null && var1.length() != 0) {
            var10000 = (TextView) view.findViewById(id.edit_about_me);
            ExtenstionsKt.visible((View) var10000);
        }

        var1 = (CharSequence) this.getUser().getProfileHighlight();
        if (var1 != null && var1.length() != 0) {
            var10000 = (TextView) view.findViewById(id.edit_profile_highlight);
            ExtenstionsKt.visible((View) var10000);
        }

        var1 = (CharSequence) this.getUser().getLookingFor();
        if (var1 != null && var1.length() != 0) {
            var10000 = (TextView) view.findViewById(id.looking_for);
            ExtenstionsKt.visible((View) var10000);
        }

        var10000 = (TextView) view.findViewById(id.edit_complexion);
        var10000.setText((CharSequence) PrefDataKt.getColorComplexion());
        var10000 = (TextView) view.findViewById(id.edit_education);
        var10000.setText((CharSequence) PrefDataKt.getEducation());
        var10000 = (TextView) view.findViewById(id.edit_eyes_color);
        var10000.setText((CharSequence) PrefDataKt.getEyeColor());
        var10000 = (TextView) view.findViewById(id.edit_hair_color);
        var10000.setText((CharSequence) PrefDataKt.getHairColor());
        var10000 = (TextView) view.findViewById(id.edit_languages);
        var10000.setText((CharSequence) PrefDataKt.getLanguage());
    }

    private final void selectOnImageClickListener() {
        ((RoundedImageView) view.findViewById(id.image1)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image1);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
        ((RoundedImageView) view.findViewById(id.image2)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image2);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
        ((RoundedImageView) view.findViewById(id.image3)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {

                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image3);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
        ((RoundedImageView) view.findViewById(id.image4)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image4);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
        ((RoundedImageView) view.findViewById(id.image5)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image5);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
        ((RoundedImageView) view.findViewById(id.image6)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RoundedImageView var10002 = (RoundedImageView) view.findViewById(id.image6);
                imageUtils.openChooser(EditProfileFragment.this, (ImageView) var10002);
            }
        }));
    }

    private final void setUserDataInProfile() {
        ((EditText) view.findViewById(id.edit_username)).setText((CharSequence) this.getUser().getName());
        TextView var10000 = (TextView) view.findViewById(id.edit_profile_highlight);
        var10000.setText((CharSequence) this.getUser().getProfileHighlight());
        var10000 = (TextView) view.findViewById(id.edit_about_family);
        var10000.setText((CharSequence) this.getUser().getAboutFamily());
        var10000 = (TextView) view.findViewById(id.looking_for);
        var10000.setText((CharSequence) this.getUser().getLookingFor());
        var10000 = (TextView) view.findViewById(id.edit_about_me);
        var10000.setText((CharSequence) this.getUser().getAboutMe());
        var10000 = (TextView) view.findViewById(id.edit_cast);
        var10000.setText((CharSequence) this.getUser().getSect());
        var10000 = (TextView) view.findViewById(id.edit_complexion);
        var10000.setText((CharSequence) this.getUser().getColorComplexion());
        var10000 = (TextView) view.findViewById(id.edit_build);
        var10000.setText((CharSequence) this.getUser().getBuild());
        var10000 = (TextView) view.findViewById(id.looking_for);
        var10000.setText((CharSequence) this.getUser().getLookingFor());
        var10000 = (TextView) view.findViewById(id.edit_education);
        var10000.setText((CharSequence) this.getUser().getEducation());
        var10000 = (TextView) view.findViewById(id.edit_eyes_color);
        var10000.setText((CharSequence) this.getUser().getEyeColour());
        var10000 = (TextView) view.findViewById(id.edit_hair_color);
        var10000.setText((CharSequence) this.getUser().getHairColour());
        var10000 = (TextView) view.findViewById(id.edit_height);
        var10000.setText((CharSequence) this.getUser().getHeight());
        var10000 = (TextView) view.findViewById(id.edit_lifeaftermarriage);
        var10000.setText((CharSequence) this.getUser().getLivingArrangmentAfterMarriage());
        var10000 = (TextView) view.findViewById(id.edit_nationality);
        var10000.setText((CharSequence) this.getUser().getNationality());
        var10000 = (TextView) view.findViewById(id.edit_origin);
        var10000.setText((CharSequence) this.getUser().getOrigin());
        var10000 = (TextView) view.findViewById(id.edit_living_arrangement);
        var10000.setText((CharSequence) this.getUser().getCurrentLivingArrangment());
        var10000 = (TextView) view.findViewById(id.edit_marital_status);
        var10000.setText((CharSequence) this.getUser().getMaritalStatus());
        var10000 = (TextView) view.findViewById(id.edit_marriage_plan);
        var10000.setText((CharSequence) this.getUser().getMarriagePlans());
        var10000 = (TextView) view.findViewById(id.edit_dob);
        var10000.setText((CharSequence) CommonsKt.getDateInformat(this.getUser().getDob()));
        ((EditText) view.findViewById(id.edit_profession)).setText((CharSequence) this.getUser().getProfession());
        RadioButton var9;
        if (Intrinsics.areEqual(this.getUser().getHalal(), Halal.INSTANCE.getNEVER())) {
            var9 = (RadioButton) view.findViewById(id.halal_never);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getHalal(), Halal.INSTANCE.getSOMETIMES())) {
            var9 = (RadioButton) view.findViewById(id.halal_sometimes);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getHalal(), Halal.INSTANCE.getALWAYS())) {
            var9 = (RadioButton) view.findViewById(id.halal_always);
            var9.setChecked(true);
        }

        if (Intrinsics.areEqual(this.getUser().getBeard(), Beard.INSTANCE.getNO())) {
            var9 = (RadioButton) view.findViewById(id.beard_no);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getBeard(), Beard.INSTANCE.getYES())) {
            var9 = (RadioButton) view.findViewById(id.beard_yes);
            var9.setChecked(true);
        }

        if (Intrinsics.areEqual(this.getUser().getCompletedQuran(), CompletedQuran.INSTANCE.getNO())) {
            var9 = (RadioButton) view.findViewById(id.quran_no);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getCompletedQuran(), CompletedQuran.INSTANCE.getYES())) {
            var9 = (RadioButton) view.findViewById(id.quran_yes);
            var9.setChecked(true);
        }

        if (Intrinsics.areEqual(this.getUser().getRevert(), Revert.INSTANCE.getNO())) {
            var9 = (RadioButton) view.findViewById(id.revert_no);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getRevert(), Revert.INSTANCE.getYES())) {
            var9 = (RadioButton) view.findViewById(id.revert_yes);
            var9.setChecked(true);
        }

        if (Intrinsics.areEqual(this.getUser().getWear(), Wear.INSTANCE.getNAQAB())) {
            var9 = (RadioButton) view.findViewById(id.naqab);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getWear(), Wear.INSTANCE.getHIJAB())) {
            var9 = (RadioButton) view.findViewById(id.hijab);
            var9.setChecked(true);
        }

        if (Intrinsics.areEqual(this.getUser().getPrays(), Prays.INSTANCE.getSOMETIMES())) {
            var9 = (RadioButton) view.findViewById(id.prays_sometimes);
            var9.setChecked(true);
        } else if (Intrinsics.areEqual(this.getUser().getPrays(), Prays.INSTANCE.getALWAYS())) {
            var9 = (RadioButton) view.findViewById(id.prays_always);
            var9.setChecked(true);
        }

        profilePic = this.getUser().getProfilePic();
        if (Intrinsics.areEqual(this.profilePic, "") ^ true) {
            Picasso.get().load(this.getUser().getProfilePic()).into((RoundedImageView) view.findViewById(id.image1));
        }

        String var1 = this.getUser().getAge();
        if(!var1.isEmpty()) {
            int var4 = Integer.parseInt(var1);
            this.age = var4;

        }
        var10000 = (TextView) view.findViewById(id.edit_languages);
        List var10001 = this.getUser().getLanguages();
        var10000.setText((CharSequence) (var10001 != null ? joinToString((ArrayList)var10001): null));

        try {
            if (CommonsKt.isNotNull(this.getUser().getImages())) {
                int i = 0;
                ArrayList var10 = this.getUser().getImages();

                for (int user = var10.size(); i < user; ++i) {
                    ArrayList var8;
                    RoundedImageView var11;
                    switch (i) {
                        case 0:
                            var11 = (RoundedImageView) view.findViewById(id.image2);
                            var8 = this.getUser().getImages();

                            var11.setImageURI(Uri.parse((String) var8.get(i)));
                            break;
                        case 1:
                            var11 = (RoundedImageView) view.findViewById(id.image3);
                            var8 = this.getUser().getImages();

                            var11.setImageURI(Uri.parse((String) var8.get(i)));
                            break;
                        case 2:
                            var11 = (RoundedImageView) view.findViewById(id.image4);
                            var8 = this.getUser().getImages();

                            var11.setImageURI(Uri.parse((String) var8.get(i)));
                            break;
                        case 3:
                            var11 = (RoundedImageView) view.findViewById(id.image5);
                            var8 = this.getUser().getImages();

                            var11.setImageURI(Uri.parse((String) var8.get(i)));
                            break;
                        case 4:
                            var11 = (RoundedImageView) view.findViewById(id.image6);
                            var8 = this.getUser().getImages();

                            var11.setImageURI(Uri.parse((String) var8.get(i)));
                    }
                }
            }
        } catch (Exception var5) {
        }

        Log.e("private", String.valueOf(this.getUser().getPrivate()));
        Switch var12 = (Switch) view.findViewById(id.editProfile_private);
        var12.setChecked(this.getUser().getPrivate());
    }

    private final void setOnRadioCheckListener() {
        ((SeekBar) view.findViewById(id.height_bar)).setOnSeekBarChangeListener((OnSeekBarChangeListener) (new OnSeekBarChangeListener() {
            public void onProgressChanged(@Nullable SeekBar seekBar, int progress, boolean fromUser) {
                TextView var10000 = (TextView) view.findViewById(id.edit_height);
                var10000.setText((CharSequence) (progress + " cm"));
            }

            public void onStartTrackingTouch(@Nullable SeekBar seekBar) {
                TextView var10000 = (TextView) view.findViewById(id.edit_height);
                var10000.setText((CharSequence) ((seekBar != null ? seekBar.getProgress() : null) + " cm"));
            }

            public void onStopTrackingTouch(@Nullable SeekBar seekBar) {
            }
        }));
        ((RadioGroup) view.findViewById(id.build_bar)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup $noName_0, int checkedId) {
                TextView var10000;
                switch (checkedId) {
                    case 1000114:
                        var10000 = (TextView) view.findViewById(id.edit_build);
                        var10000.setText((CharSequence) Weight.INSTANCE.getMEDIUM());
                        break;
                    case 1000132:
                        var10000 = (TextView)view.findViewById(id.edit_build);
                        var10000.setText((CharSequence) Weight.INSTANCE.getATHLETIC());
                        break;
                    case 1000135:
                        var10000 = (TextView) view.findViewById(id.edit_build);
                        var10000.setText((CharSequence) Weight.INSTANCE.getSLIM());
                        break;
                    case 1000168:
                        var10000 = (TextView) view.findViewById(id.edit_build);
                        var10000.setText((CharSequence) Weight.INSTANCE.getLARGE());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.life_after_marriage)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView var10000;
                switch (checkedId) {
                    case 1000350:
                        var10000 = (TextView) view.findViewById(id.edit_lifeaftermarriage);
                        var10000.setText((CharSequence) LifeAfterMarriage.INSTANCE.getSTAYWITHPARENTS());
                        break;
                    case 1000381:
                        var10000 = (TextView) view.findViewById(id.edit_lifeaftermarriage);
                        var10000.setText((CharSequence) LifeAfterMarriage.INSTANCE.getLIVESEPARATE());
                        break;
                    case 1000753:
                        var10000 = (TextView)view.findViewById(id.edit_lifeaftermarriage);
                        var10000.setText((CharSequence) LifeAfterMarriage.INSTANCE.getWILLINGTORELOCATE());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.relationship)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView var10000;
                switch (checkedId) {
                    case 1000242:
                        var10000 = (TextView) view.findViewById(id.edit_marital_status);
                        var10000.setText((CharSequence) MaritalStatus.INSTANCE.getSINGLE());
                        break;
                    case 1000396:
                        var10000 = (TextView) view.findViewById(id.edit_marital_status);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_marital_status");
                        var10000.setText((CharSequence) MaritalStatus.INSTANCE.getWIDOWED());
                        break;
                    case 1000621:
                        var10000 = (TextView) view.findViewById(id.edit_marital_status);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_marital_status");
                        var10000.setText((CharSequence) MaritalStatus.INSTANCE.getDIVORCED());
                        break;
                    case 1000622:
                        var10000 = (TextView) view.findViewById(id.edit_marital_status);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_marital_status");
                        var10000.setText((CharSequence) MaritalStatus.INSTANCE.getANNULLED());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.caste_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView var10000;
                switch (checkedId) {
                    case 1000263:
                        var10000 = (TextView) view.findViewById(id.edit_cast);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_cast");
                        var10000.setText((CharSequence) Sect.INSTANCE.getOTHER());
                        break;
                    case 1000307:
                        var10000 = (TextView) view.findViewById(id.edit_cast);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_cast");
                        var10000.setText((CharSequence) Sect.INSTANCE.getSHIA());
                        break;
                    case 1000387:
                        var10000 = (TextView) view.findViewById(id.edit_cast);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_cast");
                        var10000.setText((CharSequence) Sect.INSTANCE.getSUNNI());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.living_arrangement_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView var10000;
                switch (checkedId) {
                    case 1000288:
                        var10000 = (TextView) view.findViewById(id.edit_living_arrangement);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_living_arrangement");
                        var10000.setText((CharSequence) LivingArrangement.INSTANCE.getLIVINGWITHFRIENDS());
                        break;
                    case 1000508:
                        var10000 = (TextView) view.findViewById(id.edit_living_arrangement);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_living_arrangement");
                        var10000.setText((CharSequence) LivingArrangement.INSTANCE.getLIVINGALONE());
                        break;
                    case 1000515:
                        var10000 = (TextView) view.findViewById(id.edit_living_arrangement);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_living_arrangement");
                        var10000.setText((CharSequence) LivingArrangement.INSTANCE.getLIVINGWITHFAMILY());
                        break;
                    case 1000733:
                        var10000 = (TextView) view.findViewById(id.edit_living_arrangement);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "edit_living_arrangement");
                        var10000.setText((CharSequence) LivingArrangement.INSTANCE.getOTHER());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.wear_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                EditProfileFragment.this.setWear(checkedId == 1000326 ? Wear.INSTANCE.getNAQAB() : Wear.INSTANCE.getHIJAB());
            }
        }));
        ((RadioGroup) view.findViewById(id.halal_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case 1000002:
                        EditProfileFragment.this.setHalal(Halal.INSTANCE.getSOMETIMES());
                        break;
                    case 1000056:
                        EditProfileFragment.this.setHalal(Halal.INSTANCE.getALWAYS());
                        break;
                    case 1000212:
                        EditProfileFragment.this.setHalal(Halal.INSTANCE.getNEVER());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.revert_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 1000310) {
                    EditProfileFragment.this.setRevert(Revert.INSTANCE.getYES());
                } else if (checkedId == 1000245) {
                    EditProfileFragment.this.setRevert(Revert.INSTANCE.getNO());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.beard_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 1000614) {
                    EditProfileFragment.this.setBeard(Beard.INSTANCE.getNO());
                } else if (checkedId == 1000398) {
                    EditProfileFragment.this.setBeard(Beard.INSTANCE.getYES());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.prays_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 1000008) {
                    EditProfileFragment.this.setPrays(Prays.INSTANCE.getALWAYS());
                } else if (checkedId == 1000756) {
                    EditProfileFragment.this.setPrays(Prays.INSTANCE.getSOMETIMES());
                }

            }
        }));
        ((RadioGroup) view.findViewById(id.quran_radio_group)).setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) (new android.widget.RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup group, int checkedId) {
                EditProfileFragment.this.setCompletedQuran(checkedId == 1000243 ? CompletedQuran.INSTANCE.getNO() : CompletedQuran.INSTANCE.getYES());
            }
        }));
    }

    private final void setOnClick() {
        ((ImageView) view.findViewById(id.back)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                getActivity().finish();
            }
        }));
        ((LinearLayout) view.findViewById(id.aboutMe)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                TextView var10001 = (TextView) view.findViewById(id.edit_about_me);
                var10000.openEditDialog(var10001, "About Me");
            }
        }));
        ((LinearLayout) view.findViewById(id.profileHighlight)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                TextView var10001 = (TextView) view.findViewById(id.edit_profile_highlight);
                var10000.openEditDialog(var10001, "Profile Highlight");
            }
        }));
        ((LinearLayout) view.findViewById(id.aboutMyFamily)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                TextView var10001 = (TextView) view.findViewById(id.edit_about_family);
                var10000.openEditDialog(var10001, "About Family");
            }
        }));
        ((LinearLayout) view.findViewById(id.aboutImLookingFor)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                TextView var10001 = (TextView) view.findViewById(id.looking_for);
                var10000.openEditDialog(var10001, "Looking For");
            }
        }));
        ((TextView) view.findViewById(id.edit_nationality)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment.this.getNationalityFromPicker();
            }
        }));
        ((TextView) view.findViewById(id.edit_living_arrangement)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.living_arrangement_group);
                Intrinsics.checkExpressionValueIsNotNull(var10000, "living_arrangement_group");
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.living_arrangement_group);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.living_arrangement_group);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_dob)).setOnClickListener((OnClickListener) (new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public final void onClick(View it) {
                EditProfileFragment.this.selectDateFromDatePicker();
            }
        }));
        ((TextView) view.findViewById(id.edit_profileDone)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment.this.updateProfileData();
            }
        }));
        ((TextView) view.findViewById(id.edit_cast)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.caste_group);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.caste_group);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.caste_group);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_build)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.build_bar);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.build_bar);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.build_bar);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_marital_status)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.relationship);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.relationship);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.relationship);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_height)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                SeekBar var10000 = (SeekBar) view.findViewById(id.height_bar);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (SeekBar) view.findViewById(id.height_bar);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (SeekBar) view.findViewById(id.height_bar);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_lifeaftermarriage)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.life_after_marriage);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.life_after_marriage);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.life_after_marriage);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_marriage_plan)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                RadioGroup var10000 = (RadioGroup) view.findViewById(id.marriage_plan);
                if (var10000.getVisibility() == View.VISIBLE) {
                    var10000 = (RadioGroup) view.findViewById(id.marriage_plan);
                    ExtenstionsKt.gone((View) var10000);
                } else {
                    var10000 = (RadioGroup) view.findViewById(id.marriage_plan);
                    ExtenstionsKt.visible((View) var10000);
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_languages)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment.this.openLanguageDialog();
            }
        }));
        ((TextView) view.findViewById(id.edit_education)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                String var10001 = ProfileItem.INSTANCE.getEDUCATION();
                TextView var10002 = (TextView) view.findViewById(id.edit_education);
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((EditText) view.findViewById(id.edit_profession)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                String var10001 = ProfileItem.INSTANCE.getPROFESSION();
                EditText var10002 = (EditText) view.findViewById(id.edit_profession);
                var10000.openDialog(var10001, (TextView) var10002);
            }
        }));
        ((TextView) view.findViewById(id.edit_origin)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                CountryPickerDialog var10000 = EditProfileFragment.this.getOriginDialog();
                if (var10000 != null) {
                    var10000.show();
                }

                var10000 = EditProfileFragment.this.getOriginDialog();
                if (var10000 != null) {
                    var10000.setOnSelectedListener((OnSelectedListener) (new OnSelectedListener() {
                        public final void onSelected(@NotNull Country it) {
                            TextView var10000 = (TextView) view.findViewById(id.edit_origin);
                            Context var10002 = EditProfileFragment.this.getContext();
                            if (var10002 == null) {
                                Intrinsics.throwNpe();
                            }

                            var10000.setText((CharSequence) it.getName(var10002));
                        }
                    }));
                }

            }
        }));
        ((TextView) view.findViewById(id.edit_eyes_color)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                String var10001 = ProfileItem.INSTANCE.getEYECOLOR();
                TextView var10002 = (TextView) view.findViewById(id.edit_eyes_color);
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.edit_hair_color)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                String var10001 = ProfileItem.INSTANCE.getHAIRCOLOR();
                TextView var10002 = (TextView) view.findViewById(id.edit_hair_color);
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.edit_complexion)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditProfileFragment var10000 = EditProfileFragment.this;
                String var10001 = ProfileItem.INSTANCE.getCOLORCOMPLEXION();
                TextView var10002 = (TextView) view.findViewById(id.edit_complexion);
                var10000.openDialog(var10001, var10002);
            }
        }));
    }

    private final void openEditDialog(TextView textView, String s) {
        Bundle bundle = new Bundle();
        bundle.putString("edit_type", s);
        EditAboutDialog var5 = new EditAboutDialog();
        boolean var6 = false;
        boolean user = false;
        var5.infoSelectListener((OnEditInformation) this, textView);
        var5.setArguments(bundle);
        var5.show(this.getChildFragmentManager(), "Language");
    }

    private final void getNationalityFromPicker() {
        CountryPickerDialog var10000 = this.nationalityDialog;
        if (var10000 != null) {
            var10000.show();
        }

        var10000 = this.nationalityDialog;
        if (var10000 != null) {
            var10000.setOnSelectedListener((OnSelectedListener) (new OnSelectedListener() {
                public final void onSelected(@NotNull Country it) {
                    TextView var10000 = (TextView) view.findViewById(id.edit_nationality);
                    CharSequence var2 = (CharSequence) var10000.getText().toString();
                    boolean var3 = false;
                    StringBuilder var10001;
                    TextView var10002;
                    Context var10003;
                    if (var2.length() == 0) {
                        var10000 = (TextView) view.findViewById(id.edit_nationality);
                        var10001 = new StringBuilder();
                        var10002 = (TextView) view.findViewById(id.edit_nationality);
                        var10001 = var10001.append(var10002.getText().toString()).append(" ");
                        var10003 = EditProfileFragment.this.getContext();

                        var10000.setText((CharSequence) var10001.append(it.getName(var10003)).toString());
                    } else {
                        var10000 = (TextView) view.findViewById(id.edit_nationality);
                        var10001 = new StringBuilder();
                        var10002 = (TextView) view.findViewById(id.edit_nationality);
                        var10001 = var10001.append(var10002.getText().toString()).append(" , ");
                        var10003 = EditProfileFragment.this.getContext();

                        var10000.setText((CharSequence) var10001.append(it.getName(var10003)).toString());
                    }

                }
            }));
        }

    }

    private final void openLanguageDialog() {
        LanguageDialog var2 = new LanguageDialog();
        boolean var3 = false;
        boolean var4 = false;
        var2.languageSelectListener((OnLanguageSelect) this);
        var2.show(this.getChildFragmentManager(), "Language");
    }

    private final void updateProfileData() {
        user=getUser();
        TextView var10000 = (TextView) view.findViewById(id.edit_about_me);
        String about_me = var10000.getText().toString();
        var10000 = (TextView) view.findViewById(id.edit_about_family);
        String about_family = var10000.getText().toString();
        EditText var6 = (EditText) view.findViewById(id.edit_username);
        String userName = var6.getText().toString();
        var10000 = (TextView) view.findViewById(id.edit_profile_highlight);
        String highlight = var10000.getText().toString();
        user.setAboutMe(about_me);
        user.setAboutFamily(about_family);
        user.setBeard(this.beard);
       user.setAge(String.valueOf(this.age));
       // User user = this.getUser();
        TextView var10001 = (TextView) view.findViewById(id.edit_build);
        user.setBuild(var10001.getText().toString());
       user.setCompletedQuran(this.completedQuran);
        var10001 = (TextView) view.findViewById(id.edit_complexion);
        user.setColorComplexion(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_living_arrangement);
        user.setCurrentLivingArrangment(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_education);
        user.setEducation(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_eyes_color);
        user.setEyeColour(var10001.getText().toString());
       user.setWear(this.wear);
       user.setHalal(this.halal);
       user.setLanguages((List) this.languages);
        var10001 = (TextView) view.findViewById(id.edit_hair_color);
        user.setHairColour(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_height);
        user.setHeight(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_lifeaftermarriage);
        user.setLivingArrangmentAfterMarriage(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_marital_status);
        user.setMaritalStatus(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_marriage_plan);
        user.setMarriagePlans(var10001.getText().toString());
       user.setName(userName);
        var10001 = (TextView) view.findViewById(id.edit_nationality);
        user.setNationality(var10001.getText().toString());
        var10001 = (TextView) view.findViewById(id.edit_origin);
        user.setOrigin(var10001.getText().toString());
       user.setProfilePic(this.profilePic);
        user.setPrays(this.prays);
        EditText var5 = (EditText) view.findViewById(id.edit_profession);
        user.setProfession(var5.getText().toString());
       user.setRevert(this.revert);
        var10001 = (TextView) view.findViewById(id.edit_cast);
        user.setSect(var10001.getText().toString());
       user.setImages(this.images);
       user.setProfileHighlight(highlight);
        var10001 = (TextView) view.findViewById(id.looking_for);
        user.setLookingFor(var10001.getText().toString());
        new Commons(getContext()).showProgress();
        getProfileUpdatePresenter().updateProfileOnDatabase(this.getUser());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private  void selectDateFromDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(5);
        int month = calendar.get(2);
        int year = calendar.get(1);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
        Context var10002 = this.getContext();
        if (var10002 == null) {
            Intrinsics.throwNpe();
        }
        datePickerDialog.getDatePicker().init(year, month, date, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TextView var10000 = (TextView) view.findViewById(id.edit_dob);
                var10000.setText((CharSequence) CommonsKt.getDateInformat(CommonsKt.formatDate("" + year + '-' + (month + 1) + '-' + dayOfMonth)));
                EditProfileFragment.this.setDob(CommonsKt.formatDate("" + year + '-' + (month + 1) + '-' + dayOfMonth));
                EditProfileFragment.this.setAge(CommonsKt.getAge(year, month + 1, dayOfMonth));
                if (EditProfileFragment.this.getAge() < 18) {
                    EditProfileFragment.this.selectDateFromDatePicker();
                }

            }
        });
        datePickerDialog.show();
    }

    private final void openDialog(String item, TextView textBox) {
        Bundle bundle = new Bundle();
        bundle.putString("item_name", item);
        bundle.putString("item_type", "single");
        ProfileItemFragment var5 = new ProfileItemFragment();
        boolean var6 = false;
        boolean user = false;
        var5.infoSelectListener((EditInformation) this, textBox);
        var5.setArguments(bundle);
        var5.show(this.getChildFragmentManager(), "p");
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       imageUtils.onActivityResult(requestCode, resultCode, data);
    }

    public void onPause() {
        ScrollView var10001 = (ScrollView) view.findViewById(id.scroll);
        this.scrollX = var10001.getScrollX();
        var10001 = (ScrollView) view.findViewById(id.scroll);
        this.scrollY = var10001.getScrollY();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.setValues();
        ((ScrollView) view.findViewById(id.scroll)).scrollTo(this.scrollX, this.scrollY);
    }

    public EditProfileFragment() {
        super();
        this.wear = Wear.INSTANCE.getHIJAB();
        this.prays = Prays.INSTANCE.getALWAYS();
        this.beard = Beard.INSTANCE.getYES();
        this.halal = Halal.INSTANCE.getALWAYS();
        this.revert = Revert.INSTANCE.getYES();
        this.completedQuran = CompletedQuran.INSTANCE.getYES();
        boolean var1 = false;
        ArrayList var3 = new ArrayList();
        this.languages = var3;
    }

    public String joinToString(ArrayList arrayList) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < arrayList.size(); i++) {
            builder.append(arrayList).append(",");
        }
        String strList = builder.toString();
        if (strList.length() > 0)
            strList = strList.substring(0, strList.length() - 1);
        return strList;
    }


}
