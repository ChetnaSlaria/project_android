/*
package com.sachtech.datingapp.ui.profile

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.network.apiHitter
import com.sachtech.datingapp.data.ChatUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.*
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.ProfileImagePresenter
import com.sachtech.datingapp.presenter.ProfileUpdatePresenter
import com.sachtech.datingapp.ui.profile.listener.OnLanguageSelect
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.getprefObject
import com.sachtech.datingapp.utils.Preferences.setprefObject
import com.sachtech.datingapp.view.ProfileImageView
import com.sachtech.datingapp.view.ProfileUpdateView
import com.squareup.picasso.Picasso
import gurtek.mrgurtekbase.KotlinBaseFragment
import gurtek.mrgurtekbase.gone
import gurtek.mrgurtekbase.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.util.*

class EditProfileFragment : KotlinBaseFragment(R.layout.fragment_edit_profile),
    SelectImageUtils.OnSelectImageListener, OnEditInformation,
    ProfileImageView, ProfileUpdateView,
    OnLanguageSelect, EditInformation {
    override fun editinformation(info: String, textView: TextView) {
        if (info.isNotEmpty()) {
        textView.text= info?:"" */
/*  when (info) {
                ProfileItem.HAIRCOLOR -> {
                    get(PrefKeys.HAIRCOLOR, "")
                }
                ProfileItem.EYECOLOR -> {
                    get(PrefKeys.EYECOLOR, "")
                }
                ProfileItem.PROFESSION -> {
                    get(PrefKeys.PROFESSION, "")
                }
                ProfileItem.EDUCATION -> {
                    get(PrefKeys.EDUCATION, "")
                }
                ProfileItem.NATIONALITY -> {
                    get(PrefKeys.NATIONALITY, "")
                }
                ProfileItem.COLORCOMPLEXION -> {
                    get(PrefKeys.COLORCOMPLEXION, "")
                }
                ProfileItem.ORIGIN -> {
                    get(PrefKeys.ORIGIN, "")

                }*//*

            */
