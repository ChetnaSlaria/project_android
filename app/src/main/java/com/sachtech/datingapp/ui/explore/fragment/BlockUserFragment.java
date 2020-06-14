package com.sachtech.datingapp.ui.explore.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.CometChat.CallbackListener;
import com.cometchat.pro.exceptions.CometChatException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.explore.adapter.BlockUserAdapter;
import com.sachtech.datingapp.ui.explore.adapter.listener.UnblockUser;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.PrefDataKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;


public final class BlockUserFragment extends Fragment implements UnblockUser {
   @Nullable
   private OnFragmentChange onFragmentChange;
   @NotNull
   public BlockUserAdapter likedByMeAdapter;
   @NotNull
   public ArrayList blockUserList;
   private HashMap _$_findViewCache;

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_block_user,container,false);
   }

   public void onUnblockClick(@NotNull final String uid, final int position) {
      Log.i("uid of unblock user :", uid);
      CometChat.unblockUsers(CollectionsKt.listOf(uid), (CallbackListener)(new CallbackListener() {
         public void onSuccess(@Nullable HashMap p0) {
            CollectionReference var10000 = BlockUserFragment.this.getFirebaseHelper().getFirebaseFireStore().collection("blockedUser");
            final CollectionReference doc = var10000;
            Query var4 = doc.whereEqualTo("block_to_user_id", uid);
            Query collection = var4;
            collection.get().addOnFailureListener((OnFailureListener)(new OnFailureListener() {
               public final void onFailure(@NotNull Exception it) {
                  String var10000 = it.getLocalizedMessage();
                  CommonsKt.showToast(var10000);
               }
            })).addOnCompleteListener((OnCompleteListener)(new OnCompleteListener() {
               public final void onComplete(@NotNull Task it) {
                  Object var10000 = it.getResult();
                  if (var10000 == null) {
                     Intrinsics.throwNpe();
                  }

                  Iterator var3 = ((QuerySnapshot)var10000).iterator();

                  while(var3.hasNext()) {
                     QueryDocumentSnapshot document = (QueryDocumentSnapshot)var3.next();
                     Log.e("uid of unblock user ", String.valueOf(it.getResult()));
                     CollectionReference var4 = doc;
                     var4.document(document.getId()).delete().addOnCompleteListener((OnCompleteListener)(new OnCompleteListener() {
                        public final void onComplete(@NotNull Task it) {
                           BlockUserFragment.this.getBlockUserList().clear();
                           BlockUserFragment.this.getLikedByMeAdapter().notifyItemRemoved(position);
                           BlockUserFragment.this.getBlockedUsers();
                        }
                     })).addOnFailureListener((OnFailureListener)(new OnFailureListener() {
                        public final void onFailure(@NotNull Exception it) {
                           String var10000 = it.getLocalizedMessage();
                           CommonsKt.showToast(var10000);
                        }
                     }));
                  }

               }
            }));
         }

         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((HashMap)var1);
         }

         public void onError(@Nullable CometChatException p0) {
            CommonsKt.showToast(String.valueOf(p0));
         }
      }));
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

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
   }

   @NotNull
   public final BlockUserAdapter getLikedByMeAdapter() {
      BlockUserAdapter var10000 = this.likedByMeAdapter;
      return var10000;
   }

   public final void setLikedByMeAdapter(@NotNull BlockUserAdapter var1) {
      this.likedByMeAdapter = var1;
   }

   @NotNull
   public final ArrayList getBlockUserList() {
      ArrayList var10000 = this.blockUserList;
      return var10000;
   }

   public final void setBlockUserList(@NotNull ArrayList var1) {
      this.blockUserList = var1;
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      this.getBlockedUsers();
      this.blockUserList = new ArrayList();
      BlockUserAdapter var10001 = new BlockUserAdapter(blockUserList,this::onUnblockClick,getActivity());
      ArrayList var10003 = this.blockUserList;

      this.likedByMeAdapter = var10001;
      RecyclerView var10000 = (RecyclerView)view.findViewById(id.blockUser_rv);
      var10001 = this.likedByMeAdapter;
      var10000.setAdapter((Adapter)var10001);
   }

   private void getBlockedUsers() {
      FirebaseHelper var10000 = this.getFirebaseHelper();
      String var10001 = PrefDataKt.getUid();
      var10000.getBlockedUser(var10001).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
         // $FF: synthetic method
         // $FF: bridge method
         public void onSuccess(Object var1) {
            this.onSuccess((QuerySnapshot)var1);
         }

         public final void onSuccess(QuerySnapshot it) {
            List var10000 = it.toObjects(Blockeduser.class);
            List blockedUserData = var10000;
            Collection var3 = (Collection)blockedUserData;
            boolean $i$f$distinctBy = false;
            if (!var3.isEmpty()) {
               Iterable $this$distinctBy$iv = (Iterable)blockedUserData;
               $i$f$distinctBy = false;
               HashSet set$iv = new HashSet();
               ArrayList list$iv = new ArrayList();
               Iterator var7 = $this$distinctBy$iv.iterator();

               while(var7.hasNext()) {
                  Object e$iv = var7.next();
                  Blockeduser itx = (Blockeduser)e$iv;
                  int var10 = 0;
                  Object key$iv = itx.getBlock_to_user_id();
                  if (set$iv.add(key$iv)) {
                     list$iv.add(e$iv);
                  }
               }

               blockedUserData = (List)list$iv;
               int i = 0;

               for(int var13 = blockedUserData.size(); i < var13; ++i) {
                  Log.e("users", ((Blockeduser)blockedUserData.get(i)).getBlock_to_user_id());
                  BlockUserFragment.this.getFirebaseHelper().getUserDetailsFromDatabase(((Blockeduser)blockedUserData.get(i)).getBlock_to_user_id()).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
                     // $FF: synthetic method
                     // $FF: bridge method
                     public void onSuccess(Object var1) {
                        this.onSuccess((DocumentSnapshot)var1);
                     }

                     public final void onSuccess(DocumentSnapshot it) {
                        Log.e("users", it.toString());
                        User users = (User)it.toObject(User.class);
                        Log.e("users", String.valueOf(users));
                        if (users != null) {
                           BlockUserFragment.this.getBlockUserList().add(users);
                           BlockUserFragment.this.getLikedByMeAdapter().notifyDataSetChanged();
                        } else {
                           CommonsKt.showToast("No user found");
                        }

                     }
                  })).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }
                  });
               }
            } else {
               CommonsKt.showToast("No user found");
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
