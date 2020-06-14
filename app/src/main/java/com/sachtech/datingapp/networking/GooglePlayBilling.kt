package com.sachtech.datingapp.networking

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.*
import com.sachtech.datingapp.extensions.showToast

class GooglePlayBilling(
    val context: Context, private val subscriptionProducts: OnLoadingSubscriptionProducts,
    private val purchasesUpdatedListener: PurchasesUpdatedListener
) {
    var billingClient: BillingClient? = null
    val skuList = listOf("monthlysubscription", "quarterlysubscription","halfyearly","yearly")
    fun initGooglePlay() {
        setUpBilling()
    }

    private fun setUpBilling() {
        billingClient =
            BillingClient.newBuilder(context).setListener(purchasesUpdatedListener).build()
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                Log.e("googleBilling error", "error")
            }

            override fun onBillingSetupFinished(responseCode: Int) {
                if (responseCode == BillingClient.BillingResponse.OK) {
                   // showToast("Ok")
                    loadProducts(skuList)

                } else {
                    showToast("Billing response : $responseCode")
                }
            }
        })
    }

    fun loadProducts(skuList: List<String>) {
        if (billingClient?.isReady!!) {
            val skuParams = SkuDetailsParams.newBuilder().setSkusList(skuList)
                .setType(BillingClient.SkuType.SUBS).build()
            billingClient?.querySkuDetailsAsync(
                skuParams
            ) { responseCode, skuDetailsList ->
                if (responseCode == BillingClient.BillingResponse.OK)
                    subscriptionProducts.onLoadingSku(skuDetailsList)
                else showToast("Error")
            }
        } else {
            showToast("Billing client not ready")
        }
    }

    fun launchBillingFlow(activity: Activity, skuDetails: BillingFlowParams) {

        billingClient?.launchBillingFlow(activity, skuDetails)
    }


    fun clearHistory() {
        billingClient?.queryPurchases(BillingClient.SkuType.SUBS)?.purchasesList?.forEach {
            billingClient?.consumeAsync(
                it.purchaseToken
            ) { responseCode, purchaseToken ->
                if (responseCode == BillingClient.BillingResponse.OK && purchaseToken != null)
                    showToast("onPurchases Updated consumeAsync, purchases token removed: $purchaseToken")
                else
                    showToast("onPurchases some troubles happened: $responseCode")
            }
        }
    }
    interface OnLoadingSubscriptionProducts {
        fun onLoadingSku(skuDetailsList: MutableList<SkuDetails>)
    }
}



