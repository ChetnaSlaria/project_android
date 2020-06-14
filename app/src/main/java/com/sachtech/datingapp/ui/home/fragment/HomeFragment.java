package com.sachtech.datingapp.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatAuth;
import com.sachtech.datingapp.cometChat.listener.OnCometLogin;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.ChatUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.data.UserPreference;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.MatchUserPresenter;
import com.sachtech.datingapp.presenter.UserPresenter;
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity;
import com.sachtech.datingapp.ui.home.adapter.AboutAdapter;
import com.sachtech.datingapp.ui.home.adapter.listener.OnCardAction;
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.Constants;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.MatchUserView;
import com.sachtech.datingapp.view.UserView;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting.Builder;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gurtek.mrgurtekbase.ExtenstionsKt;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;


public final class HomeFragment extends Fragment implements CardStackListener, OnCardAction, UserView, OnCometLogin, MatchUserView, OnFriendAdded {
    @Nullable
    private OnFragmentChange listenr;
    private int count;
    private UserPreference userPrefs;
    @NotNull
    public List blockedUserData;
    @NotNull
    public ArrayList arrayList;
    ArrayList filterList;
    @NotNull
    public CardStackLayoutManager manager;
    @Nullable
    private AboutAdapter aboutAdapter;
    private int position;
    @Nullable
    private Integer disAppearedposition;
    private int filterpos;
    CardStackView stackView;

    @Nullable
    public final OnFragmentChange getListenr() {
        return this.listenr;
    }

    public final void setListenr(@Nullable OnFragmentChange var1) {
        this.listenr = var1;
    }

    public void onCardLike(@NotNull User user) {
        likeTExt.setVisibility(View.VISIBLE);
        this.swipeRight(stackView);
    }

    public void onCardUnlike(@NotNull User user) {
        unlikeText.setVisibility(View.VISIBLE);
        this.swipeLeft(stackView);
    }

    public void onUnlikeUser(@NotNull User user) {
        ExtenstionsKt.gone((View) unlikeText);
    }

    public void onSuccess() {
    }

    public void onMatchUser(@NotNull String uid) {
        Intrinsics.checkParameterIsNotNull(uid, "uid");
    }

    public void onCometLoginSuccess(@Nullable com.cometchat.pro.models.User p0) {

        String var10002 = p0 != null ? p0.getAvatar() : null;
        String var10003 = p0 != null ? p0.getUid() : null;

        String var10004 = p0 != null ? p0.getName() : null;
        String var10005 = p0 != null ? p0.getLink() : null;
        ChatUser var10000 = new ChatUser(var10003,var10004,null,var10002);
        ChatUser chatUser = var10000;
        Preferences.INSTANCE.setPref(PrefKeys.COMETUSER,new Gson().toJson(chatUser));
        this.filterUserByPrefs();
    }

    public void onCometLoginFailure(@NotNull String localizedMessage) {
        this.filterUserByPrefs();
    }

    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        OnFragmentChange var10000 = this.listenr;
        if (var10000 != null) {
            var10000.selectedPos(1);
        }

    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int var1) {
        this.count = var1;
    }

    private final FirebaseHelper getFirebaseHelper() {
        return new FirebaseHelper();
    }

    @NotNull
    public final CometChatAuth getCometChatAuth() {
        return new CometChatAuth(getContext());
    }

    @NotNull
    public final MatchUserPresenter getMatchUserPresenter() {
        return new MatchUserPresenter(this);
    }

    @Nullable
    public final UserPreference getUserPrefs() {
        return this.userPrefs;
    }

    public final void setUserPrefs(@Nullable UserPreference var1) {
        this.userPrefs = var1;
    }

    public void sendMessage(@NotNull final User user, final int position) {
        this.user=user;
        likeTExt.setVisibility(View.VISIBLE);
        this.getUserPresenter().notifyLikedUser(user);
        this.getUserPresenter().addToLikeList(user);
        getUserPresenter().addToChatFriendList(user,this::friendAdded);
       /* this.getUserPresenter().addToChatFriendList(user, (Function1) (new Function1() {


            @Override
            public Object invoke(Object o) {
                Hom
                return Unit.INSTANCE;
            }

            public  void invoke(boolean it) {
                HomeFragment.this.getMatchUserPresenter().addFriendList(user, (Function1) (new Function1() {
                    // $FF: synthetic method
                    // $FF: bridge method
                    public Object invoke(Object var1) {
                        this.invoke((Boolean) var1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean it) {
                        HomeFragment.this.getArrayList().remove(position);
                        AboutAdapter var3 = HomeFragment.this.getAboutAdapter();
                        if (var3 != null) {
                            var3.setNewList(HomeFragment.this.getArrayList());
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString("uid", user.getUid());
                    }
                }));
            }
        }));*/
    }
