package com.sachtech.datingapp.ui.profile

import android.app.Dialog
import android.content.Context
import androidx.core.content.ContextCompat
import com.sachtech.datingapp.R
import gurtek.mrgurtekbase.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_profile_items.view.*

class LanguageAdapter(val context: Context,val arraylist: Array<String>) :BaseAdapter<String>(R.layout.item_profile_items)
{
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.itemView.item_profile.text = arraylist[position]
        holder.itemView.setOnClickListener {
            holder.itemView.item_profile.setBackgroundResource(R.drawable.selected)
            holder.itemView.item_profile.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

}