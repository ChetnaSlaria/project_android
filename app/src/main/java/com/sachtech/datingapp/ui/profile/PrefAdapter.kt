/*
package com.sachtech.datingapp.ui.profile

import android.content.Context
import androidx.core.content.ContextCompat
import com.sachtech.datingapp.R
import com.sachtech.datingapp.utils.PrefType
import gurtek.mrgurtekbase.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_pref.view.*

class PrefAdapter(
    val arraylist: ArrayList<String>,
    val context: Context,
    val pref_type: String,
    val selectedItems: ArrayList<String>,
    val onPrefChange: OnPrefChange
) : BaseAdapter<String>(R.layout.item_pref) {
    var itemList: ArrayList<String>? = null
    var maritalStatusIconList= arrayListOf<Int>( R.drawable.single,
         R.drawable.divorced,R.drawable.windowed,
       R.drawable.annulled)

    var buildIconList= arrayListOf<Int>( R.drawable.athletic,
        R.drawable.large,R.drawable.medium,
        R.drawable.slim)

    var currentLivingIconList= arrayListOf<Int>( R.drawable.living_alone,
        R.drawable.living_with_family,R.drawable.living_with_friend,
        R.drawable.other)

    var sectIconList= arrayListOf<Int>( R.drawable.sunni,
        R.drawable.shia,R.drawable.other2)



    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        itemList = ArrayList()
        holder.itemView.pref_name.text = arraylist[position]

        when(pref_type){
            PrefType.MARITALSTATUS->{
                holder.itemView.pref_name.
                    setCompoundDrawablesWithIntrinsicBounds(maritalStatusIconList[position],
                        0,0,0)
            }
            PrefType.CURRENTLIVINGARRANGEMENT->{
                holder.itemView.pref_name.
                    setCompoundDrawablesWithIntrinsicBounds(currentLivingIconList[position],
                        0,0,0)
            }
            PrefType.SECT->{
                holder.itemView.pref_name.
                    setCompoundDrawablesWithIntrinsicBounds(sectIconList[position],
                        0,0,0)
            }
            PrefType.BUILD ->{
                holder.itemView.pref_name.
                    setCompoundDrawablesWithIntrinsicBounds(buildIconList[position],
                        0,0,0)
            }



        }

        if (selectedItems.contains(arraylist[position])) {
            holder.itemView.view.setBackgroundResource(R.drawable.selected)
            holder.itemView.pref_name.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            )
            itemList?.addAll(selectedItems)
        } else {
            holder.itemView.view.setBackgroundResource(R.drawable.unselected)
            holder.itemView.pref_name.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorGrey
                )
            )
        }
        holder.itemView.setOnClickListener {
            if (itemList?.contains(arraylist[position])!!) {
                holder.itemView.view.setBackgroundResource(R.drawable.unselected)
                holder.itemView.pref_name.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorGrey
                    )
                )
                itemList?.remove(holder.itemView.pref_name.text.toString())
            } else {
                holder.itemView.view.setBackgroundResource(R.drawable.selected)
                holder.itemView.pref_name.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorWhite
                    )
                )
                itemList?.add(arraylist[position])
                onPrefChange.selectedPref(pref_type, itemList!!)
            }
        }
     //   onPrefChange.selectedPref(pref_type, itemList!!)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }
}

interface OnPrefChange {
    fun selectedPref(pref_type: String, arraylist: ArrayList<String>)
}
*/
