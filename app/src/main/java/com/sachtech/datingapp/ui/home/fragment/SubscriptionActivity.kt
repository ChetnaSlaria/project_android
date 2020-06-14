/*
package com.sachtech.datingapp.ui.home.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.sachtech.datingapp.R
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.networking.GooglePlayBilling
import com.sachtech.datingapp.ui.home.adapter.SubscriptionAdapter
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.getprefObject
import kotlinx.android.synthetic.main.fragment_subscription.*
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate.onBackPressed

class SubscriptionActivity : AppCompatActivity(), GooglePlayBilling.OnLoadingSubscriptionProducts,
    PurchasesUpdatedListener, SubscriptionAdapter.OnSubscriptionSelected {

    val googlePlayBilling by lazy { GooglePlayBilling(this, this, this) }
    val firebaseHelper by lazy { FirebaseHelper() }
    var user: com.sachtech.datingapp.data.User? = null
    lateinit var updatedList: ArrayList<SkuDetails>
    lateinit var subscriptionAdapter: SubscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_subscription)
        googlePlayBilling.initGooglePlay()
        user = getprefObject(PrefKeys.USER)
        */
/* updatedList = ArrayList()
         subscriptionAdapter = SubscriptionAdapter(updatedList,this)*//*

        //subscription_rv.adapter = subscriptionAdapter
        subscription_back.setOnClickListener { onBackPressed() }
    }

    override fun onLoadingSku(skuDetailsList: MutableList<SkuDetails>) {
        */
/*  updatedList.addAll(skuDetailsList)
          subscriptionAdapter.notifyDataSetChanged()*//*

        subscriptionAdapter = SubscriptionAdapter(skuDetailsList as ArrayList<SkuDetails>) {
            //selectedSkuDetail = it
            if (user?.isSubscribed!!) {
                showToast("Already Subscribed.")
            } else {
                val billingFlowParams = BillingFlowParams
                    .newBuilder()
                    .setSkuDetails(it)
                    .build()
                // purchase = true
                googlePlayBilling.launchBillingFlow(this, billingFlowParams)
            }
        }
        subscription_rv.adapter = subscriptionAdapter
    }


    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {

        if (responseCode == BillingClient.BillingResponse.OK) {
            user?.isSubscribed = true
            purchases?.forEach {
                user?.subscriptionFrom = it.purchaseTime
                user?.subscriptionType = it.sku
            }
            updateUser(user!!)
        } else if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {

        }
    }

    private fun updateUser(user: com.sachtech.datingapp.data.User) {
        firebaseHelper.updateUserDetails(user)
    }

    override fun selectedSubscription(skuDetails: SkuDetails) {
        //  googlePlayBilling.launchBillingFlow(this, skuDetails)
    }
}*/
