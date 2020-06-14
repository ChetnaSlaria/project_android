/*
package com.sachtech.datingapp.ui.chat.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.BaseMessage
import com.cometchat.pro.models.User
import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.network.Api
import com.sachtech.datingapp.data.*
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.MatchUserPresenter
import com.sachtech.datingapp.ui.chat.adapter.ChatMessageAdapter
import com.sachtech.datingapp.ui.home.activity.AboutActivity
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.view.UserView
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.KotlinBaseActivity
import gurtek.mrgurtekbase.gone
import gurtek.mrgurtekbase.hideKeyboard
import gurtek.mrgurtekbase.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chat_message.*


class ChatMessageActivity : KotlinBaseActivity(), OnMessageUpdate, UserView, ActionListener {
    private val blockUserDialog by lazy { BlockUserDialog() }
    private val reportDialog by lazy { ReportDialog() }
    lateinit var messageList: ArrayList<BaseMessage>
    lateinit var chatMessageAdapter: ChatMessageAdapter
    private val cometMessage by lazy { CometChatMessaging(this) }
    private val matchUserPresenter by lazy { MatchUserPresenter(this) }
    private val currentUserData by lazy { getprefObject<User>(PrefKeys.USER) }
    private val loginUser by lazy { getprefObject<com.sachtech.datingapp.data.User>(PrefKeys.USER) }
    private val firebaseHelper = FirebaseHelper()
    val cometChatUser by lazy { CometChatUser() }
    var user: User? = null
    var uid: String = ""
    override fun onYesClick() {
        val uids = ArrayList<String>()
        uids.add(uid)
        blockUserDialog.dismiss()
        blockUser(uids)
    }

    override fun onUnlikeUser(user: com.sachtech.datingapp.data.User) {

    }

    override fun onMatchUser(uid: String) {

    }

    override fun onGettingAllUsers(it: QuerySnapshot) {

    }

    override fun onLikeUser(user: com.sachtech.datingapp.data.User) {

    }


    override fun onSuccess() {

    }

    override fun onRequestUpdateSuccess(isAccepted: Boolean) {
        super.onRequestUpdateSuccess(isAccepted)
        request_view.gone()
        if (isAccepted) {
            Log.e("onRequestUpdateSuccess", "" + isAccepted)

        } else {
            Log.e("onRequestUpdateSuccess", "" + isAccepted)
            finish()

        }
    }


    override fun onFailure(message: String) {

    }

    override fun onError(error: CometChatException?) {
        showToast(error?.localizedMessage!!)
    }

    override fun onUpdate(message: BaseMessage) {
        messageList.add(message)
        chatMessageAdapter.notifyDataSetChanged()
        chat_message_rv.scrollToPosition(messageList.size - 1)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_message)
        uid = intent?.getStringExtra("uid")!!
        //getAllMessages()
        if(!loginUser?.isSubscribed!!)
        {
           audioCall.gone()
            videoCall.gone()
        }
        cometChatUser.getUserDetails(uid)
        {
            this.user = it
            setUserDetails(it)
            getFriend(friendid = uid) {
                Log.e("onFriendRecieved", "" + it?.requestStatus)
                if (it == null) {
                    request_view.visible()

                } else {
                    if (it.requestedBy == RequestBy.Me) {
                        request_view.gone()

                       */
