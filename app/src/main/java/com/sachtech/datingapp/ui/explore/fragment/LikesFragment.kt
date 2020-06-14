/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.LikeUsers
import com.sachtech.datingapp.data.MatchStatus
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.MatchUserPresenter
import com.sachtech.datingapp.presenter.UserPresenter
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity
import com.sachtech.datingapp.ui.explore.adapter.LikesAdapter
import com.sachtech.datingapp.ui.explore.adapter.OnProfilesMatch
import com.sachtech.datingapp.ui.explore.adapter.listener.OnProfilesMatch
import com.sachtech.datingapp.ui.home.OnFragmentChange
import com.sachtech.datingapp.utils.getUid
import com.sachtech.datingapp.view.UserView
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_likes.*

class LikesFragment : KotlinBaseFragment(R.layout.fragment_likes), OnProfilesMatch, UserView,
    onMatched {
    override fun onMatched() {
        likeUserList?.clear()
        idList.clear()
        statusList.clear()
        getUsers()
    }

    override fun onGettingAllUsers(it: QuerySnapshot) {

    }

    override fun onLikeUser(user: User) {

    }

    override fun onUnlikeUser(user: User) {

    }

    override fun onSuccess() {

    }

    override fun onFailure(message: String) {

    }

    override fun onMatchUser(uid: String) {

    }

    override fun matchedProfile(username: User) {
        showMatchDialog(username)
    }

    override fun OnMessageClick(user: User) {
        userPresenter.addToChatFriendList(user) {
            matchPresenter.addFriendList(user) {
                hideProgress()
                val bundle = Bundle()
                bundle.putString("uid", user.uid)
                baselistener.openA(ChatMessageActivity::class, bundle)
            }
        }
    }

    var onFragmentChange: OnFragmentChange? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }

    private fun showMatchDialog(user: User) {
        val dialog = MatchDialog().apply {
            selectedUser(user)
        }.apply {
            onMatchedUser(this@LikesFragment)
        }
        dialog.show(activity?.supportFragmentManager!!, "")
    }

    private val firebaseHelper by lazy { FirebaseHelper() }
    lateinit var likedByMeAdapter: LikesAdapter
    var likeUserList: ArrayList<User>? = null
    lateinit var idList: ArrayList<String>
    lateinit var statusList: ArrayList<String>
    val userPresenter by lazy { UserPresenter(this) }
    val matchPresenter by lazy { MatchUserPresenter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentChange?.selectedPos(2)
        getUsers()
        likeUserList = ArrayList()
        idList = ArrayList()
        statusList = ArrayList()
        likedByMeAdapter = LikesAdapter(likeUserList!!, baselistener, idList, statusList, this)
        liked_to_me_rec.adapter = likedByMeAdapter
    }

    private fun getUsers() {

        showProgress()
        firebaseHelper.getLikedToMeUserList(getUid()!!).get().addOnSuccessListener {
            var likeUsers = it.toObjects(LikeUsers::class.java)
            if (!likeUsers.isNullOrEmpty()) {
                likeUsers = likeUsers.distinctBy { it.liked_by_user_id }
                likeUsers = likeUsers.filter {
                    it.like_status != MatchStatus.DECLINED
                }
                for (i in 0 until likeUsers.size) {
                    Log.e("likeUsers", likeUsers.get(i).liked_by_user_id.toString())
                    idList.add(it.documents[i].id)
                    statusList.add(likeUsers.get(i).like_status)
                    Log.e("idlist", "${idList[i]},${likeUsers.get(i).liked_by_user_id}")
                    firebaseHelper.getUserDetailsFromDatabase(likeUsers[i].liked_by_user_id.toString())
                        .addOnSuccessListener {
                            hideProgress()
                            val users = it.toObject(User::class.java)
                            Log.e("users", users.toString())
                            if (users != null) {
                                likeUserList?.add(users)
                                likedByMeAdapter.notifyDataSetChanged()
                            }

                        }.addOnFailureListener {
                            hideProgress()
                            showToast(it.localizedMessage)
                        }
                }
            } else {
                hideProgress()
                showToast("No User found")
            }
        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }
    }
}
*/
