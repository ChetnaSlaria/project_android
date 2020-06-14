/*
package com.sachtech.datingapp.ui.chat.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sachtech.datingapp.R
import kotlinx.android.synthetic.main.dialog_block_user.*

class BlockUserDialog : DialogFragment() {


    lateinit var  actionListener:ActionListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        actionListener=context as ActionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_block_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        yes.setOnClickListener {
            actionListener.onYesClick()
        }
        no.setOnClickListener {
            dismiss()
        }
    }
}

interface ActionListener{
    fun onYesClick()
}*/
