/*
package com.sachtech.datingapp.ui.home.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.isNotNull
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.ui.home.activity.AboutActivity
import com.sachtech.datingapp.ui.home.fragment.HomeFragment
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import jp.wasabeef.blurry.Blurry
import com.sachtech.datingapp.ui.home.fragment.AboutFragment
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.Preferences.setprefObject
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.item_streaming.view.*
import java.util.*


class AboutAdapter(
    private val context: HomeFragment,
    val onCardAction: OnCardAction,
    val baselistener: KotlinBaseListener

) : RecyclerView.Adapter<AboutAdapter.MyStreamingAdapter>() {

    private val userData by lazy { getprefObject<User>(PrefKeys.INSTANCE.user) }
    private var arraylist: ArrayList<User> = arrayListOf()


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyStreamingAdapter {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_streaming, p0, false)
        return MyStreamingAdapter(view)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    fun setNewList(list: ArrayList<User>) {
        arraylist.clear()
        arraylist.addAll(list)
        notifyDataSetChanged()

    }


    override fun onBindViewHolder(holder: MyStreamingAdapter, position: Int) {
        var pos = holder.adapterPosition

        if (arraylist[position].private) {
            holder.itemView.lock.visibility = View.VISIBLE
            holder.itemView.text_private.visibility = View.VISIBLE
            Picasso.get()
                .load(arraylist[pos].profilePic)
                .transform(BlurTransformation(holder.itemView.image_view_lay.context, 25, 1))
                .into(holder.itemView.holder_image)
        } else {
            holder.itemView.lock.visibility = View.GONE
            holder.itemView.text_private.visibility = View.GONE
            Picasso.get()
                .load(arraylist[pos].profilePic)
                .into(holder.itemView.holder_image)
        }

        holder.itemView.address.text=arraylist[position].profileHighlight
        holder.itemView.user_name.text = "${arraylist[position].name} , ${arraylist[position].age}"
        if(arraylist[position].gender=="Male") {
            holder.itemView.symbol.setBackgroundResource(R.drawable.male)
        }else{
            holder.itemView.symbol.setBackgroundResource(R.drawable.female_color)
        }
        holder.itemView.like.setOnClickListener {
            pos = position
            Constants.type = Constants.MESSAGEWITHOUTLIKE
            onCardAction.onCardLike(arraylist[holder.adapterPosition])
        }
        holder.itemView.message.setOnClickListener {

            if(userData?.accountStatus=="verified"){
            pos = position
            setprefObject(PrefKeys.SELECTEDCHATUSER, arraylist[holder.adapterPosition])
            Constants.type = Constants.MESSAGEWITHLIKE
            onCardAction.sendMessage(arraylist[holder.adapterPosition], holder.adapterPosition)
            }else{
                showToast("Your account is Not verified yet")
            }
        }
        holder.itemView.dislike.setOnClickListener {
            onCardAction.onCardUnlike(arraylist[holder.adapterPosition])
        }
        holder.itemView.expand.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("uid", arraylist[position].uid)
            val aboutFragment = AboutFragment(baselistener)
            aboutFragment.arguments = bundle
            aboutFragment.show(context.fragmentManager!!, aboutFragment.tag)

        }

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MyStreamingAdapter(view: View) : RecyclerView.ViewHolder(view)
}

interface OnCardAction {
    fun onCardLike(user: User)
    fun onCardUnlike(user: User)
    fun sendMessage(user: User, position: Int)
}*/
