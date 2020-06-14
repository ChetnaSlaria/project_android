/*
package com.sachtech.datingapp.ui.login.fragment

import android.os.Bundle
import android.view.View
import com.sachtech.datingapp.R
import com.sachtech.datingapp.utils.IntentString
import com.sachtech.datingapp.extensions.formatDate
import com.sachtech.datingapp.extensions.getAge
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.utils.Constants
import gurtek.mrgurtekbase.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_select_dob.*
import java.util.*

class GenderDobFragment : KotlinBaseFragment(R.layout.fragment_select_dob) {
    var gender: String = Constants.male
    val calendar = Calendar.getInstance()
    var date = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    var calendarYear = calendar.get(Calendar.YEAR)
    var day = formatDate("$calendarYear-${month + 1}-$date")
    var age: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarYear -= 18
        gender_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.gender_female) {
                gender = Constants.female
                gender_female.background=resources.getDrawable(R.drawable.female_b)
                gender_male.background=resources.getDrawable(R.drawable.male_o)
            } else if (checkedId == R.id.gender_male) {
                gender = Constants.male
                gender_female.background=resources.getDrawable(R.drawable.female_o)
                gender_male.background=resources.getDrawable(R.drawable.male_b)
            }
        }

        dob_done.setOnClickListener {
            if (age < 18) {
                showToast("User must be 18 or above")
            } else {
                arguments?.putString(IntentString.AGE, age.toString())
                arguments?.getString(IntentString.EMAIL)
                arguments?.getString(IntentString.NAME)
                arguments?.getString(IntentString.PROFILEIMAGE)
                arguments?.getStringArrayList(IntentString.IMAGELIST)
                arguments?.putString(IntentString.GENDER, gender)
                arguments?.putLong(IntentString.DOB, day)
                baselistener.navigateToFragment(VerificationImageFragment::class, arguments)
            }

        }


        datePicker.init(
            calendarYear, month, date
        ) { view, year, monthOfYear, dayOfMonth ->
            day = formatDate("$year-${monthOfYear + 1}-$dayOfMonth")
            age = getAge(year, monthOfYear, dayOfMonth)

        }

    }
}
*/
