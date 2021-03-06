package gurtek.mrgurtekbase.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


/**
 * * Created by Gurtek Singh on 1/31/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */
abstract class FragmentBehaviour {
    abstract fun execute(transaction: FragmentTransaction, fragment: Fragment, tag: String?)
}