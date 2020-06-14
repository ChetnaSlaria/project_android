package gurtek.mrgurtekbase.adapter

import android.view.View

/**
 * * Created by Gurtek Singh on 1/2/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
interface RecycleItemViewCallback<T> {
    fun onItemViewClicked(item: T?, position: Int)
    fun onItemViewClicked(item: T?, position: Int, view: View? = null) {
        onItemViewClicked(item, position)
    }
}