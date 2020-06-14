/*
package com.sachtech.datingapp.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.extensions.showToast
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.dialog_edit_about.*

class EditAboutDialog : KotlinBaseDialogFragment<DialogFragment>(R.layout.dialog_edit_about) {

    var textView:TextView?=null
    var onEditInformation: OnEditInformation? = null

    override fun adjustdisplay(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        title.text = arguments?.getString("edit_type")
        about_message.setText(textView?.text ?: "")
        about_save.setOnClickListener {
            if (about_message.text.isNotEmpty()) {
                onEditInformation?.information(about_message.text.toString(),textView!!)
                dismiss()
            }
            else showToast("Add Something")
        }
    }

    fun infoSelectListener(onEditInformation: OnEditInformation,textView: TextView) {
        this.onEditInformation = onEditInformation
        this.textView=textView
    }
}

interface OnEditInformation {
    fun information(info:String,textView: TextView)
}

interface EditInformation {
    fun editinformation(info:String,textView: TextView)
}
*/
