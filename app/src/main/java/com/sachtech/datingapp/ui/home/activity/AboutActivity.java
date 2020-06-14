package com.sachtech.datingapp.ui.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatUser;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.LikeUsers;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.model.LikedUserSnapshot;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.MatchUserPresenter;
import com.sachtech.datingapp.presenter.UserPresenter;
import com.sachtech.datingapp.ui.chat.activity.ReportDialog;
import com.sachtech.datingapp.ui.chat.activity.listener.OnGettingUserDetails;
import com.sachtech.datingapp.ui.home.adapter.DetailsAdapter;
import com.sachtech.datingapp.ui.home.adapter.MyPagerAdapter;
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.UserView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class AboutActivity extends AppCompatActivity implements UserView, OnGettingUserDetails, OnFriendAdded {
    @NotNull
    public MyPagerAdapter myPagerAdapter;
    @NotNull
    public ArrayList arraylist;
    @Nullable
    private User user;
    @NotNull
    public User currentUser;
    @NotNull
    private String userType;
    @NotNull
    public DetailsAdapter detailsAdapter;
    private String uid = "";
    @Nullable
    private ArrayList likedList;
    private HashMap _$_findViewCache;

    public void onUnlikeUser(@NotNull User user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        CommonsKt.showToast(user.getName() + " is added to unlike list");
    }

    public void onSuccess() {
    }

    public void onGettingAllUsers(@NotNull QuerySnapshot it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
    }

    public void onLikeUser(@NotNull User user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
    }

    public final void onBlockUser(@NotNull User user) {
    }

    public void onFailure(@NotNull String message) {
    }

    @NotNull
    public final MyPagerAdapter getMyPagerAdapter() {
        MyPagerAdapter var10000 = this.myPagerAdapter;
        return var10000;
    }

    public final void setMyPagerAdapter(@NotNull MyPagerAdapter var1) {
        this.myPagerAdapter = var1;
    }

    @NotNull
    public final CometChatUser getCometUser() {
        return new CometChatUser();
    }

    @NotNull
    public final ArrayList getArraylist() {
        ArrayList var10000 = this.arraylist;
        return var10000;
    }

    public final void setArraylist(@NotNull ArrayList var1) {
        this.arraylist = var1;
    }

    @NotNull
    public final UserPresenter getUserPresenter() {

        return new UserPresenter(this);
    }

    @NotNull
    public final MatchUserPresenter getMatchUserPresenter() {
        return new MatchUserPresenter(this);
    }

    @NotNull
    public final FirebaseHelper getFirebaseHelper() {

        return new FirebaseHelper();
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User var1) {
        this.user = var1;
    }

    @NotNull
    public final User getCurrentUser() {
        User var10000 = this.currentUser;
        return var10000;
    }

    public final void setCurrentUser(@NotNull User var1) {
        this.currentUser = var1;
    }

    @NotNull
    public final String getUserType() {
        return this.userType;
    }

    public final void setUserType(@NotNull String var1) {
        this.userType = var1;
    }

    @NotNull
    public final DetailsAdapter getDetailsAdapter() {
        DetailsAdapter var10000 = this.detailsAdapter;

        return var10000;
    }

    public final void setDetailsAdapter(@NotNull DetailsAdapter var1) {
        this.detailsAdapter = var1;
    }


    @Nullable
    public final ArrayList getLikedList() {
        return this.likedList;
    }

    public final void setLikedList(@Nullable ArrayList var1) {
        this.likedList = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_about);
        ((TabLayout) findViewById(id.tab_layout)).setupWithViewPager((ViewPager) findViewById(id.aboutViewpager));
        this.likedList = new ArrayList();
        String user = getIntent().getStringExtra("uid");
        uid = user;
        assert uid != null;
        if (uid != null) {
            getUserDetails();
        }
        String currentUserString = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(), "");
        currentUser = new Gson().fromJson(currentUserString, User.class);
       arraylist = new ArrayList();
        myPagerAdapter = new MyPagerAdapter(this, arraylist);
        ViewPager var10000 = (ViewPager) findViewById(id.aboutViewpager);
        var10000.setAdapter((PagerAdapter) myPagerAdapter);
       // setViewpagerAdapter(arraylist);
        ((ImageView) findViewById(id.about_cancel)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                AboutActivity.this.onBackPressed();
            }
        }));
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        ((ImageView) findViewById(id.like)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                if (AboutActivity.this.getUser() != null) {
                    AboutActivity var10000 = AboutActivity.this;
                    User user = AboutActivity.this.getUser();
                    var10000.getLikedUsers(user);
                }

            }
        }));
        ((ImageView) findViewById(id.dislike)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                if (AboutActivity.this.getUser() != null) {
                    AboutActivity var10000 = AboutActivity.this;
                    User user = AboutActivity.this.getUser();
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }

                    var10000.getUnlikeUsers(user);
                }

            }
        }));
        ((ImageView) findViewById(id.message)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                if (AboutActivity.this.getUser() != null) {
                    UserPresenter var10000 = AboutActivity.this.getUserPresenter();
                    User user = AboutActivity.this.getUser();
                    if (user == null) {
                        Intrinsics.throwNpe();
                    }

                    var10000.addToLikeList(user);
                    var10000 = AboutActivity.this.getUserPresenter();
                    user = AboutActivity.this.getUser();
                    var10000.addToChatFriendList(user, AboutActivity.this::friendAdded);
                   /* var10000.addToChatFriendList(user, (Function1) (new Function1() {
                        // $FF: synthetic method
                        // $FF: bridge method
                        public Object invoke(Object var1) {
                            this.invoke((Boolean) var1);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean it) {
                            MatchUserPresenter var10000 = AboutActivity.this.getMatchUserPresenter();
                            User user = AboutActivity.this.getUser();
                            if (user == null) {
                                Intrinsics.throwNpe();
                            }

                            var10000.addFriendList(user, (Function1) (new Function1() {
                                public Object invoke(Object var1) {
                                    this.invoke((Boolean) var1);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean it) {
                                    Bundle bundle = new Bundle();
                                    StringBuilder user = new StringBuilder();
                                    User var10002 = AboutActivity.this.getUser();
                                    user = user.append(var10002 != null ? var10002.getUid() : null).append(',');
                                    var10002 = AboutActivity.this.getUser();
                                    Log.e("user", user.append(var10002 != null ? var10002.getName() : null).toString());
                                    var10002 = AboutActivity.this.getUser();
                                    bundle.putString("uid", var10002 != null ? var10002.getUid() : null);
                                }
                            }));
                        }
                    }));*/
                }

            }
        }));
        ((ImageView) findViewById(id.block_option)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PopupMenu popupMenu = new PopupMenu((Context) AboutActivity.this, (ImageView) findViewById(id.block_option));
                popupMenu.inflate(R.menu.options);
                popupMenu.setOnMenuItemClickListener((OnMenuItemClickListener) (new OnMenuItemClickListener() {
                    public final boolean onMenuItemClick(MenuItem it) {
                        if (it.getItemId() == 1000395) {
                            AboutActivity.this.blockUser();
                            return true;
                        } else if (it.getItemId() == 1000536) {
                            AboutActivity.this.reportUser();
                            return true;
                        } else {
                            return false;
                        }
                    }
                }));
                popupMenu.show();
            }
        }));
    }

    private final void getUnlikeUsers(final User user) {
        FirebaseHelper var10000 = this.getFirebaseHelper();
        String uid = PrefDataKt.getUid();

        var10000.getCurrentLikedUser(uid, this.uid, (Function1) (new Function1() {
            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke(Object var1) {
                this.invoke((LikedUserSnapshot) var1);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull LikedUserSnapshot it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                boolean var2 = it.getStatus();
                if (var2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", user.getUid());
                    DocumentSnapshot var10002 = it.getDocumentSnapshot();
                    if (var10002 == null) {
                        Intrinsics.throwNpe();
                    }

                    bundle.putString("documentId", var10002.getId());
                    VerifyRemoveUserDialog dialog = new VerifyRemoveUserDialog();
                    dialog.setArguments(bundle);
                    dialog.show(AboutActivity.this.getSupportFragmentManager(), "verifydialog");
                } else if (!var2) {
                    AboutActivity.this.getUserPresenter().addToLikeList(user);
                    AboutActivity.this.getUserPresenter().removeFromUnlikeList(user);
                }

            }
        }));
        Unit doc = Unit.INSTANCE;
    }

    private final void getLikedUsers(final User user) {
        FirebaseHelper var10000 = this.getFirebaseHelper();
        String uid = PrefDataKt.getUid();
        if (user == null) {
            Intrinsics.throwNpe();
        }

        var10000.getLikedUserList(uid).get().addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            public void onSuccess(Object var1) {
                this.onSuccess((QuerySnapshot) var1);
            }

            public final void onSuccess(QuerySnapshot it) {
                List var10000 = it.toObjects(LikeUsers.class);
                List likeUsers = var10000;
                Collection var3 = (Collection) likeUsers;
                boolean $i$f$distinctBy = false;
                boolean var5 = false;
                if (var3 != null && !var3.isEmpty()) {
                    Iterable $this$distinctBy$iv = (Iterable) likeUsers;
                    $i$f$distinctBy = false;
                    HashSet set$iv = new HashSet();
                    ArrayList list$iv = new ArrayList();
                    Iterator var7 = $this$distinctBy$iv.iterator();

                    while (var7.hasNext()) {
                        Object e$iv = var7.next();
                        LikeUsers itx = (LikeUsers) e$iv;
                        Object key$iv = itx.getLiked_to_user_id();
                        if (set$iv.add(key$iv)) {
                            list$iv.add(e$iv);
                        }
                    }

                    likeUsers = (List) list$iv;
                    int i = 0;

                    ArrayList var18;
                    for (int var14 = likeUsers.size(); i < var14; ++i) {
                        var18 = AboutActivity.this.getLikedList();
                        if (var18 != null) {
                            LikeUsers user = (LikeUsers) likeUsers.get(i);
                            String var20 = user != null ? user.getLiked_to_user_id() : null;
                            if (var20 == null) {
                                Intrinsics.throwNpe();
                            }

                            var18.add(var20);
                        }
                    }

                    var18 = AboutActivity.this.getLikedList();
                    Boolean var19 = var18 != null ? var18.contains(uid) : null;
                    if (var19 == null) {
                        Intrinsics.throwNpe();
                    }

                    if (var19) {
                        Bundle bundle = new Bundle();
                        bundle.putString("uid", user.getUid());
                        ConfirmDialog dialog = new ConfirmDialog();
                        dialog.setArguments(bundle);
                        dialog.show(AboutActivity.this.getSupportFragmentManager(), "verifydialog");
                    } else {
                        AboutActivity.this.getUserPresenter().addToLikeList(user);
                        AboutActivity.this.getUserPresenter().removeFromUnlikeList(user);
                    }
                }

            }
        }));
    }

    private void getUserDetails() {
        this.getFirebaseHelper().getUserDetailsFromDatabase(this.uid).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                CommonsKt.showToast(e.getLocalizedMessage());
            }
        }).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((DocumentSnapshot) var1);
            }

            public final void onSuccess(DocumentSnapshot it) {
                AboutActivity var10000 = AboutActivity.this;
                Object user = it.toObject(User.class);
                if (user == null) {
                    Intrinsics.throwNpe();
                }

                var10000.setUser((User) user);
                AboutActivity.this.setUserData();
            }
        }));
    }

    private  void setUserData() {
        if (this.user != null) {
            arraylist.add(user.getProfilePic());
          if(user.getImages().size()>0)
          {
              for(int i=0;i<user.getImages().size();i++)
              {
                  arraylist.add(user.getImages().get(i));
              }
          }
        myPagerAdapter.notifyDataSetChanged();

            TextView var7 = (TextView) findViewById(id.about_name);
            StringBuilder var9 = new StringBuilder();
            User var10002 = this.user;
            var9 = var9.append(var10002 != null ? var10002.getName() : null).append(',');
            var10002 = this.user;
            var7.setText((CharSequence) var9.append(var10002 != null ? var10002.getAge() : null).toString());
            var7 = (TextView) findViewById(id.about_desc);
            var7.setText((CharSequence) (user != null ? user.getAboutMe() : null));
            var7 = (TextView) findViewById(id.about_family);
            var7.setText((CharSequence) (user != null ? user.getAboutFamily() : null));
            var7 = (TextView) findViewById(id.about_looking_for);
            var7.setText((CharSequence) (user != null ? user.getLookingFor() : null));
            var7 = (TextView) findViewById(id.about_beard);
            var7.setText((CharSequence) (user != null ? user.getBeard() : null));
            var7 = (TextView) findViewById(id.about_build);
            var7.setText((CharSequence) (user != null ? user.getBuild() : null));
            var7 = (TextView) findViewById(id.about_cast);
            var7.setText((CharSequence) (user != null ? user.getSect() : null));
            var7 = (TextView) findViewById(id.about_dob);
            var7.setText((CharSequence) (user != null ? CommonsKt.getDateInformat(user.getDob()) : null));
            var7 = (TextView) findViewById(id.about_eyes_color);
            var7.setText((CharSequence) (user != null ? user.getEyeColour() : null));
            var7 = (TextView) findViewById(id.about_halal);
            var7.setText((CharSequence) (user != null ? user.getHalal() : null));
            var7 = (TextView) findViewById(id.about_lifeaftermarriage);
            var7.setText((CharSequence) (user != null ? user.getLivingArrangmentAfterMarriage() : null));
            var7 = (TextView) findViewById(id.about_living_arrangement);
            var7.setText((CharSequence) (user != null ? user.getCurrentLivingArrangment() : null));
            var7 = (TextView) findViewById(id.about_nationality);
            var7.setText((CharSequence) (user != null ? user.getNationality() : null));
            var7 = (TextView) findViewById(id.about_marriage_plan);
            var7.setText((CharSequence) (user != null ? user.getMarriagePlans() : null));
            var7 = (TextView) findViewById(id.about_origin);
            var7.setText((CharSequence) (user != null ? user.getOrigin() : null));
            var7 = (TextView) findViewById(id.about_quran);
            var7.setText((CharSequence) (user != null ? user.getCompletedQuran() : null));
            var7 = (TextView) findViewById(id.about_revert);
            var7.setText((CharSequence) (user != null ? user.getRevert() : null));
            if (CommonsKt.isNotNull(user != null ? user.getLanguages() : null)) {
                List var10 = user != null ? user.getLanguages() : null;
                var7 = (TextView) findViewById(id.about_languages);
                var7.setText(joinToString((ArrayList) user.getLanguages()));
            }
            if (Intrinsics.areEqual(user != null ? user.getMaritalStatus() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_marital_status);
                var7.setText((CharSequence) user.getMaritalStatus());
            }

            if (Intrinsics.areEqual(user != null ? user.getHeight() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_height);
                var7.setText((CharSequence) user.getHeight());
            }

            if (Intrinsics.areEqual(user != null ? user.getHairColour() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_hair_color);
                var7.setText((CharSequence) user.getHairColour());
            }

            if (Intrinsics.areEqual(user != null ? user.getColorComplexion() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_complexion);
                var7.setText((CharSequence) user.getColorComplexion());
            }

            if (Intrinsics.areEqual(user != null ? user.getEducation() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_education);
                var7.setText((CharSequence) user.getEducation());
            }

            if (Intrinsics.areEqual(user != null ? user.getProfession() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_profession);
                var7.setText((CharSequence) user.getProfession());
            }

            if (Intrinsics.areEqual(user != null ? user.getWear() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_wear);
                var7.setText((CharSequence) user.getWear());
            }

            if (Intrinsics.areEqual(user != null ? user.getPrays() : null, "") ^ true) {
                var7 = (TextView) findViewById(id.about_prays);
                var7.setText((CharSequence) user.getPrays());
            }

            MyPagerAdapter var12 = this.myPagerAdapter;
            if (var12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPagerAdapter");
            }

            var12.notifyDataSetChanged();
            UserPresenter var13 = this.getUserPresenter();
            user = this.user;
            if (user == null) {
                Intrinsics.throwNpe();
            }

            var13.addToVisitList(user);
        }

    }

    public String joinToString(ArrayList arrayList) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < arrayList.size(); i++) {
            builder.append(arrayList).append(",");
        }
        String strList = builder.toString();
        if (strList.length() > 0)
            strList = strList.substring(0, strList.length() - 1);
        return strList;
    }

    private final void reportUser() {
        CometChatUser var10000 = this.getCometUser();
        User user = this.user;
        String var1 = user != null ? user.getUid() : null;
        if (var1 == null) {
            Intrinsics.throwNpe();
        }

        var10000.getUserDetails(var1, this::userDetails);
    }

    private final void blockUser() {

        User currentUser = this.currentUser;

        String var3 = currentUser.getUid();

        String var4 = currentUser.getName();
        User var10004 = this.user;

        User var10005 = this.user;
        String var2 = var10005 != null ? var10005.getName() : null;
        Blockeduser var10000 = new Blockeduser(var3, var4, user.getUid(), var2);
        Blockeduser blockUserData = var10000;
        this.getFirebaseHelper().blockedUsers(blockUserData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private final void setViewpagerAdapter(ArrayList arraylist) {

    }


    public void onRequestUpdateSuccess(boolean isAccepted) {
        DefaultImpls.onRequestUpdateSuccess(this, isAccepted);
    }

    public void onMatchUser(@NotNull String uid) {
        Intrinsics.checkParameterIsNotNull(uid, "uid");
        DefaultImpls.onMatchUser(this, uid);
    }

    @Override
    public void userDetails(com.cometchat.pro.models.User user) {
        ReportDialog reportDialog = new ReportDialog();
        if (user == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.cometchat.pro.models.User");
        } else {
            reportDialog.getChatUserData(user);
            FragmentManager fm = AboutActivity.this.getSupportFragmentManager();

            reportDialog.show(fm, "report");
        }
    }

    @Override
    public void friendAdded(boolean value) {
        if (value) {
            MatchUserPresenter var10000 = AboutActivity.this.getMatchUserPresenter();
            User user = AboutActivity.this.getUser();
            if (user == null) {
                Intrinsics.throwNpe();
            }
            var10000.addFriendList(user, new Function1<Boolean, Unit>() {
                @Override
                public Unit invoke(Boolean aBoolean) {
                    Bundle bundle = new Bundle();
                    StringBuilder user = new StringBuilder();
                    User var10002 = AboutActivity.this.getUser();
                    user = user.append(var10002 != null ? var10002.getUid() : null).append(',');
                    var10002 = AboutActivity.this.getUser();
                    Log.e("user", user.append(var10002 != null ? var10002.getName() : null).toString());
                    var10002 = AboutActivity.this.getUser();
                    bundle.putString("uid", var10002 != null ? var10002.getUid() : null);
                    return Unit.INSTANCE;
                }
            });
        }
    }
}