// SearchAdapter.java
package com.sachtech.datingapp.ui.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.ui.explore.adapter.listener.CallTypeListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
   @Nullable
   private final ArrayList items;
   @NotNull
   private final CallTypeListener callTypeListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false));
    }

    public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
      Picasso var10000 = Picasso.get();
      ArrayList var10001 = this.items;

      RequestCreator var3 = var10000.load(((User)var10001.get(position)).getProfilePic());
      View view = holder.itemView;
      var3.into((CircleImageView)view.findViewById(id.search_image));
      TextView var5 = (TextView)view.findViewById(id.search_name);
      var5.setText((CharSequence)((User)this.items.get(position)).getName());
      ((ImageView)view.findViewById(id.audio_call)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            SearchAdapter.this.getCallTypeListener().onAudioCallSelected(((User)SearchAdapter.this.getItems().get(position)).getUid());
         }
      }));
      ((ImageView)view.findViewById(id.audio_call)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            SearchAdapter.this.getCallTypeListener().onVideoCallSelected(((User)SearchAdapter.this.getItems().get(position)).getUid());
         }
      }));
   }


   public int getItemCount() {
      ArrayList var10000 = this.items;
      Integer var1 = var10000 != null ? var10000.size() : null;
      return var1;
   }

   @Nullable
   public final ArrayList getItems() {
      return this.items;
   }

   @NotNull
   public final CallTypeListener getCallTypeListener() {
      return this.callTypeListener;
   }

   public SearchAdapter(@Nullable ArrayList items, @NotNull CallTypeListener callTypeListener) {
      super();
      this.items = items;
      this.callTypeListener = callTypeListener;
   }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public ViewHolder(@NonNull View itemView) {
          super(itemView);
       }
    }
 }


