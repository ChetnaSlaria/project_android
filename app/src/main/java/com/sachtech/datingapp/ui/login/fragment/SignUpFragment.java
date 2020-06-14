package com.sachtech.datingapp.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.AuthResult;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.presenter.SignUpPresenter;
import com.sachtech.datingapp.utils.CommonUtil;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.IntentString;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.SignUpView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class SignUpFragment extends Fragment implements SignUpView {


   public void onFailure(@NotNull String message) {
      new Commons(getContext()).hideProgress();
      CommonsKt.showToast(message);
   }

   public void onSignUpSuccess(@Nullable AuthResult result) {
      new Commons(getContext()).hideProgress();
      if(result!=null)
      {
         Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getPASSWORD(), signUpPassword.getText().toString());
         Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getUID(), result.getUser().getUid().toLowerCase());
         Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getUSERNAME(),signUpName.getText().toString());
         Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getEMAIL(),result.getUser().getEmail());
         bundle.putString(IntentString.INSTANCE.getEMAIL(), result.getUser().getEmail());
        bundle.putString(IntentString.INSTANCE.getNAME(), signUpName.getText().toString());
        BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.login_container,new ProfileImageFragment(),bundle);
      }
   }

   @NotNull
   public final SignUpPresenter getSignUpPresenter() {

      return new SignUpPresenter(this);
   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_sign_up,container,false);
   }
   Bundle bundle;
Button signUpBtn;
   TextView email,password,name,confirmPassword,signUpSignIn;
   EditText signUpEmail,signUpPassword,signUpName,signUpConfirmPassword;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      CommonUtil instance = CommonUtil.INSTANCE;
       instance.hideKeyboard(getActivity());
       bundle=new Bundle();
      //setSpan();
     //
      signUpBtn=view.findViewById(id.signUpBtn);
      email=view.findViewById(id.email_header);
      password=view.findViewById(id.password);
      name=view.findViewById(id.name);
      confirmPassword=view.findViewById(id.confirmPassword);
      signUpEmail=view.findViewById(id.signUpEmail);
      signUpPassword=view.findViewById(id.signUpPassword);
      signUpName=view.findViewById(id.signUpName);
      signUpSignIn=view.findViewById(id.signupSignIn);
      signUpConfirmPassword=view.findViewById(id.signUpConfirmPassword);
       setOnFocusChange();
      signUpBtn.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            validateFields();
         }
      });
      signUpSignIn.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.login_container,new SignInFragment(),null);

          }
      });

   }

   private  void setOnFocusChange() {
      CommonsKt.onFocusChange(signUpEmail, (View)email, "Email");
      CommonsKt.onFocusChange(signUpPassword, (View)password, "Password");
      CommonsKt.onFocusChange(signUpName, (View)name, "Name");
      CommonsKt.onFocusChange(signUpConfirmPassword, (View)confirmPassword, "Confirm Password");
   }

  /* private  void setSpan() {
      String span = signUpSignIn.getText().toString();
      SpannableString spannableString = new SpannableString((CharSequence)span);
      spannableString.setSpan(new ClickableSpan() {
         public void onClick(@NotNull View widget) {
            BaseFragment.replaceFragment((AppCompatActivity) getActivity(),id.login_container,new SignInFragment(),null);
         }
      }, 17, 25, 33);
      signUpSignIn.setText((CharSequence)spannableString);
    //  signUpSignIn.setMovementMethod(LinkMovementMethod.getInstance());
   }*/

   private  void validateFields() {
      if (!CommonsKt.validateEmail(signUpEmail.getText().toString())) {
         CommonsKt.showToast("Enter valid email");
      }

      if (!signUpPassword.getText().toString().equals(signUpConfirmPassword.getText().toString())) {
         CommonsKt.showToast("Password not matched");
      }

      CharSequence var5 = (CharSequence)signUpName.getText();
      boolean var6 = false;
      boolean var7 = false;
      if (var5 == null || var5.length() == 0) {
         CommonsKt.showToast("Name is required");
      }

      if (CommonsKt.validateEmail(signUpEmail.getText().toString()) && signUpPassword.getText().toString().equals(signUpConfirmPassword.getText().toString())) {
         var5 = (CharSequence)signUpName.getText();
         if (var5.length() > 0) {
             SignUpPresenter presenter=new SignUpPresenter(this);
            new Commons(getContext()).showProgress();
            presenter.signUpUser(signUpEmail.getText().toString(), signUpPassword.getText().toString());
         }
      }

   }
}
