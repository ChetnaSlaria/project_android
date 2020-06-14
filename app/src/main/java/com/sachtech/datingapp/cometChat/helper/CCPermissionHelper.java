package com.sachtech.datingapp.cometChat.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.core.app.ActivityCompat;
import com.cometchat.pro.helpers.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class CCPermissionHelper {
   private static final String TAG = CCPermissionHelper.class.getSimpleName();
   @NotNull
   private static final String REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
   @NotNull
   private static final String REQUEST_PERMISSION_RECORD_AUDIO = "android.permission.RECORD_AUDIO";
   @NotNull
   private static final String REQUEST_PERMISSION_CAMERA = "android.permission.CAMERA";
   @NotNull
   private static final String REQUEST_PERMISSION_HARDWARE_LOCATION = "android.permission.LOCATION_HARDWARE";
   @NotNull
   private static final String REQUEST_PERMISSION_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
   @NotNull
   private static final String REQUEST_PERMISSION_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
   @NotNull
   private static final String REQUEST_PERMISSION_READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
 //  public static final CCPermissionHelper.Companion Companion = new CCPermissionHelper.Companion((DefaultConstructorMarker)null);

      @NotNull
      public final String getREQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE() {
         return CCPermissionHelper.REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_RECORD_AUDIO() {
         return CCPermissionHelper.REQUEST_PERMISSION_RECORD_AUDIO;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_CAMERA() {
         return CCPermissionHelper.REQUEST_PERMISSION_CAMERA;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_HARDWARE_LOCATION() {
         return CCPermissionHelper.REQUEST_PERMISSION_HARDWARE_LOCATION;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_COARSE_LOCATION() {
         return CCPermissionHelper.REQUEST_PERMISSION_COARSE_LOCATION;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_FINE_LOCATION() {
         return CCPermissionHelper.REQUEST_PERMISSION_FINE_LOCATION;
      }

      @NotNull
      public final String getREQUEST_PERMISSION_READ_PHONE_STATE() {
         return CCPermissionHelper.REQUEST_PERMISSION_READ_PHONE_STATE;
      }

      public final boolean hasPermissions(@Nullable Context context, @NotNull String... permissions) {
         Intrinsics.checkParameterIsNotNull(permissions, "permissions");
         if (VERSION.SDK_INT >= 23 && context != null) {
            String[] var5 = permissions;
            int var6 = permissions.length;

            for(int var4 = 0; var4 < var6; ++var4) {
               String permission = var5[var4];
               Logger.error(CCPermissionHelper.TAG, " hasPermissions() : Permission : " + permission + "checkSelfPermission : " + ActivityCompat.checkSelfPermission(context, permission));
               if (ActivityCompat.checkSelfPermission(context, permission) != 0) {
                  return false;
               }
            }
         }

         return true;
      }

      public final void requestPermissions(@NotNull Activity activity, @NotNull String[] permission, int requestCode) {
         Intrinsics.checkParameterIsNotNull(activity, "activity");
         Intrinsics.checkParameterIsNotNull(permission, "permission");
         ActivityCompat.requestPermissions(activity, permission, requestCode);
      }


      // $FF: synthetic method
     /*// public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }*/
   }

