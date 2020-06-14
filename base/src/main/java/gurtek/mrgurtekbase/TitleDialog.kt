package com.s10.calidad.ui.observations.utils.dialog

import android.os.Bundle
import gurtek.mrgurtekbase.KotlinBaseDialogFragment
import gurtek.mrgurtekbase.R
import kotlinx.android.synthetic.main.inflater_titledialog.*


/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
class TitleDialog : KotlinBaseDialogFragment<String>(R.layout.inflater_titledialog) {
    override fun adjustdisplay(): Boolean {
        return true
    }

    private var hint: String = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        input_txt.hint = hint


        cancel.setOnClickListener({ _ -> dialog.dismiss() })
        ok.setOnClickListener({ _ ->
            if (valid()) {
                dialogJobSubmitCallback?.onJobSubmit(title.text.toString())
                dismiss()
            } else {
                input_txt.error = "Please Enter ${input_txt.hint}"
            }
        })


    }


    private fun valid() = title.text.isNotEmpty()


    override fun setTitle(titleTxt: String) {
        hint = titleTxt
    }


}