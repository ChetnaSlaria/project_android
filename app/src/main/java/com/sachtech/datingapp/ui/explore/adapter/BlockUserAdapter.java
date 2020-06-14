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
import com.sachtech.datingapp.ui.explore.adapter.listener.UnblockUser;
import com.sachtech.datingapp.ui.home.activity.AboutActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BlockUserAdapter extends RecyclerView.Adapter<BlockUserAdapter.ViewHolder> {
   @NotNull
   private final ArrayList blockUserList;
   @NotNull
   private UnblockUser unblockUser;
   @NotNull
   private final Activity baselistener;


   public BlockUserAdapter(@NotNull ArrayList blockUserList, @NotNull UnblockUser unblockUser, @NotNull Activity baselistener) {
      this.blockUserList = blockUserList;
      this.unblockUser = unblockUser;
      this.baselistener = baselistener;
   }
   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_block_user,parent,false));
   }

   public void onBindViewHolder(@NotNull BlockUserAdapter.ViewHolder holder, final int position) {
      View view = holder.itemView;
      TextView var3 = (TextView)view.findViewById(id.blocked_user);
      var3.setText((CharSequence)((User)this.blockUserList.get(position)).getName());
      RequestCreator var4 = Picasso.get().load(((User)this.blockUserList.get(position)).getProfilePic());
      var4.into((RoundedImageView)view.findViewById(id.block_user_image));
      ((TextView)view.findViewById(id.unblock)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            unblockUser.onUnblockClick(((User)blockUserList.get(position)).getUid(), position);
         }
      }));
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
          /*  Bundle bundle = new Bundle();
            bundle.putString("uid", ((User)blockUserList.get(position)).getUid());
            baselistener.startActivity(new Intent(baselistener, AboutActivity.class),bundle);*/
            Intent bundle = new Intent(baselistener,AboutActivity.class);
            bundle.putExtra("uid", ((User)blockUserList.get(position)).getUid());
            baselistener.startActivity(bundle);
         }
      }));
   }



   public int getItemCount() {
      return this.blockUserList.size();
   }



   public class ViewHolder extends RecyclerView.ViewHolder {
       ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
