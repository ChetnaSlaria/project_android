package com.sachtech.datingapp.ui.login.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.cometchat.pro.models.User;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.gson.Gson;
import com.mgazetti.android.util.FacebookCallbackListener;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.cometChat.CometChatAuth;
import com.sachtech.datingapp.cometChat.listener.OnCometLogin;
import com.sachtech.datingapp.data.FacebookUser;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.Facebook;
import com.sachtech.datingapp.networking.Google;
import com.sachtech.datingapp.networking.GoogleCallbackListener;
import com.sachtech.datingapp.presenter.SignInPresenter;
import com.sachtech.datingapp.ui.home.HomeActivity;
import com.sachtech.datingapp.utils.CommonUtil;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.IntentString;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.SignInView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;


public final class SignInFragment extends Fragment implements SignInView, FacebookCallbackListener, GoogleCallbackListener, OnCometLogin {
    @NotNull
    public final SignInPresenter getSignInPresenter() {
        return new SignInPresenter(this);
    }


    @NotNull
    public final Facebook getFacebook() {
        return new Facebook((Fragment) SignInFragment.this, (FacebookCallbackListener) SignInFragment.this);
    }


    // $FF: synthetic method
    @NotNull
    public final CometChatAuth getCometAuth() {
        CometChatAuth var10000 = new CometChatAuth(getContext());
        Context var10002 = SignInFragment.this.getContext();
        return var10000;
    }


    public void onFbImageDownload(@NotNull InputStream byteStream, @NotNull FacebookUser fbUser) {
        this.creteFbImageFile(byteStream, fbUser);
    }

