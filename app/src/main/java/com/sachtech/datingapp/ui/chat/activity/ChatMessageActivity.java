package com.sachtech.datingapp.ui.chat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.CometChat.CallbackListener;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.cometChat.CometChatMessaging;
import com.sachtech.datingapp.cometChat.CometChatUser;
import com.sachtech.datingapp.cometChat.CometConstants;
import com.sachtech.datingapp.cometChat.listener.OnGettingMessage;
import com.sachtech.datingapp.cometChat.listener.OnMessageUpdate;
import com.sachtech.datingapp.cometChat.network.Api;
import com.sachtech.datingapp.cometChat.network.ApiInterface;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.DeleteFriend;
import com.sachtech.datingapp.data.FriendStatusData;
import com.sachtech.datingapp.data.RemoveFriendList;
import com.sachtech.datingapp.data.RequestBy;
import com.sachtech.datingapp.data.RequestSatus;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.presenter.MatchUserPresenter;
import com.sachtech.datingapp.ui.chat.activity.listener.ActionListener;
import com.sachtech.datingapp.ui.chat.activity.listener.OnGettingUserDetails;
import com.sachtech.datingapp.ui.chat.adapter.ChatMessageAdapter;
import com.sachtech.datingapp.ui.home.activity.AboutActivity;
import com.sachtech.datingapp.utils.PrefDataKt;
import com.sachtech.datingapp.view.UserView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gurtek.mrgurtekbase.ExtenstionsKt;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class ChatMessageActivity extends AppCompatActivity implements OnMessageUpdate, UserView, ActionListener, OnGettingUserDetails {
    @NotNull
    public ArrayList messageList;
    @NotNull
    public ChatMessageAdapter chatMessageAdapter;
    private FirebaseHelper firebaseHelper;
    @Nullable
    private User user;
    @NotNull
    private String uid;




    @NotNull
    public final ArrayList getMessageList() {
        ArrayList var10000 = this.messageList;
        return var10000;
    }

    public final void setMessageList(@NotNull ArrayList var1) {
        this.messageList = var1;
    }


    private final MatchUserPresenter getMatchUserPresenter() {
        return new MatchUserPresenter(this);
    }

    private final User getCurrentUserData() {
        return new User();
    }

    private final com.sachtech.datingapp.data.User getLoginUser() {
        return new com.sachtech.datingapp.data.User();
    }

    @NotNull
    public final CometChatUser getCometChatUser() {
        return new CometChatUser();
    }

    @Nullable
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User var1) {
        this.user = var1;
    }

    @NotNull
    public final String getUid() {
        return this.uid;
    }

    public final void setUid(@NotNull String var1) {
        this.uid = var1;
    }

    public void onYesClick() {
        ArrayList uids = new ArrayList();
        uids.add(this.uid);
        blockUserDialog.dismiss();
        this.blockUser(uids);
    }

    public void onUnlikeUser(@NotNull com.sachtech.datingapp.data.User user) {
    }

    public void onMatchUser(@NotNull String uid) {
    }

    public void onGettingAllUsers(@NotNull QuerySnapshot it) {
    }

    public void onLikeUser(@NotNull com.sachtech.datingapp.data.User user) {
    }

    public void onSuccess() {
    }

    public void onRequestUpdateSuccess(boolean isAccepted) {
        LinearLayout var10000 = (LinearLayout) findViewById(id.request_view);
        ExtenstionsKt.gone((View) var10000);
        if (isAccepted) {
            Log.e("onRequestUpdateSuccess", "" + isAccepted);
        } else {
            Log.e("onRequestUpdateSuccess", "" + isAccepted);
            this.finish();
        }

    }

    public void onFailure(@NotNull String message) {
    }

    public void onError(@Nullable CometChatException error) {
        String var10000 = error != null ? error.getLocalizedMessage() : null;
        CommonsKt.showToast(var10000);
    }

    public void onUpdate(@NotNull BaseMessage message) {

        messageList.add(message);

        chatMessageAdapter.notifyDataSetChanged();

        messageRv.scrollToPosition(messageList.size() - 1);
    }
    BlockUserDialog blockUserDialog;
    RecyclerView messageRv;
    ReportDialog reportDialog;
    CometChatMessaging chatMessaging;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_chat_message);
        chatMessaging=new CometChatMessaging(this);
        reportDialog=new ReportDialog();
        messageList=new ArrayList();
        User var10004 = CometChat.getLoggedInUser();
        String var8 = var10004.getUid();
        chatMessageAdapter=new ChatMessageAdapter(messageList,var8);
        messageRv=findViewById(id.chat_message_rv);

        messageRv.setAdapter((Adapter) chatMessageAdapter);
        // var5.scrollToPosition(messageList.size() - 1);
        firebaseHelper=new FirebaseHelper();
        Intent var10001 = this.getIntent();
        blockUserDialog=new BlockUserDialog();
        String var4 = var10001 != null ? var10001.getStringExtra("uid") : null;

        this.uid = var4;
        com.sachtech.datingapp.data.User var10000 = this.getLoginUser();
      //  Boolean var2 = var10000 != null ? var10000.isSubscribed() : null;

        //if (!var2) {
       /*     ImageView var3 = (ImageView) findViewById(id.audioCall);
            ExtenstionsKt.gone((View) var3);
            var3 = (ImageView) findViewById(id.videoCall);
            ExtenstionsKt.gone((View) var3);*/
        //}

        User cometUser=getCometChatUser().getUserDetails(uid,this);

        getFriend(getUid(), new Function1() {
            @Override
            public Object invoke(Object o) {
                LinearLayout var10000;
                FriendStatusData it = ((FriendStatusData) o);
                if (it == null) {
                    var10000 = (LinearLayout) findViewById(id.request_view);
                    ExtenstionsKt.visible((View) var10000);
                } else if (it.getRequestedBy() == RequestBy.INSTANCE.getMe()) {
                    var10000 = (LinearLayout) findViewById(id.request_view);
                    ExtenstionsKt.gone((View) var10000);
                } else if (it.getRequestStatus() == RequestSatus.INSTANCE.getPending()) {
                    var10000 = (LinearLayout) findViewById(id.request_view);
                    ExtenstionsKt.visible((View) var10000);
                } else {
                    var10000 = (LinearLayout) findViewById(id.request_view);
                    ExtenstionsKt.gone((View) var10000);
                }

                return Unit.INSTANCE;
            }
        });

        findViewById(id.accept).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.request_view);
                ExtenstionsKt.gone((View) var10000);
                MatchUserPresenter var2 = ChatMessageActivity.this.getMatchUserPresenter();
                com.sachtech.datingapp.data.User var10001 = PrefDataKt.getCurrentUser();

                var2.updateFriendRequestStatus(var10001.getUid(), ChatMessageActivity.this.getUid(), true);
            }
        }));
        findViewById(id.decline).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.request_view);
                ExtenstionsKt.gone((View) var10000);
                MatchUserPresenter var2 = ChatMessageActivity.this.getMatchUserPresenter();
                com.sachtech.datingapp.data.User var10001 = PrefDataKt.getCurrentUser();

                var2.updateFriendRequestStatus(var10001.getUid(), ChatMessageActivity.this.getUid(), false);
                ChatMessageActivity.this.callRemoveFriendApi();
            }
        }));
        this.setOnClick();
     /*   this.messageList = new ArrayList();
       // ChatMessageAdapter var6 = new ChatMessageAdapter(messageList, CometChat.getLoggedInUser().getUid());
       // ArrayList var10003 = this.messageList;

        User var10004 = CometChat.getLoggedInUser();
        String var8 = var10004.getUid();
        this.chatMessageAdapter = var6;
        RecyclerView var5 = (RecyclerView) findViewById(id.chat_message_rv);
        var6 = this.chatMessageAdapter;

        var5.setAdapter((Adapter) var6);
        var5 = (RecyclerView) findViewById(id.chat_message_rv);
        ArrayList var7 = this.messageList;

        var5.scrollToPosition(var7.size() - 1);*/
    }

    private  void getAllMessages() {
        messageList.clear();
        chatMessaging.fetchAllMessage(this.uid, (OnGettingMessage) (new OnGettingMessage() {
            public void onGettingMessage(@NotNull BaseMessage baseMessage) {
                messageList.add(baseMessage);
                chatMessageAdapter.notifyDataSetChanged();
            }
        }));
    }

    public final void getFriend(@NotNull String friendid, @NotNull final Function1 onFrienRecieved) {
        FirebaseHelper var10000 = this.firebaseHelper;
        String var10001 = PrefDataKt.getUid();

        Task doc = var10000.getUserFriend(var10001, friendid);
        Log.e("getFriend", "called");
        doc.addOnCompleteListener((OnCompleteListener) (new OnCompleteListener() {
            public final void onComplete(@NotNull Task it) {
                if (it.isSuccessful()) {
                    Log.e("getFriend", "isScuucxcdcds");
                    Log.e("getFriend", "isScuucxcdcds" + (DocumentSnapshot) it.getResult());
                    if (it.getResult() == null) {
                        onFrienRecieved.invoke((Object) null);
                    } else {
                        StringBuilder var10001 = (new StringBuilder()).append("has data");
                        DocumentSnapshot var10002 = (DocumentSnapshot) it.getResult();
                        Log.e("getFriend", var10001.append(var10002 != null ? (FriendStatusData) var10002.toObject(FriendStatusData.class) : null).toString());
                        Function1 var10000 = onFrienRecieved;
                        DocumentSnapshot var2 = (DocumentSnapshot) it.getResult();
                        var10000.invoke(var2 != null ? (FriendStatusData) var2.toObject(FriendStatusData.class) : null);
                    }
                } else {
                    Log.e("getFriend", "faldfdklsgksd");
                }

            }
        }));
    }

    private final void setUserDetails(User it) {
        if (it != null) {
            if (it.getAvatar() != null) {
                Picasso.get().load(it.getAvatar()).into((CircleImageView) findViewById(id.chat_user_image));
            }

            TextView var10000 = (TextView) findViewById(id.chat_user_name);
            var10000.setText((CharSequence) it.getName());
        }

    }

    private final void setOnClick() {
        findViewById(id.chat_back).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                ChatMessageActivity.this.onBackPressed();
            }
        }));
        (findViewById(id.audioCall)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                User var10001 = ChatMessageActivity.this.getUser();
                String var2 = var10001 != null ? var10001.getUid() : null;

                chatMessaging.initiateCall(var2, "audio");
            }
        }));
        (findViewById(id.videoCall)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                User var10001 = ChatMessageActivity.this.getUser();
                String var2 = var10001 != null ? var10001.getUid() : null;
                chatMessaging.initiateCall(var2, "video");
            }
        }));
        (findViewById(id.message_send)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                String var10001 = ChatMessageActivity.this.getUid();
                EditText var10002 = (EditText) findViewById(id.message_edit);
                chatMessaging.sendTextMessage(var10001, var10002.getText().toString());
                ((EditText) findViewById(id.message_edit)).setText((CharSequence) "");
                ExtenstionsKt.hideKeyboard(ChatMessageActivity.this);
            }
        }));
        findViewById(id.chatView).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                if(var10000.getVisibility()==View.VISIBLE)
                {
                    ExtenstionsKt.gone(var10000);
                }
            }
        });
        ((ImageView) findViewById(id.options)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                ExtenstionsKt.visible((View) var10000);
            }
        }));
        ((TextView) findViewById(id.view_profile)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                Intrinsics.checkExpressionValueIsNotNull(var10000, "option_layout");
                ExtenstionsKt.gone((View) var10000);
              /*  Bundle bundle = new Bundle();
                bundle.putString("uid",uid);
                startActivity(new Intent(ChatMessageActivity.this, AboutActivity.class), bundle);*/
                Intent bundle = new Intent(ChatMessageActivity.this,AboutActivity.class);
                bundle.putExtra("uid", uid);
                startActivity(bundle);
            }
        }));
        ((TextView) findViewById(id.unlike)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                ChatMessageActivity.this.callRemoveFriendApi();
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                ExtenstionsKt.gone((View) var10000);
            }
        }));
        ((TextView) findViewById(id.block)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                blockUserDialog.show(ChatMessageActivity.this.getSupportFragmentManager(), "block");
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                ExtenstionsKt.gone((View) var10000);
            }
        }));
        ((TextView) findViewById(id.report)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                LinearLayout var10000 = (LinearLayout) findViewById(id.option_layout);
                ExtenstionsKt.gone((View) var10000);

                reportDialog.getChatUserData(user);
                reportDialog.show(ChatMessageActivity.this.getSupportFragmentManager(), "report");
            }
        }));
    }

    @SuppressLint({"CheckResult"})
    private final void callRemoveFriendApi() {
        ApiInterface var10000 = Api.Companion.getService();
        User var10003 = this.getCurrentUserData();
        String var7 = var10003 != null ? var10003.getUid() : null;

        String var8 = getResources().getString(R.string.comet_app_id);
        String var5 = getResources().getString(R.string.comet_api_key);

        User var10008 = this.user;
        String var6 = var10008 != null ? var10008.getUid() : null;
        ArrayList userId = new ArrayList();
        userId.add(var10008.getUid());
        RemoveFriendList removeFriendList = new RemoveFriendList(userId);
        Api.Companion.getInstance().deleteFriend("application/json", "application/json", var8, var5, var6, removeFriendList).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeleteFriend>() {
            @Override
            public void accept(DeleteFriend deleteFriend) throws Exception {
              //  Toast.makeText(ChatMessageActivity.this, "Successfully removed", Toast.LENGTH_SHORT).show();
               finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(ChatMessageActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void blockUser(ArrayList uid) {
        CometChat.blockUsers((List) uid, (CallbackListener) (new CallbackListener() {
            public void onSuccess(@NotNull HashMap resultMap) {
                User var10002 = ChatMessageActivity.this.getCurrentUserData();

                String var4 = var10002.getUid();
                User var10003 = ChatMessageActivity.this.getCurrentUserData();

                String var5 = var10003.getName();
                User var10004 = ChatMessageActivity.this.getUser();
                String var6 = var10004 != null ? var10004.getUid() : null;

                User var10005 = ChatMessageActivity.this.getUser();
                String var3 = var10005 != null ? var10005.getName() : null;
                Blockeduser blockeduser = new Blockeduser(var4, var5, var6, var3);
                ChatMessageActivity.this.firebaseHelper.blockedUsers(blockeduser).addOnSuccessListener((OnSuccessListener) (new OnSuccessListener() {
                    public void onSuccess(Object var1) {
                        this.onSuccess((DocumentReference) var1);
                    }

                    public final void onSuccess(DocumentReference it) {
                        CommonsKt.showToast("blocked successfully");
                        ChatMessageActivity.this.onBackPressed();
                    }
                })).addOnFailureListener((OnFailureListener) (new OnFailureListener() {
                    public final void onFailure(@NotNull Exception it) {
                        String var10000 = it.getLocalizedMessage();
                        CommonsKt.showToast(var10000);
                    }
                }));
            }

            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((HashMap) var1);
            }

            public void onError(@NotNull CometChatException e) {
                CommonsKt.showToast(e.getLocalizedMessage().toString());
            }
        }));
    }

    protected void onStart() {
        super.onStart();
        chatMessaging.addCallListener((RelativeLayout) null, (Activity) this, CometConstants.INSTANCE.getCallListener());
        chatMessaging.addMessageListener(CometConstants.INSTANCE.getMessageListener());
    }

    protected void onDestroy() {
        super.onDestroy();
        chatMessaging.removeCallListener(CometConstants.INSTANCE.getCallListener());
        chatMessaging.removeMessageListener(CometConstants.INSTANCE.getMessageListener());
    }

    protected void onResume() {
        super.onResume();
        getAllMessages();
    }

    @Override
    public void userDetails(User user) {
        setUser(user);
        setUserDetails(user);
    }
}
