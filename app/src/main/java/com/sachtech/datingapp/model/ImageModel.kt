package com.sachtech.datingapp.model

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.networking.apiHitVerify
import com.sachtech.datingapp.view.ProfileImageView
import com.sachtech.datingapp.view.VerificationImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageModel {
    val firebaseHelper by lazy { FirebaseHelper() }
    fun uploadProfilePicture(uri: Uri, view: ProfileImageView) {
        firebaseHelper.uploadImageToStorage(uri).addOnSuccessListener {
            Log.e("image url upload", "" + it.metadata?.reference?.downloadUrl)
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                if (it != null) {
                    Log.e("image url upload", "" + it.toString())
                    view.onUploadSuccessful(it)
                } else showToast("error")
            }.addOnFailureListener {
                view.onFailure(it.localizedMessage)
            }
        }.addOnFailureListener {
            view.onFailure(it.localizedMessage)
        }
    }


    fun uploadVerificationPicture(
        uri: Uri,
        view: VerificationImageView
    ) {
        firebaseHelper.uploadImageToStorage(uri).addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                view.onVerificationImageUpload(it)
            }.addOnFailureListener {
                view.onFailure(it.localizedMessage)
            }
        }.addOnFailureListener {
            view.onFailure(it.localizedMessage)
        }
    }

    fun uploadAdditionalImage(
        path: Uri,
        view: ProfileImageView,
        imageView: ImageView?
    ) {
        firebaseHelper.uploadImageToStorage(path).addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                view.onAdditionalImagesUpload(it, imageView)
            }.addOnFailureListener {
                view.onFailure(it.localizedMessage)
            }
        }.addOnFailureListener {
            view.onFailure(it.localizedMessage)
        }
    }

    fun sendVerificationMail(
        username: String,
        uri: String,
        userEmail: String,
        uuid:String,
        verificationImageView: VerificationImageView
    ) {
        apiHitVerify().sendVerificationMail(
            username = username,
            userProfile = uri,
            userEmail = userEmail,uuid = uuid
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            verificationImageView.onVerifyEmail()
        }, {
            verificationImageView.onFailure(it.message!!)
        })
    }


}