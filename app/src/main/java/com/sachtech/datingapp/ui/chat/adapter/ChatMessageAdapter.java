package com.sachtech.datingapp.ui.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.cometchat.pro.core.Call;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.models.TextMessage;
import com.cometchat.pro.models.User;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;


public final class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MyMessageAdapter> {
   @NotNull
   private final ArrayList messageList;
   @NotNull
   private final String uid;

   @NotNull
   public ChatMessageAdapter.MyMessageAdapter onCreateViewHolder(@NotNull ViewGroup p0, int p1) {
      LayoutInflater view = LayoutInflater.from(p0.getContext());
      View var10003 = view.inflate(R.layout.item_chat_message, p0, false);
      return new ChatMessageAdapter.MyMessageAdapter(var10003);
   }

   public int getItemCount() {
      return this.messageList.size();
   }

   public void onBindViewHolder(@NotNull ChatMessageAdapter.MyMessageAdapter p0, int p1) {
      Object var10000 = this.messageList.get(p1);
      View var4;
      TextView var5;
      LinearLayout var7;
      if (Intrinsics.areEqual(((BaseMessage)var10000).getCategory(), "call")) {
         var10000 = this.messageList.get(p1);
         if (var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.cometchat.pro.core.Call");
         }

         String call = ((Call)var10000).getCallStatus();
         var4 = p0.itemView;
         var5 = (TextView)var4.findViewById(id.call_status);
         var5.setVisibility(View.VISIBLE);
         var4 = p0.itemView;
         var5 = (TextView)var4.findViewById(id.call_status);
         var5.setText((CharSequence)("Call " + call));
         var4 = p0.itemView;
         var7 = (LinearLayout)var4.findViewById(id.receiver_message);
         var7.setVisibility(View.GONE);
         var4 = p0.itemView;
         var7 = (LinearLayout)var4.findViewById(id.sender_message);
         var7.setVisibility(View.GONE);
      } else {
         var10000 = this.messageList.get(p1);
         if (Intrinsics.areEqual(((BaseMessage)var10000).getCategory(), "message")) {
            String var8 = this.uid;
            Object var10001 = this.messageList.get(p1);
            User var6 = ((BaseMessage)var10001).getSender();
            if (Intrinsics.areEqual(var8, var6 != null ? var6.getUid() : null)) {
               var4 = p0.itemView;
               var7 = (LinearLayout)var4.findViewById(id.sender_message);
               var7.setVisibility(View.VISIBLE);
               var4 = p0.itemView;
               var5 = (TextView)var4.findViewById(id.sender_message_text);
               var10001 = this.messageList.get(p1);

               var5.setText((CharSequence)((TextMessage)var10001).getText());
               var4 = p0.itemView;
               var7 = (LinearLayout)var4.findViewById(id.receiver_message);
               var7.setVisibility(View.GONE);
               var4 = p0.itemView;
               var5 = (TextView)var4.findViewById(id.call_status);
               var5.setVisibility(View.GONE);
            } else {
               var4 = p0.itemView;
               var7 = (LinearLayout)var4.findViewById(id.receiver_message);
               var7.setVisibility(View.VISIBLE);
               var4 = p0.itemView;
               var5 = (TextView)var4.findViewById(id.receiver_message_text);
               var10001 = this.messageList.get(p1);

               var5.setText((CharSequence)((TextMessage)var10001).getText());
               var4 = p0.itemView;
               var5 = (TextView)var4.findViewById(id.call_status);
               var5.setVisibility(View.GONE);
               var4 = p0.itemView;
               var7 = (LinearLayout)var4.findViewById(id.sender_message);
               var7.setVisibility(View.GONE);
            }
         }
      }

   }

   @NotNull
   public final ArrayList getMessageList() {
      return this.messageList;
   }

   @NotNull
   public final String getUid() {
      return this.uid;
   }

   public ChatMessageAdapter(@NotNull ArrayList messageList, @NotNull String uid) {
      super();
      this.messageList = messageList;
      this.uid = uid;
   }

   public final class MyMessageAdapter extends ViewHolder {
      public MyMessageAdapter(@NotNull View view) {
         super(view);
      }
   }
}
