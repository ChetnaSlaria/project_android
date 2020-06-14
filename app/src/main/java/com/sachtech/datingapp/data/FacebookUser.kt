package com.sachtech.datingapp.data

import androidx.annotation.Keep
import java.net.URI


@Keep
data class FacebookUser(
    val name: String? = null,
    val email: String? = null,
    var profilePic: String? = null,
    val accessToken: String? = null
)