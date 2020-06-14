package com.sachtech.datingapp.presenter

import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.model.UserModel
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded
import com.sachtech.datingapp.view.UserView

class UserPresenter(val userView: UserView) {

    val userModel by lazy { UserModel() }
    fun getAllUsers() {
        userModel.getAllUsers(userView)
    }


    fun addToLikeList(user: User) {
        userModel.addUserToLikeList(user, userView)
    }

    fun addToVisitList(user: User) {
        userModel.addToVisitList(user, userView)
    }


/* fun addToBlockList(uid: User) {

         userModel.addUserToBlockList(uid,userView)
    }*/


    fun notifyLikedUser(user: User) {
        userModel.notifyLikedUser(user)
    }

    fun addToChatFriendList(user: User, onFreindAdd: OnFriendAdded) {
        userModel.addUserToFriendList(user, onFreindAdd)
    }

    fun addToUnlikeList(user: User) {
        userModel.addUserToUnlikeList(user, userView)
    }

    fun removeFromUnlikeList(user: User) {
        userModel.removeUserFromUnliekeList(user)
    }

    fun removeFromLikeList(docId: String?) {
        userModel.removeUserFromLikeList(docId)
    }


}
