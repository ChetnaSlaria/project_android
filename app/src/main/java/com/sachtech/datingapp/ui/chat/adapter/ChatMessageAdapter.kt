/*
package com.sachtech.datingapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.Call
import com.cometchat.pro.models.BaseMessage
import com.cometchat.pro.models.TextMessage
import com.sachtech.datingapp.R
import kotlinx.android.synthetic.main.item_chat_message.view.*


class ChatMessageAdapter(
    val messageList: ArrayList<BaseMessage>,
    val uid: String
) : RecyclerView.Adapter<ChatMessageAdapter.MyMessageAdapter>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyMessageAdapter {
        val view=LayoutInflater.from(p0.context)
        return MyMessageAdapter(view.inflate(R.layout.item_chat_message,p0,false))
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(p0: MyMessageAdapter, p1: Int) {
        if(messageList[p1].category==CometChatConstants.CATEGORY_CALL)
        {
            val call = (messageList[p1] as Call).callStatus
            p0.itemView.call_status.visibility=View.VISIBLE
            p0.itemView.call_status.text="Call $call"
            p0.itemView.receiver_message.visibility=View.GONE
            p0.itemView.sender_message.visibility=View.GONE
        }
        else if (messageList[p1].category == CometChatConstants.CATEGORY_MESSAGE) {
            if (uid == messageList[p1].sender?.uid) {
               p0.itemView.sender_message.visibility=View.VISIBLE
                p0.itemView.sender_message_text.text=(messageList[p1] as TextMessage).text
                p0.itemView.receiver_message.visibility=View.GONE
                p0.itemView.call_status.visibility=View.GONE
            }
            else
            {
                p0.itemView.receiver_message.visibility=View.VISIBLE
                p0.itemView.receiver_message_text.text=(messageList[p1] as TextMessage).text
                p0.itemView.call_status.visibility=View.GONE
                p0.itemView.sender_message.visibility=View.GONE
            }
            }
        }
    inner class MyMessageAdapter(view:View):RecyclerView.ViewHolder(view)
}



*/
