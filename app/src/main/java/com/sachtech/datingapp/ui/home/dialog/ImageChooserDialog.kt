/*
package com.sachtech.datingapp.ui.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.utils.SelectImageUtils
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.gallery_dialog.*

class ImageChooserDialog(val fragment: Fragment) : KotlinBaseDialogFragment<DialogFragment>(R.layout.gallery_dialog) {
    val selectImageUtils by lazy { SelectImageUtils(context!!) }
    override fun adjustdisplay(): Boolean {
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog_text_camera!!.setOnClickListener {
            selectImageUtils.selectImageFromCamera(fragment, null)
            dismiss()
        }
        dialog_text_gallery!!.setOnClickListener {
            selectImageUtils.selectImageFromGallery(fragment)
            dismiss()
        }
    }

}
*/
