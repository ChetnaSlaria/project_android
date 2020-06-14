package com.sachtech.datingapp.ui.explore.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.ui.home.activity.AboutActivity;
import com.sachtech.datingapp.utils.UserType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public final class NotInterestedUserAdapter extends RecyclerView.Adapter<NotInterestedUserAdapter.ViewHolder> {
   @NotNull
   private final ArrayList<User> userlist;
   @NotNull
   private final Activity baselistener;

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_interested_user,parent,false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
      View view = holder.itemView;

      if(!userlist.get(position).getProfilePic().isEmpty()) {
         RequestCreator var10000 = Picasso.get().load(((User) this.userlist.get(position)).getProfilePic());
         var10000.into((RoundedImageView) view.findViewById(id.message_user_image_not_interested));
      }
      TextView var4 = (TextView)view.findViewById(id.notInterested_user);
      var4.setText((CharSequence)((User)this.userlist.get(position)).getName());
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
          /*  Bundle bundle = new Bundle();
            bundle.putString("uid", ((User)getUserlist().get(position)).getUid());
            bundle.putString("user_type", UserType.INSTANCE.getLIKEDBYME());
            baselistener.startActivity(new Intent(baselistener,AboutActivity.class),bundle);*/
            Intent bundle = new Intent(baselistener,AboutActivity.class);
            bundle.putExtra("uid", ((User)getUserlist().get(position)).getUid());
            bundle.putExtra("user_type", UserType.INSTANCE.getLIKEDBYME());
            baselistener.startActivity(bundle);
         }
      }));
   }



   public int getItemCount() {
      return this.userlist.size();
   }

   @NotNull
   public final ArrayList getUserlist() {
      return this.userlist;
   }

   @NotNull
   public final Activity getBaselistener() {
      return this.baselistener;
   }

   public NotInterestedUserAdapter(@NotNull ArrayList userlist, @NotNull Activity baselistener) {
      super();
      this.userlist = userlist;
      this.baselistener = baselistener;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
