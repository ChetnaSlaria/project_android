package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class VisitUsers(
    var visit_by_user_id: String = "",
    var visit_to_user_id: String = ""

)