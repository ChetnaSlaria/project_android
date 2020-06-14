package com.sachtech.datingapp.ui.home.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.ui.home.activity.EditProfileActivity
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.dialog_fun_fact.*

class FunFactDialog :KotlinBaseDialogFragment<DialogFragment>(R.layout.dialog_fun_fact) {
    override fun adjustdisplay(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fact_name.text = arguments?.getString("fact_name")
        fact_save.setOnClickListener { dismiss() }
    }
}