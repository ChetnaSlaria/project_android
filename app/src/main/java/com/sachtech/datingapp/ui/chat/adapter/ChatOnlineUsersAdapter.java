package com.sachtech.datingapp.ui.chat.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.utils.CircleTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import de.hdodenhof.circleimageview.CircleImageView;
import gurtek.mrgurtekbase.adapter.BaseAdapter;
import gurtek.mrgurtekbase.adapter.BaseAdapter.IViewHolder;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

public final class ChatOnlineUsersAdapter extends RecyclerView.Adapter<ChatOnlineUsersAdapter.ViewHolder> {
   @NotNull
   private final ArrayList<User> onlineUserList;


   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_users,parent,false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      View view = holder.itemView;
      CircleImageView var7 = (CircleImageView)view.findViewById(id.user_image);

      Picasso.get().load(onlineUserList.get(position).getAvatar()).transform(new CircleTransform()).into(var7);
      TextView var10 = (TextView)view.findViewById(id.user_name);
      var10.setText(onlineUserList.get(position).getName());
   }


   public int getItemCount() {
      return this.onlineUserList.size();
   }

   public ChatOnlineUsersAdapter(@NotNull ArrayList<User> onlineUserList) {
      super();
      this.onlineUserList = onlineUserList;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
