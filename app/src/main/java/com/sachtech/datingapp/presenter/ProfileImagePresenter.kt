package com.sachtech.datingapp.presenter

import android.net.Uri
import android.widget.ImageView
import com.sachtech.datingapp.model.ImageModel
import com.sachtech.datingapp.view.ProfileImageView

class ProfileImagePresenter(val view: ProfileImageView) {

    val profileImageModel by lazy { ImageModel() }

   fun uploadProfilePicture(uri:Uri)
   {
       profileImageModel.uploadProfilePicture(uri,view)
   }

    fun uploadAdditionalImages(path: Uri, imageView: ImageView?) {
        profileImageModel.uploadAdditionalImage(path,view,imageView)
    }


}
