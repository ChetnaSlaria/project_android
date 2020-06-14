/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.UnlikeUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.explore.adapter.NotInterestedUserAdapter
import com.sachtech.datingapp.ui.home.OnFragmentChange
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_not_interested_user.*

class NotInterestedUserFragment : KotlinBaseFragment(R.layout.fragment_not_interested_user) {
    val firebaseHelper by lazy { FirebaseHelper() }
    lateinit var userlist: ArrayList<User>
    var notInterestedAdapter: NotInterestedUserAdapter? = null
    var onFragmentChange: OnFragmentChange?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
onFragmentChange?.selectedPos(2)
        userlist = ArrayList()
        notInterestedAdapter = NotInterestedUserAdapter(userlist, baselistener)
        notIntersted_rv.adapter = notInterestedAdapter
        getUsers()

    }

    private fun getUsers() {
        showProgress()
        firebaseHelper.getNotInterestedUsers().addOnSuccessListener {
            val unlikeUsers = it.toObject(UnlikeUser::class.java)
            if(unlikeUsers?.userId.isNullOrEmpty()==false)
            {
                unlikeUsers?.userId?.forEach {
                    Log.e("notInterseted", unlikeUsers.userId?.size.toString())

                    firebaseHelper.getUserDetailsFromDatabase(it).addOnSuccessListener {
                        hideProgress()
                        val users = it.toObject(User::class.java)
                        Log.e("notuser", users.toString())
                        if (users != null) {
                            userlist.add(users)
                            notInterestedAdapter!!.notifyDataSetChanged()
                        }
                    }.addOnFailureListener {
                        hideProgress()
                        showToast(it.localizedMessage)
                    }
            }

            }
            else
            {
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