/* if (it.requestStatus == RequestSatus.pending)
                            request_pendingView.visible()
                        else request_pendingView.gone()*//*


                    } else {
                        if (it.requestStatus == RequestSatus.pending)
                            request_view.visible()
                        else request_view.gone()
                    }

                }
            }
        }


        accept.setOnClickListener {
            request_view.gone()
            matchUserPresenter.updateFriendRequestStatus(
                uid = getCurrentUser()!!.uid,
                frienduid = uid,
                isAccepted = true
            )
        }
        decline.setOnClickListener {
            request_view.gone()
            matchUserPresenter.updateFriendRequestStatus(
                uid = getCurrentUser()!!.uid,
                frienduid = uid,
                isAccepted = false
            )
            callRemoveFriendApi()
        }
        setOnClick()

        messageList = ArrayList()

        chatMessageAdapter =
            ChatMessageAdapter(messageList, CometChat.getLoggedInUser().uid)
        chat_message_rv.adapter = chatMessageAdapter
        chat_message_rv.scrollToPosition(messageList.size - 1)

    }

    private fun getAllMessages() {
        messageList.clear()
        cometMessage.fetchAllMessage(uid, ongettingMessage = object : OnGettingMessage {
            override fun onGettingMessage(baseMessage: BaseMessage) {
                messageList.add(baseMessage)
                chatMessageAdapter.notifyDataSetChanged()
            }
        })
    }


    fun getFriend(friendid: String, onFrienRecieved: (FriendStatusData?) -> Unit) {

        val doc = firebaseHelper.getUserFriend(getUid()!!, friendid)
        Log.e("getFriend", "called")

        doc.addOnCompleteListener {

            if (it.isSuccessful) {
                Log.e("getFriend", "isScuucxcdcds")
                Log.e("getFriend", "isScuucxcdcds" + it.result)

                if (it.result == null) {
                    onFrienRecieved.invoke(null)
                } else {
                    Log.e(
                        "getFriend",
                        "has data" + (it.result?.toObject(FriendStatusData::class.java))
                    )
                    onFrienRecieved.invoke((it.result?.toObject(FriendStatusData::class.java)))
                }
            } else {
                Log.e("getFriend", "faldfdklsgksd")
            }
        }
    }

    private fun setUserDetails(it: User) {
        if(it!=null) {
            if (it.avatar != null) {
                Picasso.get().load(it.avatar).into(chat_user_image)
               // chat_user_image.loadImage(it.avatar)
            }

            chat_user_name.text = it.name
        }
    }

    private fun setOnClick() {
        chat_back.setOnClickListener { onBackPressed() }
        audioCall.setOnClickListener {
            cometMessage.initiateCall(
                user?.uid!!,
                CometChatConstants.CALL_TYPE_AUDIO
            )
        }
        videoCall.setOnClickListener {
            cometMessage.initiateCall(
                user?.uid!!,
                CometChatConstants.CALL_TYPE_VIDEO
            )
        }
        message_send.setOnClickListener {
            cometMessage.sendTextMessage(uid, message_edit.text.toString())
            message_edit.setText("")
            hideKeyboard()
        }

        options.setOnClickListener {
            option_layout.visible()
        }
        view_profile.setOnClickListener {
            option_layout.gone()
            val bundle = Bundle()
            bundle.putString("uid", uid)
            openA(AboutActivity::class, bundle)
            // showToast("screen pending")
        }
        unlike.setOnClickListener {
            callRemoveFriendApi()
            option_layout.gone()
        }

        block.setOnClickListener {

            blockUserDialog.show(supportFragmentManager, "block")

            option_layout.gone()
        }
        report.setOnClickListener {
            option_layout.gone()
            reportDialog.getChatUserData(user!!)
            reportDialog.show(supportFragmentManager, "report")
        }
    }

    @SuppressLint("CheckResult")
    private fun callRemoveFriendApi() {
        Api.getService().deleteFriend(
            uid = currentUserData?.uid!!,
            appId = DatingApp.application?.getString(R.string.comet_app_id)!!,
            apikey = DatingApp.application?.getString(R.string.comet_api_key)!!,
            friend = RemoveFriendList(listOf(user?.uid!!))
        ).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe({
            //showToast(it.data.message)
            onBackPressed()
        }, {
            showToast(it.localizedMessage)
        })
    }

    private fun blockUser(uid: ArrayList<String>) {
        showProgress()
        CometChat.blockUsers(uid, object : CometChat.CallbackListener<HashMap<String, String>>() {
            override fun onSuccess(resultMap: HashMap<String, String>) {
                val blockUserData =
                    Blockeduser(
                        currentUserData!!.uid,
                        currentUserData!!.name,
                        user?.uid!!,
                        user?.name!!
                    )
                firebaseHelper.blockedUsers(block = blockUserData).addOnSuccessListener {
                    hideProgress()
                    showToast("blocked successfully")
                    onBackPressed()
                }.addOnFailureListener {
                    showToast(it.localizedMessage)
                    hideProgress()
                }
            }

            override fun onError(e: CometChatException) {
                hideProgress()
                showToast(e.localizedMessage.toString())
            }
        })
    }

    override fun onStart() {
        super.onStart()
        cometMessage.addCallListener(null, this, CometConstants.CallListener)
        cometMessage.addMessageListener(CometConstants.MessageListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        cometMessage.removeCallListener(CometConstants.CallListener)
        cometMessage.removeMessageListener(CometConstants.MessageListener)
    }

    override fun onResume() {
        super.onResume()
        getAllMessages()
    }

}

*/
