package gurtek.mrgurtekbase

import android.content.Context

import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * * Created by Gurtek Singh on 2/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */

abstract class AppBottomSheet @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val bottomsheet: BottomSheetBehavior<LinearLayout> by lazy {
        BottomSheetBehavior.from<LinearLayout>(this)
    }


    abstract fun onBehaviourAttachted(behavior: BottomSheetBehavior<LinearLayout>)


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setinitalstateofbottomsheet()
    }

    private fun setinitalstateofbottomsheet() {
        bottomsheet.peekHeight = 0
        bottomsheet.state = BottomSheetBehavior.STATE_HIDDEN
        onBehaviourAttachted(bottomsheet)
    }

    fun togglebottomheetstate() {
        if (bottomsheet.state == BottomSheetBehavior.STATE_EXPANDED) {
            hide()
        } else {
            show()
        }
    }

    fun show() {
        bottomsheet.state = BottomSheetBehavior.STATE_EXPANDED

    }

    fun hide() {
        bottomsheet.state = BottomSheetBehavior.STATE_HIDDEN
    }

    fun isvisible(): Boolean {
        return bottomsheet.peekHeight != 0
    }

}
