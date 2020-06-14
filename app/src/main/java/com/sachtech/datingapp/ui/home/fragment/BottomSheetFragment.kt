/*
package com.sachtech.datingapp.ui.home.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sachtech.datingapp.R
import com.sachtech.datingapp.customcountrypicker.CountryPickerDialog
import com.sachtech.datingapp.data.UserPreference
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.ui.profile.*
import com.sachtech.datingapp.ui.profile.listener.OnLanguageSelect
import com.sachtech.datingapp.ui.profile.listener.OnPrefChange
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.getprefObject
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.fragment_partner_preference.*
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate.onBackPressed


class BottomSheetFragment(val baselistener: KotlinBaseListener) : BottomSheetDialogFragment(),
    OnLanguageSelect, OnPrefChange,EditInformation {

    override fun selectedPref(pref_type: String, arraylist: ArrayList<String>) {
        when (pref_type) {
            PrefType.MARITALSTATUS -> marital.addAll(arraylist)
            PrefType.MARRIAGEPLANS -> marriageplan.addAll(arraylist)
            PrefType.LIVINGARRANGEMENTSAFTERMARRIAGE -> arrangements.addAll(arraylist)
            PrefType.PRAYS -> prays.addAll(arraylist)
            PrefType.SECT -> caste.addAll(arraylist)
            PrefType.QURAN -> completedQuran.addAll(arraylist)
            PrefType.CURRENTLIVINGARRANGEMENT -> livingArrangements.addAll(arraylist)
            PrefType.HALAL -> halal.addAll(arraylist)
            PrefType.REVERT -> revert.addAll(arraylist)
            PrefType.BUILD -> build.addAll(arraylist)
            PrefType.BEARD -> beard.addAll(arraylist)
            PrefType.WEAR -> wear.addAll(arraylist)
            PrefType.PROFESSION -> profession.addAll(arraylist)
        }
    }

    var stateChanged = false

    var originDialog: CountryPickerDialog? = null
    var nationalityDialog: CountryPickerDialog? = null
    val dob: Long = 0
    val age: Int = 0
    val languageList by lazy { ArrayList<String>() }
    var userPreference: UserPreference? = null
    val prays by lazy { ArrayList<String>() }
    val completedQuran by lazy { ArrayList<String>() }
    val arrangements by lazy { ArrayList<String>() }
    val marital by lazy { ArrayList<String>() }
    val marriageplan by lazy { ArrayList<String>() }
    val caste by lazy { ArrayList<String>() }
    val livingArrangements by lazy { ArrayList<String>() }
    val build by lazy { ArrayList<String>() }
    val halal by lazy { ArrayList<String>() }
    val revert by lazy { ArrayList<String>() }
    val beard by lazy { ArrayList<String>() }
    val wear by lazy { ArrayList<String>() }
    val profession by lazy {
        ArrayList<String>()}


    override fun getLanguage(language: ArrayList<String>?) {
        if (language != null) {
            languageList.addAll(language)
        }
        pref_languages.text = language?.joinToString(",")
    }
        @Nullable
        override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_partner_preference, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            prefCancel.setOnClickListener { dismiss() }
            nationalityDialog = CountryPickerDialog(context!!)
            originDialog = CountryPickerDialog(context!!)
            if (Preferences.get(PrefKeys.INSTANCE.isDataSaved, false) == true) {
                getValues()
            }
            pref_minAge.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (pref_minAge.text.isNotEmpty()) {
                        if (s.toString().toInt() < 18) {
                            showToast("User must be 18 or above")
                        }
                    }
                }

            })

            pref_maxAge.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (pref_maxAge.text.isNotEmpty()) {
                        if (s.toString().toInt() < 18) {
                            showToast("User must be 18 or above")
                        }
                    }

                }

            })
            setOnClick()
            setValues()
        }


    private fun setValues() {
        pref_complexion.text = Preferences.get(PrefKeys.INSTANCE.colorcomplexion, "")
        pref_education.text = Preferences.get(PrefKeys.INSTANCE.preF_EDUCATION, "")
        pref_eyes_color.text = Preferences.get(PrefKeys.INSTANCE.preF_EYECOLOR, "")
        pref_hair_color.text = Preferences.get(PrefKeys.INSTANCE.preF_HAIRCOLOR, "")
        //  edit_profession.text = getProfession()
    }

    private fun getValues() {
        userPreference = getprefObject<UserPreference>(PrefKeys.INSTANCE.userprefs)
        pref_height.setText(userPreference?.height)
        pref_minAge.setText(userPreference?.minAge)
        pref_maxAge.setText(userPreference?.maxAge)
        pref_nationality.text = userPreference?.nationalty?.joinToString(",")
        pref_name.setText(userPreference?.name)
        pref_origin.text = userPreference?.origin?.joinToString(",")
        pref_languages.text = userPreference?.language?.joinToString(",")
        pref_halal.text=userPreference?.halal
        pref_quran.text=userPreference?.completedQuran
        pref_prays.text=userPreference?.prays
        pref_revert.text=userPreference?.revert
        pref_beard.text=userPreference?.beard
        pref_wear.text=userPreference?.wears
        pref_living_after_marriage.text=userPreference?.livingArrangementAfterMarriage
        pref_marriage_plans.text=userPreference?.marriagePlan
        pref_profession.text=userPreference?.profession
        pref_complexion.text = userPreference?.complexion
        pref_eyes_color.text = userPreference?.eyeColor
        pref_hair_color.text = userPreference?.hairColor
        pref_education.text = userPreference?.education

    }


    private fun setOnClick() {

        preference_done.setOnClickListener {

            userPreference = UserPreference(
                name = pref_name.text.toString(),
                minAge = pref_minAge.text.toString(),
                maxAge = pref_maxAge.text.toString(),
                nationalty = pref_nationality.text.split(","),
                origin = pref_origin.text.split(","),
                language = languageList,
                sect = caste,
                marital = marital,
                livingArrangement = livingArrangements,
                height = pref_height.text.toString(),
                build = build,
                complexion = pref_complexion.text.toString(),
                eyeColor = pref_eyes_color.text.toString(),
                hairColor = pref_hair_color.text.toString(),
                education = pref_education.text.toString(),
                profession = pref_profession.text.toString(),
                marriagePlan = pref_marriage_plans.text.toString(),
                livingArrangementAfterMarriage = pref_living_after_marriage.text.toString(),
                wears = pref_wear.text.toString(),
                beard = pref_beard.text.toString(),
                revert = pref_revert.text.toString(),
                halal = pref_halal.text.toString(),
                prays = pref_prays.text.toString(),
                completedQuran = pref_quran.text.toString()
            )
            Preferences.setprefObject(PrefKeys.INSTANCE.userprefs, userPreference)
            Preferences.setPref(PrefKeys.INSTANCE.isDataSaved, true)
            onBackPressed()
        }
        pref_nationality.setOnClickListener {
            nationalityDialog?.show()
            getNationalityFromPicker()
        }
        */
