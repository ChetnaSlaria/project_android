package gurtek.mrgurtekbase.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Gurtek Singh on 5/21/18.
 * gurtek@protonmail.ch
 */
abstract class TextChangeListener(private val editText: EditText) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        onTextChanged(s.toString(), editText)
    }

    abstract fun onTextChanged(text: String, editText: EditText)

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }


}


