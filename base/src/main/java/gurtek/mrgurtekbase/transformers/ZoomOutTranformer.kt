package gurtek.mrgurtekbase.transformers

import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ZoomOutTranformer : BaseTransformer() {

    override fun onTransform(view: View, position: Float) {
        val scale = 1f + Math.abs(position)
        if (position >= -1 && position <= 1) {
            if (view is ViewGroup) {
                (0..view.childCount)
                    .map { view.getChildAt(it) }
                    .filterIsInstance<TextView>()
                    .forEach { it.translationX = -position * view.width / 2 }

            }
        } else {
            view.alpha = 1f
        }

        view.pivotX = view.width * 0.5f
        view.pivotY = view.height * 0.5f
        view.alpha = if (position < -1f || position > 1f) 0f else 1f - (scale - 1f)
        if (position == -1f) {
            view.translationX = (view.width * -1).toFloat()
        }
    }

}