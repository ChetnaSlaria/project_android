package com.sachtech.datingapp.ui.home.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.utils.SelectImageUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class ImageChooserDialog extends DialogFragment {
   @NotNull
   private final Fragment fragment;

   @NotNull
   public final SelectImageUtils getSelectImageUtils() {
      return new SelectImageUtils(getContext());
   }

   public boolean adjustdisplay() {
      return false;
   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.gallery_dialog,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      Dialog var10000 = this.getDialog();
      if (var10000 != null) {
         Window var3 = var10000.getWindow();
         if (var3 != null) {
            var3.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }

      TextView var4 = (TextView)view.findViewById(id.dialog_text_camera);
      var4.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ImageChooserDialog.this.getSelectImageUtils().selectImageFromCamera(ImageChooserDialog.this.getFragment(), (ImageView)null);
            ImageChooserDialog.this.dismiss();
         }
      }));
      var4 = (TextView)view.findViewById(id.dialog_text_gallery);
      var4.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            ImageChooserDialog.this.getSelectImageUtils().selectImageFromGallery(ImageChooserDialog.this.getFragment());
            ImageChooserDialog.this.dismiss();
         }
      }));
   }

   @NotNull
   public final Fragment getFragment() {
      return this.fragment;
   }

   public ImageChooserDialog(@NotNull Fragment fragment) {
      this.fragment = fragment;
   }
   
}
