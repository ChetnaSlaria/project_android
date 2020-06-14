package com.sachtech.datingapp.ui.home.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.dialog_confrim.*

class ConfirmDialog: KotlinBaseDialogFragment<DialogFragment>(R.layout.dialog_confrim){
    val firebaseHelper by lazy { FirebaseHelper() }

    override fun adjustdisplay(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = arguments?.getString("uid")
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cancelLike.setOnClickListener {
            dismissAllowingStateLoss()
        }
       /* confirm_unlike.setOnClickListener {
            //unlikeUser(uid)
            firebaseHelper.deleteLikedUser(arguments?.getString("documentId")!!).addOnSuccessListener {
                showToast("User unliked")
                dismissAllowingStateLoss()
            }.addOnFailureListener {
                showToast(it?.localizedMessage ?: "Sorry, something went wrong. please try again after sometime.")
            }
        }
*/
    }
    private fun unlikeUser(uid: String?) {
        /* val doc = firebaseHelper.addUserToNotInterestedList(getUid()!!)
         doc.get().addOnSuccessListener {
             if (it.exists()) {
                 val userList = ArrayList(it.toObject(UnlikeUser::class.java)?.userId)
                 if (userList.contains(uid))
                     userList.remove(uid)
                 doc.update("userId", userList).addOnSuccessListener {
                  dismiss()
                 }.addOnFailureListener {
                     showToast(it.localizedMessage)
                 }
             }
         }
 */
    }

}

