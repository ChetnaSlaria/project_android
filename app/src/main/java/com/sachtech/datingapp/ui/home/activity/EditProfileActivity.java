package com.sachtech.datingapp.ui.home.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.ui.profile.EditProfileFragment;

import org.jetbrains.annotations.Nullable;

public class EditProfileActivity extends AppCompatActivity {

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(R.layout.activity_edit_profile);
      BaseFragment.replaceFragment(this,R.id.edit_profile_container,new EditProfileFragment(),null);
   }
}
