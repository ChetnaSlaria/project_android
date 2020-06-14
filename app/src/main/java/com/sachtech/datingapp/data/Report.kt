package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class Report(
    val report_by_user_id: String = "",
    val report_by_user_name: String = "",
    val report_reason: String = "",
    val report_to_user_id: String = "",
    val report_to_user_name: String = ""
)
