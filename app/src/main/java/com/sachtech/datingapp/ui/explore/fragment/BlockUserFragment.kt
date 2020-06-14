/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.sachtech.datingapp.R
import com.sachtech.datingapp.data.Blockeduser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.explore.adapter.BlockUserAdapter
import com.sachtech.datingapp.ui.explore.adapter.listener.UnblockUser
import com.sachtech.datingapp.ui.home.OnFragmentChange
import com.sachtech.datingapp.utils.getUid
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_block_user.*


class BlockUserFragment : KotlinBaseFragment(R.layout.fragment_block_user), UnblockUser {
    override fun onUnblockClick(uid: String, position: Int) {
        showProgress()
        Log.i("uid of unblock user :",uid)
        CometChat.unblockUsers(listOf(uid),
            object : CometChat.CallbackListener<HashMap<String, String>>() {
                override fun onSuccess(p0: HashMap<String, String>?) {
                    val doc = firebaseHelper.firebaseFireStore.collection("blockedUser")
                    val collection = doc.whereEqualTo("block_to_user_id", uid)

                    collection.get().addOnFailureListener {
                        hideProgress()
                        showToast(it.localizedMessage)
                    }.addOnCompleteListener {
                        hideProgress()
                        for (document in it.result!!) {
                            Log.e("uid of unblock user ",it.result.toString())
                            doc.document(document.id).delete().addOnCompleteListener {
                                blockUserList.clear()
                                likedByMeAdapter.notifyItemRemoved(position)
                                getBlockedUsers()
                            }.addOnFailureListener {
                                hideProgress()
                                showToast(it.localizedMessage)
                            }

                        }

                    }
                }

                override fun onError(p0: CometChatException?) {
                    hideProgress()
                    showToast(p0.toString())
                }

            })
    }
    var onFragmentChange: OnFragmentChange?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    val firebaseHelper by lazy { FirebaseHelper() }
    lateinit var likedByMeAdapter: BlockUserAdapter
    lateinit var blockUserList: ArrayList<User>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBlockedUsers()
        blockUserList = ArrayList()
        likedByMeAdapter =
            BlockUserAdapter(blockUserList, this,baselistener)
        blockUser_rv.adapter = likedByMeAdapter
    }

    private fun getBlockedUsers() {
        firebaseHelper.getBlockedUser(getUid()!!).addOnSuccessListener {
            var blockedUserData = it.toObjects(Blockeduser::class.java)
            if (blockedUserData.isNotEmpty()) {
                showProgress()
              blockedUserData=blockedUserData.distinctBy { it.block_to_user_id }
                for (i in 0 until  blockedUserData.size) {
                    Log.e("users", blockedUserData.get(i).block_to_user_id)
                    firebaseHelper.getUserDetailsFromDatabase(blockedUserData.get(i).block_to_user_id)
                        .addOnSuccessListener {
                            hideProgress()
                            Log.e("users",it.toString())
                            val users = it.toObject(User::class.java)
                            Log.e("users", users.toString())
                            if (users != null) {
                                blockUserList.add(users)
                                likedByMeAdapter.notifyDataSetChanged()
                            }else{
                                showToast("No user found")
                              //  showToast("not found")
                            }
                        }.addOnFailureListener {
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
            showToast(it.localizedMessage)
        }

    }
}

*/
