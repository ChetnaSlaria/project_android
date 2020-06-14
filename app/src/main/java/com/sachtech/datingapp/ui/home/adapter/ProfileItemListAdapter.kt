/*
package com.sachtech.datingapp.ui.home.adapter

import android.content.Context
import androidx.core.content.ContextCompat

import com.sachtech.datingapp.R
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences.setPref
import com.sachtech.datingapp.utils.ProfileItem
import com.sachtech.datingapp.utils.setPref
import gurtek.mrgurtekbase.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_profile_items.view.*

class ProfileItemListAdapter(
    val context: Context,
    val stringlist: ArrayList<String>,
    val item: String?,
    val selectedType: String?,
    val onItemSelected: OnItemSelected
) :
    BaseAdapter<String>(R.layout.item_profile_items) {
    private var selected_position = -1

    var itemList:ArrayList<String>?=null
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        itemList= ArrayList()
        holder.itemView.item_profile.text = stringlist[position]
        if(selectedType=="single") {
            if (holder.adapterPosition == selected_position) {
                holder.itemView.item_profile.setBackgroundResource(R.drawable.dialog_background)
                holder.itemView.item_profile.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorWhite
                    )
                )
            } else {
                holder.itemView.item_profile.setBackgroundResource(R.drawable.dialog_unselected)
                holder.itemView.item_profile.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorGrey
                    )
                )
            }
            holder.itemView.setOnClickListener {
                when (item) {
                    ProfileItem.ORIGIN -> setPref(PrefKeys.ORIGIN, stringlist[position])
                    ProfileItem.COLORCOMPLEXION -> setPref(
                        PrefKeys.COLORCOMPLEXION,
                        stringlist[position]
                    )
                    ProfileItem.HAIRCOLOR -> setPref(PrefKeys.HAIRCOLOR, stringlist[position])
                    ProfileItem.EYECOLOR -> setPref(PrefKeys.EYECOLOR, stringlist[position])
                    ProfileItem.PROFESSION -> setPref(PrefKeys.PROFESSION, stringlist[position])
                    ProfileItem.EDUCATION -> setPref(PrefKeys.EDUCATION, stringlist[position])
                    ProfileItem.NATIONALITY -> setPref(PrefKeys.NATIONALITY, stringlist[position])
                }
                onItemSelected.selectedItem(stringlist[position])
                selected_position = holder.adapterPosition
                notifyDataSetChanged()
            }
        }
        else if(selectedType=="multiple") {
            holder.itemView.item_profile.setBackgroundResource(R.drawable.dialog_unselected)
            holder.itemView.item_profile.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorGrey
                )
            )

            holder.itemView.setOnClickListener {
                holder.itemView.item_profile.setBackgroundResource(R.drawable.dialog_background)
                holder.itemView.item_profile.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorWhite
                    )
                )
                itemList!!.add(holder.itemView.item_profile.text.toString())
                when (item) {
                    ProfileItem.COLORCOMPLEXION -> setPref(
                        PrefKeys.PREF_COLORCOMPLEXION,
                        itemList?.joinToString(",")
                    )
                    ProfileItem.HAIRCOLOR -> setPref(
                        PrefKeys.PREF_HAIRCOLOR,
                        itemList?.joinToString(",")
                    )
                    ProfileItem.EYECOLOR -> setPref(PrefKeys.PREF_EYECOLOR, itemList?.joinToString(","))
                    ProfileItem.PROFESSION -> setPref(PrefKeys.PREF_PROFESSION, itemList?.joinToString(","))
                    ProfileItem.EDUCATION -> setPref(PrefKeys.PREF_EDUCATION, itemList?.joinToString(","))
                    ProfileItem.QURAN -> setPref(PrefKeys.PREF_QURAN, itemList?.joinToString(","))
                    ProfileItem.PRAYS -> setPref(PrefKeys.PREF_PRAYS, itemList?.joinToString(","))
                    ProfileItem.HALAL -> setPref(PrefKeys.PREF_HALAL, itemList?.joinToString(","))
                    ProfileItem.WEAR -> setPref(PrefKeys.PREF_WEAR, itemList?.joinToString(","))
                    ProfileItem.LIFEAFTERMARRIAGE -> setPref(PrefKeys.PREF_LIFE_AFTER_MARRIAGE, itemList?.joinToString(","))
                    ProfileItem.MARRIAGEPLANS -> setPref(PrefKeys.PREF_MARRIAGE_PLANS, itemList?.joinToString(","))
                    ProfileItem.REVERT -> setPref(PrefKeys.PREF_REVERT, itemList?.joinToString(","))
                    ProfileItem.BEARD -> setPref(PrefKeys.PREF_BEARD, itemList?.joinToString(","))
                }
                onItemSelected.selectedItem(itemList?.joinToString(",")!!)
            }
        }

    }

    override fun getItemCount(): Int {
        return stringlist.size
    }

}

interface OnItemSelected{
    fun selectedItem(item:String)
}


*/
