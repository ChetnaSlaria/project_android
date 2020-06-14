/*
package com.sachtech.datingapp.ui.chat.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cometchat.pro.models.User
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.Report
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.getprefObject
import kotlinx.android.synthetic.main.dialog_tell_us_more.*

class TellUsMoreDialog : DialogFragment() {

    private val firebaseHelper = FirebaseHelper()
    private val userData by lazy { getprefObject<User>(PrefKeys.USER) }


    lateinit var reportedUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_tell_us_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)


        send.setOnClickListener {

            val reportData = Report(
                report_by_user_id = userData!!.uid,
                report_by_user_name = userData!!.name,
                report_to_user_id = reportedUser.uid,
                report_reason = message.text.toString(),
                report_to_user_name = reportedUser.name
            )
            firebaseHelper.addReport(reportData)
                .addOnSuccessListener {
                    showToast("reported successfully")
                    dismiss()
                }.addOnFailureListener {
                showToast(it.localizedMessage)
            }
        }
    }

    fun getReportedUserData(data:User) {
        reportedUser = data
    }
}
*/
