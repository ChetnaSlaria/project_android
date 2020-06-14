package gurtek.mrgurtekbase.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: ArrayList<Fragment>,
    private val titles: ArrayList<String> = arrayListOf(),
    val showTitle: Boolean = false
) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (showTitle) {

            titles[position]
        } else
            super.getPageTitle(position)
    }


}