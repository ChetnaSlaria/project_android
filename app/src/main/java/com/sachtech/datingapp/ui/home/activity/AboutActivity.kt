/*
package com.sachtech.datingapp.ui.home.activity

import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatUser
import com.sachtech.datingapp.data.Blockeduser
import com.sachtech.datingapp.data.LikeUsers
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.extensions.getDateInformat
import com.sachtech.datingapp.extensions.isNotNull
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.MatchUserPresenter
import com.sachtech.datingapp.presenter.UserPresenter
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity
import com.sachtech.datingapp.ui.chat.activity.ReportDialog
import com.sachtech.datingapp.ui.home.adapter.DetailsAdapter
import com.sachtech.datingapp.ui.home.adapter.MyPagerAdapter
import com.sachtech.datingapp.utils.PrefKeys
import com.sachtech.datingapp.utils.Preferences
import com.sachtech.datingapp.utils.getUid
import com.sachtech.datingapp.view.UserView
import kotlinx.android.synthetic.main.activity_about.*
import kotlin.collections.ArrayList


class AboutActivity : AppCompatActivity(), UserView {
    override fun onUnlikeUser(user: User) {
        showToast("${user.name} is added to unlike list")
    }

    override fun onSuccess() {

    }

    override fun onGettingAllUsers(it: QuerySnapshot) {

    }

    override fun onLikeUser(user: User) {
        // showToast("${user.name} is added to your like list.")
    }


    fun onBlockUser(user: User) {

    }

    override fun onFailure(message: String) {

    }

    lateinit var myPagerAdapter: MyPagerAdapter
    val cometUser by lazy { CometChatUser() }
    lateinit var arraylist: ArrayList<String>
    val userPresenter by lazy { UserPresenter(this) }
    val matchUserPresenter by lazy { MatchUserPresenter(this) }
    val firebaseHelper by lazy { FirebaseHelper() }
    var user: User? = null
    lateinit var currentUser: User
    var userType: String = ""
    lateinit var detailsAdapter: DetailsAdapter
    var uid: String = ""
    var likedList: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        tab_layout.setupWithViewPager(aboutViewpager)
        likedList = ArrayList()
        uid = intent.getStringExtra("uid")
        //  userType = intent.getStringExtra("user_type")
        getUserDetails()
        //getLikedUsers()
        currentUser = Preferences.getprefObject<User>(PrefKeys.INSTANCE.user)!!
        arraylist = ArrayList()
        setViewpagerAdapter(arraylist)
        about_cancel.setOnClickListener {
            onBackPressed()
        }

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER

        like.setOnClickListener {
            if (user != null)
                getLikedUsers(user!!)
        }
        dislike.setOnClickListener {
            if (user != null)
                getUnlikeUsers(user!!)
        }
        message.setOnClickListener {
          //  showProgress()
            if (user != null) {
                userPresenter.addToLikeList(user!!)
                userPresenter.addToChatFriendList(user!!) {
                    matchUserPresenter.addFriendList(user!!) {
            //            hideProgress()
                        val bundle = Bundle()
                        Log.e("user", "${user?.uid},${user?.name}")
                        bundle.putString("uid", user?.uid)
              //          openA(ChatMessageActivity::class, bundle)
                    }
                }
            }
        }
        block_option.setOnClickListener {
            val popupMenu = PopupMenu(this, block_option)
            popupMenu.inflate(R.menu.options)
            popupMenu.setOnMenuItemClickListener {
                when {
                    it.itemId == R.id.item_blocked -> {
                        blockUser()
                        return@setOnMenuItemClickListener true
                    }
                    it.itemId == R.id.item_report -> {
                        reportUser()
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            popupMenu.show()
        }

    }

    private fun getUnlikeUsers(user: User) {
        val doc = firebaseHelper.getCurrentLikedUser(getUid()!!, uid) {
            //hideProgress()
            when (it.status) {
                true -> {
                    val bundle=Bundle()
                    bundle.putString("uid",user.uid)
                    bundle.putString("documentId", it.documentSnapshot!!.id)
                    val dialog=VerifyRemoveUserDialog()
                    dialog.arguments=bundle
                    dialog.show(supportFragmentManager,"verifydialog")
                }
                false -> {
                    userPresenter.addToLikeList(user)
                    userPresenter.removeFromUnlikeList(user)
                }
            }
        }

    }

    private fun getLikedUsers(user: User) {
        firebaseHelper.getLikedUserList(getUid()!!).get().addOnSuccessListener {
            var likeUsers = it.toObjects(LikeUsers::class.java)
            if (!likeUsers.isNullOrEmpty()) {
              //  showProgress()
                likeUsers = likeUsers.distinctBy { it.liked_to_user_id }
                for (i in 0 until likeUsers.size) {
                    likedList?.add(likeUsers.get(i)?.liked_to_user_id!!)
                }
                if (likedList?.contains(uid)!!) {
                //    hideProgress()
                  //  showToast("Already liked")
                    val bundle = Bundle()
                    bundle.putString("uid", user.uid)
                    val dialog = ConfirmDialog()
                    dialog.arguments = bundle
                    dialog.show(supportFragmentManager, "verifydialog")
                } else {
                  //  hideProgress()
                    userPresenter.addToLikeList(user)
                    userPresenter.removeFromUnlikeList(user)
                }
            }
        }
    }

    private fun getUserDetails() {
        //showProgress()
        firebaseHelper.getUserDetailsFromDatabase(uid).addOnFailureListener {
          //  hideProgress()
            showToast(it.message.toString())
        }.addOnSuccessListener {
            //hideProgress()
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
            about_desc.text = user?.aboutMe
            about_family.text = user?.aboutFamily
            about_looking_for.text = user?.lookingFor
            about_beard.text = user?.beard
            about_build.text = user?.build
            about_cast.text = user?.sect
            about_dob.text = user?.dob?.getDateInformat()
            about_eyes_color.text = user?.eyeColour
            about_halal.text = user?.halal
            about_lifeaftermarriage.text = user?.livingArrangmentAfterMarriage
            about_living_arrangement.text = user?.currentLivingArrangment
            about_nationality.text = user?.nationality
            about_marriage_plan.text = user?.marriagePlans
            about_origin.text = user?.origin
            about_quran.text = user?.completedQuran
            about_revert.text = user?.revert
            if (user?.languages.isNotNull()) {
                if (user?.languages!!.isNotEmpty()) about_languages.text =
                    user?.languages?.joinToString(",")!!
            }
            if (user?.maritalStatus != "") about_marital_status.text = user?.maritalStatus!!
            if (user?.height != "") about_height.text = user?.height!!
            if (user?.hairColour != "") about_hair_color.text = user?.hairColour!!
            if (user?.colorComplexion != "") about_complexion.text = user?.colorComplexion!!
            if (user?.education != "") about_education.text = user?.education!!
            if (user?.profession != "") about_profession.text = user?.profession!!
            if (user?.wear != "") about_wear.text = user?.wear!!
            if (user?.prays != "") about_prays.text = user?.prays!!
            //detailsAdapter.notifyDataSetChanged()
            myPagerAdapter.notifyDataSetChanged()
            userPresenter.addToVisitList(user!!)
        }
    }

    private fun reportUser() {
        cometUser.getUserDetails(user?.uid!!)
        {
            val reportDialog = ReportDialog()
            reportDialog.getChatUserData(it as com.cometchat.pro.models.User)
            reportDialog.show(supportFragmentManager!!, "report")
        }
    }

    private fun blockUser() {
     //   showProgress()
        val blockUserData =
            Blockeduser(currentUser.uid, currentUser.name, user?.uid!!, user?.name!!)
        firebaseHelper.blockedUsers(block = blockUserData).addOnSuccessListener {
       //     hideProgress()
            showToast("Blocked successfully")
            //onBackPressed()
        }.addOnFailureListener {
            showToast(it.localizedMessage)
         //   hideProgress()
        }
    }

    private fun setViewpagerAdapter(arraylist: ArrayList<String>) {
        myPagerAdapter = MyPagerAdapter(this, arraylist)
        aboutViewpager.adapter = myPagerAdapter
    }

}
*/
