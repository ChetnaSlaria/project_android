/*
package com.sachtech.datingapp.ui.chat.adapter


import android.os.Bundle
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.BaseMessage
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatMessaging
import com.sachtech.datingapp.cometChat.CometChatUser
import com.sachtech.datingapp.cometChat.listener.OnMessageUpdate
import com.sachtech.datingapp.data.FriendUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.getprefObject
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.item_chat_messages.view.*

class ChatAdapter(
    val userlist: ArrayList<FriendUser>,
    val baselistener: KotlinBaseListener,
    val cometChatUser: CometChatUser

) : BaseAdapter<String>(R.layout.item_chat_messages), OnMessageUpdate {

    private val userData by lazy { getprefObject<User>(PrefKeys.INSTANCE.user) }


    override fun onUpdate(messageList: BaseMessage) {

    }

    override fun onError(error: CometChatException?) {

    }


    private val cometMessage by lazy { CometChatMessaging(this) }

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Picasso.get().load(userlist[position].avatar).into(holder.itemView.message_user_image)

      //  holder.itemView.higlight.text = userlist[position].profileHighlight

        holder.itemView.message_user_name.text = userlist[position].name
        holder.itemView.video.setBackgroundResource(R.drawable.video_green)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("uid", userlist[position].uid)
            baselistener.openA(ChatMessageActivity::class, bundle)
        }
        holder.itemView.video.setOnClickListener {
          //  if (userData?.isSubscribed!!) {
                cometMessage.initiateCall(
                    userlist[position].uid!!,
                    CometChatConstants.CALL_TYPE_VIDEO
                )
            }
        }


       // if (userData?.isSubscribed!!) {
     */
/*   } else {

            holder.itemView.video.setBackgroundResource(R.drawable.video_grey)
        }*//*



    override fun getItemCount(): Int {
        return userlist.size
    }
}


*/
