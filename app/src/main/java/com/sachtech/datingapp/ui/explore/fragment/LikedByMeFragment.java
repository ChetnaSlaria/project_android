package com.sachtech.datingapp.ui.explore.fragment;

import android.content.Context;
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
import com.sachtech.datingapp.ui.explore.adapter.LikedByMeAdapter;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.UserType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public  class LikedByMeFragment extends Fragment {

   @Nullable
   private ArrayList<User> likeUserList;
   @NotNull
   public ArrayList<String> idList;
   @Nullable
   private OnFragmentChange onFragmentChange;
   private HashMap _$_findViewCache;




   public final void setLikedByMeAdapter(@NotNull LikedByMeAdapter var1) {
      this.likedByMeAdapter = var1;
   }


   public final void setLikeUserList(@Nullable ArrayList var1) {
      this.likeUserList = var1;
   }



   public final void setIdList(@NotNull ArrayList var1) {
      Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
      this.idList = var1;
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
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_liked_by_me,container,false);
   }
FirebaseHelper firebaseHelper;
   LikedByMeAdapter likedByMeAdapter;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(2);
      }
      firebaseHelper=new FirebaseHelper();

      this.getUsers();
      this.likeUserList = new ArrayList();
      this.idList = new ArrayList();
     likedByMeAdapter = new LikedByMeAdapter(likeUserList,getActivity(),idList,UserType.INSTANCE.getLIKEDBYME());
      ArrayList var10003 = this.likeUserList;
      RecyclerView var3 = (RecyclerView)view.findViewById(id.likedByMe_rv);
      var3.setAdapter(likedByMeAdapter);
   }


   private final void getUsers() {
      String var10001 = PrefDataKt.getUid();

      firebaseHelper.getLikedUserList(var10001).get().addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         public void onSuccess(Object var1) {
            this.onSuccess((QuerySnapshot)var1);
         }

         public final void onSuccess(QuerySnapshot it) {
            List<LikeUsers> likeUsers = it.toObjects(LikeUsers.class);
            Collection var3 = (Collection)likeUsers;
            boolean $i$f$distinctBy = false;
            boolean var5 = false;
            if (var3 != null && !var3.isEmpty()) {
               Iterable $this$distinctBy$iv = (Iterable)likeUsers;
               $i$f$distinctBy = false;
               HashSet set$iv = new HashSet();
               ArrayList list$iv = new ArrayList();
               Iterator var7 = $this$distinctBy$iv.iterator();

               while(var7.hasNext()) {
                  Object e$iv = var7.next();
                  LikeUsers itx = (LikeUsers)e$iv;
                  int var10 = 0;
                  Object key$iv = itx.getLiked_to_user_id();
                  if (set$iv.add(key$iv)) {
                     list$iv.add(e$iv);
                  }
               }

               likeUsers = (List)list$iv;
               int i = 0;

               for(int var14 = likeUsers.size(); i < var14; ++i) {
                  idList.add(it.getDocuments().get(i).getId());
                  Log.e("likeUsers", String.valueOf((likeUsers.get(i)).getLiked_to_user_id()));
                  if (!likeUsers.get(i).getLiked_to_user_id().isEmpty()) {
                     firebaseHelper.getUserDetailsFromDatabase(String.valueOf(((LikeUsers) likeUsers.get(i)).getLiked_to_user_id())).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
                        // $FF: synthetic method
                        // $FF: bridge method
                        public void onSuccess(Object var1) {
                           this.onSuccess((DocumentSnapshot) var1);
                        }

                        public final void onSuccess(DocumentSnapshot it) {
                           User users = (User) it.toObject(User.class);
                           Log.e("users", String.valueOf(users));
                           if (users != null) {
                              if (likeUserList != null) {
                                 likeUserList.add(users);
                              }

                              likedByMeAdapter.notifyDataSetChanged();
                           }

                        }
                     })).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
                        public final void onFailure(@NotNull Exception it) {
                           String var10000 = it.getLocalizedMessage();
                           CommonsKt.showToast(var10000);
                        }
                     }));
                  }
               }
            } else {
               CommonsKt.showToast("No user found");
            }

         }
      })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
         public final void onFailure(@NotNull Exception it) {
            String var10000 = it.getLocalizedMessage();
            CommonsKt.showToast(var10000);
         }
      }));
   }
}
