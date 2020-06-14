package gurtek.mrgurtekbase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlin.reflect.KClass

/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class KotlinBaseFragment(@LayoutRes val view: Int = 0) : Fragment() {

    protected lateinit var baselistener: KotlinBaseListener

    val API_KEY = "abcd"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is KotlinBaseListener) {
            baselistener = context
        } else {
            throw IllegalStateException("You Must have to extends your activity with KotlinBaseActivity")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(view, container, false)
    }

    fun <T> showDialog(
        clazz: KClass<out KotlinBaseDialogFragment<T>>,
        bundle: Bundle? = Bundle(),
        show: Boolean = true
    ): Fragment? {
        val tag = clazz.java.simpleName
        val ft = fragmentManager?.beginTransaction()
        var fragment = fragmentManager?.findFragmentByTag(tag)
        if (fragment != null) {
            ft?.remove(fragment)
        }
        ft?.addToBackStack(tag)

        // Create and show the dialog.
        fragment = clazz.java.newInstance()
        fragment.arguments = bundle
        if (show) fragment.show(ft, tag)

        return fragment
    }

    @DrawableRes
    open fun setBreadCrumbsImage(): Int? {
        return null
    }

    open fun setBreadCrumbsTitle(): String {
        return ""
    }

    fun showProgress() {
        baselistener.showProgress()
    }

    fun hideProgress() {
        baselistener.hideProgress()
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }
    fun showErrorMessage(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

}