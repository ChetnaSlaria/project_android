/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.LikeUsers
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.explore.adapter.LikedByMeAdapter
import com.sachtech.datingapp.ui.home.OnFragmentChange
import com.sachtech.datingapp.utils.UserType
import com.sachtech.datingapp.utils.getUid
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_liked_by_me.*

class LikedByMeFragment : KotlinBaseFragment(R.layout.fragment_liked_by_me) {
    private val firebaseHelper by lazy { FirebaseHelper() }
    lateinit var likedByMeAdapter: LikedByMeAdapter
    var likeUserList: ArrayList<User>? = null
    lateinit var idList:ArrayList<String>

    var onFragmentChange:OnFragmentChange?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentChange?.selectedPos(2)
        getUsers()
        likeUserList = ArrayList()
        idList= ArrayList()
        likedByMeAdapter =
            LikedByMeAdapter(likeUserList!!, baselistener, idList, UserType.LIKEDBYME)
        likedByMe_rv.adapter = likedByMeAdapter
    }

    private fun getUsers() {
        showProgress()
        firebaseHelper.getLikedUserList(getUid()!!).get().addOnSuccessListener {
            var likeUsers = it.toObjects(LikeUsers::class.java)
            if (likeUsers.isNullOrEmpty() == false) {
                showProgress()
                likeUsers = likeUsers.distinctBy { it.liked_to_user_id }
                for (i in 0 until likeUsers.size) {
                    idList.add(it.documents.get(i).id)
                    Log.e("likeUsers", likeUsers.get(i).liked_to_user_id.toString())
                    firebaseHelper.getUserDetailsFromDatabase(likeUsers.get(i).liked_to_user_id.toString())
                        .addOnSuccessListener {
                            hideProgress()
                            val users = it.toObject(User::class.java)
                            Log.e("users", users.toString())
                            if(users!=null) {
                                likeUserList?.add(users)
                                likedByMeAdapter.notifyDataSetChanged()
                            }
                        }.addOnFailureListener {
                            hideProgress()
                            showToast(it.localizedMessage)
                        }
                }
            }
            else{
                hideProgress()
                showToast("No user found")
            }
        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }
    }
}
*/
