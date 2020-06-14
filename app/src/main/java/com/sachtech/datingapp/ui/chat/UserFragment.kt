/*
package com.sachtech.datingapp.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cometchat.pro.models.User
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatUser
import com.sachtech.datingapp.data.Blockeduser
import com.sachtech.datingapp.data.FriendUser
import com.sachtech.datingapp.data.GetFriends
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.ChatUserPresenter
import com.sachtech.datingapp.ui.chat.adapter.ChatAdapter
import com.sachtech.datingapp.ui.chat.adapter.ChatOnlineUsersAdapter
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.getUid
import com.sachtech.datingapp.view.ChatUserView
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_chat_user.*

class UserFragment : KotlinBaseFragment(R.layout.fragment_chat_user), ChatUserView {
    override fun onFailure(message: String) {
        hideProgress()
        showToast(message)
    }

    var onFragmentChange: OnFragmentChange?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    override fun onGettingFriends(it: GetFriends) {
        hideProgress()
        var arrayList = it.data as ArrayList<FriendUser>
        firebaseHelper.getBlockedUser(userData!!.uid).addOnSuccessListener {
            val blockedUserData = it.toObjects(Blockeduser::class.java)
            blockedUserData.forEach { blockedUser ->
                arrayList = arrayList.filter { originalData ->
                    originalData.uid != blockedUser.block_to_user_id
                } as ArrayList<FriendUser>

            }
            userlist.addAll(arrayList)
            chatMessagesAdapter?.notifyDataSetChanged()
        }.addOnFailureListener {
            showToast(it.localizedMessage)
        }

    }

    val chatUserPresenter by lazy { ChatUserPresenter(this) }
    var chatOnlineUsersAdapter: ChatOnlineUsersAdapter? = null
    lateinit var userlist: ArrayList<FriendUser>
    var chatMessagesAdapter: ChatAdapter? = null
    val cometChatUser by lazy { CometChatUser() }
    private val userData by lazy { getprefObject<User>(PrefKeys.INSTANCE.user) }

    private val firebaseHelper by lazy { FirebaseHelper() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentChange?.selectedPos(3)
        getUsers()
        cometChatUser.getOnlineUsers()
        userlist = ArrayList()
        chatMessagesAdapter = ChatAdapter(userlist, baselistener, cometChatUser)
        chat_messages_rv.adapter = chatMessagesAdapter
    }

    private fun getUsers() {
        showProgress()
        chatUserPresenter.getUsers(getUid()!!)
        //cometChatUser.getUserList(this)

    }
}

*/
