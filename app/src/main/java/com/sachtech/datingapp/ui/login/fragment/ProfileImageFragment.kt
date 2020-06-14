/*
package com.sachtech.datingapp.ui.login.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.base.BaseFragment
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.data.GoogleUser
import com.sachtech.datingapp.extensions.isNotNull
import com.sachtech.datingapp.extensions.isNull
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.presenter.ProfileImagePresenter
import com.sachtech.datingapp.utils.IntentString
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.SelectImageUtils
import com.sachtech.datingapp.utils.loadImage
import com.sachtech.datingapp.view.ProfileImageView
import kotlinx.android.synthetic.main.fragment_select_profile.*
import java.io.File


class ProfileImageFragment :Fragment(),
    SelectImageUtils.OnSelectImageListener, ProfileImageView {
    override fun onAdditionalImagesUpload(it: Uri, imageView: ImageView?) {
        imageArray?.add(it.toString())
        if (imageView?.drawable.isNotNull()) {
            imageView?.loadImage(uri)
        } else {
            when {
                profile_image1.drawable.isNull() -> profile_image1.loadImage(uri)
                profile_image2.drawable.isNull() -> profile_image2.loadImage(uri)
                profile_image3.drawable.isNull() -> profile_image3.loadImage(uri)
                profile_image4.drawable.isNull() -> profile_image4.loadImage(uri)
                profile_image5.drawable.isNull() -> profile_image5.loadImage(uri)
            }
        }
    }

    override fun onFailure(message: String) {
        showToast(message)
    }

    override fun onUploadSuccessful(it: Uri) {
        Log.e("data",""+it)
        uploadedUri = it.toString()
        profile_image.loadImage(it)
    }

    override fun onImageSelected(path: Uri, imageView: ImageView?) {
        uri = path
        if (imageView == profile_image)
            profileImagePresenter.uploadProfilePicture(path)
        else
            profileImagePresenter.uploadAdditionalImages(path, imageView)
    }


    var uri: Uri? = null
    var uploadedUri: String? = null
    var imageArray: ArrayList<String>? = null
    val profileImagePresenter by lazy { ProfileImagePresenter(this) }
    val selectImageUtils by lazy { SelectImageUtils(context!!).apply { selectImage(this@ProfileImageFragment) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context!!).inflate(R.layout.fragment_select_profile,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageArray = ArrayList()
        setOnImageClickListener()
        val facebookUser = getprefObject<FacebookUser>(PrefKeys.INSTANCE.facebookuser)
        val googleUser = getprefObject<GoogleUser>(PrefKeys.INSTANCE.googleuser)
        when {
            facebookUser?.profilePic != null -> {
                 uploadedUri=facebookUser.profilePic
                profile_image.loadImage(uploadedUri)
                Log.e("fbprofilepic", facebookUser.profilePic)
                profileImagePresenter.uploadProfilePicture(Uri.fromFile(File(facebookUser.profilePic)))

            }
            googleUser?.photoUrl != null -> {
                uploadedUri = googleUser.photoUrl
                profile_image.loadImage(uploadedUri)
            }
            else -> profile_image.setImageResource(R.drawable.user)
        }

        profile_next.setOnClickListener {
            checkProfileImage()
        }

    }

    private fun setOnImageClickListener() {
        profile_image.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image)
        }
        profile_image1.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image1)
        }
        profile_image2.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image2)
        }
        profile_image3.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image3)
        }
        profile_image4.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image4)
        }
        profile_image5.setOnClickListener {
            selectImageUtils.openChooser(this, profile_image5)
        }

    }

    private fun checkProfileImage() {
        if (uploadedUri != null) {
            arguments?.getString(IntentString.EMAIL)
            arguments?.getString(IntentString.NAME)
            arguments?.putStringArrayList(IntentString.IMAGELIST, imageArray ?: arrayListOf())
            arguments?.putString(IntentString.PROFILEIMAGE, uploadedUri)
            BaseFragment.replaceFragment(activity as AppCompatActivity?,R.id.login_container,GenderDobFragment(),arguments)
         //   baselistener.navigateToFragment(GenderDobFragment::class, arguments)

        } else Toast.makeText(context!!,"Please add Profile Picture before proceeding..",Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        selectImageUtils.onActivityResult(requestCode, resultCode, data)
    }

}*/
