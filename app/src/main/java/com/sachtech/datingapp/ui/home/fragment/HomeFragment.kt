/*
package com.sachtech.datingapp.ui.home.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.firebase.firestore.QuerySnapshot
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatAuth
import com.sachtech.datingapp.cometChat.listener.OnCometLogin
import com.sachtech.datingapp.data.Blockeduser
import com.sachtech.datingapp.data.ChatUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.data.UserPreference
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.networking.FirebaseHelper
import com.sachtech.datingapp.presenter.MatchUserPresenter
import com.sachtech.datingapp.presenter.UserPresenter
import com.sachtech.datingapp.ui.home.adapter.AboutAdapter
import com.sachtech.datingapp.ui.home.adapter.OnCardAction
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.Preferences.setPref
import com.sachtech.datingapp.utils.PreferencesKt.*
import com.sachtech.datingapp.view.MatchUserView
import com.sachtech.datingapp.view.UserView
import com.yuyakaido.android.cardstackview.*
import gurtek.mrgurtekbase.KotlinBaseFragment
import gurtek.mrgurtekbase.gone
import kotlinx.android.synthetic.main.content_home.*


class HomeFragment : KotlinBaseFragment(R.layout.fragment_home), CardStackListener, OnCardAction,
    UserView,
    OnCometLogin, MatchUserView {
    var listenr: OnFragmentChange? = null
    override fun onCardLike(user: User) {
        showProgress()
        like_text.visibility = View.VISIBLE
        streamingCardStackView.swipeRight()
    }

    override fun onCardUnlike(user: User) {
        showProgress()
        unlike_text.visibility = View.VISIBLE
        streamingCardStackView.swipeLeft()
    }

    override fun onUnlikeUser(user: User) {
        hideProgress()
        if (unlike_text != null) {
            unlike_text.gone()
        }
    }


    override fun onSuccess() {

    }

    override fun onMatchUser(uid: String) {

    }

    override fun onCometLoginSuccess(p0: com.cometchat.pro.models.User?) {
        val chatUser=ChatUser(avatar = p0?.avatar!!,uid = p0?.uid!!,name = p0?.name!!,email = p0?.email!!)
        setprefObject(PrefKeys.COMETUSER, chatUser)
        filterUserByPrefs()
    }

    override fun onCometLoginFailure(localizedMessage: String) {
        filterUserByPrefs()
        // getUsers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenr?.selectedPos(1)
    }

    var count = 0
    private val firebaseHelper by lazy { FirebaseHelper() }
    val cometChatAuth by lazy { CometChatAuth(context!!) }
    val matchUserPresenter by lazy { MatchUserPresenter(this) }
    var userPrefs: UserPreference? = null
    override fun sendMessage(user: User, position: Int) {
        like_text.visibility = View.VISIBLE
        showProgress()
        userPresenter.notifyLikedUser(user)
        userPresenter.addToLikeList(user)
        userPresenter.addToChatFriendList(user) {
            matchUserPresenter.addFriendList(user) {
                hideProgress()
                like_text.gone()
                arrayList.removeAt(position)
                aboutAdapter?.setNewList(arrayList)
                val bundle = Bundle()
                bundle.putString("uid", user.uid)
               // baselistener.openA(ChatMessageActivity::class, bundle)

            }
        }

    }


    private fun getCurrentUserData() {
        showProgress()
        firebaseHelper.getUserDetailsFromDatabase(get(PrefKeys.INSTANCE.uid, "") as String? ?: "1")
            .addOnFailureListener {
                hideProgress()
                showToast(it.message.toString())

            }.addOnSuccessListener {
                hideProgress()
                setprefObject(PrefKeys.INSTANCE.user,it.data)
            }
    }


    override fun onFailure(message: String) {
        hideProgress()
        showToast(message)
    }

    override fun onLikeUser(likeduser: User) {
        hideProgress()
        if (like_text != null) {
            like_text.gone()
        }
        //
    }

    lateinit var blockedUserData: List<Blockeduser>
    override fun onGettingAllUsers(it: QuerySnapshot) {
        hideProgress()
        if (!it.isEmpty) {
            arrayList.clear()
            val user = it.toObjects(User::class.java)

            filterBlockedUSer(user as ArrayList<User>)

        } else showToast("No User Found")

    }

    private fun filterBlockedUSer(List: ArrayList<User>) {
        var arrayList = List

        firebaseHelper.getBlockedUser(userData!!.uid).addOnSuccessListener {
            blockedUserData = it.toObjects(Blockeduser::class.java)

            blockedUserData.forEach { blockedUser ->
                arrayList = arrayList.filter { originalData ->
                    originalData.uid != blockedUser.block_to_user_id
                } as ArrayList<User>

            }

            arrayList = arrayList.filter {
                it.uid != getUid()
            } as ArrayList<User>

            arrayList = arrayList.filter {
                it.gender != userData?.gender
            } as ArrayList<User>

            arrayList = arrayList.filter {
                it.accountStatus != Constants.HIDDEN
            } as ArrayList<User>


            this.arrayList = arrayList
            hideProgress()
            if (arrayList.size == 0)
                showToast("No users found having your interest")
            else
                aboutAdapter?.setNewList(arrayList)

        }.addOnFailureListener {
            hideProgress()
            showToast(it.localizedMessage)
        }
    }

    private val userData by lazy { getprefObject(PrefKeys.INSTANCE.user) as User }
    lateinit var arrayList: ArrayList<User>
    lateinit var manager: CardStackLayoutManager
    val userPresenter by lazy { UserPresenter(this) }
    var aboutAdapter: AboutAdapter? = null
    var position = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentUserData()
        listenr?.selectedPos(1)
        arrayList = ArrayList()
        aboutAdapter = AboutAdapter(this, this, baselistener)
        initializeCards()
        //if (get(PrefKeys.isDataSaved, false) == true) {
        filterUserByPrefs()
        // }
        loginWithCometChat()



        home_sort.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(baselistener)
            bottomSheetFragment.show(fragmentManager!!, bottomSheetFragment.tag)
        }
        tab_icon.setOnClickListener {
            home_sort.performClick()
        }

    }

    private fun loginWithCometChat() {
        //  showProgress()
        cometChatAuth.loginToCometChat(getUid()!!, this)
    }

    private fun getUsers() {
        showProgress()
        userPresenter.getAllUsers()
    }


    private fun initializeCards() {
        manager = CardStackLayoutManager(context, this)
        manager.setStackFrom(StackFrom.Bottom)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.07f)
        manager.setMaxDegree(30.0f)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setDirections(listOf(Direction.Left, Direction.Right))
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator() as Interpolator)
        streamingCardStackView.layoutManager = manager
        streamingCardStackView.adapter = aboutAdapter
        streamingCardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    var disAppearedposition: Int? = null
    override fun onCardDisappeared(view: View?, position: Int) {

        disAppearedposition = position
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {


    }

    override fun onCardSwiped(direction: Direction?) {
        count++
        setPref(PrefKeys.INSTANCE.swipecount, count)
        if (!userData?.isSubscribed) {
            if (get(PrefKeys.INSTANCE.swipecount, 0) as Int > 10) {
                showToast("Subscribe for more swipes")
                manager.setSwipeableMethod(SwipeableMethod.None)
            }
        }

        if (disAppearedposition != null) {
            if (direction == Direction.Left) {
                unlike_text.visibility = View.VISIBLE
                userPresenter.addToUnlikeList(arrayList[disAppearedposition!!])
            } else if (direction == Direction.Right) {
                like_text.visibility = View.VISIBLE
                userPresenter.addToLikeList(arrayList[disAppearedposition!!])
            }
        }
    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }


    private fun CardStackView.swipeRight() {
        like_text.visibility = View.GONE
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        this.swipe()
    }

    private fun CardStackView.swipeLeft() {
        unlike_text.visibility = View.GONE
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        this.swipe()
    }

    private fun CardStackView.swipeTop() {
        superlike_text.visibility = View.GONE
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Top)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        this.swipe()
    }

    val filterList: ArrayList<Pair<String, String>> = ArrayList()

    fun prepareFilterlist() {
        filterList.clear()
        userPrefs = getprefObject(PrefKeys.INSTANCE.userprefs) as UserPreference
        Log.e("userPrefs", userPrefs.toString())
        if (userPrefs?.halal?.isNotEmpty() == true) {
            userPrefs?.halal?.split(",")?.forEach {
                filterList.add(Pair("halal", it))
            }
        }

        if (userPrefs?.beard?.isNotEmpty() == true) {
            userPrefs?.beard?.split(",")?.forEach {
                filterList.add(Pair("beard", it))
            }
        }

        if (userPrefs?.build?.isNotEmpty() == true) {
            userPrefs?.build?.forEach {
                filterList.add(Pair("build", it))
            }
        }

        if (userPrefs?.completedQuran?.isNotEmpty() == true) {
            userPrefs?.completedQuran?.split(",")?.forEach {
                filterList.add(Pair("completedQuran", it))
            }
        }

        if (userPrefs?.hairColor?.isNotEmpty() == true) {
            userPrefs?.hairColor?.split(",")?.forEach {
                filterList.add(Pair("hairColor", ""))
            }
        }

        if (userPrefs?.complexion?.isNotEmpty() == true) {
            userPrefs?.complexion?.split(",")?.forEach {
                filterList.add(Pair("complexion", it))
            }
        }

        if (userPrefs?.education?.isNotEmpty() == true) {
            userPrefs?.education?.split(",")?.forEach {
                filterList.add(Pair("education", it))
            }
        }
        if (userPrefs?.language?.isNotEmpty() == true) {
            userPrefs?.language?.forEach {
                filterList.add(Pair("languages", it))
            }
        }

        if (userPrefs?.livingArrangement?.isNotEmpty() == true) {
            userPrefs?.livingArrangement?.forEach {
                filterList.add(Pair("currentLivingArrangment", it))
            }
        }

        if (userPrefs?.name?.isNotEmpty() == true) {
            filterList.add(Pair("name", userPrefs?.name.toString()))
        }

        if (userPrefs?.sect?.isNotEmpty() == true) {
            userPrefs?.sect?.forEach {
                filterList.add(Pair("sect", it))
            }
        }

        if (userPrefs?.education?.isNotEmpty() == true) {
            userPrefs?.education?.split(",")?.forEach {
                filterList.add(Pair("education", it))
            }
        }

    }

    private fun filterUserByPrefs() {
        prepareFilterlist()
        arrayList.clear()
        if (filterList.size == 0) {
            var query = firebaseHelper.firebaseFireStore.collection(firebaseHelper.collection)

            query.get().addOnSuccessListener {
                if (!it.isEmpty) {
                    arrayList.addAll(it.toObjects(User::class.java))

                    filterBlockedUSer(arrayList)
                }
            }.addOnFailureListener {

                // showToast(it.localizedMessage)
            }
        } else {
            filterpos = 0

            getFilterData(filterList[filterpos])
        }

    }

    var filterpos = 0
    fun getFilterData(filter: Pair<String, String>) {
        val query = firebaseHelper.firebaseFireStore.collection(firebaseHelper.collection)
            .whereEqualTo(filter.first, filter.second)

        query.get().addOnSuccessListener {
            if (!it.isEmpty) {
                arrayList.addAll(it.toObjects(User::class.java))
                filterpos += 1
                if (filterpos >= filterList.size) {
                    arrayList.distinctBy { it.uid }
                    filterBlockedUSer(arrayList)
                } else {
                    getFilterData(filterList[filterpos])
                }


            } else {
                filterpos += 1
                if (filterpos >= filterList.size) {
                    arrayList.distinctBy { it.uid }
                    filterBlockedUSer(arrayList)
                } else {
                    getFilterData(filterList[filterpos])
                }

            }
        }.addOnFailureListener {
            hideProgress()

        }

    }
}
*/
