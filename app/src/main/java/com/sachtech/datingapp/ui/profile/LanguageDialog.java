// LanguageDialog.java
package com.sachtech.datingapp.ui.profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.profile.listener.OnLanguageSelect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;

public final class LanguageDialog extends DialogFragment {
  OnLanguageSelect onLanguageSelect;
   @NotNull
   private String language = "";
   @NotNull
   public ArrayList list;
   private HashMap _$_findViewCache;

   @Nullable
   public final OnLanguageSelect getOnLanguageSelect() {
      return this.onLanguageSelect;
   }

   public final void setOnLanguageSelect(@Nullable OnLanguageSelect var1) {
      this.onLanguageSelect = var1;
   }

   public boolean adjustdisplay() {
      return true;
   }

   @NotNull
   public final String getLanguage() {
      return this.language;
   }

   public final void setLanguage(@NotNull String var1) {
      Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
      this.language = var1;
   }

   @androidx.annotation.Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
      return LayoutInflater.from(getContext()).inflate(R.layout.dialog_language,container,false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      list = new ArrayList();
      TextView languageAdd = view.findViewById(id.langugageAdd);
      EditText language1 = view.findViewById(id.language1);
      EditText language2 = view.findViewById(id.language2);
      languageAdd.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            if (!language1.getText().toString().isEmpty() && !language2.getText().toString().isEmpty()) {
               language = "" + language1.getText().toString()+" "+language2.getText().toString();
               list.add(language1.getText().toString());
               list.add(language2.getText().toString());
               onLanguageSelect.getLanguage(list);
               dismiss();
            } else if (!language1.getText().toString().isEmpty()) {
               language = "" + language1.getText().toString();
               list.add(language);
               onLanguageSelect.getLanguage(list);
               dismiss();
            } else if (!language2.getText().toString().isEmpty()) {
               language = "" + language2.getText().toString();
               list.add(language);
               onLanguageSelect.getLanguage(list);
               dismiss();
            }
         }
      });
   }

   public final void languageSelectListener(@NotNull OnLanguageSelect onLanguageSelect) {
      this.onLanguageSelect = onLanguageSelect;
   }



}
