package gurtek.mrgurtekbase.adapter


import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

/**
 * Created by Akash Saggu(R4X) on 21/9/18 at 15:girl8.
 * akashsaggu@protonmail.com
 * @Version 1 (21/9/18)
 * @Updated on 21/9/18
 */
abstract class BasePagerAdapter<T> constructor(val list: ArrayList<T> = arrayListOf(),fragmentManager: FragmentManager) : PagerAdapter(fragmentManager) {

    /**
     * inflate view and do work on it then add to container-viewGroup and return the view
     * */
    abstract override fun instantiateItem(container: ViewGroup, position: Int): Any

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun addNewList(newList: ArrayList<T>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun appendList(newList: ArrayList<T>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

}