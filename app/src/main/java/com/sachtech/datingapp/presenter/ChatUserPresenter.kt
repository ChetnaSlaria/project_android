package com.sachtech.datingapp.presenter

import com.sachtech.datingapp.model.ChatUserModel
import com.sachtech.datingapp.view.ChatUserView

class ChatUserPresenter(val userView: ChatUserView) {

    val chatUserModel by lazy { ChatUserModel() }

    fun getUsers(uid: String) {
        chatUserModel.getChatUsers(uid, userView)
    }
}
