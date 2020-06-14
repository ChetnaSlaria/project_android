package com.sachtech.datingapp.presenter

import android.util.Log
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.model.UserModel
import com.sachtech.datingapp.view.UserView

class MatchUserPresenter(val matchedView: UserView) {
    val matchUserModel by lazy { UserModel() }


    fun addFriendList(likedUser: User, onFriendAdd: (Boolean) -> Unit) {
        matchUserModel.getFriend(likedUser.uid) {
            if (it == null) {
                Log.e("new user add", likedUser.name)
                matchUserModel.addfriendToMatchList(likedUser, matchedView, onFriendAdd)

            } else {
                Log.e("user has already friend", likedUser.name)
                onFriendAdd.invoke(false)
            }
        }

    }


    fun updateFriendRequestStatus(uid: String, frienduid: String, isAccepted: Boolean) {


        matchUserModel.updateFriendRequestStatus(uid, frienduid, matchedView, isAccepted)
    }

    fun getFriend(friendid: String) {
        matchUserModel.getFriend(friendid) {}

    }


}
