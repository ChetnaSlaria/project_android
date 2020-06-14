/*
package com.sachtech.datingapp.ui.explore.adapter

import android.os.Bundle
import android.util.Log
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.LikeUsers
import com.sachtech.datingapp.data.MatchStatus
import com.sachtech.datingapp.data.UnlikeUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.home.activity.AboutActivity
import com.sachtech.datingapp.utils.CircleTransform
import com.sachtech.datingapp.utils.UserType
import com.sachtech.datingapp.utils.getUid
import com.sachtech.datingapp.utils.loadImage
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.gone
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import gurtek.mrgurtekbase.visible
import kotlinx.android.synthetic.main.item_likes.view.*

class LikesAdapter(
    val userlist: ArrayList<User>,
    val baselistener: KotlinBaseListener,
    val idList: ArrayList<String>,
    val userStatus: ArrayList<String>,
    val onProfilesMatch: OnProfilesMatch
) :
    BaseAdapter<String>(R.layout.item_likes) {
    val firebaseHelper by lazy { FirebaseHelper() }
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Log.e("profilepic",userlist[position].profilePic)
        Picasso.get().load(userlist[position].profilePic).transform(CircleTransform()).into(holder.itemView.message_user_image)
       // holder.itemView.message_user_image.loadImage(userlist[position].profilePic)
        holder.itemView.liked_by_me_user.text = userlist[position].name

        if (userStatus[position] == MatchStatus.ACCEPTED) {
            holder.itemView.likeAccept.gone()
            holder.itemView.likeDecline.gone()
            holder.itemView.likeMessage.visible()
        }
        else
        {
            holder.itemView.likeMessage.gone()
            holder.itemView.likeAccept.visible()
            holder.itemView.likeDecline.visible()
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("uid", userlist.get(position).uid)
            bundle.putString("docid", idList.get(position))
            bundle.putString("user_type", UserType.LIKE)
            baselistener.openA(AboutActivity::class, bundle)
        }
        holder.itemView.likeAccept.setOnClickListener {
            addToLikeList(idList[position], userlist[position])
        }

        holder.itemView.likeDecline.setOnClickListener {
            addToUnlikeList(userlist[position], idList[position])
        }

        holder.itemView.likeMessage.setOnClickListener {
            openMessageActivity(userlist[position])
        }

    }

    private fun openMessageActivity(user: User) {
        onProfilesMatch.OnMessageClick(user)
    }

    private fun addToUnlikeList(user: User, id: String) {
        val doc = firebaseHelper.addUserToNotInterestedList(getUid()!!)
        doc.get().addOnSuccessListener {
            if (it.exists()) {
                val userList = ArrayList<String>(it.toObject(UnlikeUser::class.java)?.userId!!)
                if (!userList.contains(user.uid))
                    userList.add(user.uid)
                doc.update("userId", userList).addOnSuccessListener {
                    updateMatchStatus(id, user, MatchStatus.DECLINED)
                }.addOnFailureListener {
                    showToast(it.message!!)
                }
            } else {
                doc.set(UnlikeUser(arrayListOf(user.uid))).addOnSuccessListener {
                    updateMatchStatus(id, user, MatchStatus.DECLINED)
                }.addOnFailureListener {
                    showToast(it.message!!)
                }
            }
        }
            .addOnFailureListener {
                showToast(it.message!!)
            }
    }

    private fun addToLikeList(s: String, user: User) {
        val document = firebaseHelper.addUserToLikeList()
        Log.e("id", "" + user.uid)

        document.set(LikeUsers().apply {
            this.liked_to_user_id = user.uid
            this.liked_by_user_id = getUid()!!
            this.like_status = MatchStatus.ACCEPTED
        }).addOnCompleteListener {
            if (it.isSuccessful) {
                updateMatchStatus(s, user, MatchStatus.ACCEPTED)
            } else showToast(it.exception?.localizedMessage ?: "")

        }
    }

    private fun updateMatchStatus(
        doc: String,
        user: User,
        status: String
    ) {
        val likeUser = LikeUsers()
        likeUser.liked_by_user_id = user.uid
        likeUser.liked_to_user_id = getUid()!!
        likeUser.like_status = status
        firebaseHelper.updateLikeStatus(doc, likeUser).addOnFailureListener {
            showToast(it.localizedMessage)
        }.addOnSuccessListener {
            if (status == MatchStatus.ACCEPTED) {
                onProfilesMatch.matchedProfile(user)
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int {
        return userlist.size
    }
}

interface OnProfilesMatch {
    fun matchedProfile(username: User)
    fun OnMessageClick(user: User)
}*/
