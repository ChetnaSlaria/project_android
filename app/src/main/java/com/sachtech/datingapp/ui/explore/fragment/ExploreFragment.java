package com.sachtech.datingapp.ui.explore.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatMessaging;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.explore.adapter.SearchAdapter;
import com.sachtech.datingapp.ui.explore.adapter.listener.CallTypeListener;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;

public final class ExploreFragment extends Fragment implements CallTypeListener {

   @Nullable
   private SearchAdapter searchAdapter;
   @Nullable
   private ArrayList arrayList;

   @Nullable
   private OnFragmentChange onFragmentChange;

   public void onAudioCallSelected(@NotNull String uid) {
      this.getCometMessage().initiateCall(uid, "audio");
   }

   public void onVideoCallSelected(@NotNull String uid) {
      this.getCometMessage().initiateCall(uid, "video");
   }

   @NotNull
   public final CometChatMessaging getCometMessage() {

      return new CometChatMessaging();
   }

   @Nullable
   public final SearchAdapter getSearchAdapter() {
      return this.searchAdapter;
   }

   public final void setSearchAdapter(@Nullable SearchAdapter var1) {
      this.searchAdapter = var1;
   }

   @Nullable
   public final ArrayList getArrayList() {
      return this.arrayList;
   }

   public final void setArrayList(@Nullable ArrayList var1) {
      this.arrayList = var1;
   }

   @NotNull
   public final FirebaseHelper getFirebaseHelper() {
      return new FirebaseHelper();
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
      return LayoutInflater.from(getContext()).inflate(R.layout.fragment_search,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      OnFragmentChange var10000 = this.onFragmentChange;
      if (var10000 != null) {
         var10000.selectedPos(2);
      }

      this.arrayList = new ArrayList();
     setOnClickListener(view);
      this.openFragment((Fragment) (new LikesFragment()));
      this.searchAdapter = new SearchAdapter(this.arrayList, (CallTypeListener) this);
   }

   private  void setOnClickListener(View view) {
      ((TabLayout) view.findViewById(id.tabLayout)).setOnTabSelectedListener((BaseOnTabSelectedListener) (new BaseOnTabSelectedListener() {
         public void onTabReselected(@Nullable Tab p0) {
         }

         public void onTabUnselected(@Nullable Tab p0) {
         }

         public void onTabSelected(@Nullable Tab p0) {
            if (p0 == null) {
               Intrinsics.throwNpe();
            }

            switch (p0.getPosition()) {
               case 0:
                  ExploreFragment.this.openFragment((Fragment) (new LikesFragment()));
                  break;
               case 1:
                  ExploreFragment.this.openFragment((Fragment) (new VisitorsFragment()));
                  break;
               case 2:
                  ExploreFragment.this.openFragment((Fragment) (new LikedByMeFragment()));
                  break;
               case 3:
                  ExploreFragment.this.openFragment((Fragment) (new NotInterestedUserFragment()));
                  break;
               case 4:
                  ExploreFragment.this.openFragment((Fragment) (new BlockUserFragment()));
            }

         }
      }));
   }

   private final void openFragment(Fragment kClass) {
      FragmentTransaction var10000 = this.getChildFragmentManager().beginTransaction();
      FragmentTransaction transaction = var10000;
      transaction.replace(id.exploreView, kClass).commit();
   }

}