    @SuppressLint("CheckResult")
    private void creteFbImageFile(final InputStream byteStream, final FacebookUser fbUser) {
        final ObjectRef theFile = new ObjectRef();
        File var10001 = this.createFile(".png");

        theFile.element = var10001;

        Observable.fromCallable((Callable) () -> {
            writeFile(byteStream, var10001.getAbsolutePath());
            return null;
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                fbUser.setProfilePic(((File) theFile.element).getAbsolutePath());
                PrefKeys var10000 = PrefKeys.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(var10000, "PrefKeys.INSTANCE");
                Preferences.INSTANCE.setPref(var10000.getFACEBOOKUSER(), new Gson().toJson(fbUser));
                Log.e("fbuser", fbUser.toString());
                SignInPresenter var2 = SignInFragment.this.getSignInPresenter();
                String var10001 = fbUser.getAccessToken();
                if (var10001 == null) {
                    Intrinsics.throwNpe();
                }

                AuthCredential var3 = FacebookAuthProvider.getCredential(var10001);
                Intrinsics.checkExpressionValueIsNotNull(var3, "FacebookAuthProvider.get…ial(fbUser.accessToken!!)");
                var2.signInWithCredentials(var3);
            }
        });
    }

    private final File createFile(String ext) {
        FragmentActivity var10000 = this.getActivity();

        File mediaStorageDir = var10000.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            return null;
        } else {
            File file = new File(mediaStorageDir.getPath() + File.separator + System.currentTimeMillis() + ext);
            return file;
        }
    }

    public final void writeFile(@Nullable InputStream inputStream, @NotNull String absolutePath) {
        InputStream in = (InputStream) null;
        OutputStream out = (OutputStream) null;
        boolean var15 = false;

        label143:
        {
            try {
                var15 = true;
                in = inputStream;
                File outFile = new File(absolutePath);
                out = (OutputStream) (new FileOutputStream(outFile));
                if (inputStream == null) {
                    Intrinsics.throwNpe();
                }

                this.copyFile(inputStream, out);
                var15 = false;
                break label143;
            } catch (IOException var22) {
                var15 = false;
            } finally {
                if (var15) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException var17) {
                        }
                    }

                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException var16) {
                        }
                    }

                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException var19) {
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException var18) {
                }

                return;
            }

            return;
        }

        try {
            in.close();
        } catch (IOException var21) {
        }

        try {
            out.close();
        } catch (IOException var20) {
        }

    }

    // $FF: synthetic method
    public static void writeFile$default(SignInFragment var0, InputStream var1, String var2, int var3, Object var4) {
        if ((var3 & 1) != 0) {
            var1 = (InputStream) null;
        }

        var0.writeFile(var1, var2);
    }

    private final void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read = 0;
        boolean linehasData = true;

        while (linehasData) {
            read = in.read(buffer);
            if (read != -1) {
                out.write(buffer, 0, read);
            } else {
                linehasData = false;
            }
        }

    }


    public void onCometLoginFailure(@NotNull String localizedMessage) {
        new Commons(getContext()).hideProgress();

        CommonsKt.showToast("Sign In Failed");
    }

    public void onCometLoginSuccess(@Nullable User p0) {
        new Commons(getContext()).hideProgress();

        PrefKeys var10000 = PrefKeys.INSTANCE;
        Preferences.INSTANCE.setPref(var10000.getPASSWORD(), this.password.getText().toString());
        Intrinsics.checkExpressionValueIsNotNull(var10000, "PrefKeys.INSTANCE");
        Preferences.INSTANCE.setPref(var10000.getEMAIL(), this.email.getText().toString());
        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finishAffinity();

    }

    public void onFailure(@NotNull String message) {
        new Commons(getContext()).hideProgress();
        CommonsKt.showToast(message);
    }

    public void onGoogleSignInSuccess(@NotNull AuthCredential account) {
        this.getSignInPresenter().signInWithCredentials(account);
    }

    public void onFacebookUserDetails(@NotNull final FacebookUser fbuser) {
        Intrinsics.checkParameterIsNotNull(fbuser, "fbuser");
        Observable.fromCallable((Callable) (new Callable() {
            public final InputStream call() throws IOException {
                URL url = new URL(fbuser.getProfilePic());
                return url.openConnection().getInputStream();
            }
        })).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Consumer) (new Consumer() {
            // $FF: synthetic method
            // $FF: bridge method
            public void accept(Object var1) {
                this.accept((InputStream) var1);
            }

            public final void accept(InputStream it) {
                SignInFragment var10000 = SignInFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                var10000.creteFbImageFile(it, fbuser);
            }
        }));
    }

    public void onUserDetails(@NotNull Task<DocumentSnapshot> it) {
       /* Log.e("data", String.valueOf(it.getResult()));
        Task<DocumentSnapshot> doc= (Task<DocumentSnapshot>) ((DocumentSnapshot) it.getResult()).getData();
        DocumentSnapshot var10000 = (DocumentSnapshot) ((DocumentSnapshot) it.getResult());*/
       if(it.getResult().getData()!=null){
           PrefKeys var3 = PrefKeys.INSTANCE;
           String var4 = var3.getUSER();
           Preferences.INSTANCE.setPref(var4, new Gson().toJson(it.getResult().toObject(com.sachtech.datingapp.data.User.class)));

           CometChatAuth var6 = this.getCometAuth();
           String var7 = PrefDataKt.getUid();

           var6.loginToCometChat(var7, (OnCometLogin) this);
       }
        else {
           new Commons(getContext()).hideProgress();

           BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new ProfileImageFragment(), bundle);
        }

    }

    public void onFacebookLoginSuccess(@Nullable LoginResult result) {
        SignInPresenter var10000 = this.getSignInPresenter();
        AccessToken var10001 = result != null ? result.getAccessToken() : null;

        var10000.getFacebookUserDetails(var10001);
    }

    public void onSignIn(@NotNull AuthResult result) {
        String var2;
        boolean var3;
        String var5;
        String var7;
        String var8;
        FirebaseUser var10001;
        Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getUID(), result.getUser().getUid().toLowerCase());
        Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getEMAIL(), result.getUser().getEmail());
        Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getUSERNAME(), result.getUser().getDisplayName());
        bundle.putString(IntentString.INSTANCE.getEMAIL(), result.getUser().getEmail());
        bundle.putString(IntentString.INSTANCE.getNAME(), result.getUser().getDisplayName());
        SignInPresenter signInPresenter = getSignInPresenter();
        signInPresenter.getUserDetails(result.getUser().getUid().toLowerCase());

    }

    Bundle bundle;
    Button signInFb, signInGoogle, signInBtn;
    EditText email, password;
    TextView emailText, passwordText, signInSignUp, signInForgotPassword;
    Google google;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_sign_in, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        google = new Google(this, this);
        google.init();
        bundle = new Bundle();
        signInFb = view.findViewById(R.id.signInFacebook);
        signInGoogle = view.findViewById(id.signInGoogle);
        email = view.findViewById(id.signInEmail);
        password = view.findViewById(id.signInPassword);
        emailText = view.findViewById(id.email);
        passwordText = view.findViewById(id.password);
        signInSignUp = view.findViewById(id.signInSignUpText);
        signInBtn = view.findViewById(id.signInBtn);
        signInForgotPassword = view.findViewById(id.signInForgotPassword);
        CommonUtil var10000 = CommonUtil.INSTANCE;
        FragmentActivity activity = this.getActivity();
        var10000.hideKeyboard((Activity) activity);
        this.setOnClick();
        //this.setSpan();
        onItemFocusChange();
        signInSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new SignUpFragment(), null);

            }
        });
        signInFb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getFacebook().init();
            }
        });
        signInGoogle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                google.signInWithGoogle();
            }
        });

    }

    private void onItemFocusChange() {
        CommonsKt.onFocusChange(email, (View) emailText, "Email");
        CommonsKt.onFocusChange(password, (View) passwordText, "Password");
    }

    private void setSpan() {
        String span = signInSignUp.getText().toString();
        SpannableString spannableString = new SpannableString((CharSequence) span);

        spannableString.setSpan(new URLSpan("") {
            public void onClick(@NotNull View widget) {
                BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new SignUpFragment(), null);
            }
        }, 17, 24, 33);
        signInSignUp.setText(spannableString);

        signInSignUp.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private final void setOnClick() {
        signInBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signInForgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new ForgotPasswordFragment(), null);
            }
        });
        email.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        password.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
    }

    private void signIn() {

        if (!CommonsKt.validateEmail(email.getText().toString())) {
            CommonsKt.showToast("Enter valid Email");
        } else {
            //  this.showProgress();
            SignInPresenter var1 = this.getSignInPresenter();
            String e = email.getText().toString();
            String p = password.getText().toString();
            new Commons(getContext()).showProgress();

            var1.signIn(e, p);
        }

        if (!CommonsKt.validatePassword(password.getText().toString())) {
            CommonsKt.showToast("Enter valid password");
        }

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == google.getRC_SIGN_IN()) {
            google.onActivityResult(requestCode, resultCode, data);
        } else {
            this.getFacebook().onActivityResult(requestCode, resultCode, data);
        }

    }
}
