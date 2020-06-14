package gurtek.mrgurtekbase.navigator

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import gurtek.mrgurtekbase.KotlinBaseFragment
import gurtek.mrgurtekbase.conditions.PreConditions
import kotlin.reflect.KClass

/**
 * * Created by Gurtek Singh on 1/30/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
class Navigator(private val activity: AppCompatActivity, private val container: Int) {

    private val fragmentAdder: FragmentBehaviour by lazy {
        FragmentAdd(container)
    }

    private val fragmentReplacer: FragmentBehaviour by lazy {
        FragmentReplace(container)
    }

    private var lastVisibleFragment: KotlinBaseFragment? = null


    fun openA(kClass: KClass<out AppCompatActivity>, bundle: Bundle? = Bundle()) {
        val intent = Intent(activity, kClass.java)
        intent.putExtras(bundle)
        activity.startActivity(intent)
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            activity.startActivity(intent)
        }
*/
    }

    fun replaceFragment(clazz: Class<out Fragment>, bundle: Bundle?, transitionView: View?, userTag: String = "") {
        executetransition(clazz, bundle, transitionView, fragmentReplacer, activity.supportFragmentManager, userTag)
    }

    fun executetransition(
        clazz: Class<out Fragment>, bundle: Bundle?, transitionView: View?,
        behaviour: FragmentBehaviour, manager: FragmentManager, userTag: String = ""
    ) {

        PreConditions.checkIsContainerProvide(container)

        val tag = if (userTag.isEmpty()) clazz.simpleName else userTag
        var fragment = manager.findFragmentByTag(tag)

        if (fragment == null) {
            fragment = makeFragmentAndShowOnScreen(fragment, clazz, manager, behaviour, tag, transitionView)
        } else {
            manager.popBackStackImmediate(tag, 0)
        }

        fragment?.arguments = bundle

    }

    fun executetransition(
        clazz: Fragment, bundle: Bundle?, transitionView: View?,
        behaviour: FragmentBehaviour, manager: FragmentManager, userTag: String = ""
    ) {

        PreConditions.checkIsContainerProvide(container)

        val tag = if (userTag.isEmpty()) clazz.javaClass.simpleName else userTag
        var fragment = manager.findFragmentByTag(tag)

        if (fragment == null) {
            fragment = makeFragmentAndShowOnScreen(clazz, clazz.javaClass, manager, behaviour, tag, transitionView)
        } else {
            manager.popBackStackImmediate(tag, 0)
        }

        fragment?.arguments = bundle

    }

    private fun makeFragmentAndShowOnScreen(
        fragment: Fragment?,
        clazz: Class<out Fragment>,
        manager: FragmentManager,
        behaviour: FragmentBehaviour,
        tag: String?, transitionView: View?
    ): Fragment? {
        var fragment1 = fragment
        if (fragment1 == null)
            fragment1 = clazz.newInstance()
        val transaction = manager.beginTransaction()
        setTitle(fragment1, transaction)
        behaviour.execute(transaction, fragment1!!, tag)

        if (transitionView != null) {
            transaction.addSharedElement(transitionView, ViewCompat.getTransitionName(transitionView)?:"")
        }
        transaction.addToBackStack(tag)
        transaction.commit()
        return fragment1
    }

    private fun setTitle(fragment: Fragment?, transaction: FragmentTransaction) {
        if (fragment is KotlinBaseFragment) {

            if (fragment.setBreadCrumbsImage() != null) {
                transaction.setBreadCrumbTitle("" + fragment.setBreadCrumbsImage())
            }
            transaction.setBreadCrumbShortTitle(fragment.setBreadCrumbsTitle())

        }
    }


    fun addFragment(
        clazz: Class<out Fragment>,
        bundle: Bundle? = null,
        transitionView: View? = null,
        tag: String = ""
    ) {
        executetransition(clazz, bundle, transitionView, fragmentAdder, activity.supportFragmentManager, tag)
    }

    fun addFragment(clazz: Fragment, bundle: Bundle? = null, transitionView: View? = null, tag: String = "") {
        executetransition(clazz, bundle, transitionView, fragmentAdder, activity.supportFragmentManager, tag)
    }

    fun getCurrentFragmentTag(): String? {
        val findFragmentById = activity.supportFragmentManager.findFragmentById(container)
        return findFragmentById?.javaClass?.simpleName

    }

    fun addChildFragment(childFragmentManager: FragmentManager, container: Int, kClass: KClass<out Fragment>) {
        executetransition(kClass.java, Bundle(), null, fragmentAdder, childFragmentManager)
    }


    fun bringFragmentToFrontIfPresentOrCreate(clazz: Class<out KotlinBaseFragment>) {

        PreConditions.checkIsContainerProvide(container)

        val tag = clazz.simpleName
        val manager = activity.supportFragmentManager
        var fragment = manager.findFragmentByTag(tag)

        val transition = manager.beginTransaction()

        if (fragment == null) {
            fragment = clazz.newInstance()
            fragmentAdder.execute(transition, fragment, tag)
            transition.addToBackStack(tag)
            setTitle(fragment, transition)
        } else {
            transition.show(fragment)
        }

        lastVisibleFragment?.run {
            if (this != fragment) {
                transition.hide(this)
                userVisibleHint = false
            }
        }

        transition.commitAllowingStateLoss()


        val newFragment = fragment as KotlinBaseFragment
        newFragment.userVisibleHint = true

        lastVisibleFragment = newFragment


    }


    @SuppressLint("RestrictedApi")
    fun openAForResult(kClass: KClass<out AppCompatActivity>, bundle: Bundle, code: Int) {
        val intent = Intent(activity, kClass.java)
        intent.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivityForResult(
                intent,
                code,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            )
        } else {
            activity.startActivityForResult(intent, code)
        }
    }

    fun getLastAddedFragment(): KotlinBaseFragment? {
        return lastVisibleFragment
    }

    fun lastFragmentChanged(fragment: KotlinBaseFragment) {
        lastVisibleFragment = fragment
    }

}