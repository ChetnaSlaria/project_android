package com.sachtech.datingapp.view

import android.net.Uri
import android.widget.ImageView
import com.sachtech.datingapp.listener.FailureListener

interface ProfileImageView:FailureListener {

   fun  onUploadSuccessful(it: Uri)
   fun onAdditionalImagesUpload(it: Uri, imageView: ImageView?)
}