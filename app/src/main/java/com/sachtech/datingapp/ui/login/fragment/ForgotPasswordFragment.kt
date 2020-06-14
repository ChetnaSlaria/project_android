/*
package com.sachtech.datingapp.ui.login.fragment


import android.os.Bundle
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.extensions.onFocusChange
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.extensions.validateEmail
import com.sachtech.datingapp.networking.FirebaseHelper
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ForgotPasswordFragment : KotlinBaseFragment(R.layout.fragment_forgot_password) {
    val firebaseHelper by lazy { FirebaseHelper() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forgotSendBtn.setOnClickListener { sendVerificationEmail() }
        setOnFocusChange()
    }

    private fun sendVerificationEmail() {
        val email = forgotEmail.text.toString()
        if(email.validateEmail()) {
            showProgress()
            firebaseHelper.firebaseAuth.sendPasswordResetEmail(email).addOnFailureListener {
                hideProgress()
                showToast(it.localizedMessage)

            }.addOnSuccessListener {
                hideProgress()
                showToast("Reset password link will be send to your email,please check it.")
                onBackPressed()
            }
        }
        else showToast("Enter your correct email")
    }

    private fun setOnFocusChange() {
        onFocusChange(forgotEmail, email, "Email")

    }
}*/
