package com.sachtech.datingapp.ui.explore.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.data.VisitUsers;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public final class VisitorsFragment extends Fragment {
   @NotNull
   public LikedByMeAdapter likedByMeAdapter;
   @Nullable
   private ArrayList likeUserList;
   @NotNull
   public ArrayList idList;
   @Nullable
   private OnFragmentChange onFragmentChange;

   private final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   @NotNull
   public final LikedByMeAdapter getLikedByMeAdapter() {
      LikedByMeAdapter var10000 = this.likedByMeAdapter;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("likedByMeAdapter");
      }

      return var10000;
   }

   public final void setLikedByMeAdapter(@NotNull LikedByMeAdapter var1) {
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

   @Nullable
   @Override
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_visitors,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(2);
      }

      this.getUsers();
      this.likeUserList = new ArrayList();
      this.idList = new ArrayList();
      ;
      ArrayList var10003 = this.likeUserList;
      if (var10003 == null) {
         Intrinsics.throwNpe();
      }

      FragmentActivity var10004 = this.getActivity();
      if (var10004 == null) {
         Intrinsics.throwNpe();
      }

      Activity var4 = (Activity)var10004;
      ArrayList var10005 = this.idList;
   
      LikedByMeAdapter var10001 = new LikedByMeAdapter(var10003, var4, var10005, UserType.INSTANCE.getVISITORS());
      this.likedByMeAdapter = var10001;
      RecyclerView var3 = (RecyclerView)view.findViewById(id.visit_to_me_rec);
      Intrinsics.checkExpressionValueIsNotNull(var3, "visit_to_me_rec");
      var10001 = this.likedByMeAdapter;

      var3.setAdapter((Adapter)var10001);
   }

   private final void getUsers() {
      FirebaseHelper var10000 = this.getFirebaseHelper();
      String var10001 = PrefDataKt.getUid();
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }

      var10000.getVisitUserList(var10001).get().addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((QuerySnapshot)var1);
         }

         public final void onSuccess(QuerySnapshot it) {
            List var10000 = it.toObjects(VisitUsers.class);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "it.toObjects(VisitUsers::class.java)");
            List likeUsers = var10000;
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
                  VisitUsers itx = (VisitUsers)e$iv;
                  Object key$iv = itx.getVisit_by_user_id();
                  if (set$iv.add(key$iv)) {
                     list$iv.add(e$iv);
                  }
               }

               likeUsers = (List)list$iv;
               int i = 0;

               for(int var13 = likeUsers.size(); i < var13; ++i) {
                  ArrayList var16 = VisitorsFragment.this.getIdList();
                  Intrinsics.checkExpressionValueIsNotNull(it, "it");
                  Object var10001 = it.getDocuments().get(i);
                  Intrinsics.checkExpressionValueIsNotNull(var10001, "it.documents.get(i)");
                  var16.add(((DocumentSnapshot)var10001).getId());
                  Log.e("likeUsers", ((VisitUsers)likeUsers.get(i)).getVisit_by_user_id().toString());
                  VisitorsFragment.this.getFirebaseHelper().getUserDetailsFromDatabase(((VisitUsers)likeUsers.get(i)).getVisit_by_user_id().toString()).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
                     // $FF: synthetic method
                     // $FF: bridge method
                     public void onSuccess(Object var1) {
                        this.onSuccess((DocumentSnapshot)var1);
                     }

                     public final void onSuccess(DocumentSnapshot it) {
                        User users = (User)it.toObject(User.class);
                        Log.e("users", String.valueOf(users));
                        if (users != null) {
                           ArrayList var10000 = VisitorsFragment.this.getLikeUserList();
                           if (var10000 != null) {
                              var10000.add(users);
                           }

                           VisitorsFragment.this.getLikedByMeAdapter().notifyDataSetChanged();
                        }

                     }
                  })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
                     public final void onFailure(@NotNull Exception it) {
                        String var10000 = it.getLocalizedMessage();
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "it.localizedMessage");
                        CommonsKt.showToast(var10000);
                     }
                  }));
               }
            } else {
               CommonsKt.showToast("No user Found");
            }

         }
      })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
         public final void onFailure(@NotNull Exception it) {
            String var10000 = it.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "it.localizedMessage");
            CommonsKt.showToast(var10000);
         }
      }));
   }

}