/*else ->{
                ""
            }*//*

            }
    }

    var wear: String = Wear.HIJAB
    var prays: String = Prays.ALWAYS
    var beard = Beard.YES
    var halal = Halal.ALWAYS
    var revert = Revert.YES
    var completedQuran = CompletedQuran.YES
    var dob: Long = 0
    var age = 0
    var languages: ArrayList<String> = arrayListOf()
    var originDialog: CountryPickerDialog? = null
    var nationalityDialog: CountryPickerDialog? = null

    private val firebaseHelper by lazy { FirebaseHelper() }

    private val user by lazy { getprefObject<User>(PrefKeys.INSTANCE.user)!! }
    override fun getLanguage(language: ArrayList<String>?) {
        languages.addAll(language!!)
        edit_languages.text = language.joinToString(",")
    }

    override fun onUpdateProfile() {
        firebaseHelper.getUserDetailsFromDatabase(user.uid).addOnSuccessListener {
            setprefObject(PrefKeys.INSTANCE.user, it.toObject(User::class.java))
           updateOnCometChat()
        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }

    }
    private fun updateOnCometChat() {
        cometUser?.avatar=profilePic
        cometUser?.email=user.email
        cometUser?.name=user.name
        cometUser?.uid=user.uid
        apiHitter().updateUserOnCometChat(apikey = resources.getString(R.string.comet_api_key),appId = resources.getString(R.string.comet_app_id),uid = cometUser?.uid!!,chatUser = cometUser!!).subscribeOn(
            Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            hideProgress()
            setprefObject(PrefKeys.COMETUSER,cometUser)
            onBackPressed()

        },{
            hideProgress()
            showToast(it.localizedMessage)
        })
    }

    override fun onAdditionalImagesUpload(it: Uri, imageView: ImageView?) {
        hideProgress()
        if (imageView?.drawable.isNotNull()) {
            imageView?.setImageURI(uri)
            when (imageView) {
                image2 -> images.set(0, it.toString())
                image3 -> images.set(1, it.toString())
                image4 -> images.set(2, it.toString())
                image5 -> images.set(3, it.toString())
                image6 -> images.set(4, it.toString())
            }
        } else {
            when {
                image2.drawable.isNull() -> {
                    images.add(0, it.toString())
                    image2.setImageURI(uri)
                }
                image3.drawable.isNull() -> {
                    images.add(1, it.toString())
                    image3.setImageURI(uri)
                }
                image4.drawable.isNull() -> {
                    images.add(2, it.toString())
                    image4.setImageURI(uri)
                }
                image5.drawable.isNull() -> {
                    images.add(3, it.toString())
                    image5.setImageURI(uri)
                }
                image6.drawable.isNull() -> {
                    images.add(4, it.toString())
                    image6.setImageURI(uri)
                }
            }
        }
    }

    override fun onUploadSuccessful(it: Uri) {
        hideProgress()
        profilePic = it.toString()
        image1.setImageURI(uri)
    }

    override fun onFailure(message: String) {
        hideProgress()
        showToast(message)
    }

    override fun onImageSelected(path: Uri, view: ImageView?) {
        uri = path
        showProgress()
        when (view) {
            image1 -> profileImagePresenter.uploadProfilePicture(path)
            else -> profileImagePresenter.uploadAdditionalImages(path, view)
        }
    }

    override fun information(info: String, textView: TextView) {
        if(info.isNotEmpty())
        {
            textView.visible()
        }
        textView.text = info
    }

    var cometUser:ChatUser?=null
    var scrollX = 0
    var scrollY = -1
    val selectImageUtils by lazy {
        SelectImageUtils(context!!).apply {
            selectImage(this@EditProfileFragment)
        }
    }
    var images: ArrayList<String> = arrayListOf()
    var profilePic: String = ""
    var uri: Uri? = null
    val profileImagePresenter by lazy { ProfileImagePresenter(this) }
    val profileUpdatePresenter by lazy { ProfileUpdatePresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance
        cometUser= getprefObject(PrefKeys.COMETUSER)
        nationalityDialog = CountryPickerDialog(context!!)
        originDialog = CountryPickerDialog(context!!)
        setUserDataInProfile()
        selectOnImageClickListener()
        setOnClick()
        setOnRadioCheckListener()
        setValues()
        editProfile_private.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                showToast("Your account is now public")
            } else {
                showToast("Your account is now private")
            }
        }
    }


    private fun setValues() {
        if(!user.aboutFamily.isNullOrEmpty())
            edit_about_family.visible()
        if(!user.aboutMe.isNullOrEmpty())
            edit_about_me.visible()
        if(!user.profileHighlight.isNullOrEmpty())
            edit_profile_highlight.visible()
        if(!user.lookingFor.isNullOrEmpty())
            looking_for.visible()
        edit_complexion.text = getColorComplexion()
        edit_education.text = getEducation()
        edit_eyes_color.text = getEyeColor()
        edit_hair_color.text = getHairColor()
        //  edit_profession.text = getProfession()
        edit_languages.text = getLanguage()
    }

    private fun selectOnImageClickListener() {
        image1.setOnClickListener {
            selectImageUtils.openChooser(this, image1)
        }
        image2.setOnClickListener {
            selectImageUtils.openChooser(this, image2)
        }
        image3.setOnClickListener {
            selectImageUtils.openChooser(this, image3)
        }
        image4.setOnClickListener {
            selectImageUtils.openChooser(this, image4)
        }
        image5.setOnClickListener {
            selectImageUtils.openChooser(this, image5)
        }
        image6.setOnClickListener {
            selectImageUtils.openChooser(this, image6)
        }
    }

    private fun setUserDataInProfile() {
        edit_username.setText(user.name)
        edit_profile_highlight.text = user.profileHighlight
        edit_about_family.text = user.aboutFamily
        looking_for.text = user.lookingFor
        edit_about_me.text = user.aboutMe
        edit_cast.text = user.sect
        edit_complexion.text = user.colorComplexion
        edit_build.text = user.build
        looking_for.text = user.lookingFor
        edit_education.text = user.education
        edit_eyes_color.text = user.eyeColour
        edit_hair_color.text = user.hairColour
        edit_height.text = user.height
        edit_lifeaftermarriage.text = user.livingArrangmentAfterMarriage
        edit_nationality.text = user.nationality
        edit_origin.text = user.origin
        edit_living_arrangement.text = user.currentLivingArrangment
        edit_marital_status.text = user.maritalStatus
        edit_marriage_plan.text = user.marriagePlans
        edit_dob.text = user.dob.getDateInformat()
        edit_profession.setText(user.profession)

        when {
            user.halal == Halal.NEVER -> halal_never.isChecked = true
            user.halal == Halal.SOMETIMES -> halal_sometimes.isChecked = true
            user.halal == Halal.ALWAYS -> halal_always.isChecked = true
        }

        if (user.beard == Beard.NO)
            beard_no.isChecked = true
        else if (user.beard == Beard.YES)
            beard_yes.isChecked = true

        if (user.completedQuran == CompletedQuran.NO)
            quran_no.isChecked = true
        else if (user.completedQuran == CompletedQuran.YES) quran_yes.isChecked = true

        if (user.revert == Revert.NO)
            revert_no.isChecked = true
        else if (user.revert == Revert.YES) revert_yes.isChecked = true

        if (user.wear == Wear.NAQAB)
            naqab.isChecked = true
        else if (user.wear == Wear.HIJAB) hijab.isChecked = true

        if (user.prays == Prays.SOMETIMES)
            prays_sometimes.isChecked = true
        else if (user.prays == Prays.ALWAYS)
            prays_always.isChecked = true
        profilePic = user.profilePic
        if (profilePic != "") {
            Picasso.get().load(user.profilePic).into(image1)
        }
        age = user.age.toInt()
        edit_languages.text = user.languages?.joinToString(",")

        try {
            if (user.images.isNotNull()) {
                for (i in 0 until user.images!!.size)
                    when (i) {
                        0 -> image2.setImageURI(Uri.parse(user.images!![i]))
                        1 -> image3.setImageURI(Uri.parse(user.images!![i]))
                        2 -> image4.setImageURI(Uri.parse(user.images!![i]))
                        3 -> image5.setImageURI(Uri.parse(user.images!![i]))
                        4 -> image6.setImageURI(Uri.parse(user.images!![i]))
                    }
            }
        } catch (e: Exception) {

        }

        Log.e("private", user.private.toString())
        editProfile_private.isChecked = user.private
    }

    private fun setOnRadioCheckListener() {
        height_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                edit_height.text = "$progress cm"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                edit_height.text = "${seekBar?.progress} cm"
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        build_bar.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.weight_athletic -> edit_build.text = Weight.ATHLETIC
                R.id.weight_large -> edit_build.text = Weight.LARGE
                R.id.weight_medium -> edit_build.text = Weight.MEDIUM
                R.id.weight_slim -> edit_build.text = Weight.SLIM
            }
        }

        life_after_marriage.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.after_marriage_live_separately -> edit_lifeaftermarriage.text =
                    LifeAfterMarriage.LIVESEPARATE
                R.id.after_marriage_willing_to_relocate -> edit_lifeaftermarriage.text =
                    LifeAfterMarriage.WILLINGTORELOCATE
                R.id.after_marriagr_stay_with_parents -> edit_lifeaftermarriage.text =
                    LifeAfterMarriage.STAYWITHPARENTS
            }
        }
        relationship.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.single_status -> edit_marital_status.text = MaritalStatus.SINGLE
                R.id.widowed_status -> edit_marital_status.text = MaritalStatus.WIDOWED
                R.id.annulled_status -> edit_marital_status.text = MaritalStatus.ANNULLED
                R.id.divorced_status -> edit_marital_status.text = MaritalStatus.DIVORCED
            }
        }

        caste_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.caste_other -> edit_cast.text = Sect.OTHER
                R.id.caste_shia -> edit_cast.text = Sect.SHIA
                R.id.caste_sunni -> edit_cast.text = Sect.SUNNI
            }
        }

        living_arrangement_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.living_alone -> edit_living_arrangement.text = LivingArrangement.LIVINGALONE
                R.id.living_friends -> edit_living_arrangement.text =
                    LivingArrangement.LIVINGWITHFRIENDS
                R.id.living_family -> edit_living_arrangement.text =
                    LivingArrangement.LIVINGWITHFAMILY
                R.id.living_other -> edit_living_arrangement.text = LivingArrangement.OTHER
            }
        }

        wear_radio_group.setOnCheckedChangeListener { group, checkedId ->
            wear = if (checkedId == R.id.naqab)
                Wear.NAQAB
            else Wear.HIJAB
        }
        halal_radio_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.halal_always -> halal = Halal.ALWAYS
                R.id.halal_sometimes -> halal = Halal.SOMETIMES
                R.id.halal_never -> halal = Halal.NEVER
            }
        }
        revert_radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.revert_yes)
                revert = Revert.YES
            else if (checkedId == R.id.revert_no)
                revert = Revert.NO
        }

        beard_radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.beard_no)
                beard = Beard.NO
            else if (checkedId == R.id.beard_yes)
                beard = Beard.YES
        }
        prays_radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.prays_always)
                prays = Prays.ALWAYS
            else if (checkedId == R.id.prays_sometimes)
                prays = Prays.SOMETIMES
        }
        quran_radio_group.setOnCheckedChangeListener { group, checkedId ->
            completedQuran = if (checkedId == R.id.quran_no) {
                CompletedQuran.NO
            } else CompletedQuran.YES
        }


    }

    private fun setOnClick() {
        back.setOnClickListener { onBackPressed() }
        aboutMe.setOnClickListener {
            openEditDialog(edit_about_me, "About Me")
        }
        profileHighlight.setOnClickListener {
            openEditDialog(edit_profile_highlight, "Profile Highlight")
        }
        aboutMyFamily.setOnClickListener {
            openEditDialog(edit_about_family, "About Family")
        }
        aboutImLookingFor.setOnClickListener {
            openEditDialog(looking_for, "Looking For")
        }
        edit_nationality.setOnClickListener {
            getNationalityFromPicker()

        }
        edit_living_arrangement.setOnClickListener {
            if (living_arrangement_group.visibility == View.VISIBLE) {
                living_arrangement_group.gone()
            } else living_arrangement_group.visible()
        }
        edit_dob.setOnClickListener {
            selectDateFromDatePicker()
        }
        edit_profileDone.setOnClickListener {
            updateProfileData()
        }

        edit_cast.setOnClickListener {
            if (caste_group.visibility == View.VISIBLE) {
                caste_group.gone()
            } else {
                caste_group.visible()
            }
        }
        edit_build.setOnClickListener {
            if (build_bar.visibility == View.VISIBLE) {
                build_bar.gone()
            } else {
                build_bar.visible()
            }
        }
        edit_marital_status.setOnClickListener {
            if (relationship.visibility == View.VISIBLE) {
                relationship.gone()
            } else {
                relationship.visible()
            }
        }

        edit_height.setOnClickListener {
            if (height_bar.visibility == View.VISIBLE) {
                height_bar.gone()
            } else {
                height_bar.visible()
            }
        }

        edit_lifeaftermarriage.setOnClickListener {
            if (life_after_marriage.visibility == View.VISIBLE) {
                life_after_marriage.gone()
            } else {
                life_after_marriage.visible()
            }
        }

        edit_marriage_plan.setOnClickListener {
            if (marriage_plan.visibility == View.VISIBLE) {
                marriage_plan.gone()
            } else {
                marriage_plan.visible()
            }
        }

        edit_languages.setOnClickListener {
            openLanguageDialog()
        }

        edit_education.setOnClickListener {
            openDialog(ProfileItem.EDUCATION, edit_education)
        }

        edit_profession.setOnClickListener {
            openDialog(ProfileItem.PROFESSION, edit_profession)
        }
        edit_origin.setOnClickListener {
            originDialog?.show()
            originDialog?.setOnSelectedListener {
                edit_origin.text = it.getName(context!!)
            }

            //  openDialog(ProfileItem.ORIGIN)
        }
        edit_eyes_color.setOnClickListener {
            openDialog(ProfileItem.EYECOLOR, edit_eyes_color)
        }
        edit_hair_color.setOnClickListener {
            openDialog(ProfileItem.HAIRCOLOR, edit_hair_color)
        }
        edit_complexion.setOnClickListener {
            openDialog(ProfileItem.COLORCOMPLEXION, edit_complexion)
        }

    }

    private fun openEditDialog(textView: TextView, s: String) {
        val bundle = Bundle()
        bundle.putString("edit_type", s)
        val dialog = EditAboutDialog().apply {
            infoSelectListener(this@EditProfileFragment, textView)
        }
        dialog.arguments = bundle
        dialog.show(childFragmentManager, "Language")
    }


    private fun getNationalityFromPicker() {
        nationalityDialog?.show()
        nationalityDialog?.setOnSelectedListener {
            if (edit_nationality.text.toString().isEmpty()) {
                edit_nationality.text =
                    edit_nationality.text.toString() + " " + it.getName(context!!)
            } else {
                edit_nationality.text =
                    edit_nationality.text.toString() + " , " + it.getName(context!!)
            }
        }

    }

    private fun openLanguageDialog() {
        val dialog = LanguageDialog().apply {
            languageSelectListener(this@EditProfileFragment)
        }
        dialog.show(childFragmentManager, "Language")
    }

    private fun updateProfileData() {
        val about_me = edit_about_me.text.toString()
        val about_family = edit_about_family.text.toString()
        val userName = edit_username.text.toString()
        val highlight = edit_profile_highlight.text.toString()
        user.aboutMe = about_me
        user.aboutFamily = about_family
        user.beard = beard
        user.age = age.toString()
        user.build = edit_build.text.toString()
        user.completedQuran = completedQuran
        user.colorComplexion = edit_complexion.text.toString()
        user.currentLivingArrangment = edit_living_arrangement.text.toString()
        user.education = edit_education.text.toString()
        user.eyeColour = edit_eyes_color.text.toString()
        user.wear = wear
        user.halal = halal
        user.languages = languages
        user.hairColour = edit_hair_color.text.toString()
        user.height = edit_height.text.toString()
        user.livingArrangmentAfterMarriage = edit_lifeaftermarriage.text.toString()
        user.maritalStatus = edit_marital_status.text.toString()
        user.marriagePlans = edit_marriage_plan.text.toString()
        user.name = userName
        user.nationality = edit_nationality.text.toString()
        user.origin = edit_origin.text.toString()
        user.profilePic = profilePic
        user.prays = prays
        user.profession = edit_profession.text.toString()
        user.revert = revert
        user.sect = edit_cast.text.toString()
        user.images = images
        user.profileHighlight = highlight
        user.lookingFor = looking_for.text.toString()
        showProgress()
        profileUpdatePresenter.updateProfileOnDatabase(user)
    }

    private fun selectDateFromDatePicker() {
        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val datePicker = DatePickerDialog(
            context!!,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                edit_dob.text = formatDate("$year-${month + 1}-$dayOfMonth").getDateInformat()
                dob = formatDate("$year-${month + 1}-$dayOfMonth")
                age = getAge(year, month + 1, dayOfMonth)
                if (age < 18)
                    selectDateFromDatePicker()
            }, year, month, date
        )
        datePicker.show()
    }


    private fun openDialog(item: String, textBox: TextView) {
        val bundle = Bundle()
        bundle.putString("item_name", item)
        bundle.putString("item_type", "single")
        val profileItemFragment = ProfileItemFragment().apply {
            infoSelectListener(this@EditProfileFragment, textBox)
        }
        profileItemFragment.arguments = bundle
        profileItemFragment.show(childFragmentManager, "p")
        // showDialog(ProfileItemFragment::class,bundle)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        selectImageUtils.onActivityResult(requestCode, resultCode, data)
    }


    override fun onPause() {
        scrollX = scroll.scrollX
        scrollY = scroll.scrollY
        super.onPause()


    }

    override fun onResume() {
        super.onResume()
        setValues()
        scroll.scrollTo(scrollX, scrollY)
    }

}

*/
