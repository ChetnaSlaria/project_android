package gurtek.mrgurtekbase

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import gurtek.mrgurtekbase.listeners.KotlinBaseListener

/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class KotlinBaseDialogFragment<T>(@LayoutRes val view: Int) : DialogFragment() {

    protected lateinit var baseListener: KotlinBaseListener

    var dialogJobSubmitCallback: onDialogJobSubmit<T>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseListener = context as KotlinBaseListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        if (adjustdisplay()) setdialogwidth(dialog)

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(view, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (adjustdisplay()) setDisplay()
    }

    abstract fun adjustdisplay(): Boolean

    open fun setTitle(titleTxt: String) {

    }


    private fun setdialogwidth(dialog: Dialog) {
        val display = activity?.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics()
        display?.getMetrics(outMetrics)

        val attrs = dialog.window.attributes
        attrs.width = (356 * outMetrics.density).toInt()
    }

    private fun setDisplay() {

        val display = activity?.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics()
        display?.getMetrics(outMetrics)
        //   dialog.window.setLayout((312 * outMetrics.density).toInt(), (436 * outMetrics.density).toInt())

        val attrs = dialog.window.attributes
        attrs.width = (356 * outMetrics.density).toInt()

    }

    interface onDialogJobSubmit<T> {
        fun onJobSubmit(job: T)
    }

}