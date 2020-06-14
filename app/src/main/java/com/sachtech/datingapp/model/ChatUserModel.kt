package com.sachtech.datingapp.model

import android.annotation.SuppressLint
import com.sachtech.datingapp.R
import com.sachtech.datingapp.app.DatingApp
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.view.ChatUserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatUserModel {

    @SuppressLint("CheckResult")
    fun getChatUsers(uid: String, userView: ChatUserView) {
        apiHitter().getFriends(
            appId = DatingApp.application?.getString(R.string.comet_app_id)!!,
            apikey = DatingApp.application?.getString(R.string.comet_api_key)!!,
            uid = uid
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                userView.onGettingFriends(it)
            }, {
                userView.onFailure(it.localizedMessage)
            })
    }


}