/* pref_living_arrangement.setOnClickListener {
             if (living_arrangement_group.visibility == View.VISIBLE) {
                 living_arrangement_group.gone()
             } else living_arrangement_group.visible()
         }*//*


        */
/* pref_caste.setOnClickListener {
             if (caste_group.visibility == View.VISIBLE) {
                 caste_group.gone()
             } else {
                 caste_group.visible()
             }
         }*//*

        */
/* pref_build.setOnClickListener {
             if (build_bar.visibility == View.VISIBLE) {
                 build_bar.gone()
             } else {
                 build_bar.visible()
             }
         }*//*

        */
/* pref_marital_status.setOnClickListener {
             if (pref_relationship.visibility == View.VISIBLE) {
                 pref_relationship.gone()
             } else {
                 pref_relationship.visible()
             }
         }*//*




        */
/*   pref_lifeaftermarriage.setOnClickListener {
               if (life_after_marriage.visibility == View.VISIBLE) {
                   life_after_marriage.gone()
               } else {
                   life_after_marriage.visible()
               }
           }*//*


        */
/* pref_marriage_plan.setOnClickListener {
             if (marriage_plan.visibility == View.VISIBLE) {
                 marriage_plan.gone()
             } else {
                 marriage_plan.visible()
             }
         }*//*


        pref_languages.setOnClickListener {
            openLanguageDialog()
        }

        pref_education.setOnClickListener {
            openDialog(ProfileItem.EDUCATION,pref_education)
        }

        pref_profession.setOnClickListener {
            openDialog(ProfileItem.PROFESSION,pref_profession)
        }
        pref_origin.setOnClickListener {
            originDialog?.show()
            getOriginFromPicker()
        }
        pref_eyes_color.setOnClickListener {
            openDialog(ProfileItem.EYECOLOR,pref_eyes_color)
        }
        pref_hair_color.setOnClickListener {
            openDialog(ProfileItem.HAIRCOLOR,pref_hair_color)
        }
        pref_complexion.setOnClickListener {
            openDialog(ProfileItem.COLORCOMPLEXION,pref_complexion)
        }


        pref_marriage_plans.setOnClickListener {
            openDialog(ProfileItem.MARRIAGEPLANS,pref_marriage_plans)
        }
        pref_living_after_marriage.setOnClickListener {
            openDialog(ProfileItem.LIFEAFTERMARRIAGE,pref_living_after_marriage)
        }
        pref_prays.setOnClickListener {
            openDialog(ProfileItem.PRAYS,pref_prays)
        }
        pref_quran.setOnClickListener {
            openDialog(ProfileItem.QURAN,pref_quran)
        }
        pref_halal.setOnClickListener {
            openDialog(ProfileItem.HALAL,pref_halal)
        }
        pref_revert.setOnClickListener {
            openDialog(ProfileItem.REVERT,pref_revert)
        }
        pref_beard.setOnClickListener {
            openDialog(ProfileItem.BEARD,pref_beard)
        }
        pref_wear.setOnClickListener {
            openDialog(ProfileItem.WEAR,pref_wear)
        }


        // setPrayClick()
        // setQuranClick()
        // setAfterMarriageArrangementsClick()
        setMaritalStatusClick()
        // setBeardClick()
        setBuildClick()
        setCasteClick()
        // setHalalClick()
        setLivingArrangementClick()
        //  setRevertClick()
        //setWearClick()
        //  setMarriagePlansClick()

    }

    */
