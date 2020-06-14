package com.sachtech.datingapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.cometChat.listener.OnCometLogin;
import com.sachtech.datingapp.data.ChatUser;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.location.MyLocationLIstener;
import com.sachtech.datingapp.location.MyLocationLIstener.OnLocationChanged;
import com.sachtech.datingapp.networking.FirebaseHelper;
import com.sachtech.datingapp.ui.home.HomeActivity;
import com.sachtech.datingapp.ui.login.fragment.ProfileImageFragment;
import com.sachtech.datingapp.ui.login.fragment.SignInFragment;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import kotlin.text.Charsets;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.sachtech.datingapp.utils.PrefDataKt.getUid;


public final class LoginActivity extends AppCompatActivity implements OnLocationChanged, OnCometLogin {

    private String location = "";
    private double latitude;
    private double longitude;

    public void onCometLoginSuccess(@Nullable User p0) {
        String var10002 = p0 != null ? p0.getAvatar() : null;
        String var10003 = p0 != null ? p0.getUid() : null;
        String var10004 = p0 != null ? p0.getName() : null;

        String var10005 = p0 != null ? p0.getLink() : null;

        String var3 = var10005;
        String key$iv = var10004;
        String var5 = var10003;
        String var6 = var10002;
        ChatUser var10000 = new ChatUser(var5, key$iv, var3, var6);
        ChatUser chatUser = var10000;
        Preferences var7 = Preferences.INSTANCE;
        Preferences.INSTANCE.setPref(PrefKeys.COMETUSER,new Gson().toJson(chatUser));
        CommonsKt.showToast(chatUser.getUid());
    }

    public void onCometLoginFailure(@NotNull String localizedMessage) {
        CommonsKt.showToast(localizedMessage);
    }

    @NotNull
    public final String getLocation() {
        return this.location;
    }

    public final void setLocation(@NotNull String var1) {
        this.location = var1;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double var1) {
        this.latitude = var1;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(double var1) {
        this.longitude = var1;
    }

    @NotNull
    public final FirebaseHelper getFirebaseHelper() {
       return new FirebaseHelper();
    }

    @NotNull
    public final MyLocationLIstener getMyLocationListener() {
       return new MyLocationLIstener(this,this::onLocationUpdated);
    }

    public void onLocationUpdated(@NotNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        Preferences.INSTANCE.setPref("latitude", String.valueOf(this.latitude));
        Preferences.INSTANCE.setPref("longitude", String.valueOf(this.longitude));
        this.getLocationFromLatLng(this.latitude, this.longitude);
    }

    private final void getLocationFromLatLng(double lat, double var3) {
        Geocoder geocoder = new Geocoder((Context)this);
        Preferences var10000;
        String var10001;
        try {
            List addresses = geocoder.getFromLocation(lat, var3, 1);
            if (addresses.size() > 0) {
                Address var9 = (Address)addresses.get(0);
                String address = var9 != null ? var9.getCountryName() : null;
                Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getLOCATION(), address);
            }
        } catch (Exception var8) {
            Preferences.INSTANCE.setPref(PrefKeys.INSTANCE.getLOCATION(), "India");

        }

    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        FirebaseInstanceId fcmId = FirebaseInstanceId.getInstance();
        fcmId.getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
            String fcm=instanceIdResult.getToken();
            Preferences.INSTANCE.setPref( PrefKeys.INSTANCE.getFCM(), fcm);
            }
        });
        this.getUserStatus();
        String var2 = getUid();
      /*  if(var2!="")
        {
            new CometChatUser().getLoggedInUser();
        }*/
printHashKey(this);
        (new Handler()).postDelayed((Runnable)(new Runnable() {
            public final void run() {
             int permission=ActivityCompat.checkSelfPermission(LoginActivity.this,"android.permission.ACCESS_FINE_LOCATION");
               if(permission==PERMISSION_GRANTED)
               {
                   LoginActivity.this.getCurrentLocation();
               }
               else {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"},1000);
                   }
               }

            }
        }), 3000L);
    }

    public final void printHashKey(@NotNull Context pContext) {

        try {
            PackageInfo var10000 = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            PackageInfo info = var10000;
            Signature[] var5 = info.signatures;
            int var6 = var5.length;

            for(int var4 = 0; var4 < var6; ++var4) {
                Signature signature = var5[var4];
                MessageDigest var13 = MessageDigest.getInstance("SHA");
                MessageDigest md = var13;
                md.update(signature.toByteArray());
                byte[] var14 = Base64.encode(md.digest(), 0);
                byte[] var9 = var14;
                boolean var10 = false;
                String hashKey = new String(var9, Charsets.UTF_8);
                Log.i("hash", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException var11) {
            Log.e("exc", "printHashKey()", (Throwable)var11);
        } catch (Exception var12) {
            Log.e("exc", "printHashKey()", (Throwable)var12);
        }

    }

    private final void getUserStatus() {
        Preferences preferences = Preferences.INSTANCE;
        PrefKeys prefKeys = PrefKeys.INSTANCE;
        String userString=preferences.getPrefs().getString(prefKeys.getUSER(),"");
        com.sachtech.datingapp.data.User user=new Gson().fromJson(userString, com.sachtech.datingapp.data.User.class);
       if(user==null&&getUid()=="")
       {
           BaseFragment.replaceFragment(LoginActivity.this,R.id.login_container, (Fragment)(new SignInFragment()), (Bundle)null);
       }
       else if (user == null && getUid() != "") {
           //navigateToFragment<ProfileImageFragment>()
           BaseFragment.replaceFragment(LoginActivity.this,R.id.login_container,new ProfileImageFragment(),null);
       } else {
           startActivity(new Intent(this, HomeActivity.class));
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000)
        {
            LoginActivity.this.getLocation();
        }
    }

    private final void getCurrentLocation() {
        this.getMyLocationListener().init();
    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1)
        {
            super.onBackPressed();
        }
        else finish();
    }
}
