package com.sachtech.datingapp.ui.chat.activity;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.chat.activity.listener.ActionListener;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



public class BlockUserDialog extends DialogFragment {


   ActionListener actionListener;

   public void onAttach(@NotNull Context context) {
      Intrinsics.checkParameterIsNotNull(context, "context");
      super.onAttach(context);
      this.actionListener = (ActionListener) context;
   }



   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.dialog_block_user, container, false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(view, "view");
      super.onViewCreated(view, savedInstanceState);
      Dialog var10000 = this.getDialog();
      if (var10000 != null) {
         var10000.setCanceledOnTouchOutside(false);
      }

      var10000 = this.getDialog();
      if (var10000 != null) {
         Window var3 = var10000.getWindow();
         if (var3 != null) {
            var3.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
         }
      }

      ((TextView)view.findViewById(id.yes)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
           actionListener.onYesClick();
         }
      }));
      ((TextView)view.findViewById(id.no)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BlockUserDialog.this.dismiss();
         }
      }));
   }


}
