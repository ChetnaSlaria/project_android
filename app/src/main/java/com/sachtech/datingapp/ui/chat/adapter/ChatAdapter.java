package com.sachtech.datingapp.ui.chat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.BaseMessage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatMessaging;
import com.sachtech.datingapp.cometChat.CometChatUser;
import com.sachtech.datingapp.cometChat.listener.OnMessageUpdate;
import com.sachtech.datingapp.data.FriendUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.ui.chat.activity.ChatMessageActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.jvm.internal.Intrinsics;


public final class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> implements OnMessageUpdate {
   @NotNull
   private final ArrayList<FriendUser> userlist;
   @NotNull
   private final Activity baselistener;
   @NotNull
   private final CometChatUser cometChatUser;

   private final User getUserData() {

      return new User();
   }

   public void onUpdate(@NotNull BaseMessage messageList) {
      Intrinsics.checkParameterIsNotNull(messageList, "messageList");
   }

   public void onError(@Nullable CometChatException error) {
   }

   private final CometChatMessaging getCometMessage() {
 return new CometChatMessaging();
   }

   @NotNull
   @Override
   public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_messages,parent,false));
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
      RequestCreator var10000 = Picasso.get().load(((FriendUser)this.userlist.get(position)).getAvatar());
      View view = holder.itemView;
      var10000.into((RoundedImageView)view.findViewById(id.message_user_image));
      TextView var4 = (TextView)view.findViewById(id.message_user_name);
      var4.setText((CharSequence)((FriendUser)this.userlist.get(position)).getName());
      ((CircleImageView)view.findViewById(id.video)).setBackgroundResource(R.drawable.video_green);
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
             Intent intent=new Intent(baselistener,ChatMessageActivity.class);
             intent.putExtra("uid", ((FriendUser)ChatAdapter.this.getUserlist().get(position)).getUid());
        baselistener.startActivity(intent);
         }
      }));
      ((CircleImageView)view.findViewById(id.video)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            CometChatMessaging var10000 = ChatAdapter.this.getCometMessage();
            String var10001 = ((FriendUser)ChatAdapter.this.getUserlist().get(position)).getUid();

            var10000.initiateCall(var10001, "video");
         }
      }));
   }


   public int getItemCount() {
      return this.userlist.size();
   }

   @NotNull
   public final ArrayList<FriendUser> getUserlist() {
      return this.userlist;
   }

   @NotNull
   public final Activity getBaselistener() {
      return this.baselistener;
   }

   @NotNull
   public final CometChatUser getCometChatUser() {
      return this.cometChatUser;
   }

   public ChatAdapter(@NotNull ArrayList userlist, @NotNull Activity baselistener, @NotNull CometChatUser cometChatUser) {
      super();
      this.userlist = userlist;
      this.baselistener = baselistener;
      this.cometChatUser = cometChatUser;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
      }
   }
}