User user;
    private final void getCurrentUserData() throws Throwable {
        FirebaseHelper var10000 = this.getFirebaseHelper();
        PrefKeys var10001 = PrefKeys.INSTANCE;
        String var1 = (String) Preferences.INSTANCE.getPrefs().getString(var10001.getUID(), "");
        if (var1 == null) {
            var1 = "1";
        }

        var10000.getUserDetailsFromDatabase(var1).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
            public final void onFailure(@NotNull Exception it) {
                CommonsKt.showToast(String.valueOf(it.getMessage()));
            }
        })).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((DocumentSnapshot) var1);
            }

            public final void onSuccess(DocumentSnapshot it) {
                PrefKeys var10000 = PrefKeys.INSTANCE;
                String var2 = var10000.getUSER();
                Preferences.INSTANCE.setPref(var2,new Gson().toJson(it.getData()));
            }
        }));
    }

    public void onFailure(@NotNull String message) {
        CommonsKt.showToast(message);
    }

    public void onLikeUser(@NotNull User likeduser) {
        if (likeTExt != null) {
            ExtenstionsKt.gone((View) likeTExt);
        }

    }

    @NotNull
    public final List getBlockedUserData() {
        List var10000 = this.blockedUserData;
        return var10000;
    }

    public final void setBlockedUserData(@NotNull List var1) {
        this.blockedUserData = var1;
    }

    public void onGettingAllUsers(@NotNull QuerySnapshot it) {
        if (!it.isEmpty()) {
            ArrayList var10000 = this.arrayList;

            var10000.clear();
            List var3 = it.toObjects(User.class);
            List user = var3;
            if (user == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.sachtech.datingapp.data.User> /* = java.util.ArrayList<com.sachtech.datingapp.data.User> */");
            }

            this.filterBlockedUSer((ArrayList) user);
        } else {
            CommonsKt.showToast("No User Found");
        }

    }

    private final void filterBlockedUSer(ArrayList List) {
        final ObjectRef arrayList = new ObjectRef();
        arrayList.element = List;
        FirebaseHelper var10000 = this.getFirebaseHelper();
        User var10001 = this.getUserData();

        var10000.getBlockedUser(var10001.getUid()).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((QuerySnapshot) var1);
            }

            public final void onSuccess(QuerySnapshot it) {
                HomeFragment var10000 = HomeFragment.this;
                List var10001 = it.toObjects(Blockeduser.class);
                var10000.setBlockedUserData(var10001);
                Iterable $this$filter$iv = (Iterable) HomeFragment.this.getBlockedUserData();

                ObjectRef var9;
                boolean $i$f$filterx;
                List var18;
                for (Iterator var4 = $this$filter$iv.iterator(); var4.hasNext(); var9.element = (ArrayList) var18) {
                    Object element$iv = var4.next();
                    Blockeduser blockedUser = (Blockeduser) element$iv;
                    Iterable $this$filter$ivx = (Iterable) ((ArrayList) arrayList.element);
                    var9 = arrayList;
                    $i$f$filterx = false;
                    Collection destination$iv$iv = (Collection) (new ArrayList());
                    Iterator var14 = $this$filter$ivx.iterator();

                    while (var14.hasNext()) {
                        Object element$iv$ivx = var14.next();
                        User originalData = (User) element$iv$ivx;
                        if (Intrinsics.areEqual(originalData.getUid(), blockedUser.getBlock_to_user_id()) ^ true) {
                            destination$iv$iv.add(element$iv$ivx);
                        }
                    }

                    var18 = (List) destination$iv$iv;
                }

                $this$filter$iv = (Iterable) ((ArrayList) arrayList.element);
                ObjectRef var19 = arrayList;
                Collection destination$iv$ivx = (Collection) (new ArrayList());
                Iterator var23 = $this$filter$iv.iterator();

                Object element$iv$iv;
                User itx;
                while (var23.hasNext()) {
                    element$iv$iv = var23.next();
                    itx = (User) element$iv$iv;
                    $i$f$filterx = false;
                    if (Intrinsics.areEqual(itx.getUid(), PrefDataKt.getUid()) ^ true) {
                        destination$iv$ivx.add(element$iv$iv);
                    }
                }

                List var20 = (List) destination$iv$ivx;
                var19.element = (ArrayList) var20;
                $this$filter$iv = (Iterable) ((ArrayList) arrayList.element);
                var19 = arrayList;
                destination$iv$ivx = (Collection) (new ArrayList());
                var23 = $this$filter$iv.iterator();

                while (var23.hasNext()) {
                    element$iv$iv = var23.next();
                    itx = (User) element$iv$iv;
                    $i$f$filterx = false;
                    String var26 = itx.getGender();
                    User var28 = HomeFragment.this.getUserData();
                    if (Intrinsics.areEqual(var26, var28 != null ? var28.getGender() : null) ^ true) {
                        destination$iv$ivx.add(element$iv$iv);
                    }
                }

                var20 = (List) destination$iv$ivx;
                var19.element = (ArrayList) var20;
                $this$filter$iv = (Iterable) ((ArrayList) arrayList.element);
                var19 = arrayList;
                destination$iv$ivx = (Collection) (new ArrayList());
                var23 = $this$filter$iv.iterator();

                while (var23.hasNext()) {
                    element$iv$iv = var23.next();
                    itx = (User) element$iv$iv;
                    $i$f$filterx = false;
                    if (Intrinsics.areEqual(itx.getAccountStatus(), Constants.INSTANCE.getHIDDEN()) ^ true) {
                        destination$iv$ivx.add(element$iv$iv);
                    }
                }

                var20 = (List) destination$iv$ivx;
                var19.element = (ArrayList) var20;
                HomeFragment.this.setArrayList((ArrayList) arrayList.element);
                if (((ArrayList) arrayList.element).size() == 0) {
                    CommonsKt.showToast("No users found having your interest");
                } else {
                    AboutAdapter var27 = HomeFragment.this.getAboutAdapter();
                    if (var27 != null) {
                        var27.setNewList((ArrayList) arrayList.element);
                    }
                }

            }
        })).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
            public final void onFailure(@NotNull Exception it) {
                String var10000 = it.getLocalizedMessage();
                CommonsKt.showToast(var10000);
            }
        }));
    }

    private final User getUserData() {

        return new User();
    }

    @NotNull
    public final ArrayList getArrayList() {
        ArrayList var10000 = this.arrayList;
        return var10000;
    }

    public final void setArrayList(@NotNull ArrayList var1) {
        this.arrayList = var1;
    }

    @NotNull
    public final CardStackLayoutManager getManager() {
        CardStackLayoutManager var10000 = this.manager;

        return var10000;
    }

    public final void setManager(@NotNull CardStackLayoutManager var1) {
        this.manager = var1;
    }

    @NotNull
    public final UserPresenter getUserPresenter() {
        return new UserPresenter(this);
    }

    @Nullable
    public final AboutAdapter getAboutAdapter() {
        return this.aboutAdapter;
    }

    public final void setAboutAdapter(@Nullable AboutAdapter var1) {
        this.aboutAdapter = var1;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int var1) {
        this.position = var1;
    }

    TextView likeTExt, unlikeText;
    CircleImageView sortImage;
    OnFragmentChange onFragmentChange;
    ImageView tabIcon;
    Preferences preferences;
