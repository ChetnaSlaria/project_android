/*
package com.sachtech.datingapp.ui.explore.adapter

import android.net.Uri
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.utils.loadImage
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(val items: ArrayList<User>?,val callTypeListener: CallTypeListener) : BaseAdapter<String>(R.layout.item_search) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Picasso.get().load(items!![position].profilePic).into(holder.itemView.search_image)
      //  holder.itemView.search_image.loadImage(Uri.parse(items!![position].profilePic))
        holder.itemView.search_name.text = items[position].name
        holder.itemView.audio_call.setOnClickListener {  callTypeListener.onAudioCallSelected(items[position].uid)}
        holder.itemView.audio_call.setOnClickListener { callTypeListener.onVideoCallSelected(items[position].uid) }
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

}
interface CallTypeListener{
    fun onAudioCallSelected(uid: String)
    fun onVideoCallSelected(uid: String)
}*/
