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
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public final class LikedByMeAdapter extends RecyclerView.Adapter<LikedByMeAdapter.ViewHolder> {
   @NotNull
   private final ArrayList<User> likeUserList;
   @NotNull
   private final Activity baselistener;
   @NotNull
   private final ArrayList idList;
   @NotNull
   private final String visitors;

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liked_by_me,parent,false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
//      RequestCreator var10000 = Picasso.get().load(((User)this.likeUserList.get(position)).getProfilePic());
     // View view = holder.itemView;
      if(!likeUserList.get(position).getProfilePic().isEmpty())
      Picasso.get().load(likeUserList.get(position).getProfilePic()).into((RoundedImageView)holder.itemView.findViewById(id.message_user_image_liked_by_me));
    //  var10000.into((RoundedImageView)view.findViewById(id.message_user_image));
      View var3 = holder.itemView;
      TextView var4 = (TextView)var3.findViewById(id.liked_by_me_user);
      var4.setText((CharSequence)((User)this.likeUserList.get(position)).getName());
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {

            Intent bundle = new Intent(baselistener,AboutActivity.class);
            bundle.putExtra("uid", ((User)likeUserList.get(position)).getUid());
            bundle.putExtra("docid", (String)idList.get(position));
            bundle.putExtra("user_type", visitors);
            baselistener.startActivity(bundle);
         }
      }));
   }



   public int getItemCount() {
      return this.likeUserList.size();
   }


   public LikedByMeAdapter(@NotNull ArrayList likeUserList, @NotNull Activity baselistener, @NotNull ArrayList idList, @NotNull String visitors) {
      super();
      this.likeUserList = likeUserList;
      this.baselistener = baselistener;
      this.idList = idList;
      this.visitors = visitors;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
