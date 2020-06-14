/*
package com.sachtech.datingapp.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.ChatUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.home.activity.EditProfileActivity
import com.sachtech.datingapp.ui.home.fragment.SubscriptionActivity
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.Preferences.setprefObject
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.KotlinBaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*



class ProfileFragment : KotlinBaseFragment(R.layout.fragment_profile),
    SelectImageUtils.OnSelectImageListener {
    override fun onImageSelected(path: Uri, view: ImageView?) { showProgress()
        uploadImage(path)

    }

    private fun uploadImage(path: Uri) {
        firebaseHelper.uploadImageToStorage(path).addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
              updateUserDetail(it)
            }.addOnFailureListener {
                showToast(it.localizedMessage)
            }
        }.addOnFailureListener {
           showToast(it.localizedMessage)
        }
}

    private fun updateUserDetail(it: Uri?) {
        Picasso.get().load(it).into(profile_image)
        newuser.profilePic=it.toString()
        firebaseHelper.updateUserDetails(newuser).addOnCompleteListener { task ->
            updateOnCometChat(it.toString())

        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }
    }

    private fun updateOnCometChat(uri: String) {
        cometUser?.avatar=uri
        apiHitter().updateUserOnCometChat(apikey = resources.getString(R.string.comet_api_key),appId = resources.getString(R.string.comet_app_id),uid = cometUser?.uid!!,chatUser = cometUser!!).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            hideProgress()
            setprefObject(PrefKeys.USER,newuser)
            setprefObject(PrefKeys.COMETUSER,cometUser)
        },{
            hideProgress()
            showToast(it.localizedMessage)
        })
    }

    var onFragmentChange: OnFragmentChange? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(4)
    }

    val selectImageUtils by lazy { SelectImageUtils(context!!).apply { selectImage(this@ProfileFragment) } }
    val firebaseHelper by lazy { FirebaseHelper() }
var cometUser:ChatUser?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        selectImageUtils.onActivityResult(requestCode, resultCode, data)
    }

    lateinit var newuser: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentChange?.selectedPos(4)
        val user = getprefObject<User>(PrefKeys.USER)
        cometUser= getprefObject(PrefKeys.COMETUSER)
        newuser = user!!
        setAccount(user)
        Picasso.get().load(user.profilePic).into(profile_image)
      // profile_image.loadImage(user.profilePic)
        profile_name.text = user.name
        profile_edit.setOnClickListener { baselistener.openA(EditProfileActivity::class) }
        profile_settings.setOnClickListener { baselistener.navigateToFragment(SettingFragment::class) }
        profile_partner_preferences.setOnClickListener {
            baselistener.navigateToFragment(
                PartnerPreferenceFragment::class
            )
        }
        profile_privacy.setOnClickListener { activity?.startActivity(Intent(activity,PrivacyActivity::class.java)) }
        profile_terms.setOnClickListener { activity?.startActivity(Intent(activity,TermsAndConditionActivity::class.java)) }


        add_image.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image)
        }

        profile_subscription.setOnClickListener {
            baselistener.openA(SubscriptionActivity::class)
        }

 back.setOnClickListener {
            onBackPressed()
        }


    }

    private fun setAccount(user: User) {
        if (user.accountStatus == "verified") {
            profile_subscription.isEnabled = true
            profile_subscription.setTextColor(resources.getColor(R.color.colorWhite))

        } else {
            profile_subscription.isEnabled = false
            profile_subscription.setTextColor(resources.getColor(R.color.colorGrey))
        }
    }
}
*/
