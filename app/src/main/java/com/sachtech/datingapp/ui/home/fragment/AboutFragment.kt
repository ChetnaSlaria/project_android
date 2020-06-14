/*
package com.sachtech.datingapp.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sachtech.datingapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.cometChat.CometChatUser
import com.sachtech.datingapp.data.Blockeduser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.getDateInformat
import com.sachtech.datingapp.extensions.isNotNull
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.MatchUserPresenter
import com.sachtech.datingapp.presenter.UserPresenter
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity
import com.sachtech.datingapp.ui.chat.activity.ReportDialog
import com.sachtech.datingapp.ui.home.adapter.MyPagerAdapter
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.PreferencesKt.getprefObject
import com.sachtech.datingapp.utils.getprefObject
import com.sachtech.datingapp.view.UserView
import gurtek.mrgurtekbase.listeners.KotlinBaseListener
import kotlinx.android.synthetic.main.activity_about.*

class AboutFragment(val baseListener: KotlinBaseListener) : BottomSheetDialogFragment(), UserView {
    override fun onUnlikeUser(user: User) {
        showToast("${user.name} is added to unlike list")

    }

    override fun onSuccess() {

    }

    override fun onGettingAllUsers(it: QuerySnapshot) {

    }

    override fun onLikeUser(user: User) {
//showToast("${user.name} is added to your like list.")
    }

    fun onBlockUser(user: User) {
    }

    override fun onFailure(message: String) {

    }

    private val userData by lazy { getprefObject<User>(PrefKeys.USER) }
    lateinit var myPagerAdapter: MyPagerAdapter
    val cometUser by lazy { CometChatUser() }
    lateinit var arraylist: ArrayList<String>
    val userPresenter by lazy { UserPresenter(this) }
    val firebaseHelper by lazy { FirebaseHelper() }
    var user: User? = null
    lateinit var currentUser: User
    //lateinit var detailsAdapter: DetailsAdapter
    var uid: String = ""
    val matchUserPresenter by lazy { MatchUserPresenter(this) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab_layout.setupWithViewPager(aboutViewpager)
        uid = arguments?.getString("uid")!!
        getUserDetails()
        currentUser = getprefObject<User>(PrefKeys.USER)!!
        arraylist = ArrayList()
        setViewpagerAdapter(arraylist)
        about_cancel.setOnClickListener {
            dismiss()
        }

        val layoutManager = FlexboxLayoutManager(context!!)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        like.setOnClickListener {
            if (user != null)
                userPresenter.addToLikeList(user!!)
        }
        dislike.setOnClickListener {
            if (user != null)
                userPresenter.addToUnlikeList(user!!)
        }
        message.setOnClickListener {

            //            if(userData?.accountStatus=="verified") {
            baseListener.showProgress()
            if (user != null) {

                userPresenter.addToLikeList(user!!)
                userPresenter.addToChatFriendList(user!!) {
                    matchUserPresenter.addFriendList(user!!) {
                        baseListener.hideProgress()
                        // arrayList.removeAt(position)
                        // aboutAdapter?.setNewList(arrayList)
                        val bundle = Bundle()
                        Log.e("user", "${user?.uid},${user?.name}")
                        bundle.putString("uid", user?.uid)
                        baseListener.openA(ChatMessageActivity::class, bundle)
                    }
        }
        showToast("Your account is Not verified yet")
    }
        block_option.setOnClickListener {
            val popupMenu = PopupMenu(context!!, block_option)
            popupMenu.inflate(R.menu.options)
            popupMenu.setOnMenuItemClickListener {
                when {
                    it.itemId == R.id.item_blocked -> {
                        if(user!=null) {
                            blockUser()
                        }
                        return@setOnMenuItemClickListener true
                    }
                    it.itemId == R.id.item_report -> {
                        if(user!=null) {
                            reportUser()
                        }
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popupMenu.show()
        }

    }


    private fun getUserDetails() {
        baseListener.showProgress()
        firebaseHelper.getUserDetailsFromDatabase(uid).addOnFailureListener {
            baseListener.hideProgress()
            showToast(it.message.toString())
        }.addOnSuccessListener {
            baseListener.hideProgress()
            user = it.toObject(User::class.java)!!
            setUserData()
        }
    }

    private fun setUserData() {
        if (user != null) {
            arraylist.add(user?.profilePic!!)
            if (user?.images != null) {
                for (i in 0 until user?.images!!.size) {
                    arraylist.add(user?.images!!.get(i))
                }
            }
            about_name.text = "${user?.name},${user?.age}"
          //  about_joindate.text = user?.createdOn?.getDateFromTimestamp()
            about_desc.text = user?.aboutMe
            about_family.text=user?.aboutFamily
            about_looking_for.text=user?.lookingFor
            about_beard.text=user?.beard
            about_build.text=user?.build
            about_cast.text=user?.sect
            about_dob.text=user?.dob?.getDateInformat()
            about_eyes_color.text=user?.eyeColour
            about_halal.text=user?.halal
            about_lifeaftermarriage.text=user?.livingArrangmentAfterMarriage
            about_living_arrangement.text=user?.currentLivingArrangment
            about_nationality.text=user?.nationality
            about_marriage_plan.text=user?.marriagePlans
            about_origin.text=user?.origin
            about_quran.text=user?.completedQuran
  if (user?.location != "")
                details.add(user?.location!!)


            if (user?.languages.isNotNull()) {
                if (user?.languages!!.isNotEmpty()) about_languages.text = user?.languages?.joinToString(",")!!
            }
            if (user?.maritalStatus != "") about_marital_status.text = user?.maritalStatus!!
            if (user?.height != "") about_height.text = user?.height!!
            if (user?.hairColour != "") about_hair_color.text = user?.hairColour!!
            if (user?.colorComplexion != "") about_complexion.text = user?.colorComplexion!!
            if (user?.education != "") about_education.text = user?.education!!
            if (user?.profession != "") about_profession.text = user?.profession!!
            if (user?.wear != "") about_wear.text = user?.wear!!
            if (user?.prays != "") about_prays.text = user?.prays!!
          //  detailsAdapter.notifyDataSetChanged()
            myPagerAdapter.notifyDataSetChanged()
            userPresenter.addToVisitList(user!!)
        }
    }
    private fun reportUser() {
        cometUser.getUserDetails(uid = user?.uid!!)
            {
                val reportDialog = ReportDialog()
                reportDialog.getChatUserData(it)
                reportDialog.show(activity?.supportFragmentManager!!, "report")
            }
    }

    private fun blockUser() {
       // baseListener.showProgress()
        val blockUserData =
            Blockeduser(currentUser.uid, currentUser.name, user?.uid!!, user?.name!!)
        firebaseHelper.blockedUsers(block = blockUserData).addOnSuccessListener {
           //  baseListener.hideProgress()
            showToast("Blocked successfully")
            //onBackPressed()
        }.addOnFailureListener {
            showToast(it.localizedMessage)
           //  baseListener.hideProgress()
        }
    }

    private fun setViewpagerAdapter(arraylist: ArrayList<String>) {
        myPagerAdapter = MyPagerAdapter(activity!!, arraylist)
        aboutViewpager.adapter = myPagerAdapter
    }

}
*/
