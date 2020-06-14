package com.sachtech.datingapp.data

import android.net.Uri
import androidx.annotation.Keep

@Keep
data class GoogleUser(
    val displayName: String?,
    val email: String?,
    val photoUrl: String?,
    val idToken: String?
)