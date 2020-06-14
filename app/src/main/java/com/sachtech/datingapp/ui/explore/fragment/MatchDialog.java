// onMatched.java
package com.sachtech.datingapp.ui.explore.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.ui.explore.adapter.listener.onMatched;
import com.sachtech.datingapp.utils.CircleTransform;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;



public final class MatchDialog extends DialogFragment {
   @Nullable
   private String username;
   @Nullable
   private User currentUser;
   @Nullable
   private User selectedUser;
   @Nullable
   private com.sachtech.datingapp.ui.explore.adapter.listener.onMatched onMatched;

   @Nullable
   public final String getUsername() {
      return this.username;
   }

   public final void setUsername(@Nullable String var1) {
      this.username = var1;
   }

   @Nullable
   public final User getCurrentUser() {
      return this.currentUser;
   }

   public final void setCurrentUser(@Nullable User var1) {
      this.currentUser = var1;
   }

   @Nullable
   public final User getSelectedUser() {
      return this.selectedUser;
   }

   public final void setSelectedUser(@Nullable User var1) {
      this.selectedUser = var1;
   }

   @Nullable
   public final onMatched getOnMatched() {
      return this.onMatched;
   }

   public final void setOnMatched(@Nullable onMatched var1) {
      this.onMatched = var1;
   }

   public boolean adjustdisplay() {
      return true;
   }

   @Nullable
   @Override
   public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.dialog_match,container,false);
   }

   public void onViewCreated(@NotNull android.view.View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

      ((TextView)view.findViewById(R.id.matchOk)).setOnClickListener((android.view.View.OnClickListener)(new android.view.View.OnClickListener() {
         @Override
         public void onClick(android.view.View v) {
            onMatched var10000 = MatchDialog.this.getOnMatched();
            if (var10000 != null) {
               var10000.onMatched();
            }
            MatchDialog.this.dismiss();
         }
      }));
      String userString= Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSER(),"");
      this.currentUser = (User) new Gson().fromJson(userString,User.class);
      Picasso var4 = Picasso.get();
      User var10001 = this.currentUser;
      String var5 = var10001 != null ? var10001.getProfilePic() : null;
Picasso.get().load(var5).into((CircleImageView)view.findViewById(R.id.yourImage));
    //  var4.load(var5).transform((Transformation)(new CircleTransform())).into((CircleImageView)view.findViewById(R.id.yourImage));
      var4 = Picasso.get();
      var10001 = this.selectedUser;
      var5 = var10001 != null ? var10001.getProfilePic() : null;
      Picasso.get().load(var5).into((CircleImageView)view.findViewById(R.id.likedUserImage));

    //  var4.load(var5).transform((Transformation)(new CircleTransform())).into((CircleImageView)view.findViewById(R.id.likedUserImage));
      TextView var6 = (TextView)view.findViewById(R.id.match_text);
      var6.setText((CharSequence)("You and " + this.username + " has matched"));
   }

   public final void selectedUser(@NotNull User user) {
      this.selectedUser = user;
      this.username = user.getName();
   }

   public final void onMatchedUser(@NotNull onMatched onMatched) {
      this.onMatched = onMatched;
   }



}
