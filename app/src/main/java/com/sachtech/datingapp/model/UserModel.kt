package com.sachtech.datingapp.model

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.sachtech.datingapp.R
import com.sachtech.datingapp.app.DatingApp
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.*
import com.sachtech.datingapp.data.LikeUsers
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.networking.apiHit
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded
import com.sachtech.datingapp.utils.getUid
import com.sachtech.datingapp.view.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserModel {

    val firebaseHelper by lazy { FirebaseHelper() }

    fun getAllUsers(userDetailView: UserView) {
        firebaseHelper.getAllUsers().addOnSuccessListener {
            userDetailView.onGettingAllUsers(it)
        }.addOnFailureListener {
            userDetailView.onFailure(it.localizedMessage)
        }
    }


    fun addUserToLikeList(user: User, userView: UserView) {
        val document = firebaseHelper.addUserToLikeList()
        Log.e("id", "" + user.uid)

        document.set(LikeUsers().apply {
            this.liked_to_user_id = user.uid
            this.liked_by_user_id = getUid()!!
            this.like_status = MatchStatus.INSTANCE.pending
        }).addOnCompleteListener {
            if (it.isSuccessful) {
                userView.onLikeUser(user)

            } else userView.onFailure(it.exception?.localizedMessage ?: "")


        }
    }

/*    fun addUserToBlockList(uid: User, userView: UserView) {
        val doc = firebaseHelper.addUserToBlockList(getUid()!!)
        doc.get().addOnSuccessListener {
            if (it.exists()) {
                val userList = ArrayList(it.toObject(LikeUser::class.java)?.userId)
                if (!userList.contains(uid.uid))
                    userList.add(uid.uid)
                doc.update("userId", userList).addOnSuccessListener {
                    userView.onBlockUser(uid)
                }.addOnFailureListener {
                    userView.onFailure(it.message!!)
                }
            } else {
                doc.set(BlockUser(arrayListOf(uid.uid))).addOnSuccessListener {
                    userView.onBlockUser(uid)

                }.addOnFailureListener {
                    userView.onFailure(it.message!!)
                }
            }
        }
            .addOnFailureListener {
                userView.onFailure(it.message!!)
            }
    }*/

    @SuppressLint("CheckResult")
    fun notifyLikedUser(user: User) {
        val notification = FirebaseNotification(
            to = user.fcm,
            notification = Notification(
                body = "${user.name} wants to connect you",
                title = "RNikah"
            )
        )
        apiHit().sendNotification(fcNotification = notification).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    if (it.success == 0) {
                        showToast("Liked")
                    }
                }, {
                    //showToast(it.localizedMessage)
                })
    }

    @SuppressLint("CheckResult")
    fun addUserToFriendList(user: User,onFriendAdd:OnFriendAdded) {
        val app_id = DatingApp.application?.resources?.getString(R.string.comet_app_id)!!
        val api_key = DatingApp.application?.resources?.getString(R.string.comet_api_key)!!

        Log.e("user added", Gson().toJson(Friend(accepted = listOf(user.uid))))
        apiHitter().addFriend(
            appId = app_id,
            apikey = api_key,
            uid = getUid()!!,
            friend = /*Gson().toJson(*/Friend(accepted = listOf(user.uid))
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {

                apiHitter().addFriend(
                    appId = app_id,
                    apikey = api_key,
                    uid = user.uid,
                    friend = /*Gson().toJson(*/Friend(accepted = listOf(getUid()!!))
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        showToast("Successfully added")
                        Log.e("user added", "user")
                        onFriendAdd.friendAdded(true)
                    }, {
onFriendAdd.friendAdded(false)
                        showToast(it.localizedMessage)
                        Log.e("user", it.localizedMessage)
                    })

            }, {
                showToast(it.localizedMessage)
                onFriendAdd.friendAdded(false)
                Log.e("user", it.localizedMessage)
            })


    }


    fun updateFriendRequestStatus(
        uid: String,
        frienduid: String,
        matchedView: UserView,
        isAccepted: Boolean
    ) {

        // update current user list
        Log.e("updateFriendReq", "called")
        val doc = firebaseHelper.getUserFriendList(uid)
        doc.document(frienduid).set(FriendStatusData().apply {
            this.uid = frienduid
            this.requestedBy = RequestBy.Other
            if (isAccepted)
                this.requestStatus = RequestSatus.accept
            else
                this.requestStatus = RequestSatus.decline

        })
            .addOnSuccessListener { void ->
                Log.e("updateFriendReq", "current user true")
                // update requested user lis
                val doc1 = firebaseHelper.getUserFriendList(frienduid)
                doc1.document(uid).set((FriendStatusData().apply {
                    this.uid = uid
                    this.requestedBy = RequestBy.Me
                    if (isAccepted)
                        this.requestStatus = RequestSatus.accept
                    else
                        this.requestStatus = RequestSatus.decline

                })).addOnSuccessListener { void ->
                    Log.e("updateFriendReq", "request user true")

                    matchedView.onRequestUpdateSuccess(isAccepted)
                }.addOnFailureListener {
                    Log.e("updateFriendReq", "" + it.localizedMessage)
                    matchedView.onFailure(it.localizedMessage)
                }

            }.addOnFailureListener {
                Log.e("updateFriendReq", "" + it.localizedMessage)
                matchedView.onFailure(it.localizedMessage)
            }

    }

    fun addfriendToMatchList(likedUser: User, userView: UserView, onFriendAdd: (Boolean) -> Unit) {
        val doc = firebaseHelper.addToUserFriendList(getUid()!!)
        doc.document(likedUser.uid).set((FriendStatusData().apply {
            this.uid = likedUser.uid
            this.requestStatus = RequestSatus.pending
            this.requestedBy = RequestBy.Me

        }))
            .addOnSuccessListener { void ->
                val doc = firebaseHelper.addToUserFriendList(likedUser.uid)
                doc.document(getUid()!!).set((FriendStatusData().apply {
                    this.uid = getUid()!!
                    this.requestStatus = RequestSatus.pending
                    this.requestedBy = RequestBy.Other

                }))
                    .addOnSuccessListener { void ->
                        userView.onSuccess()
                        onFriendAdd.invoke(true)

                    }.addOnFailureListener {
                        userView.onFailure(it.localizedMessage)
                    }

            }.addOnFailureListener {
                onFriendAdd.invoke(false)
                userView.onFailure(it.localizedMessage)
            }
    }

    fun getFriend(friendid: String, onFrienRecieved: (FriendStatusData?) -> Unit) {

        val doc = firebaseHelper.getUserFriend(getUid()!!, friendid)
        Log.e("getFriend", "called")

        doc.addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result == null) {
                    onFrienRecieved.invoke(null)
                } else {
                    Log.e(
                        "getFriend",
                        "has data" + (it.result?.toObject(FriendStatusData::class.java))
                    )
                    onFrienRecieved.invoke((it.result?.toObject(FriendStatusData::class.java)))
                }
            } else {
                Log.e("getFriend", "faldfdklsgksd")
            }
        }
    }

    fun addUserToUnlikeList(user: User, userView: UserView) {
        val doc = firebaseHelper.addUserToNotInterestedList(getUid()!!)
        doc.get().addOnSuccessListener {
            if (it.exists()) {
                val userList = it.toObject(UnlikeUser::class.java)?.userId?.let { it1 ->
                    ArrayList(
                        it1
                    )
                }
                if (!userList?.contains(user.uid)!!)
                    userList?.add(user.uid)
                doc.update("userId", userList).addOnSuccessListener {
                    userView.onUnlikeUser(user)
                }.addOnFailureListener {
                    userView.onFailure(it.message!!)
                }
            } else {
                doc.set(UnlikeUser(arrayListOf(user.uid))).addOnSuccessListener {
                    userView.onUnlikeUser(user)

                }.addOnFailureListener {
                    userView.onFailure(it.message!!)
                }
            }
        }
            .addOnFailureListener {
                userView.onFailure(it.message!!)
            }
    }

    fun addToVisitList(user: User, userView: UserView) {

        val document = firebaseHelper.addUserToVisitList()
        Log.e("id", "" + user.uid)

        document.set(VisitUsers().apply {
            this.visit_to_user_id = user.uid
            this.visit_by_user_id = getUid()!!
        }).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.e("visit", "success")
            } else userView.onFailure(it.exception?.localizedMessage ?: "")


        }
    }

    fun removeUserFromUnliekeList(user: User) {
        val doc = firebaseHelper.addUserToNotInterestedList(getUid()!!)
        doc.get().addOnSuccessListener {
            if (it.exists()) {
                val userList = ArrayList(it.toObject(UnlikeUser::class.java)?.userId)
                if (userList.contains(user.uid))
                    userList.remove(user.uid)
                doc.update("userId", userList).addOnSuccessListener {
                    showToast("success")
                }.addOnFailureListener {
                    showToast(it.localizedMessage)
                }
            } /*else {
                doc.set(UnlikeUser(arrayListOf(user.uid))).addOnSuccessListener {


                }.addOnFailureListener {

                }
            }
        }
            .addOnFailureListener {

            }    }*/
        }

    }

    fun removeUserFromLikeList(docId: String?) {
      val doc=firebaseHelper.firebaseFireStore.collection("likedUsers").document(docId!!)
        doc.delete().addOnSuccessListener {
            showToast("Removed from Likes")
        }.addOnFailureListener {
            showToast(it.localizedMessage)
        }
    }
}