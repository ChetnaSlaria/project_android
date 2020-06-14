/*
package com.sachtech.datingapp.ui.home

import android.os.Bundle
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatMessaging
import com.sachtech.datingapp.cometChat.CometConstants
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.ui.chat.UserFragment
import com.sachtech.datingapp.ui.explore.fragment.ExploreFragment
import com.sachtech.datingapp.ui.home.fragment.HomeFragment
import com.sachtech.datingapp.ui.profile.ProfileFragment
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.PreferencesKt.getprefObject
import gurtek.mrgurtekbase.KotlinBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : KotlinBaseActivity(R.id.home_container), OnFragmentChange {
    override fun selectedPos(int: Int) {
        when (int) {
            1 -> bottom_nav.selectedItemId=R.id.main_home
            2 -> bottom_nav.selectedItemId=R.id.main_explore
            3 -> bottom_nav.selectedItemId=R.id.main_chat
            4 -> bottom_nav.selectedItemId=R.id.main_profile
        }
    }

    lateinit var listener: OnIconClick
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragment<HomeFragment>()
        setBottomNavigationClick()
    }

    private fun setBottomNavigationClick() {
        val userData by lazy { getprefObject(PrefKeys.INSTANCE.user) }
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_home -> {
                    navigateToFragment<HomeFragment>()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.main_explore -> {
                  //  if(userData?.accountStatus=="verified")
                        navigateToFragment<ExploreFragment>()
                  */
/*  else
                        showToast("Your account is Not verified yet")*//*

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.main_chat -> {
                   // if(userData?.accountStatus=="verified")
                        navigateToFragment<UserFragment>()
                   */
/* else
                        showToast("Your account is Not verified yet")*//*

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.main_profile -> {
                    navigateToFragment<ProfileFragment>()
                    return@setOnNavigationItemSelectedListener true}
                else -> {
                    navigateToFragment<HomeFragment>()
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
      */
/*  bottom_nav.add(MeowBottomNavigation.Model(1, R.drawable.ic_card))
        bottom_nav.add(MeowBottomNavigation.Model(2, R.drawable.ic_explore))
        bottom_nav.add(MeowBottomNavigation.Model(3, R.drawable.ic_chat))
        bottom_nav.add(MeowBottomNavigation.Model(4, R.drawable.ic_user))
        bottom_nav.setOnClickMenuListener {

            when {
                it.icon == R.drawable.ic_card -> navigateToFragment<HomeFragment>()
                it.icon == R.drawable.ic_explore ->{

                }
                it.icon == R.drawable.ic_chat ->{

                }
                it.icon == R.drawable.ic_user ->
            }
        }*//*

    }

    override fun onStart() {
        super.onStart()
        CometChatMessaging().addCallListener(null, this, CometConstants.CallListener)
    }

    override fun onDestroy() {
        super.onDestroy()

        CometChatMessaging().removeCallListener(CometConstants.CallListener)
    }

}

interface OnFragmentChange {
    fun selectedPos(int: Int)
}

interface OnIconClick {
    fun onClick()
}
*/
