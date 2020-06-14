package com.sachtech.datingapp.ui.chat;

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

import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatUser;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.FriendUser;
import com.sachtech.datingapp.data.GetFriends;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.ChatUserPresenter;
import com.sachtech.datingapp.ui.chat.adapter.ChatAdapter;
import com.sachtech.datingapp.ui.chat.adapter.ChatOnlineUsersAdapter;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.view.ChatUserView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;

public final class UserFragment extends Fragment implements ChatUserView {
   @Nullable
   private OnFragmentChange onFragmentChange;
   @Nullable
   private ChatOnlineUsersAdapter chatOnlineUsersAdapter;
   @NotNull
   public ArrayList userlist;
   @Nullable
   private ChatAdapter chatMessagesAdapter;


   public void onFailure(@NotNull String message) {
      CommonsKt.showToast(message);
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
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat_user,container,false);
   }

   public void onGettingFriends(@NotNull GetFriends it) {
      final ObjectRef arrayList = new ObjectRef();
      List var10001 = it.getData();
      if (var10001 == null) {
         throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.sachtech.datingapp.data.FriendUser> /* = java.util.ArrayList<com.sachtech.datingapp.data.FriendUser> */");
      } else {
         arrayList.element = (ArrayList)var10001;
         FirebaseHelper var10000 = this.getFirebaseHelper();
         User var3 = this.getUserData();
         if (var3 == null) {
            Intrinsics.throwNpe();
         }

         String var4 = var3.getUid();
         var10000.getBlockedUser(user.getUid()).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
               this.onSuccess((QuerySnapshot)var1);
            }

            public final void onSuccess(QuerySnapshot it) {
               List var10000 = it.toObjects(Blockeduser.class);
               List blockedUserData = var10000;
               Iterable $this$forEach$iv = (Iterable)blockedUserData;

               ObjectRef var10;
               List var19;
               for(Iterator var5 = $this$forEach$iv.iterator(); var5.hasNext(); var10.element = (ArrayList)var19) {
                  Object element$iv = var5.next();
                  Blockeduser blockedUser = (Blockeduser)element$iv;
                  Iterable $this$filter$iv = (Iterable)((ArrayList)arrayList.element);
                  var10 = arrayList;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  Iterator var15 = $this$filter$iv.iterator();

                  while(var15.hasNext()) {
                     Object element$iv$iv = var15.next();
                     FriendUser originalData = (FriendUser)element$iv$iv;
                     if (Intrinsics.areEqual(originalData.getUid(), blockedUser.getBlock_to_user_id()) ^ true) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  var19 = (List)destination$iv$iv;
               }

               UserFragment.this.getUserlist().addAll((Collection)((ArrayList)arrayList.element));
               ChatAdapter var20 = UserFragment.this.getChatMessagesAdapter();
               if (var20 != null) {
                  var20.notifyDataSetChanged();
               }

            }
         })).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
         });
      }
   }

   @NotNull
   public final ChatUserPresenter getChatUserPresenter() {
     return new ChatUserPresenter(this);
   }

   @Nullable
   public final ChatOnlineUsersAdapter getChatOnlineUsersAdapter() {
      return this.chatOnlineUsersAdapter;
   }

   public final void setChatOnlineUsersAdapter(@Nullable ChatOnlineUsersAdapter var1) {
      this.chatOnlineUsersAdapter = var1;
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
   public final ChatAdapter getChatMessagesAdapter() {
      return this.chatMessagesAdapter;
   }

   public final void setChatMessagesAdapter(@Nullable ChatAdapter var1) {
      this.chatMessagesAdapter = var1;
   }

   @NotNull
   public final CometChatUser getCometChatUser() {
     return new CometChatUser();
   }

   private final User getUserData() {
     return new User();
   }

   private final FirebaseHelper getFirebaseHelper() {
     return new FirebaseHelper();
   }

   com.sachtech.datingapp.data.User user;
   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
    String  userstring= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
    user=new Gson().fromJson(userstring, com.sachtech.datingapp.data.User.class);
      if (onFragmentChange != null) {
         onFragmentChange.selectedPos(3);
      }
      this.getUsers();
     // this.getCometChatUser().getOnlineUsers();
      this.userlist = new ArrayList();
      
      ArrayList var10003 = this.userlist;
      if (var10003 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("userlist");
      }

      ChatAdapter var10001 = new ChatAdapter(var10003, getActivity(), this.getCometChatUser());
      this.chatMessagesAdapter = var10001;
      RecyclerView var3 = (RecyclerView)view.findViewById(id.chat_messages_rv);
      var3.setAdapter((Adapter)this.chatMessagesAdapter);
   }

   private final void getUsers() {
      ChatUserPresenter var10000 = this.getChatUserPresenter();
      String var10001 = PrefDataKt.getUid();
      var10000.getUsers(var10001);
   }


}
