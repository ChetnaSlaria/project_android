/*
package com.sachtech.datingapp.ui.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.*
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.data.GoogleUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.login.LoginActivity
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.clearPref
import com.sachtech.datingapp.utils.Preferences.get
import com.sachtech.datingapp.utils.Preferences.getprefObject
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.confirmation_dialog.*

class ConfirmationDialog :KotlinBaseDialogFragment<DialogFragment>(R.layout.confirmation_dialog) {
    override fun adjustdisplay(): Boolean {
        return true
    }

    val firebaseHelper by lazy { FirebaseHelper() }
    val user by lazy { getprefObject<User>(PrefKeys.INSTANCE.user) }
    var credential:AuthCredential?=null
    var facebookUser:FacebookUser?=null
    var googleUser:GoogleUser?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        facebookUser= getprefObject(PrefKeys.INSTANCE.facebookuser)
        googleUser= getprefObject(PrefKeys.INSTANCE.googleuser)
        yes.setOnClickListener {
            baseListener.showProgress()
            if(user?.isSubscribed==false) {
                firebaseHelper.deleteUserFromFirebase(getUid()!!).addOnFailureListener {
                    showToast(it.localizedMessage)
                }.addOnSuccessListener {
                    val user1 = FirebaseAuth.getInstance().currentUser

                     if(facebookUser!=null)
                    {
                        credential=FacebookAuthProvider.getCredential(facebookUser?.accessToken!!)
                    }
                    else if(googleUser!=null)
                    {
                        credential=GoogleAuthProvider.getCredential(googleUser?.idToken,null)
                    }
                    else{
                         val email = get(PrefKeys.INSTANCE.email, "")
                         val password = get(PrefKeys.INSTANCE.password, "")
                         credential= EmailAuthProvider
                             .getCredential(email!!, password!!)
                     }

                    user1?.reauthenticate(credential!!)
                            ?.addOnCompleteListener {
                                user1.delete()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            apiHitter().deleteUser(appId = resources.getString(R.string.comet_app_id),apikey = resources.getString(R.string.comet_api_key),uid = user1.uid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                                                baseListener.hideProgress()
                                                clearPref()
                                                dismiss()
                                                val intent =
                                                    Intent(activity!!, LoginActivity::class.java)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                activity?.startActivity(intent)
                                                activity?.finish()
                                            },{
                                                baseListener.hideProgress()
                                                showToast(it.localizedMessage)

                                            })

                                        }
                                    }
                            }?.addOnFailureListener {
                                baseListener.hideProgress()
                                showToast(it.localizedMessage)
                            }
                }
            }
            else
            {
                showToast("You are premium user,you cannot delete it until your subscription ends")
            }
        }
        no.setOnClickListener { dismiss() }
    }
}*/
