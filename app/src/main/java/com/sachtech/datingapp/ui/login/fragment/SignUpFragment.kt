/*
package com.sachtech.datingapp.ui.login.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.google.firebase.auth.AuthResult
import com.sachtech.datingapp.R
import com.sachtech.datingapp.extensions.isNotNull
import com.sachtech.datingapp.extensions.onFocusChange
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.extensions.validateEmail
import com.sachtech.datingapp.presenter.SignUpPresenter
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.PreferencesKt.setPref
import com.sachtech.datingapp.view.SignUpView
import gurtek.mrgurtekbase.KotlinBaseFragment
import gurtek.mrgurtekbase.hideKeyboard
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : KotlinBaseFragment(R.layout.fragment_sign_up), SignUpView {

    override fun onFailure(message: String) {
        hideProgress()
        showToast(message)
    }

    override fun onSignUpSuccess(result: AuthResult?) {
        hideProgress()
        if (result.isNotNull()) {
            setPref(PrefKeys.INSTANCE.password, signUpPassword.text.toString())
            setPref(PrefKeys.UID, result?.user?.uid?.toLowerCase())
            setPref(PrefKeys.USERNAME,signUpName.text.toString())
            setPref(PrefKeys.EMAIL,result?.user?.email)
            arguments?.putString(IntentString.EMAIL, result?.user?.email)
            arguments?.putString(IntentString.NAME, signUpName.text.toString())
            baselistener.navigateToFragment(ProfileImageFragment::class, arguments)
        }

    }


    val signUpPresenter by lazy { SignUpPresenter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CommonUtil.hideKeyboard(activity!!)

        setSpan()
        //setOnFocusChange()
        signUpBtn.setOnClickListener {
            validateFields()
        }
    }

    private fun setOnFocusChange() {
        onFocusChange(signUpEmail, email_header, "Email")
        onFocusChange(signUpPassword, password, "Password")
        onFocusChange(signUpName, name, "Name")
        onFocusChange(signUpConfirmPassword, confirmPassword, "Confirm Password")
    }

    private fun setSpan() {
        val span = signupSignIn.text.toString()
        val spannableString = SpannableString(span)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                baselistener.navigateToFragment(SignInFragment::class)
            }

        }, 17, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        signupSignIn.text = spannableString
        signupSignIn.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun validateFields() {
        val email = signUpEmail.text.toString()
        val password = signUpPassword.text.toString()
        val confirmPassword = signUpConfirmPassword.text.toString()
        val name = signUpName.text.toString()
        if(!email.validateEmail())
        {
            showToast("Enter valid email")
        }
        if(password!=confirmPassword)
        {
            showToast("Password not matched")
        }
        if(name.isNullOrEmpty())
        {
            showToast("Name is required")
        }
        if (email.validateEmail() && password == confirmPassword && name.isNotEmpty()) {
            showProgress()
            signUpPresenter.signUpUser(email, password)
        }
    }
}*/
