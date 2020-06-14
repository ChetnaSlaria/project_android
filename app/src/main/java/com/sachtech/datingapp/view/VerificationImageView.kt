package com.sachtech.datingapp.view

import android.net.Uri
import com.google.android.gms.tasks.OnFailureListener
import com.hbb20.CountryCodePicker
import com.sachtech.datingapp.listener.FailureListener

interface VerificationImageView:FailureListener{
    fun onVerificationImageUpload(it: Uri)
    fun onVerifyEmail()
}