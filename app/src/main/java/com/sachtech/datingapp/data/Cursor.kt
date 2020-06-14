package com.sachtech.datingapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cursor(

    @SerializedName("updatedAt") var updatedAt: Int? = null,
    @SerializedName("affix") var affix: String? = null
)

data class FriendUser(

    @SerializedName("uid") var uid: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("profileHighlight") var profileHighlight: String? = null,
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("hasBlockedMe") var hasBlockedMe: Boolean? = null,
    @SerializedName("blockedByMe") var blockedByMe: Boolean? = null,
    @SerializedName("Accept_status") var Accept_status: Boolean? = false
)

data class GetFriends(

    @SerializedName("data") var data: List<FriendUser>? = null,
    @SerializedName("meta") var meta: Meta? = null
)

data class Meta(

    @SerializedName("pagination") var pagination: Pagination? = null,
    @SerializedName("cursor") var cursor: Cursor? = null
)

data class Pagination(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null
)


