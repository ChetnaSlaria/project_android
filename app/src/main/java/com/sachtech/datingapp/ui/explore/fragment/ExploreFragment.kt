/*
package com.sachtech.datingapp.ui.explore.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cometchat.pro.constants.CometChatConstants
import com.google.android.material.tabs.TabLayout
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatMessaging
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.ui.explore.adapter.CallTypeListener
import com.sachtech.datingapp.ui.explore.adapter.SearchAdapter
import com.sachtech.datingapp.ui.explore.adapter.listener.CallTypeListener
import com.sachtech.datingapp.ui.home.OnFragmentChange
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_search.*


class ExploreFragment : KotlinBaseFragment(R.layout.fragment_search),
    CallTypeListener {

    override fun onAudioCallSelected(uid: String) {
        cometMessage.initiateCall(uid, CometChatConstants.CALL_TYPE_AUDIO)
    }

    override fun onVideoCallSelected(uid: String) {
        cometMessage.initiateCall(uid, CometChatConstants.CALL_TYPE_VIDEO)
    }

    val cometMessage by lazy { CometChatMessaging() }
    var searchAdapter: SearchAdapter? = null
    var arrayList: ArrayList<User>? = null
    val firebaseHelper by lazy { FirebaseHelper() }
    var onFragmentChange: OnFragmentChange?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentChange?.selectedPos(2)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentChange?.selectedPos(2)
        arrayList = ArrayList()
        setOnClickListener()
        openFragment(LikesFragment())
        searchAdapter =
            SearchAdapter(
                arrayList ,
                this
            )
    }


    private fun setOnClickListener() {
        tabLayout.setOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab?> {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0!!.position) {
                    0 -> openFragment(LikesFragment())
                    1 -> openFragment(VisitorsFragment())
                    2 -> openFragment(LikedByMeFragment())
                    3 -> openFragment(NotInterestedUserFragment())
                    4 -> openFragment(BlockUserFragment())
                }
            }
        })

    }

    private fun openFragment(kClass: Fragment) {
        val videoFragment = kClass
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.exploreView, videoFragment).commit()
        // baselistener.addChildFragment(childFragmentManager, R.id.exploreView, kClass)
    }

}
*/
