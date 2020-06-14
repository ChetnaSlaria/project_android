package com.sachtech.datingapp.presenter

import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.model.ProfileUpdateModel
import com.sachtech.datingapp.view.ProfileUpdateView

class ProfileUpdatePresenter(val profileUpdateView: ProfileUpdateView)
{
    val profileUpdateModel by lazy { ProfileUpdateModel() }

    fun updateProfileOnDatabase(user: User)
    {
        profileUpdateModel.updateProfile(user, profileUpdateView)
    }

   fun deleteuser(user: User)
   {
       profileUpdateModel.deleteUser(user,profileUpdateView)
   }
}