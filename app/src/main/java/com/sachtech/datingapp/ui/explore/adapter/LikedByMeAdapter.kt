/*
package com.sachtech.datingapp.ui.explore.adapter

import android.os.Bundle
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.ui.home.activity.AboutActivity
import com.sachtech.datingapp.utils.UserType
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.item_liked_by_me.view.*

class LikedByMeAdapter(
    val likeUserList: ArrayList<User>,
    val baselistener: KotlinBaseListener,
    val idList: ArrayList<String>,
    val visitors: String
) :
    BaseAdapter<String>(R.layout.item_liked_by_me) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Picasso.get().load(likeUserList[position].profilePic).into( holder.itemView.message_user_image)
      //  holder.itemView.message_user_image.loadImage(likeUserList[position].profilePic)
        holder.itemView.liked_by_me_user.text = likeUserList[position].name

        holder.itemView.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("uid",likeUserList[position].uid)
            bundle.putString("docid", idList.get(position))
            bundle.putString("user_type", visitors)

            baselistener.openA(AboutActivity::class,bundle)
        }
    }

    override fun getItemCount(): Int {
        return likeUserList.size
    }

}
*/
