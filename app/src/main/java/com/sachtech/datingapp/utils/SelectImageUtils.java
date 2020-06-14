package com.sachtech.datingapp.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.sachtech.datingapp.ui.home.dialog.ImageChooserDialog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import cool.rishab.gallerydemo.FileUtils;
import kotlin.jvm.internal.Intrinsics;

public final class SelectImageUtils {
   private int CAMERA_REQUEST_CODE;
   private int GALLERY_REQUEST_CODE;
   @Nullable
   private Bitmap bitmap;
   @Nullable
   private String path;
   private boolean hasImage;
   @Nullable
   private OnSelectImageListener selectImageListener;
   @Nullable
   private Dialog dialog;
   @Nullable
   private Uri uri;
   @Nullable
   private ImageView view;
   @NotNull
   private final Context context;

   public final int getCAMERA_REQUEST_CODE() {
      return this.CAMERA_REQUEST_CODE;
   }

   public final void setCAMERA_REQUEST_CODE(int var1) {
      this.CAMERA_REQUEST_CODE = var1;
   }

   public final int getGALLERY_REQUEST_CODE() {
      return this.GALLERY_REQUEST_CODE;
   }

   public final void setGALLERY_REQUEST_CODE(int var1) {
      this.GALLERY_REQUEST_CODE = var1;
   }

   @Nullable
   public final Bitmap getBitmap() {
      return this.bitmap;
   }

   public final void setBitmap(@Nullable Bitmap var1) {
      this.bitmap = var1;
   }

   @Nullable
   public final String getPath() {
      return this.path;
   }

   public final void setPath(@Nullable String var1) {
      this.path = var1;
   }

   public final boolean getHasImage() {
      return this.hasImage;
   }

   public final void setHasImage(boolean var1) {
      this.hasImage = var1;
   }

   @Nullable
   public final OnSelectImageListener getSelectImageListener() {
      return this.selectImageListener;
   }


   @Nullable
   public final Dialog getDialog() {
      return this.dialog;
   }

   public final void setDialog(@Nullable Dialog var1) {
      this.dialog = var1;
   }

   @Nullable
   public final Uri getUri() {
      return this.uri;
   }

   public final void setUri(@Nullable Uri var1) {
      this.uri = var1;
   }

   @Nullable
   public final ImageView getView() {
      return this.view;
   }

   public final void setView(@Nullable ImageView var1) {
      this.view = var1;
   }

   public final void selectImageFromCamera(@NotNull final Fragment activity, @Nullable ImageView verification_image) {
      Intrinsics.checkParameterIsNotNull(activity, "activity");
      this.view = verification_image;
      String[] var10000 = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
      FragmentActivity var10001 = activity.getActivity();
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }
     int per= ActivityCompat.checkSelfPermission(activity.getContext(),var10000[0]);
   if(per== PackageManager.PERMISSION_GRANTED)
   {
      SelectImageUtils.this.openCamera(activity);
   }
   else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      activity.getActivity().requestPermissions(var10000,100);
   }
    /*  Intrinsics.checkExpressionValueIsNotNull(var10001, "activity.activity!!");
      PermssionKt.withPermissions(var10000, (Activity)var10001, (Function0)(new Function0() {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke() {
            this.invoke();
            return Unit.INSTANCE;
         }

         public final void invoke() {
            SelectImageUtils.this.openCamera(activity);
         }
      }));*/
   }

   private final void openCamera(Fragment activity) {
      Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
      activity.startActivityForResult(intent, this.CAMERA_REQUEST_CODE);
   }

   public final void selectImageFromGallery(@NotNull final Fragment activity) {
      String[] var10000 = new String[]{"android.permission.READ_EXTERNAL_STORAGE"};
      FragmentActivity var10001 = activity.getActivity();
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }
      int per= ActivityCompat.checkSelfPermission(activity.getContext(),var10000[0]);
      if(per== PackageManager.PERMISSION_GRANTED)
      {
         SelectImageUtils.this.openGallery(activity);
      }
      else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         activity.getActivity().requestPermissions(var10000,100);
      }
    /*  Intrinsics.checkExpressionValueIsNotNull(var10001, "activity.activity!!");
      PermssionKt.withPermissions(var10000, (Activity)var10001, (Function0)(new Function0() {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke() {
            this.invoke();
            return Unit.INSTANCE;
         }

         public final void invoke() {
            SelectImageUtils.this.openGallery(activity);
         }
      }));*/
   }

   private final void openGallery(Fragment activity) {
      Intent intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
      activity.startActivityForResult(intent, this.GALLERY_REQUEST_CODE);
   }

   public final void openChooser(@NotNull Fragment activity, @NotNull ImageView image) {
      this.view = image;
      this.createDialog(activity);
   }

   @SuppressLint("WrongConstant")
   private final void createDialog(Fragment activity) {
      ImageChooserDialog dialog = new ImageChooserDialog(activity);
      dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
      FragmentManager var10001 = activity.getFragmentManager();
      dialog.show(var10001, "image");
   }

   public final void selectImage(@NotNull OnSelectImageListener selectListener) {
      this.selectImageListener = selectListener;
   }

   public final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      try {
         if (data != null) {
            FileUtils var10001;
            Context var10002;
            Uri var10003;
            if (requestCode == this.CAMERA_REQUEST_CODE && resultCode == -1) {
               Bundle var10000 = data.getExtras();
               Object var6 = var10000 != null ? var10000.get("data") : null;

               Bitmap bitmap = (Bitmap)var6;
               this.uri = this.getImageUri(this.context, bitmap);
               var10001 = FileUtils.INSTANCE;
               var10002 = this.context;
               var10003 = this.uri;
               if (var10003 == null) {
                  Intrinsics.throwNpe();
               }

               this.path = var10001.getPath(var10002, var10003);
            } else if (requestCode == this.GALLERY_REQUEST_CODE && resultCode == -1) {
               this.uri = data.getData();
               var10001 = FileUtils.INSTANCE;
               var10002 = this.context;
               var10003 = this.uri;


               this.path = var10001.getPath(var10002, var10003);
            }

           OnSelectImageListener var7 = this.selectImageListener;

            Uri var9 = this.uri;

            ImageView var10 = this.view;
            var7.onImageSelected(var9, var10);
         }

         if (this.dialog != null) {
            Dialog var8 = this.dialog;
            if (var8 != null) {
               var8.dismiss();
            }
         }
      } catch (Exception var5) {
         Toast.makeText(context, var5.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
      }

   }

   private final Uri getImageUri(Context context, Bitmap photo) {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      photo.compress(CompressFormat.JPEG, 100, (OutputStream)bytes);
      String path = Media.insertImage(context.getContentResolver(), photo, "Title", (String)null);
      Uri var10000 = Uri.parse(path);
      return var10000;
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   public SelectImageUtils(@NotNull Context context) {
      super();
      this.context = context;
      this.CAMERA_REQUEST_CODE = 10;
      this.GALLERY_REQUEST_CODE = 20;
   }


}