/*private fun setMarriagePlansClick() {
        val arraylist= arrayListOf("Small","Destination","Traditional")
        marriage_plan.adapter=PrefAdapter(arraylist, context!!, PrefType.MARRIAGEPLANS,  userPreference?.marriagePlan,this)
    }*//*


    */
/*  private fun setAfterMarriageArrangementsClick() {
          val arraylist= arrayListOf("Stay with Parents","Willing to relocate","Live Separately")
          life_after_marriage.adapter=PrefAdapter(arraylist, context!!, PrefType.LIVINGARRANGEMENTSAFTERMARRIAGE, userPreference?.livingArrangementAfterMarriage,this)
      }
  *//*


    */
/* private fun setPrayClick() {
         val arraylist= arrayListOf("Always","Sometimes")
         prays_group.adapter=PrefAdapter(arraylist, context!!, PrefType.PRAYS,userPreference?.prays,this)
     }*//*


    private fun getOriginFromPicker() {
        originDialog?.updateListener {
            pref_origin.text = it.joinToString(",")
        }
    }

    private fun setMaritalStatusClick() {
        val arraylist= arrayListOf("Single","Divorced","Widowed","Annulled")
        marital_status_recycler.adapter=PrefAdapter(arraylist, context!!, PrefType.MARITALSTATUS,userPreference?.marital!!,this)
    }

    private fun setCasteClick() {
        val arraylist= arrayListOf("Sunni","Shia","Other")
        caste_group.adapter=PrefAdapter(arraylist,context!!,PrefType.SECT,userPreference?.sect!!,this)
    }

    private fun setLivingArrangementClick() {
        val arraylist= arrayListOf("Living Alone","Living with Family","Living with Friends","Other")
        living_arrangement_group.adapter=PrefAdapter(arraylist,context!!,PrefType.CURRENTLIVINGARRANGEMENT,userPreference?.livingArrangement!!,this)
    }

    private fun setBuildClick() {
        val arraylist= arrayListOf("Athletic","Large","Medium","Slim")
        build_bar.adapter=PrefAdapter(arraylist,context!!,PrefType.BUILD,userPreference?.build!!,this)
    }

    */
/*  private fun setQuranClick() {
          val arraylist= arrayListOf("Yes","No")
          quran_radio_group.adapter=PrefAdapter(arraylist,context!!,PrefType.QURAN,userPreference?.completedQuran,this)
      }*//*



*/
/*    private fun setHalalClick() {
        val arraylist= arrayListOf("Always","Sometimes","Never")
        halal_radio_group.adapter=PrefAdapter(arraylist,context!!,PrefType.HALAL,userPreference?.halal,this)

    }

    private fun setRevertClick(){
        val arraylist= arrayListOf("Yes","No")
        revert_group.adapter=PrefAdapter(arraylist,context!!,PrefType.REVERT,userPreference?.halal,this)
    }

    private fun setBeardClick(){
        val arraylist= arrayListOf("Yes","No")
        beard_group.adapter=PrefAdapter(arraylist,context!!,PrefType.BEARD,userPreference?.beard,this)
    }

    private fun setWearClick() {
        val arraylist= arrayListOf("Hijab","Naqab")
        wear_group.adapter=PrefAdapter(arraylist,context!!,PrefType.WEAR,userPreference?.wears,this)
    }*//*


    private fun openDialog(item: String,textView: TextView) {
        val bundle = Bundle()
        bundle.putString("item_name", item)
        bundle.putString("item_type", "multiple")
        val dialog=ProfileItemFragment().apply {
            infoSelectListener(this@BottomSheetFragment,textView)
        }
        dialog.arguments=bundle
        dialog.show(fragmentManager!!,"profileItem")
        // baselistener.navigateToFragment(ProfileItemFragment::class, bundle)

    }

    private fun openLanguageDialog() {
        val dialog = LanguageDialog().apply {
            languageSelectListener(this@BottomSheetFragment)
        }
        dialog.show(fragmentManager!!, "Language")
    }

    private fun getNationalityFromPicker() {
        nationalityDialog?.updateListener {
            pref_nationality.text = it.joinToString(",")
        }
    }

    override fun editinformation(info: String, textView: TextView) {
        textView.setText(info)
    }


}
*/
