/*
package com.sachtech.datingapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.cometchat.pro.core.CometChat
import com.mgazetti.android.util.Google
import com.sachtech.datingapp.R
import com.sachtech.datingapp.networking.Facebook
import com.sachtech.datingapp.ui.login.LoginActivity
import com.sachtech.datingapp.utils.*
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import com.cometchat.pro.exceptions.CometChatException
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter
import com.sachtech.datingapp.view.ProfileUpdateView
import com.google.firebase.auth.*
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.home.OnFragmentChange
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange


class SettingFragment : KotlinBaseFragment(R.layout.fragment_settings), ProfileUpdateView {
    override fun onUpdateProfile() {
        hideProgress()
        showToast("Your profile will be hidden to everyone")
    }

    override fun onFailure(message: String) {
        hideProgress()
    }

    val firebaseHelper by lazy { FirebaseHelper() }
    val facebook by lazy { Facebook(this) }
    val google by lazy { Google(this) }
    val profileUpdatePresenter by lazy { ProfileUpdatePresenter(this) }
    val user by lazy { Preferences.getprefObject<User>(PrefKeys.USER) }
    var onFragmentChange: OnFragmentChange?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //facebook.init()
        back.setOnClickListener { onBackPressed() }
        onFragmentChange?.selectedPos(4)
        google.init()
        setting_password.text = Preferences.get(PrefKeys.INSTANCE.password, "")
        setting_email.text = Preferences.get(PrefKeys.INSTANCE.email, "")
        setting_password.transformationMethod = PasswordTransformationMethod.getInstance()
        password_show_hide.setOnClickListener {
            if (password_show_hide.text.toString() == "Show") {
                password_show_hide.text = "Hide"
                setting_password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                password_show_hide.text = "Show"
                setting_password.transformationMethod = PasswordTransformationMethod.getInstance()

            }
        }
        hideSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                user?.accountStatus = Constants.HIDDEN
                showProgress()
                profileUpdatePresenter.updateProfileOnDatabase(user!!)
            }
        }

        deleteAccount.setOnCheckedChangeListener {
            buttonView,isChecked->
            if(isChecked)
            {
                showConfirmationDialog()
            }


        }
        sign_out.setOnClickListener {
            CometChat.logout(object : CometChat.CallbackListener<String>() {
                override fun onSuccess(successMessage: String) {
                    finishActivityAndClearPrefs()
                }

                private fun finishActivityAndClearPrefs() {
                    if (facebookUser() != null) {
                      facebook.logout()
                    } else if (googleUser() != null) {
                       google.signOutFromGoogle()
                    }
                    Preferences.clearPref()
                    val intent = Intent(activity!!, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity?.startActivity(intent)
                    activity?.finish()
                }

                override fun onError(e: CometChatException) {
                    showToast("Error while signing out")
                }
            })

        }
    }

    private fun showConfirmationDialog() {
       // val bundle = Bundle()
        val dialog = ConfirmationDialog()
        dialog.show(childFragmentManager, "confirm")
    }
}*/
