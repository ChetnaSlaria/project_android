/*
package com.sachtech.datingapp.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sachtech.datingapp.R
import com.sachtech.datingapp.ui.home.adapter.ProfileItemListAdapter
import com.sachtech.datingapp.ui.home.adapter.listener.OnItemSelected
import com.sachtech.datingapp.ui.profile.listener.EditInformation
import com.sachtech.datingapp.utils.ProfileItem
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.fragment_profile_item.*

class ProfileItemFragment : KotlinBaseDialogFragment<String>(R.layout.fragment_profile_item),
    OnItemSelected {
    override fun adjustdisplay(): Boolean {
        return true
    }


    var textView: TextView? = null
    var onEditInformation: EditInformation? = null

    var list: ArrayList<String>? = null
    var selectedItem: String? = null
    var adapter: ProfileItemListAdapter? = null
    var selectedType: String? = null
    var booleanArray = arrayOf("Yes", "No")
    var item=""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        selectedItem = arguments?.getString("item_name")
        selectedType = arguments?.getString("item_type")

        item_name.text = selectedItem
        list = ArrayList()
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        profile_item_rv.layoutManager = layoutManager
        adapter = ProfileItemListAdapter(context!!, list!!, selectedItem, selectedType,this)
        profile_item_rv.adapter = adapter

        when (selectedItem) {
            ProfileItem.EDUCATION -> list?.addAll(resources.getStringArray(R.array.education))
            ProfileItem.PROFESSION -> list?.addAll(resources.getStringArray(R.array.profession))
            ProfileItem.EYECOLOR -> list?.addAll(resources.getStringArray(R.array.eyeColor))
            ProfileItem.HAIRCOLOR -> list?.addAll(resources.getStringArray(R.array.hairColor))
            ProfileItem.COLORCOMPLEXION -> list?.addAll(resources.getStringArray(R.array.colorComplexion).toList())
            ProfileItem.QURAN -> list?.addAll(booleanArray)
            ProfileItem.PRAYS -> list?.addAll(resources.getStringArray(R.array.prays))
            ProfileItem.HALAL -> list?.addAll(resources.getStringArray(R.array.halal))
            ProfileItem.WEAR -> list?.addAll(resources.getStringArray(R.array.wear))
            ProfileItem.LIFEAFTERMARRIAGE -> list?.addAll(resources.getStringArray(R.array.after_marriage_plans))
            ProfileItem.MARRIAGEPLANS -> list?.addAll(resources.getStringArray(R.array.marriage_plans))
            ProfileItem.REVERT -> list?.addAll(booleanArray)
            ProfileItem.BEARD -> list?.addAll(booleanArray)
        }

        profile_item_save.setOnClickListener {
            onEditInformation?.editinformation(item ?: "", textView!!)
            dismiss()
        }
    }

    fun infoSelectListener(onEditInformation: EditInformation, textView: TextView) {
        this.onEditInformation = onEditInformation
        this.textView = textView
    }

    override fun selectedItem(item: String) {
        this.item=item
    }


}
*/
