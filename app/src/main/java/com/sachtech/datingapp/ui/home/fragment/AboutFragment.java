package com.sachtech.datingapp.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatUser;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.MatchUserPresenter;
import com.sachtech.datingapp.presenter.UserPresenter;
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity;
import com.sachtech.datingapp.ui.chat.activity.ReportDialog;
import com.sachtech.datingapp.ui.chat.activity.listener.OnGettingUserDetails;
import com.sachtech.datingapp.ui.home.adapter.MyPagerAdapter;
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded;
import com.sachtech.datingapp.utils.Commons;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.UserView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class AboutFragment extends BottomSheetDialogFragment implements UserView, OnGettingUserDetails, OnFriendAdded {
   @NotNull
   public MyPagerAdapter myPagerAdapter;

   @Nullable
   private User user;
   @NotNull
   public User currentUser;
   @NotNull
   private String uid;

   public void onUnlikeUser(@NotNull User user) {
      new Commons(getContext()).hideProgress();
      CommonsKt.showToast(user.getName() + " is added to unlike list");
   }

   public void onSuccess() {
      new Commons(getContext()).hideProgress();
   }

   public void onGettingAllUsers(@NotNull QuerySnapshot it) {
   }

   public void onLikeUser(@NotNull User user) {
      new Commons(getContext()).hideProgress();
      CommonsKt.showToast(user.getName() + " is added to Like list");

   }

   public final void onBlockUser(@NotNull User user) {
      CommonsKt.showToast(user.getName() + " is added to Like list");

   }

   public void onFailure(@NotNull String message) {
      new Commons(getContext()).hideProgress();
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



   public final void setArraylist(@NotNull ArrayList var1) {
      this.arrayList = var1;
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
   public final String getUid() {
      return this.uid;
   }

   public final void setUid(@NotNull String var1) {
      this.uid = var1;
   }

   @NotNull
   public final MatchUserPresenter getMatchUserPresenter() {
      return new MatchUserPresenter(this);
   }

   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.activity_about, container, false);
   }
TabLayout tabLayout;
   ViewPager viewPager;
   ArrayList<String> arrayList;
   View view;
   UserPresenter userPresenter;
   ImageView cancelImage,like,unlike,message,block_option;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      this.view=view;
      arrayList=new ArrayList();
      userPresenter=new UserPresenter(this);
      tabLayout = view.findViewById(id.tab_layout);
      viewPager = view.findViewById(id.aboutViewpager);
      cancelImage = view.findViewById(id.about_cancel);
      block_option = view.findViewById(id.block_option);
      tabLayout.setupWithViewPager(viewPager);
      like = view.findViewById(id.like);
      unlike = view.findViewById(id.dislike);
      message = view.findViewById(id.message);
      myPagerAdapter=new MyPagerAdapter(getActivity(),arrayList);
      viewPager.setAdapter(myPagerAdapter);
    //  this.setViewpagerAdapter(arraylist);
      Bundle bundle = this.getArguments();
      String var4 = bundle != null ? bundle.getString("uid") : null;
      uid = var4;
      this.getUserDetails();
      String userString= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      if(!userString.isEmpty()) {
         currentUser =new Gson().fromJson(userString,User.class);
      }

      cancelImage.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });
      FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
      layoutManager.setFlexDirection(FlexDirection.ROW);
      layoutManager.setJustifyContent(JustifyContent.CENTER);
      like.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            if (user != null) {
               new Commons(getContext()).showProgress();

               userPresenter.addToLikeList(user);
            }
         }
      });
      unlike.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            if (user != null) {
               new Commons(getContext()).showProgress();
               userPresenter.addToUnlikeList(user);
            }
         }
      });
      message.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       if (user != null) {
                                          new Commons(getContext()).showProgress();

                                          userPresenter.addToLikeList(user);
                                          userPresenter.addToChatFriendList(user, AboutFragment.this::friendAdded);
             /*  getUserPresenter().addToChatFriendList(user, new Function1<Boolean, Unit>() {
                  @Override
                  public Unit invoke(Boolean aBoolean) {
                     getMatchUserPresenter().addFriendList(user, new Function1<Boolean, Unit>() {
                        @Override
                        public Unit invoke(Boolean aBoolean) {
                           Bundle bundle = new Bundle();
                           bundle.putString("uid", user.getUid());
                           getActivity().startActivity(new Intent(getActivity(), ChatMessageActivity.class), bundle);
                           return Unit.INSTANCE;
                        }
                     });
                     return Unit.INSTANCE;
                  }
*/
         /*      });
            }
         }
      });*/
                                       }
                                    }
                                 });
               block_option.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(getContext(),block_option);
            popupMenu.inflate(R.menu.options);
            popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                  if (item.getItemId() == id.item_blocked) {
                     if (user != null) {
                        blockUser();
                     }

                     return true;

                  } else if (item.getItemId() == id.item_report) {
                     if (user != null) {
                        reportUser();
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            });
            popupMenu.show();
         }
      });
   }
   private  void getUserDetails() {
      this.getFirebaseHelper().getUserDetailsFromDatabase(this.uid).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
         public final void onFailure(@NotNull Exception it) {
            CommonsKt.showToast(String.valueOf(it.getMessage()));
         }
      })).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((DocumentSnapshot)var1);
         }

         public final void onSuccess(DocumentSnapshot it) {
            AboutFragment var10000 = AboutFragment.this;
           user = it.toObject(User.class);
           setUserData();
         }
      }));
   }

   private  void setUserData() {
      if (this.user != null) {
         arrayList.add(user.getProfilePic());
         if(user.getImages().size()>0)
         {
            for(int i=0;i<user.getImages().size();i++)
            {
               arrayList.add(user.getImages().get(i));
            }
         }
         myPagerAdapter.notifyDataSetChanged();

         TextView var7 = (TextView) view.findViewById(id.about_name);
         StringBuilder var9 = new StringBuilder();
         User var10002 = this.user;
         var9 = var9.append(var10002 != null ? var10002.getName() : null).append(',');
         var10002 = this.user;
         var7.setText((CharSequence) var9.append(var10002 != null ? var10002.getAge() : null).toString());
         var7 = (TextView) view.findViewById(id.about_desc);
         var7.setText((CharSequence) (user != null ? user.getAboutMe() : null));
         var7 = (TextView) view.findViewById(id.about_family);
         var7.setText((CharSequence) (user != null ? user.getAboutFamily() : null));
         var7 = (TextView) view.findViewById(id.about_looking_for);
         var7.setText((CharSequence) (user != null ? user.getLookingFor() : null));
         var7 = (TextView) view.findViewById(id.about_beard);
         var7.setText((CharSequence) (user != null ? user.getBeard() : null));
         var7 = (TextView) view.findViewById(id.about_build);
         var7.setText((CharSequence) (user != null ? user.getBuild() : null));
         var7 = (TextView) view.findViewById(id.about_cast);
         var7.setText((CharSequence) (user != null ? user.getSect() : null));
         var7 = (TextView) view.findViewById(id.about_dob);
         var7.setText((CharSequence) (user != null ? CommonsKt.getDateInformat(user.getDob()) : null));
         var7 = (TextView) view.findViewById(id.about_eyes_color);
         var7.setText((CharSequence) (user != null ? user.getEyeColour() : null));
         var7 = (TextView) view.findViewById(id.about_halal);
         var7.setText((CharSequence) (user != null ? user.getHalal() : null));
         var7 = (TextView) view.findViewById(id.about_lifeaftermarriage);
         var7.setText((CharSequence) (user != null ? user.getLivingArrangmentAfterMarriage() : null));
         var7 = (TextView) view.findViewById(id.about_living_arrangement);
         var7.setText((CharSequence) (user != null ? user.getCurrentLivingArrangment() : null));
         var7 = (TextView) view.findViewById(id.about_nationality);
         var7.setText((CharSequence) (user != null ? user.getNationality() : null));
         var7 = (TextView) view.findViewById(id.about_marriage_plan);
         var7.setText((CharSequence) (user != null ? user.getMarriagePlans() : null));
         var7 = (TextView) view.findViewById(id.about_origin);
         var7.setText((CharSequence) (user != null ? user.getOrigin() : null));
         var7 = (TextView) view.findViewById(id.about_quran);
         var7.setText((CharSequence) (user != null ? user.getCompletedQuran() : null));
         var7 = (TextView) view.findViewById(id.about_revert);
         var7.setText((CharSequence) (user != null ? user.getRevert() : null));
         if (CommonsKt.isNotNull(user != null ? user.getLanguages() : null)) {
            List var10 = user != null ? user.getLanguages() : null;
            var7 = (TextView) view.findViewById(id.about_languages);
            var7.setText(joinToString((ArrayList) user.getLanguages()));
         }
         if (Intrinsics.areEqual(user != null ? user.getMaritalStatus() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_marital_status);
            var7.setText((CharSequence) user.getMaritalStatus());
         }

         if (Intrinsics.areEqual(user != null ? user.getHeight() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_height);
            var7.setText((CharSequence) user.getHeight());
         }

         if (Intrinsics.areEqual(user != null ? user.getHairColour() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_hair_color);
            var7.setText((CharSequence) user.getHairColour());
         }

         if (Intrinsics.areEqual(user != null ? user.getColorComplexion() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_complexion);
            var7.setText((CharSequence) user.getColorComplexion());
         }

         if (Intrinsics.areEqual(user != null ? user.getEducation() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_education);
            var7.setText((CharSequence) user.getEducation());
         }

         if (Intrinsics.areEqual(user != null ? user.getProfession() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_profession);
            var7.setText((CharSequence) user.getProfession());
         }

         if (Intrinsics.areEqual(user != null ? user.getWear() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_wear);
            var7.setText((CharSequence) user.getWear());
         }

         if (Intrinsics.areEqual(user != null ? user.getPrays() : null, "") ^ true) {
            var7 = (TextView) view.findViewById(id.about_prays);
            var7.setText((CharSequence) user.getPrays());
         }

         MyPagerAdapter var12 = this.myPagerAdapter;
         if (var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myPagerAdapter");
         }

         var12.notifyDataSetChanged();
         user = this.user;
         if (user == null) {
            Intrinsics.throwNpe();
         }

         userPresenter.addToVisitList(user);
      }

   }

   private final void reportUser() {
      CometChatUser var10000 = this.getCometUser();
      User var10001 = this.user;
      String var1 = var10001 != null ? var10001.getUid() : null;
      if (var1 == null) {
         Intrinsics.throwNpe();
      }
      var10000.getUserDetails(var1,this);
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
   private  void blockUser() {
      User var10002 = this.currentUser;

      String var3 = var10002.getUid();
      User var10003 = this.currentUser;

      String var4 = var10003.getName();
      User var10004 = this.user;
      String var5 = var10004 != null ? var10004.getUid() : null;

      User var10005 = this.user;
      String var2 = var10005 != null ? var10005.getName() : null;
      Blockeduser blockeduser = new Blockeduser(var3,var4,var5,var2);

      getFirebaseHelper().blockedUsers(blockeduser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
         @Override
         public void onSuccess(DocumentReference documentReference) {
            Toast.makeText(getContext(), "Blocked succesfully", Toast.LENGTH_SHORT).show();
         }
      }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
         }
      });
   }

   private final void setViewpagerAdapter(ArrayList arraylist) {
     myPagerAdapter = new MyPagerAdapter(getActivity(),arraylist);
viewPager.setAdapter(myPagerAdapter);
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
      reportDialog.getChatUserData(user);
      FragmentActivity var10001 = AboutFragment.this.getActivity();
      FragmentManager var3 = var10001 != null ? var10001.getSupportFragmentManager() : null;
      if (var3 == null) {
         Intrinsics.throwNpe();
      }

      reportDialog.show(var3, "report");
   }

   @Override
   public void friendAdded(boolean value) {
      if(value)
      {
         getMatchUserPresenter().addFriendList(user, new Function1<Boolean, Unit>() {
            @Override
            public Unit invoke(Boolean aBoolean) {
               new Commons(getContext()).hideProgress();

               Intent bundle = new Intent(getActivity(),ChatMessageActivity.class);
               bundle.putExtra("uid", user.getUid());
               getActivity().startActivity(bundle);
               return Unit.INSTANCE;
            }
      });
      }
   }
}
