package com.sachtech.datingapp.view

import com.sachtech.datingapp.data.GetFriends
import com.sachtech.datingapp.listener.FailureListener

interface ChatUserView : FailureListener {
    fun onGettingFriends(it: GetFriends)

}
