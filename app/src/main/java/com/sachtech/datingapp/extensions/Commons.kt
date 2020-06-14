package com.sachtech.datingapp.extensions

import android.text.format.DateFormat
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sachtech.datingapp.R
import com.sachtech.datingapp.app.DatingApp
import gurtek.mrgurtekbase.gone
import gurtek.mrgurtekbase.visible
import java.text.SimpleDateFormat
import java.util.*


fun formatDate(date: String): Long {
    var sd = SimpleDateFormat("yyyy-M-dd")
    val d = sd.parse(date)
    val timestamp=d.time
    sd = SimpleDateFormat("MMM dd,yyyy")
    val dateformatdate = sd.format(d)

    return timestamp
}
fun Long.getDateInformat(): String? {
    val sd = SimpleDateFormat("MMM dd,yyyy")
    val dateformatdate = sd.format(this)
    return dateformatdate
}

fun Long.getDateFromTimestamp():String{
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = this * 1000
    return DateFormat.format("MMM dd,yyyy", cal).toString()
}


fun <T> T.isNull(): Boolean {
    return this == null
}

fun <T> T.isNotNull(): Boolean {
    return this != null
}

fun showToast(msg:String)
{
    Toast.makeText(DatingApp.application,msg,Toast.LENGTH_SHORT).show()
}

fun View.onCallButtonClick(message:String,body:()->Unit)
{
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).setAction("OK"
    ) {
        body.invoke()
    }.setActionTextColor(ContextCompat.getColor(context, R.color.colorWhite)).show()
}
var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
fun String.validateEmail(): Boolean {
    return this.matches(emailPattern.toRegex())
}

fun String.validatePassword():Boolean{
    return this.length > 4
}

 fun getAge(year: Int, month: Int, day: Int): Int {
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()

    dob.set(year, month, day)

    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    val ageInt = age

    return ageInt
}

fun onFocusChange(itemToClick: EditText, showItem: View, hint:String){
    itemToClick.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus ->
        if(v!!.hasFocus()) {
            showItem.visible()
            itemToClick.hint = ""
              }else{
             if(itemToClick.text.isNotEmpty()){
                     showItem.visible()
                 itemToClick.isFocusable = true
                 itemToClick.isFocusableInTouchMode = true
             }else {
                     showItem.gone()
                 itemToClick.hint = hint
                 }
            //showItem.gone()
            //itemToClick.hint = hint
             }
        }
    }

