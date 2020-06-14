package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class Blockeduser(
    val block_by_user_id: String = "",
    val block_by_user_name: String = "",
    val block_to_user_id: String = "",
    val block_to_user_name: String = ""
)