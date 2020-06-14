/*
package com.sachtech.datingapp.ui.login.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.google.android.gms.tasks.Task
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatAuth
import com.sachtech.datingapp.data.CreateUserResponse
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.presenter.UserDetailPresenter
import com.sachtech.datingapp.presenter.VerificationImagePresenter
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.view.UserDetailView
import com.sachtech.datingapp.view.VerificationImageView
import gurtek.mrgurtekbase.KotlinBaseFragment
import gurtek.mrgurtekbase.gone
import gurtek.mrgurtekbase.visible
import kotlinx.android.synthetic.main.fragment_verify_image.*
import java.util.*

import com.sachtech.datingapp.extensions.getDateInformat
import com.sachtech.datingapp.utils.Preferences.get
import com.sachtech.datingapp.utils.Preferences.setprefObject
import java.text.SimpleDateFormat


class VerificationImageFragment : KotlinBaseFragment(R.layout.fragment_verify_image),
    VerificationImageView,
   OnSelectImageListener, UserDetailView {

    val cometUser: CometChatAuth? = null
    val userDetailPresenter by lazy { UserDetailPresenter(this) }
    var verificationImageUri: Uri? = null
    var uri: String=""
    var name: String=""
    var email: String=""
    val profileImagePresenter by lazy { VerificationImagePresenter(this) }
    val selectImage by lazy {
        SelectImageUtils(context!!).apply {
            selectImage(this@VerificationImageFragment)
        }
    }

    override fun onCreateChatUser(it: CreateUserResponse) {
        hideProgress()
        Log.e("user", it.data.toString())
        setprefObject(PrefKeys.INSTANCE.user, getUserDetails(getUid()!!))
        baselistener.navigateToFragment(NotVerifiedStatusFragment::class)
    }

    override fun onVerificationImageUpload(it: Uri) {
        profileImagePresenter.sendVerifyEmail(
            uri = it.toString(),
            userEmail = getUserDetails(getUid()).email,
            userName = getUserDetails(getUid()).name,
            uid= getUid()
        )
        verification_image.loadImage(verificationImageUri)
        this.uri = it.toString()
    }

    override fun onVerifyEmail() {
        hideProgress()
        image_layout.visible()
        verify_camera.gone()
    }

    override fun onImageSelected(path: Uri, view: ImageView?) {
        verificationImageUri = path
        if (path != null) {
            showProgress()
            profileImagePresenter.uploadVerificationPicture(path)
        }
    }

    override fun onSuccess(result: Task<Void>) {
        if (result.isSuccessful) {
            setprefObject(PrefKeys.INSTANCE.user, getUserDetails(getUid()!!))
            Log.e("user", getUserDetails(getUid()!!).toString())
            var userDetails = getUserDetails(getUid())
            userDetailPresenter.createChatUser(
                userDetails.uid,
                userDetails.name,
                userDetails.email,
                userDetails.profilePic
            )

        } else {
            hideProgress()
            // baselistener.openA(HomeActivity::class)
            activity?.supportFragmentManager?.popBackStack()
            showToast(result.exception?.localizedMessage!!)
        }
    }

    override fun onFailure(localizedMessage: String) {
        hideProgress()
        showErrorMessage(localizedMessage)
    }


    private fun getUserDetails(uid: String?): User {

        when {
            facebookUser() != null -> {
                name = facebookUser()?.name!!
                email = facebookUser()?.email!!
            }
            googleUser() != null -> {
                name = googleUser()?.displayName!!
                email = googleUser()?.email!!
            }
            else -> {
                name = arguments?.getString(IntentString.NAME) ?: get(PrefKeys.USERNAME, "")!!
                email = arguments?.getString(IntentString.EMAIL) ?: get(PrefKeys.EMAIL, "")!!
            }
        }
        val imageArray = arguments?.getStringArrayList(IntentString.IMAGELIST)
        val profileImage = arguments?.getString(IntentString.PROFILEIMAGE)!!
        val gender = arguments?.getString(IntentString.GENDER)
        val dob = arguments?.getLong(IntentString.DOB)
        val age = arguments?.getString(IntentString.AGE)
        val verificationImage = uri
        val user = User()
        user.uid = uid?.toLowerCase().toString()
        user.name = name!!
        user.email = email!!
        user.fcm = getFcm()!!
        user.profilePic = profileImage
        user.verificationImage = verificationImage.toString()
        user.gender = gender!!
        user.age = age!!
        user.dob = dob!!
        user.accountStatus = Constants.NOT_VERIFIED
        user.colorComplexion = ""
        user.latitude = getLatitude()?.toDouble()!!
        user.longitude = getLongitude()?.toDouble()!!
        user.location = get(PrefKeys.LOCATION, "")!!
        user.images = imageArray
        user.createdOn = getDate()
        user.private = private_account.isChecked
        return user
    }

   private fun getDate(): Long {
        val str_date = Date().time
        var dateFormatted = str_date.getDateInformat()
        val formatter = SimpleDateFormat("MMM dd,yyyy")
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val date = (formatter.parse(dateFormatted) as Date)
        return date.time / 1000
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verify_camera.setOnClickListener {
            selectImage.selectImageFromCamera(this, verification_image)
        }
        change_verify_image.setOnClickListener {
            selectImage.selectImageFromCamera(this, verification_image)
        }
        verification_done.setOnClickListener {
            verifyImage()
        }
        private_account.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {

            }
        }
    }

    private fun verifyImage() {
        if (uri != "") {
            showProgress()
            userDetailPresenter.addUserDetailsToDatabase(getUserDetails(getUid()!!))
        } else showErrorMessage("You cannot skip this step")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        selectImage.onActivityResult(requestCode, resultCode, data)
    }

}
*/
