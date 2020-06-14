/*
package com.sachtech.datingapp.ui.login.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.base.BaseFragment
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.presenter.ProfileImagePresenter
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter
import com.sachtech.datingapp.presenter.VerificationImagePresenter
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.SelectImageUtils
import com.sachtech.datingapp.utils.loadImage
import com.sachtech.datingapp.view.ProfileImageView
import com.sachtech.datingapp.view.ProfileUpdateView
import com.sachtech.datingapp.view.VerificationImageView
import kotlinx.android.synthetic.main.dialog_not_verified_status.*

class NotVerifiedStatusFragment : Fragment(),
    ProfileImageView, VerificationImageView, SelectImageUtils.OnSelectImageListener,ProfileUpdateView {
    override fun onUpdateProfile() {
        BaseFragment.replaceFragment(R.id.login_container,SignInFragment(),null)
    }

    val imageUtils by lazy {
        SelectImageUtils(context!!).apply {
            selectImage(this@NotVerifiedStatusFragment)
        }
    }
    val verificationImagePresenter by lazy { VerificationImagePresenter(this) }
    val profileImagePresenter by lazy { ProfileImagePresenter(this) }
    val profileUpdatePresenter by lazy { ProfileUpdatePresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context!!).inflate(R.layout.dialog_not_verified_status,container,false)
    }
    override fun onImageSelected(path: Uri, view: ImageView?) {
        uri=path
        if (view == notVerify_verification_image) {
            verificationImagePresenter.uploadVerificationPicture(path)
        } else if (view == notVerify_profile_image) {
            profileImagePresenter.uploadProfilePicture(path)
        }
    }

    override fun onVerificationImageUpload(it: Uri) {
        notVerify_verification_image.loadImage(it)
        verificationUri=it.toString()
    }

    override fun onVerifyEmail() {

    }

    override fun onUploadSuccessful(it: Uri) {
        notVerify_profile_image.loadImage(it)
        profileUri=it.toString()
    }

    override fun onAdditionalImagesUpload(it: Uri, imageView: ImageView?) {

    }

    override fun onFailure(message: String) {
        showToast(message)
    }

    var uri:Uri?=null
    var profileUri:String?=null
    var verificationUri:String?=null
    var user:User?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user= getprefObject<User>(PrefKeys.INSTANCE.user)
        if(user!=null) {
            notVerify_profile_image.loadImage(user?.profilePic)
            notVerify_verification_image.loadImage(user?.verificationImage)
        }
        notVerify_change_profile.setOnClickListener {
            imageUtils.openChooser(
                this,
                notVerify_profile_image
            )
        }
        notVerify_change_verification.setOnClickListener {
            imageUtils.selectImageFromCamera(
                this,
                notVerify_verification_image
            )
        }
        notVerify_ok.setOnClickListener {

            if(profileUri!=null||verificationUri!=null) {
                if (profileUri != null) {
                    user?.profilePic = profileUri!!
                }
                if (verificationUri != null) {
                    user?.verificationImage = verificationUri!!
                }
                profileUpdatePresenter.updateProfileOnDatabase(user!!)
            }
            else {        BaseFragment.replaceFragment(R.id.login_container,SignInFragment(),null)
           }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageUtils.onActivityResult(requestCode, resultCode, data)
    }
}

*/
