package com.sachtech.datingapp.ui.explore.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.LikeUsers;
import com.sachtech.datingapp.data.MatchStatus;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.MatchUserPresenter;
import com.sachtech.datingapp.presenter.UserPresenter;
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity;
import com.sachtech.datingapp.ui.explore.adapter.LikesAdapter;
import com.sachtech.datingapp.ui.explore.adapter.listener.OnProfilesMatch;
import com.sachtech.datingapp.ui.explore.adapter.listener.onMatched;
import com.sachtech.datingapp.ui.home.adapter.listener.OnFriendAdded;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.view.UserView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;


public class LikesFragment extends Fragment implements OnProfilesMatch, UserView, onMatched, OnFriendAdded {

   private OnFragmentChange onFragmentChange;
   @NotNull
   public LikesAdapter likedByMeAdapter;
   @Nullable
   private ArrayList<User> likeUserList;
   @NotNull
   public ArrayList idList;
   @NotNull
   public ArrayList statusList;


   public void onMatched() {
      ArrayList var10000 = this.likeUserList;
      if (var10000 != null) {
         var10000.clear();
      }

      var10000 = this.idList;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("idList");
      }

      var10000.clear();
      var10000 = this.statusList;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("statusList");
      }

      var10000.clear();
      this.getUsers();
   }

   public void onGettingAllUsers(@NotNull QuerySnapshot it) {
      Intrinsics.checkParameterIsNotNull(it, "it");
   }

   public void onLikeUser(@NotNull User user) {
      Intrinsics.checkParameterIsNotNull(user, "user");
   }

   public void onUnlikeUser(@NotNull User user) {
      Intrinsics.checkParameterIsNotNull(user, "user");
   }

   public void onSuccess() {
   }

   public void onFailure(@NotNull String message) {
      Intrinsics.checkParameterIsNotNull(message, "message");
   }

   public void onMatchUser(@NotNull String uid) {
      Intrinsics.checkParameterIsNotNull(uid, "uid");
   }

   public void matchedProfile(@NotNull User username) {
      Intrinsics.checkParameterIsNotNull(username, "username");
      this.showMatchDialog(username);
   }

   public void OnMessageClick(@NotNull final User user) {
      Intrinsics.checkParameterIsNotNull(user, "user");
      this.user=user;
      getUserPresenter().addToChatFriendList(user,this::friendAdded);
     /* this.getUserPresenter().addToChatFriendList(user, (Function1)(new Function1() {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke(Object var1) {
            this.invoke((Boolean)var1);
            return Unit.INSTANCE;
         }

         public final void invoke(boolean it) {
            LikesFragment.this.getMatchPresenter().addFriendList(user, (Function1)(new Function1() {
               // $FF: synthetic method
               // $FF: bridge method
               public Object invoke(Object var1) {
                  this.invoke((Boolean)var1);
                  return Unit.INSTANCE;
               }

               public final void invoke(boolean it) {
                  Bundle bundle = new Bundle();
                  bundle.putString("uid", user.getUid());
                  getActivity().startActivity(new Intent(getContext(),ChatMessageActivity.class),bundle);
               }
            }));
         }
      }));*/
   }

   @Nullable
   public final OnFragmentChange getOnFragmentChange() {
      return this.onFragmentChange;
   }

   public final void setOnFragmentChange(@Nullable OnFragmentChange var1) {
      this.onFragmentChange = var1;
   }

   public void onAttach(@NotNull Context context) {
      super.onAttach(context);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(2);
      }

   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_likes,container,false);
   }

   private  void showMatchDialog(User user) {
      MatchDialog dialog = new MatchDialog();
      dialog.setSelectedUser(user);
      dialog.setOnMatched(this::onMatched);
      dialog.show(getActivity().getSupportFragmentManager(),"match");
   }

   private final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }



   public final void setLikedByMeAdapter(@NotNull LikesAdapter var1) {
      this.likedByMeAdapter = var1;
   }

   @Nullable
   public final ArrayList getLikeUserList() {
      return this.likeUserList;
   }

   public final void setLikeUserList(@Nullable ArrayList var1) {
      this.likeUserList = var1;
   }

   @NotNull
   public final ArrayList getIdList() {
      ArrayList var10000 = this.idList;
      return var10000;
   }

   public final void setIdList(@NotNull ArrayList var1) {
      this.idList = var1;
   }

   @NotNull
   public final ArrayList getStatusList() {
      ArrayList var10000 = this.statusList;
      return var10000;
   }

   public final void setStatusList(@NotNull ArrayList var1) {
      this.statusList = var1;
   }

   User user;
   @NotNull
   public final UserPresenter getUserPresenter() {
      return new UserPresenter(this);
   }

   @NotNull
   public final MatchUserPresenter getMatchPresenter() {

      return new MatchUserPresenter(this);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      OnFragmentChange onFragmentChange = this.onFragmentChange;
      if (onFragmentChange != null) {
         onFragmentChange.selectedPos(2);
      }

      this.getUsers();
      likeUserList = new ArrayList();
      this.idList = new ArrayList();
      this.statusList = new ArrayList();
     likedByMeAdapter = new LikesAdapter(likeUserList,getActivity(),idList,statusList,this);
      ArrayList var10003 = this.likeUserList;
      if (var10003 == null) {
         Intrinsics.throwNpe();
      }

      ArrayList var10005 = this.idList;
      ArrayList var10006 = this.statusList;
      RecyclerView var3 = (RecyclerView)view.findViewById(id.liked_to_me_rec);
      var3.setAdapter(likedByMeAdapter);
   }

   private  void getUsers() {
      FirebaseHelper var10000 = this.getFirebaseHelper();
      String var10001 = PrefDataKt.getUid();
      var10000.getLikedToMeUserList(var10001).get().addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         public void onSuccess(Object var1) {
            this.onSuccess((QuerySnapshot)var1);
         }

         public final void onSuccess(QuerySnapshot it) {
            List var10000 = it.toObjects(LikeUsers.class);
            List likeUsers = var10000;
            Collection var3 = (Collection)likeUsers;
            boolean $i$f$filter = false;
            boolean var5 = false;
            if (var3 != null && !var3.isEmpty()) {
               Iterable $this$filter$iv = (Iterable)likeUsers;
               $i$f$filter = false;
               HashSet set$iv = new HashSet();
               ArrayList list$iv = new ArrayList();
               Iterator var7 = $this$filter$iv.iterator();

               while(var7.hasNext()) {
                  Object e$iv = var7.next();
                  LikeUsers itx = (LikeUsers)e$iv;
                  Object key$iv = itx.getLiked_by_user_id();
                  if (set$iv.add(key$iv)) {
                     list$iv.add(e$iv);
                  }
               }

               likeUsers = (List)list$iv;
               $this$filter$iv = (Iterable)likeUsers;
               $i$f$filter = false;
               Collection destination$iv$iv = (Collection)(new ArrayList());
               Iterator var18 = $this$filter$iv.iterator();

               while(var18.hasNext()) {
                  Object element$iv$iv = var18.next();
                  LikeUsers itxx = (LikeUsers)element$iv$iv;
                  if (Intrinsics.areEqual(itxx.getLike_status(), MatchStatus.INSTANCE.getDECLINED()) ^ true) {
                     destination$iv$iv.add(element$iv$iv);
                  }
               }

               likeUsers = (List)destination$iv$iv;
               int i = 0;

               for(int var14 = likeUsers.size(); i < var14; ++i) {
                  Log.e("likeUsers", String.valueOf(((LikeUsers)likeUsers.get(i)).getLiked_by_user_id()));
                  ArrayList var22 = LikesFragment.this.getIdList();
                  Object var10001 = it.getDocuments().get(i);
                  var22.add(((DocumentSnapshot)var10001).getId());
                  LikesFragment.this.getStatusList().add(((LikeUsers)likeUsers.get(i)).getLike_status());
                  Log.e("idlist", (String)LikesFragment.this.getIdList().get(i) + ',' + ((LikeUsers)likeUsers.get(i)).getLiked_by_user_id());
                  LikesFragment.this.getFirebaseHelper().getUserDetailsFromDatabase(String.valueOf(((LikeUsers)likeUsers.get(i)).getLiked_by_user_id())).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
                     public void onSuccess(Object var1) {
                        this.onSuccess((DocumentSnapshot)var1);
                     }

                     public final void onSuccess(DocumentSnapshot it) {
                        User users = (User)it.toObject(User.class);
                        Log.e("users", String.valueOf(users));
                        if (users != null) {
                              likeUserList.add(users);

                           likedByMeAdapter.notifyDataSetChanged();
                        }

                     }
                  })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
                     public final void onFailure(@NotNull Exception it) {
                        String var10000 = it.getLocalizedMessage();
                        CommonsKt.showToast(var10000);
                     }
                  }));
               }
            } else {
               CommonsKt.showToast("No User found");
            }

         }
      })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
         public final void onFailure(@NotNull Exception it) {
            String var10000 = it.getLocalizedMessage();
            CommonsKt.showToast(var10000);
         }
      }));
   }

   public LikesFragment() {
      super();
   }

   @Override
   public void onRequestUpdateSuccess(boolean isAccepted) {

   }

   @Override
   public void friendAdded(boolean value) {
      if(value)
      {
         getMatchPresenter().addFriendList(user, new Function1<Boolean, Unit>() {
            @Override
            public Unit invoke(Boolean aBoolean) {
               Bundle bundle = new Bundle();
               bundle.putString("uid", user.getUid());
               getActivity().startActivity(new Intent(getContext(),ChatMessageActivity.class),bundle);
               return Unit.INSTANCE;
            }
         });
       /*  LikesFragment.this.getMatchPresenter().addFriendList(user, new Function1() {
            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke(Object var1) {
               this.invoke((Boolean)var1);
               return Unit.INSTANCE;
            }

            public final void invoke(boolean it) {
               Bundle bundle = new Bundle();
               bundle.putString("uid", user.getUid());
               getActivity().startActivity(new Intent(getContext(),ChatMessageActivity.class),bundle);
            }
         }));*/
      }
   }
}
