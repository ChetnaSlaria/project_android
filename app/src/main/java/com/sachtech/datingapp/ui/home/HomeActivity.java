// HomeActivity.java
package com.sachtech.datingapp.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.cometChat.CometChatMessaging;
import com.sachtech.datingapp.cometChat.CometConstants;
import com.sachtech.datingapp.ui.chat.UserFragment;
import com.sachtech.datingapp.ui.explore.fragment.ExploreFragment;
import com.sachtech.datingapp.ui.home.fragment.HomeFragment;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
import com.sachtech.datingapp.ui.home.listener.OnIconClick;
import com.sachtech.datingapp.ui.profile.ProfileFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HomeActivity extends AppCompatActivity implements OnFragmentChange {
   @NotNull
   public OnIconClick listener;

   public void selectedPos(int var1) {
      switch (var1) {
         case 1:
            navigationView.setSelectedItemId(id.main_home);
            break;
         case 2:
            navigationView.setSelectedItemId(id.main_explore);
            break;
         case 3:
            navigationView.setSelectedItemId(id.main_chat);
            break;
         case 4:
            navigationView.setSelectedItemId(id.main_profile);
      }

   }

   @NotNull
   public final OnIconClick getListener() {
      OnIconClick var10000 = this.listener;
      return var10000;
   }

   public final void setListener(@NotNull OnIconClick var1) {
      this.listener = var1;
   }

   BottomNavigationView navigationView;

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_main);
      navigationView = findViewById(id.bottom_nav);
      BaseFragment.replaceFragment(this,id.home_container, new HomeFragment(), null);
      setBottomNavigationClick();
   }

   private void setBottomNavigationClick() {
      navigationView.setOnNavigationItemSelectedListener((OnNavigationItemSelectedListener) (new OnNavigationItemSelectedListener() {
         public final boolean onNavigationItemSelected(@NotNull MenuItem it) {
            switch (it.getItemId()) {
               case id.main_home:
                  BaseFragment.replaceFragment(HomeActivity.this,id.home_container, new HomeFragment(), null);
                  return true;
               case id.main_explore:
                  BaseFragment.replaceFragment(HomeActivity.this,id.home_container, new ExploreFragment(), null);
                  return true;
               case id.main_chat:
                  BaseFragment.replaceFragment(HomeActivity.this,id.home_container, new UserFragment(), null);
                  return true;
               case id.main_profile:
                  BaseFragment.replaceFragment(HomeActivity.this,id.home_container, new ProfileFragment(), null);
                  return true;
               default:
                  BaseFragment.replaceFragment(HomeActivity.this,id.home_container, new HomeFragment(), null);
                  return false;
            }
         }
      }));
   }

   protected void onStart() {
      super.onStart();
      (new CometChatMessaging()).addCallListener((RelativeLayout) null, (Activity) this, CometConstants.INSTANCE.getCallListener());
   }

   protected void onDestroy() {
      super.onDestroy();
      (new CometChatMessaging()).removeCallListener(CometConstants.INSTANCE.getCallListener());
   }

}

