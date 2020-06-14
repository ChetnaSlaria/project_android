package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class LikeUsers(
    var liked_by_user_id: String?=null,
    var liked_to_user_id: String?=null,
    var like_status:String=""
)

object MatchStatus{
    val PENDING="pending"
    val ACCEPTED="accepted"
    val DECLINED="declined"
}