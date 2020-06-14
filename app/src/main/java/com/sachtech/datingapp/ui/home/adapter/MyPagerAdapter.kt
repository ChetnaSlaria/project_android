/*
package com.sachtech.datingapp.ui.home.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.sachtech.datingapp.R
import com.sachtech.datingapp.utils.loadImage
import com.squareup.picasso.Picasso

class MyPagerAdapter(activity: Activity?, arraylist: ArrayList<String>) : PagerAdapter() {
    val context = activity
    val arrayList = arraylist
    internal var mLayoutInflater: LayoutInflater =
        activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1 as RelativeLayout
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)
        val imageView = itemView.findViewById(R.id.imageView) as ImageView
        Picasso.get().load(arrayList[position]).into(imageView)
        //imageView.setImageURI(Uri.parse(arrayList[position]))
        container.addView(itemView)
        return itemView
    }
}*/
