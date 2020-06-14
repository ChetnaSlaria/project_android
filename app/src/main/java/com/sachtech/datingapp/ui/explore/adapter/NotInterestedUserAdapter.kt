/*
package com.sachtech.datingapp.ui.explore.adapter

import android.os.Bundle
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.ui.home.HomeActivity
import com.sachtech.datingapp.ui.home.activity.AboutActivity
import com.sachtech.datingapp.utils.UserType
import com.sachtech.datingapp.utils.loadImage
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.item_not_interested_user.view.*

class NotInterestedUserAdapter(
    val userlist: ArrayList<User>,
    val baselistener: KotlinBaseListener
) :
    BaseAdapter<String>(R.layout.item_not_interested_user) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Picasso.get().load(userlist[position].profilePic).into(holder.itemView.message_user_image)
       // holder.itemView.message_user_image.loadImage(userlist[position].profilePic)
        holder.itemView.notInterested_user.text = userlist[position].name
        holder.itemView.setOnClickListener {
            val bundle= Bundle()
            bundle.putString("uid",userlist.get(position).uid)
            bundle.putString("user_type", UserType.LIKEDBYME)
            baselistener.openA(AboutActivity::class,bundle)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }
}
*/
