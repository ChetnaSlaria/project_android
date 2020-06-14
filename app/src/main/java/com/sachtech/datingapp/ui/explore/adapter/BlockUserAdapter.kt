/*
package com.sachtech.datingapp.ui.explore.adapter

import android.os.Bundle
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.ui.home.HomeActivity
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.item_block_user.view.*

class BlockUserAdapter(
    val blockUserList: ArrayList<User>,
    val unblockUser: UnblockUser,
   val baselistener: KotlinBaseListener
) :
    BaseAdapter<String>(R.layout.item_block_user) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.blocked_user.text = blockUserList.get(position).name
        Picasso.get().load(blockUserList[position].profilePic).into(holder.itemView.block_user_image)
        holder.itemView.unblock.setOnClickListener {
            unblockUser.onUnblockClick(blockUserList.get(position).uid,position)
        }
        holder.itemView.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("uid",blockUserList.get(position).uid)
            baselistener.openA(HomeActivity::class)
        }
    }

    override fun getItemCount(): Int {
        return blockUserList.size
    }
}

interface UnblockUser{
    fun onUnblockClick(uid: String, position: Int)
}
*/
