/*
package com.sachtech.datingapp.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import com.android.billingclient.api.SkuDetails
import com.sachtech.datingapp.R
import gurtek.mrgurtekbase.adapter.BaseAdapter
import gurtek.mrgurtekbase.visible
import kotlinx.android.synthetic.main.item_subscription.view.*

class SubscriptionAdapter(val updatedList: ArrayList<SkuDetails>,
                          private val onProductClicked: (SkuDetails) -> Unit
                          ) :BaseAdapter<String>(R.layout.item_subscription)
{

    @SuppressLint("LogNotTimber")
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        Log.e("subsc",updatedList.toString())
        holder.itemView.text_price.text=updatedList[position].price
        holder.itemView.text_get.text=updatedList[position].description
        holder.itemView.text_off.text=updatedList[position].title
        holder.itemView.setOnClickListener {
           // onSubscriptionSelected.selectedSubscription(updatedList[position])
            onProductClicked(updatedList[position])
        }
       */
/* holder.itemView.apply {
            text_price.text = priceArray[position]
        }*//*


        if (position == 3) {
            holder.itemView.popular.visible()
        }
    }

    override fun getItemCount(): Int {
        return updatedList.size
    }
interface OnSubscriptionSelected
{
    fun selectedSubscription(skuDetails: SkuDetails)
}
}*/
