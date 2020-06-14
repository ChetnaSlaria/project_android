package com.sachtech.datingapp.presenter

import android.net.Uri
import com.sachtech.datingapp.model.ImageModel
import com.sachtech.datingapp.view.VerificationImageView

class VerificationImagePresenter(val verificationImageView: VerificationImageView) {

    val imageModel by lazy { ImageModel() }
    fun uploadVerificationPicture(uri: Uri)
    {
        imageModel.uploadVerificationPicture(uri,verificationImageView)
    }

    fun sendVerifyEmail(
        userName: String,
        uri: String,
        userEmail: String,
        uid: String?
    ){
        imageModel.sendVerificationMail(userEmail = userEmail,username = userName,uri = uri,uuid = uid!!,verificationImageView = verificationImageView)
    }
}