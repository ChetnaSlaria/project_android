/*
package com.sachtech.datingapp.ui.explore.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.utils.CircleTransform
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.PreferencesKt.getprefObject
import com.sachtech.datingapp.utils.getprefObject
import com.sachtech.datingapp.utils.loadImage
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.dialog_match.*

class MatchDialog:KotlinBaseDialogFragment<DialogFragment>(R.layout.dialog_match){
    var username:String?=null
    var currentUser:User?=null
    var selectedUser:User?=null
    var onMatched:onMatched?=null
    override fun adjustdisplay(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        matchOk.setOnClickListener {
            onMatched?.onMatched()
            dismiss() }
        currentUser= getprefObject(PrefKeys.USER)
        Picasso.get().load(currentUser?.profilePic!!).transform(CircleTransform()).into(yourImage)
        Picasso.get().load(selectedUser?.profilePic!!).transform(CircleTransform()).into(likedUserImage)
       // yourImage.loadImage(currentUser?.profilePic!!)
       // likedUserImage.loadImage(selectedUser?.profilePic!!)
        match_text.text = "You and $username has matched"
    }

    fun selectedUser(user: User)
    {
        this.selectedUser=user
        this.username=user.name
    }

    fun onMatchedUser(onMatched: onMatched)
    {
        this.onMatched=onMatched
    }
}
interface onMatched{
    fun onMatched()
}

*/
