/*
package com.sachtech.datingapp.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import kotlinx.android.synthetic.main.dialog_language.*

class LanguageDialog : KotlinBaseDialogFragment<DialogFragment>(R.layout.dialog_language) {

    var onLanguageSelect: OnLanguageSelect? = null
    override fun adjustdisplay(): Boolean {
        return true
    }

    var language = ""
    lateinit var list: ArrayList<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        list = ArrayList()
        langugageAdd.setOnClickListener {
            if (language1.text.isNotEmpty() && language2.text.isNotEmpty()) {
                language = "${language1.text},${language2.text}"
                list.add(language1.text.toString())
                list.add(language2.text.toString())
                onLanguageSelect?.getLanguage(list)
                dismiss()
            } else if (language1.text.isNotEmpty()) {
                language = "${language1.text}"
                list.add(language)
                onLanguageSelect?.getLanguage(list)
                dismiss()
            } else if (language2.text.isNotEmpty()) {
                language = "${language2.text}"
                list.add(language)
                onLanguageSelect?.getLanguage(list)
                dismiss()
            }
        }
    }

    fun languageSelectListener(onLanguageSelect: OnLanguageSelect) {
        this.onLanguageSelect = onLanguageSelect
    }
}

interface OnLanguageSelect {
    fun getLanguage(language: ArrayList<String>?)
}*/
