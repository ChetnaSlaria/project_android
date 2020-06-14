/*
package gurtek.mrgurtekbase

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import gurtek.mrgurtekbase.navigator.Navigator
import java.util.*
import kotlin.reflect.KClass


*/
/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 *//*


open class KotlinBaseActivity(@IdRes private val container: Int = 0) : AppCompatActivity(), KotlinBaseListener {
    override fun showProgress() {
        if (progress.isShowing)
            progress.hide()
        progress.show()
    }

    override fun hideProgress() {
        if (progress.isShowing)
            progress.dismiss()
    }

    lateinit var progress: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     */
/*   window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )*//*

        inItDialog()
        initBackStackListener()

    }

    private fun inItDialog() {
        progress = Dialog(this)
        progress.setContentView(R.layout.progress_layout)
        progress.setCancelable(false)
        progress.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    private fun initBackStackListener() {

        with(supportFragmentManager) {
            addOnBackStackChangedListener {
                val fragment = findFragmentById(container)
                navigator.lastFragmentChanged(fragment = fragment as KotlinBaseFragment)
            }
        }
    }


    override fun getFragment(kClass: KClass<out Fragment>): Fragment? {
        val fragment = supportFragmentManager.findFragmentByTag(kClass.java.simpleName)
        return if (fragment != null) fragment else null
    }


    override fun openAForResult(kClass: KClass<out AppCompatActivity>, bundle: Bundle, code: Int) {
        navigator.openAForResult(kClass, bundle, code)
    }


    override fun navigateToFragment(java: KClass<out Fragment>, extras: Bundle?) {
        navigateToFragment(java.java, extras, transitionView = null)
    }

    override fun navigateToFragment(java: KClass<out Fragment>, extras: Bundle?, userTag: String) {
        navigateToFragment(java.java, extras, transitionView = null, userTag = userTag)
    }

    override fun addFragment(fragment: KClass<out Fragment>, extras: Bundle?, tag: String) {
        navigator.addFragment(fragment.java, tag = tag, bundle = extras)
    }

    override fun addFragment(fragment: Fragment, extras: Bundle?, tag: String) {
        navigator.addFragment(fragment, tag = tag, bundle = extras)

    }

    private val manager: FragmentManager by lazy {
        supportFragmentManager
    }

    inline fun <reified T : Fragment> navigateToFragment(bundle: Bundle? = Bundle()) {
        navigateToFragment(T::class.java, bundle)
    }

    private val navigator: Navigator by lazy { Navigator(this, container) }


    fun navigateToFragment(
        clazz: Class<out Fragment>,
        bundle: Bundle? = null,
        transitionView: View? = null,
        userTag: String = ""
    ) {
        navigator.replaceFragment(clazz, bundle, transitionView, userTag)
    }


    fun switchFragment(clazz: Class<out Fragment>, bundle: Bundle? = null, transitionView: View? = null) {
        navigator.addFragment(clazz, bundle, transitionView)
    }


    override fun openA(kClass: KClass<out AppCompatActivity>, extras: Bundle?) {
        navigator.openA(kClass, extras)
    }

    override fun onBackPressed() {
        if (containsOnlyFragment()) {
            if (manager.backStackEntryCount == 1) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    open fun containsOnlyFragment(): Boolean {
        return true
    }

    fun currentVisibleFragmentTag(): String? {
        return navigator.getCurrentFragmentTag()
    }

    inline fun <reified T : Fragment> getFragment(): T? {
        val fragment = supportFragmentManager.findFragmentByTag(T::class.java.simpleName)
        return if (fragment != null) fragment as T else null
    }


    override fun addChildFragment(childFragmentManager: FragmentManager, container: Int, kClass: KClass<out Fragment>) {
        navigator.addChildFragment(childFragmentManager, container, kClass)
    }

    fun bringtoFront(kClass: KClass<out KotlinBaseFragment>) {
        navigator.bringFragmentToFrontIfPresentOrCreate(kClass.java)
    }

    fun getLastAddedFragment(): KotlinBaseFragment? {
        return navigator.getLastAddedFragment()
    }

    fun <T> showDialog(clazz: Class<out KotlinBaseDialogFragment<T>>, bundle: Bundle? = Bundle()): Fragment {
        val tag = clazz.simpleName
        val ft = supportFragmentManager?.beginTransaction()
        var fragment = supportFragmentManager?.findFragmentByTag(tag)
        if (fragment != null) {
            ft?.remove(fragment)
        }
        ft?.addToBackStack(tag)

        // Create and show the dialog.
        fragment = clazz.newInstance()
        fragment.arguments = bundle
        fragment.show(ft, tag)

        return fragment
    }

    fun isNight(): Boolean {
        var isNightModeEnable=false
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)
        if (timeOfDay in 6..18) {
            isNightModeEnable=false
        }
        else if (timeOfDay in 0..5||timeOfDay in 19..23) {
            isNightModeEnable=true
        }
        return isNightModeEnable
    }

    override fun onDestroy() {
        super.onDestroy()
      //  window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
}
*/