View view;
    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      this.view=view;
      filterList=new ArrayList();
      arrayList=new ArrayList();
        stackView = view.findViewById(id.streamingCardStackView);
        likeTExt = (TextView) view.findViewById(id.like_text);
        unlikeText = view.findViewById(id.unlike_text);
        sortImage = view.findViewById(id.home_sort);
        tabIcon = view.findViewById(id.tab_icon);
        this.arrayList = new ArrayList();
        this.aboutAdapter = new AboutAdapter(this, (OnCardAction) this, getActivity());
        this.initializeCards();
        this.filterUserByPrefs();
        this.loginWithCometChat();
        try {
            this.getCurrentUserData();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        onFragmentChange = this.listenr;
        if (onFragmentChange != null) {
            onFragmentChange.selectedPos(1);
        }
        sortImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());

            }
        });
        tabIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sortImage.performClick();
            }
        });
    }

    private void loginWithCometChat() {
        CometChatAuth var10000 = this.getCometChatAuth();
        String var10001 = PrefDataKt.getUid();
        var10000.loginToCometChat(var10001, (OnCometLogin) this);
    }

    private final void getUsers() {
        this.getUserPresenter().getAllUsers();
    }

    private final void initializeCards() {
        this.manager = new CardStackLayoutManager(this.getContext(), (CardStackListener) this);
        CardStackLayoutManager var10000 = this.manager;

        var10000.setStackFrom(StackFrom.Bottom);
        var10000 = this.manager;

        var10000.setVisibleCount(3);
        var10000 = this.manager;

        var10000.setTranslationInterval(8.0F);
        var10000 = this.manager;

        var10000.setScaleInterval(0.95F);
        var10000 = this.manager;

        var10000.setSwipeThreshold(0.07F);
        var10000 = this.manager;

        var10000.setMaxDegree(30.0F);
        var10000 = this.manager;

        var10000.setCanScrollHorizontal(true);
        var10000 = this.manager;

        var10000.setCanScrollVertical(true);
        var10000 = this.manager;

        var10000.setDirections(CollectionsKt.listOf(new Direction[]{Direction.Left, Direction.Right}));
        var10000 = this.manager;

        var10000.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        var10000 = this.manager;

        var10000.setOverlayInterpolator((Interpolator) (new LinearInterpolator()));

        stackView.setLayoutManager((LayoutManager) var10000);
        stackView.setAdapter((Adapter) this.aboutAdapter);
        ItemAnimator var1 = stackView.getItemAnimator();
        boolean var2 = false;
        boolean var3 = false;
        if (var1 instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) var1).setSupportsChangeAnimations(false);
        }

    }

    @Nullable
    public final Integer getDisAppearedposition() {
        return this.disAppearedposition;
    }

    public final void setDisAppearedposition(@Nullable Integer var1) {
        this.disAppearedposition = var1;
    }

    public void onCardDisappeared(@Nullable View view, int position) {
        this.disAppearedposition = position;
    }

    public void onCardDragging(@Nullable Direction direction, float ratio) {
    }

    public void onCardSwiped(@Nullable Direction direction) {
        int var10001 = this.count++;
        PrefKeys var10000 = PrefKeys.INSTANCE;
        Preferences.INSTANCE.setPref(var10000.getSWIPECOUNT(), this.count);
        User var3 = this.getUserData();
        if (var3 != null ? var3.isSubscribed() : null) {
            var10000 = PrefKeys.INSTANCE;
            Object var4 = preferences.getPrefs().getInt(var10000.getSWIPECOUNT(), 0);
            if (var4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }

            if ((Integer) var4 > 10) {
                CommonsKt.showToast("Subscribe for more swipes");
                CardStackLayoutManager var5 = this.manager;

                var5.setSwipeableMethod(SwipeableMethod.None);
            }
        }

        if (this.disAppearedposition != null) {
            Integer var10002;
            ArrayList var6;
            TextView var7;
            Object var8;
            UserPresenter var9;
            if (direction == Direction.Left) {
                var7 = (TextView) view.findViewById(id.unlike_text);
                var7.setVisibility(View.VISIBLE);
                var9 = this.getUserPresenter();
                var6 = this.arrayList;

                var10002 = this.disAppearedposition;
                if (var10002 == null) {
                    Intrinsics.throwNpe();
                }

                var8 = var6.get(var10002);
                var9.addToUnlikeList((User) var8);
            } else if (direction == Direction.Right) {
                likeTExt.setVisibility(View.VISIBLE);
                var9 = this.getUserPresenter();
                var6 = this.arrayList;

                var10002 = this.disAppearedposition;

                var8 = var6.get(var10002);
                var9.addToLikeList((User) var8);
            }
        }

    }

    public void onCardCanceled() {
    }

    public void onCardAppeared(@Nullable View view, int position) {
    }

    public void onCardRewound() {
    }

    private final void swipeRight(@NotNull CardStackView $this$swipeRight) {
        likeTExt.setVisibility(View.GONE);
        SwipeAnimationSetting setting = (new Builder()).setDirection(Direction.Right).setDuration(Duration.Normal.duration).setInterpolator((Interpolator) (new AccelerateInterpolator())).build();
        CardStackLayoutManager var3 = this.manager;

        var3.setSwipeAnimationSetting(setting);
        $this$swipeRight.swipe();
    }

    private final void swipeLeft(@NotNull CardStackView $this$swipeLeft) {
        unlikeText.setVisibility(View.GONE);
        SwipeAnimationSetting setting = (new Builder()).setDirection(Direction.Left).setDuration(Duration.Normal.duration).setInterpolator((Interpolator) (new AccelerateInterpolator())).build();
        CardStackLayoutManager var3 = this.manager;
        if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manager");
        }

        var3.setSwipeAnimationSetting(setting);
        $this$swipeLeft.swipe();
    }

    private final void swipeTop(@NotNull CardStackView $this$swipeTop) {
        TextView var10000 = (TextView) view.findViewById(id.superlike_text);
        var10000.setVisibility(View.GONE);
        SwipeAnimationSetting setting = (new Builder()).setDirection(Direction.Top).setDuration(Duration.Normal.duration).setInterpolator((Interpolator) (new AccelerateInterpolator())).build();
        CardStackLayoutManager var3 = this.manager;

        var3.setSwipeAnimationSetting(setting);
        $this$swipeTop.swipe();
    }

    @NotNull
    public final ArrayList getFilterList() {
        return this.filterList;
    }

    public final void prepareFilterlist() {
        this.filterList.clear();
        PrefKeys var10001 = PrefKeys.INSTANCE;
        String userPrefString=Preferences.INSTANCE.getPrefs().getString(var10001.getUSERPREFS(),"");
        if(!userPrefString.isEmpty()) {
            UserPreference userPreference = new Gson().fromJson(userPrefString, UserPreference.class);
/*
        if (userPreference == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.sachtech.datingapp.data.UserPreference");
        } else {*/
            this.userPrefs = (UserPreference) userPreference;
            Log.e("userPrefs", String.valueOf(this.userPrefs));

            if (!userPreference.getHalal().isEmpty()) {
                String[] halal = userPreference.getHalal().split(",");
                for (int i = 0; i < halal.length; i++) {
                    getFilterList().add(new Pair("beard", halal[i]));
                }
            }
            if (!userPreference.getBeard().isEmpty()) {
                String[] beard = userPreference.getBeard().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("beard", beard[i]));
                }
            }
            if (!userPreference.getBuild().isEmpty()) {
                ArrayList beard = userPreference.getBuild();
                for (int i = 0; i < beard.size(); i++) {
                    getFilterList().add(new Pair("build", beard.get(i)));
                }
            }
            if (!userPreference.getCompletedQuran().isEmpty()) {
                String[] beard = userPreference.getCompletedQuran().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("completedQuran", beard[i]));
                }
            }
            if (!userPreference.getHairColor().isEmpty()) {
                String[] beard = userPreference.getHairColor().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("hairColor", beard[i]));
                }
            }
            if (!userPreference.getComplexion().isEmpty()) {
                String[] beard = userPreference.getComplexion().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("complexion", beard[i]));
                }
            }
            if (!userPreference.getEducation().isEmpty()) {
                String[] beard = userPreference.getEducation().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("education", beard[i]));
                }
            }
            if (!userPreference.getLanguage().isEmpty()) {
                ArrayList beard = userPreference.getLanguage();
                for (int i = 0; i < beard.size(); i++) {
                    getFilterList().add(new Pair("languages", beard.get(i)));
                }
            }
            if (!userPreference.getLivingArrangement().isEmpty()) {
                ArrayList beard = userPreference.getLivingArrangement();
                for (int i = 0; i < beard.size(); i++) {
                    getFilterList().add(new Pair("currentLivingArrangment", beard.get(i)));
                }
            }
            if (!userPreference.getName().isEmpty()) {
                String[] beard = userPreference.getName().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("name", beard[i]));
                }
            }
            if (!userPreference.getSect().isEmpty()) {
                ArrayList beard = userPreference.getSect();
                for (int i = 0; i < beard.size(); i++) {
                    getFilterList().add(new Pair("sect", beard.get(i)));
                }
            }
            if (!userPreference.getEducation().isEmpty()) {
                String[] beard = userPreference.getEducation().split(",");
                for (int i = 0; i < beard.length; i++) {
                    getFilterList().add(new Pair("sect", beard[i]));
                }
            }
        }
    }

    private void filterUserByPrefs() {
        this.prepareFilterlist();
        ArrayList var10000 = this.arrayList;
        var10000.clear();
        if (this.filterList.size() == 0) {
            CollectionReference var2 = this.getFirebaseHelper().getFirebaseFireStore().collection(this.getFirebaseHelper().getCollection());
            CollectionReference query = var2;
            Intrinsics.checkExpressionValueIsNotNull(query.get().addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
                // $FF: synthetic method
                // $FF: bridge method
                public void onSuccess(Object var1) {
                    this.onSuccess((QuerySnapshot) var1);
                }

                public final void onSuccess(QuerySnapshot it) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (!it.isEmpty()) {
                        HomeFragment.this.getArrayList().addAll((Collection) it.toObjects(User.class));
                        HomeFragment.this.filterBlockedUSer(HomeFragment.this.getArrayList());
                    }

                }
            })).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    CommonsKt.showToast(e.getLocalizedMessage());
                }
            }), "query.get().addOnSuccess…zedMessage)\n            }");
        } else {
            this.filterpos = 0;
            Object var10001 = this.filterList.get(this.filterpos);
            Intrinsics.checkExpressionValueIsNotNull(var10001, "filterList[filterpos]");
            this.getFilterData((Pair) var10001);
        }

    }

    public final int getFilterpos() {
        return this.filterpos;
    }

    public final void setFilterpos(int var1) {
        this.filterpos = var1;
    }

    public final void getFilterData(@NotNull Pair filter) {
        Intrinsics.checkParameterIsNotNull(filter, "filter");
        Query var10000 = this.getFirebaseHelper().getFirebaseFireStore().collection(this.getFirebaseHelper().getCollection()).whereEqualTo((String) filter.getFirst(), filter.getSecond());
        Intrinsics.checkExpressionValueIsNotNull(var10000, "firebaseHelper.firebaseF…ter.first, filter.second)");
        Query query = var10000;
        query.get().addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((QuerySnapshot) var1);
            }

            public final void onSuccess(QuerySnapshot it) {
                Iterable $this$distinctBy$iv;
                boolean $i$f$distinctBy;
                HashSet set$iv;
                ArrayList list$iv;
                Iterator var6;
                Object e$iv;
                User itx;
                boolean var9;
                String key$iv;
                List var11;
                HomeFragment var10000;
                Object var10001;
                if (!it.isEmpty()) {
                    HomeFragment.this.getArrayList().addAll((Collection) it.toObjects(User.class));
                    var10000 = HomeFragment.this;
                    var10000.setFilterpos(var10000.getFilterpos() + 1);
                    if (HomeFragment.this.getFilterpos() >= HomeFragment.this.getFilterList().size()) {
                        $this$distinctBy$iv = (Iterable) HomeFragment.this.getArrayList();
                        $i$f$distinctBy = false;
                        set$iv = new HashSet();
                        list$iv = new ArrayList();
                        var6 = $this$distinctBy$iv.iterator();

                        while (var6.hasNext()) {
                            e$iv = var6.next();
                            itx = (User) e$iv;
                            var9 = false;
                            key$iv = itx.getUid();
                            if (set$iv.add(key$iv)) {
                                list$iv.add(e$iv);
                            }
                        }

                        var11 = (List) list$iv;
                        HomeFragment.this.filterBlockedUSer(HomeFragment.this.getArrayList());
                    } else {
                        var10000 = HomeFragment.this;
                        var10001 = HomeFragment.this.getFilterList().get(HomeFragment.this.getFilterpos());
                        Intrinsics.checkExpressionValueIsNotNull(var10001, "filterList[filterpos]");
                        var10000.getFilterData((Pair) var10001);
                    }
                } else {
                    var10000 = HomeFragment.this;
                    var10000.setFilterpos(var10000.getFilterpos() + 1);
                    if (HomeFragment.this.getFilterpos() >= HomeFragment.this.getFilterList().size()) {
                        $this$distinctBy$iv = (Iterable) HomeFragment.this.getArrayList();
                        $i$f$distinctBy = false;
                        set$iv = new HashSet();
                        list$iv = new ArrayList();
                        var6 = $this$distinctBy$iv.iterator();

                        while (var6.hasNext()) {
                            e$iv = var6.next();
                            itx = (User) e$iv;
                            var9 = false;
                            key$iv = itx.getUid();
                            if (set$iv.add(key$iv)) {
                                list$iv.add(e$iv);
                            }
                        }

                        var11 = (List) list$iv;
                        HomeFragment.this.filterBlockedUSer(HomeFragment.this.getArrayList());
                    } else {
                        var10000 = HomeFragment.this;
                        var10001 = HomeFragment.this.getFilterList().get(HomeFragment.this.getFilterpos());
                        Intrinsics.checkExpressionValueIsNotNull(var10001, "filterList[filterpos]");
                        var10000.getFilterData((Pair) var10001);
                    }
                }

            }
        })).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
            public final void onFailure(@NotNull Exception it) {
            }
        }));
    }

    @Override
    public void onRequestUpdateSuccess(boolean isAccepted) {

    }

    @Override
    public void friendAdded(boolean value) {
        if (value) {
            getMatchUserPresenter().addFriendList(user, new Function1<Boolean, Unit>() {
                @Override
                public Unit invoke(Boolean aBoolean) {
                    HomeFragment.this.getArrayList().remove(position);
                    AboutAdapter var3 = HomeFragment.this.getAboutAdapter();
                    if (var3 != null) {
                        var3.setNewList(HomeFragment.this.getArrayList());
                    }

                    Intent bundle = new Intent(getActivity(), ChatMessageActivity.class);
                    bundle.putExtra("uid", user.getUid());
                    getActivity().startActivity(bundle);
                    return Unit.INSTANCE;
                }});
           /* HomeFragment.this.getMatchUserPresenter().addFriendList(user, (Function1) (new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((Boolean) var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean it) {
                    HomeFragment.this.getArrayList().remove(position);
                    AboutAdapter var3 = HomeFragment.this.getAboutAdapter();
                    if (var3 != null) {
                        var3.setNewList(HomeFragment.this.getArrayList());
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("uid", user.getUid());
                }
            }));*/
        }
    }
}
