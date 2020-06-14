package com.sachtech.datingapp.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.CometChat.CallbackListener;
import com.cometchat.pro.exceptions.CometChatException;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.Facebook;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.networking.Google;
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.ui.login.LoginActivity;
import com.sachtech.datingapp.utils.Constants;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.ProfileUpdateView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

import static org.jitsi.meet.sdk.JitsiMeetActivityDelegate.onBackPressed;


public final class SettingFragment extends Fragment implements ProfileUpdateView {

    @Nullable
    private OnFragmentChange onFragmentChange;

    public void onUpdateProfile() {
        CommonsKt.showToast("Your profile will be hidden to everyone");
    }

    public void onFailure(@NotNull String message) {
    }

    @NotNull
    public final FirebaseHelper getFirebaseHelper() {
     return new FirebaseHelper();
    }

    @NotNull
    public final Facebook getFacebook() {
      return new Facebook(this);
    }

    @NotNull
    public final Google getGoogle() {
       return new Google(this);
    }

    @NotNull
    public final ProfileUpdatePresenter getProfileUpdatePresenter() {
        return new ProfileUpdatePresenter(this);
    }

    @Nullable
    public final User getUser() {
        return new User();
    }

    @Nullable
    public final OnFragmentChange getOnFragmentChange() {
        return this.onFragmentChange;
    }

    public final void setOnFragmentChange(@Nullable OnFragmentChange var1) {
        this.onFragmentChange = var1;
    }

    public void onAttach(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        OnFragmentChange var10000 = this.onFragmentChange;
        if (var10000 != null) {
            var10000.selectedPos(2);
        }

    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings, container, false);
    }

    Preferences preferences;
    ImageView backImage;
    TextView settinPassword, settingEmail, passwordShoHide,signOut;
    Switch hideSwitch,deleteAccount;

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backImage = view.findViewById(id.back);
        settingEmail = view.findViewById(id.setting_email);
        passwordShoHide = view.findViewById(id.password_show_hide);
        settinPassword = view.findViewById(id.setting_password);
        hideSwitch = view.findViewById(id.hideSwitch);
        deleteAccount=view.findViewById(id.deleteAccount);
        signOut=view.findViewById(id.sign_out);
        backImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity()!=null)
                    getActivity().onBackPressed();
            }
        });
        this.getGoogle().init();

        OnFragmentChange var10000 = this.onFragmentChange;
        if (var10000 != null) {
            var10000.selectedPos(4);
        }
        settingEmail.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getEMAIL(), ""));
        settinPassword.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPASSWORD(), ""));
        settinPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordShoHide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordShoHide.getText().toString() == "Show") {
                    passwordShoHide.setText("Hide");
                    settinPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordShoHide.setText("Show");
                    settinPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
        hideSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    User user = getUser();
                    user.setAccountStatus(Constants.INSTANCE.getHIDDEN());
                    getProfileUpdatePresenter().updateProfileOnDatabase(user);

                }
            }
        });
               deleteAccount.setOnCheckedChangeListener((OnCheckedChangeListener) (new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SettingFragment.this.showConfirmationDialog();
                }

            }
        }));
        signOut.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                CometChat.logout((CallbackListener) (new CallbackListener() {
                    public void onSuccess(@NotNull String successMessage) {
                        this.finishActivityAndClearPrefs();
                    }

                    // $FF: synthetic method
                    // $FF: bridge method
                    public void onSuccess(Object var1) {
                        this.onSuccess((String) var1);
                    }

                    private final void finishActivityAndClearPrefs() {
                       if (PrefDataKt.facebookUser() != null) {
                          SettingFragment.this.getFacebook().logout();
                       } else if (PrefDataKt.googleUser() != null) {
                          SettingFragment.this.getGoogle().signOutFromGoogle();
                       }

                       Preferences.INSTANCE.clearPref();
                       Intent intent = new Intent(getActivity(), LoginActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       getActivity().startActivity(intent);
                       getActivity().finish();
                    }

                    public void onError(@NotNull CometChatException e) {
                        CommonsKt.showToast("Error while signing out");
                    }
                }));
            }
        }));
    }

    private final void showConfirmationDialog() {
        ConfirmationDialog dialog = new ConfirmationDialog(getContext());
        dialog.show(getActivity().getSupportFragmentManager(), "confirm");
    }

}
