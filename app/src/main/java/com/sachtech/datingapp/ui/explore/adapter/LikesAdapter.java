// LikesAdapter.java
package com.sachtech.datingapp.ui.explore.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.data.LikeUsers;
import com.sachtech.datingapp.data.MatchStatus;
import com.sachtech.datingapp.data.UnlikeUser;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.explore.adapter.listener.OnProfilesMatch;
import com.sachtech.datingapp.ui.home.activity.AboutActivity;
import com.sachtech.datingapp.utils.CircleTransform;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.utils.UserType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import gurtek.mrgurtekbase.ExtenstionsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    @NotNull
    private final ArrayList<User> userlist;
    @NotNull
    private final Activity baselistener;
    @NotNull
    private final ArrayList<String> idList;
    @NotNull
    private final ArrayList<String> userStatus;
    @NotNull
    private final OnProfilesMatch onProfilesMatch;
    private FirebaseHelper firebaseHelper;

    @NotNull
    public final FirebaseHelper getFirebaseHelper() {

        return new FirebaseHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_likes, parent, false));
    }

    public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
        Log.e("userList", userlist.get(position).getProfilePic());
        RoundedImageView imageView = holder.itemView.findViewById(id.message_user_image_like);
        if(!userlist.get(position).getProfilePic().isEmpty())
        Picasso.get().load(userlist.get(position).getProfilePic()).into(imageView);
        View view = holder.itemView;
        TextView var4 = (TextView) view.findViewById(id.liked_by_me_user);
        var4.setText((CharSequence) ((User) this.userlist.get(position)).getName());
        ImageView var5;
        if (this.userStatus.get(position).equals(MatchStatus.INSTANCE.getACCEPTED())) {
            var5 = (ImageView) view.findViewById(id.likeAccept);
            ExtenstionsKt.gone((View) var5);
            var5 = (ImageView) view.findViewById(id.likeDecline);
            ExtenstionsKt.gone((View) var5);
            var5 = (ImageView) view.findViewById(id.likeMessage);
            ExtenstionsKt.visible((View) var5);
        } else {
            var5 = (ImageView) view.findViewById(id.likeMessage);
            ExtenstionsKt.gone((View) var5);
            var5 = (ImageView) view.findViewById(id.likeAccept);
            ExtenstionsKt.visible((View) var5);
            var5 = (ImageView) view.findViewById(id.likeDecline);
            ExtenstionsKt.visible((View) var5);
        }

        holder.itemView.setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
             /*   Bundle bundle = new Bundle();
                bundle.putString("uid", ((User) LikesAdapter.this.getUserlist().get(position)).getUid());
                bundle.putString("docid", (String) LikesAdapter.this.getIdList().get(position));
                bundle.putString("user_type", UserType.INSTANCE.getLIKE());
                baselistener.startActivity(new Intent(baselistener, AboutActivity.class), bundle);*/
                Intent bundle = new Intent(baselistener, AboutActivity.class);
                bundle.putExtra("uid", ((User) getUserlist().get(position)).getUid());
                bundle.putExtra("docid", (String) getIdList().get(position));
                bundle.putExtra("user_type", UserType.INSTANCE.getLIKE());
                baselistener.startActivity(bundle);
            }
        }));
        ((ImageView) view.findViewById(id.likeAccept)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                addToLikeList(getIdList().get(position).toString(), ((User) getUserlist().get(position)));
            }
        }));
        ((ImageView) view.findViewById(id.likeDecline)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                addToUnlikeList(((User) getUserlist().get(position)), getIdList().get(position).toString());
            }
        }));
        view = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        ((ImageView) view.findViewById(id.likeMessage)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LikesAdapter var10000 = LikesAdapter.this;
                Object var10001 = LikesAdapter.this.getUserlist().get(position);
                Intrinsics.checkExpressionValueIsNotNull(var10001, "userlist[position]");
                var10000.openMessageActivity((User) var10001);
            }
        }));
    }

    private final void openMessageActivity(User user) {
        this.onProfilesMatch.OnMessageClick(user);
    }

    private final void addToUnlikeList(final User user, final String id) {
        FirebaseHelper var10000 = this.getFirebaseHelper();
        String var10001 = PrefDataKt.getUid();
        if (var10001 == null) {
            Intrinsics.throwNpe();
        }

        final DocumentReference doc = var10000.addUserToNotInterestedList(var10001);
        doc.get().addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((DocumentSnapshot) var1);
            }

            public final void onSuccess(DocumentSnapshot it) {
                if (it.exists()) {
                    ArrayList var10000 = new ArrayList();
                    UnlikeUser var10002 = (UnlikeUser) it.toObject(UnlikeUser.class);
                    List var3 = var10002 != null ? var10002.getUserId() : null;
                    if (var3 == null) {
                        Intrinsics.throwNpe();
                    }
                    ArrayList userList = var10000;
                    if (!userList.contains(user.getUid())) {
                        userList.add(user.getUid());
                    }

                    doc.update("userId", userList, new Object[0]).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
                        // $FF: synthetic method
                        // $FF: bridge method
                        public void onSuccess(Object var1) {
                            this.onSuccess((Void) var1);
                        }

                        public final void onSuccess(Void it) {
                            LikesAdapter.this.updateMatchStatus(id, user, MatchStatus.INSTANCE.getDECLINED());
                        }
                    })).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                } else {
                    doc.set(new UnlikeUser((List) CollectionsKt.arrayListOf(new String[]{user.getUid()}))).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
                        public void onSuccess(Object var1) {
                            this.onSuccess((Void) var1);
                        }

                        public final void onSuccess(Void it) {
                            LikesAdapter.this.updateMatchStatus(id, user, MatchStatus.INSTANCE.getDECLINED());
                        }
                    })).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(baselistener, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        })).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(baselistener, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final void addToLikeList(final String s, final User user) {
        DocumentReference document = this.getFirebaseHelper().addUserToLikeList();
        Log.e("id", "" + user.getUid());
        LikeUsers users = new LikeUsers();
        users.setLiked_to_user_id(user.getUid());
        users.setLiked_by_user_id(PrefDataKt.getUid());
        users.setLike_status(MatchStatus.INSTANCE.getACCEPTED());
        document.set(users).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    updateMatchStatus(s, user, MatchStatus.INSTANCE.getACCEPTED());
                } else
                    Toast.makeText(baselistener, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateMatchStatus(String doc, final User user, final String status) {
        LikeUsers users = new LikeUsers();
        users.setLiked_to_user_id(user.getUid());
        users.setLiked_by_user_id(PrefDataKt.getUid());
        users.setLike_status(status);
        getFirebaseHelper().updateLikeStatus(doc, users).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(baselistener, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (status == MatchStatus.INSTANCE.getACCEPTED()) {
                    onProfilesMatch.matchedProfile(user);
                    notifyDataSetChanged();
                }
            }
        });

    }

    public int getItemCount() {
        return this.userlist.size();
    }

    @NotNull
    public final ArrayList<User> getUserlist() {
        return this.userlist;
    }

    @NotNull
    public final Activity getBaselistener() {
        return this.baselistener;
    }

    @NotNull
    public final ArrayList<String> getIdList() {
        return this.idList;
    }

    @NotNull
    public final ArrayList getUserStatus() {
        return this.userStatus;
    }

    @NotNull
    public final OnProfilesMatch getOnProfilesMatch() {
        return this.onProfilesMatch;
    }

    public LikesAdapter(@NotNull ArrayList<User> userlist, @NotNull Activity baselistener, @NotNull ArrayList<String> idList, @NotNull ArrayList<String> userStatus, @NotNull OnProfilesMatch onProfilesMatch) {
        super();
        this.userlist = userlist;
        this.baselistener = baselistener;
        this.idList = idList;
        this.userStatus = userStatus;
        this.onProfilesMatch = onProfilesMatch;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


