/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.data.VisitUsers
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.explore.adapter.LikedByMeAdapter
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange
import com.sachtech.datingapp.utils.UserType
import com.sachtech.datingapp.utils.getUid
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_visitors.*

class VisitorsFragment : KotlinBaseFragment(R.layout.fragment_visitors) {
    private val firebaseHelper by lazy { FirebaseHelper() }
    lateinit var likedByMeAdapter: LikedByMeAdapter
    var likeUserList: ArrayList<User>? = null
    lateinit var idList:ArrayList<String>
    var onFragmentChange: OnFragmentChange?=null

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
            LikedByMeAdapter(likeUserList!!, activity!!, idList,UserType.VISITORS)
        visit_to_me_rec.adapter = likedByMeAdapter
    }

    private fun getUsers() {
        showProgress()
        firebaseHelper.getVisitUserList(getUid()!!).get().addOnSuccessListener {
            var likeUsers = it.toObjects(VisitUsers::class.java)
            if (!likeUsers.isNullOrEmpty()) {

                likeUsers = likeUsers.distinctBy { it.visit_by_user_id }
                for (i in 0 until likeUsers.size) {
                    idList.add(it.documents.get(i).id)
                    Log.e("likeUsers", likeUsers.get(i).visit_by_user_id.toString())
                    firebaseHelper.getUserDetailsFromDatabase(likeUsers.get(i).visit_by_user_id.toString())
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
            }
            else
            {
                hideProgress()
showToast("No user Found")
            }
        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }
    }

}
*/
