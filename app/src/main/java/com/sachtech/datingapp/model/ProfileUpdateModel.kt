package com.sachtech.datingapp.model

import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.view.ProfileUpdateView

class ProfileUpdateModel {

    val firebaseHelper by lazy { FirebaseHelper() }
    fun updateProfile(
        user: User,
        profileUpdateView: ProfileUpdateView
    ) {
        firebaseHelper.updateUserDetails(user).addOnSuccessListener {
            profileUpdateView.onUpdateProfile()
        }.addOnFailureListener {
            profileUpdateView.onFailure(it.localizedMessage)
        }
    }

    fun deleteUser(
        user: User,
        profileUpdateView: ProfileUpdateView
    ) {
        firebaseHelper.deleteUser(user).addOnSuccessListener {
            profileUpdateView.onUpdateProfile()
        }.addOnFailureListener {
            profileUpdateView.onFailure(it.localizedMessage)
        }
    }


}