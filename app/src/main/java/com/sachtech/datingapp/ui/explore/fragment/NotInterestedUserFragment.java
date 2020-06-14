// NotInterestedUserFragment.java
package com.sachtech.datingapp.ui.explore.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.UnlikeUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.explore.adapter.NotInterestedUserAdapter;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class NotInterestedUserFragment extends Fragment {
   @NotNull
   public ArrayList userlist;
   @Nullable
   private NotInterestedUserAdapter notInterestedAdapter;
   @Nullable
   private OnFragmentChange onFragmentChange;
   private HashMap _$_findViewCache;

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   @NotNull
   public final ArrayList getUserlist() {
      ArrayList var10000 = this.userlist;
      return var10000;
   }

   public final void setUserlist(@NotNull ArrayList var1) {
      this.userlist = var1;
   }

   @Nullable
   public final NotInterestedUserAdapter getNotInterestedAdapter() {
      return this.notInterestedAdapter;
   }

   public final void setNotInterestedAdapter(@Nullable NotInterestedUserAdapter var1) {
      this.notInterestedAdapter = var1;
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
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_not_interested_user, container, false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(2);
      }

      this.userlist = new ArrayList();
      NotInterestedUserAdapter var10001 = new NotInterestedUserAdapter(userlist,getActivity());
      ArrayList var10003 = this.userlist;
      this.notInterestedAdapter = var10001;
      RecyclerView var3 = (RecyclerView) view.findViewById(id.notIntersted_rv);
      var3.setAdapter((Adapter) this.notInterestedAdapter);
      this.getUsers();
   }

   private void getUsers() {
      this.getFirebaseHelper().getNotInterestedUsers().addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((DocumentSnapshot) var1);
         }

         public final void onSuccess(DocumentSnapshot it) {
            UnlikeUser unlikeUsers = (UnlikeUser) it.toObject(UnlikeUser.class);
            if (unlikeUsers != null) {
               if (!unlikeUsers.getUserId().isEmpty() || unlikeUsers.getUserId() != null) {
                  for (int i = 0; i < unlikeUsers.getUserId().size(); i++) {
                     String uid = unlikeUsers.getUserId().get(i);
                     if (!uid.isEmpty()) {
                        getFirebaseHelper().getUserDetailsFromDatabase(uid).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                              User user = documentSnapshot.toObject(User.class);
                              if (user != null) {
                                 userlist.add(user);
                                 notInterestedAdapter.notifyDataSetChanged();
                              }
                           }
                        }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                              Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                           }
                        });
                     }
                  }
               } else {
                  Toast.makeText(getContext(), "No users found", Toast.LENGTH_SHORT).show();
               }
            }
         }
   })).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
         }
      });
   }


}