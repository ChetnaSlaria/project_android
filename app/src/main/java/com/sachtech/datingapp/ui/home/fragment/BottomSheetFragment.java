package com.sachtech.datingapp.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.customcountrypicker.CountryPickerDialog;
import com.sachtech.datingapp.customcountrypicker.UpdateListener;
import com.sachtech.datingapp.data.UserPreference;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.ui.profile.LanguageDialog;
import com.sachtech.datingapp.ui.profile.PrefAdapter;
import com.sachtech.datingapp.ui.profile.ProfileItemFragment;
import com.sachtech.datingapp.ui.profile.listener.EditInformation;
import com.sachtech.datingapp.ui.profile.listener.OnLanguageSelect;
import com.sachtech.datingapp.ui.profile.listener.OnPrefChange;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.PrefType;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.ProfileItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;


public final class BottomSheetFragment extends BottomSheetDialogFragment implements OnLanguageSelect, OnPrefChange, EditInformation {
   private boolean stateChanged;
   @Nullable
   private CountryPickerDialog originDialog;
   @Nullable
   private CountryPickerDialog nationalityDialog;
   @Nullable
   private UserPreference userPreference;
   private long dob;
   private int age;

   public void selectedPref(@NotNull String pref_type, @NotNull ArrayList arraylist) {
      if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getMARITALSTATUS())) {
         this.getMarital().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getMARRIAGEPLANS())) {
         this.getMarriageplan().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getLIVINGARRANGEMENTSAFTERMARRIAGE())) {
         this.getArrangements().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getPRAYS())) {
         this.getPrays().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getSECT())) {
         this.getCaste().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getQURAN())) {
         this.getCompletedQuran().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getCURRENTLIVINGARRANGEMENT())) {
         this.getLivingArrangements().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getHALAL())) {
         this.getHalal().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getREVERT())) {
         this.getRevert().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getBUILD())) {
         this.getBuild().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getBEARD())) {
         this.getBeard().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getWEAR())) {
         this.getWear().addAll((Collection)arraylist);
      } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getPROFESSION())) {
         this.getProfession().addAll((Collection)arraylist);
      }

   }

   public final boolean getStateChanged() {
      return this.stateChanged;
   }

   public final void setStateChanged(boolean var1) {
      this.stateChanged = var1;
   }

   @Nullable
   public final CountryPickerDialog getOriginDialog() {
      return this.originDialog;
   }

   public final void setOriginDialog(@Nullable CountryPickerDialog var1) {
      this.originDialog = var1;
   }

   @Nullable
   public final CountryPickerDialog getNationalityDialog() {
      return this.nationalityDialog;
   }

   public final void setNationalityDialog(@Nullable CountryPickerDialog var1) {
      this.nationalityDialog = var1;
   }

   public final long getDob() {
      return this.dob;
   }

   public final int getAge() {
      return this.age;
   }

   @NotNull
   public final ArrayList getLanguageList() {
     return new ArrayList<String>();
   }

   @Nullable
   public final UserPreference getUserPreference() {
      return this.userPreference;
   }

   public final void setUserPreference(@Nullable UserPreference var1) {
      this.userPreference = var1;
   }

   @NotNull
   public final ArrayList getPrays() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getCompletedQuran() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getArrangements() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getMarital() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getMarriageplan() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getCaste() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getLivingArrangements() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getBuild() {
      return new ArrayList<String>();
   }

   @NotNull
   public final ArrayList getHalal() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getRevert() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getBeard() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getWear() {
      return new ArrayList<String>();

   }

   @NotNull
   public final ArrayList getProfession() {
      return new ArrayList<String>();

   }

   public void getLanguage(@Nullable ArrayList language) {
      if (language != null) {
         this.getLanguageList().addAll((Collection)language);
      }

      TextView var10000 = (TextView)view.findViewById(id.pref_languages);
      var10000.setText(joinToString(language));
   }

   View view;
   @androidx.annotation.Nullable
   @Nullable
   public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @androidx.annotation.Nullable @Nullable ViewGroup container, @androidx.annotation.Nullable @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(inflater, "inflater");
      return inflater.inflate(R.layout.fragment_partner_preference, container, false);
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      this.view=view;
      ((ImageView)view.findViewById(id.prefCancel)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment.this.dismiss();
         }
      }));
      CountryPickerDialog var10001 = new CountryPickerDialog(getContext());
      this.nationalityDialog = var10001;
      this.originDialog = var10001;
      if(Preferences.INSTANCE.getPrefs().getBoolean(PrefKeys.INSTANCE.isDataSaved(),false)==true)
      {
          getValues();
      }
      ((EditText)view.findViewById(id.pref_minAge)).addTextChangedListener((TextWatcher)(new TextWatcher() {
         public void afterTextChanged(@Nullable Editable s) {
         }

         public void beforeTextChanged(@Nullable CharSequence s, int start, int count, int after) {
         }

         public void onTextChanged(@Nullable CharSequence s, int start, int before, int count) {
            EditText var10000 = (EditText)view.findViewById(id.pref_minAge);
            Editable var8 = var10000.getText();
            CharSequence var5 = (CharSequence)var8;
            boolean var6 = false;
            if (var5.length() > 0) {
               String var7 = String.valueOf(s);
               var6 = false;
               if (Integer.parseInt(var7) < 18) {
                  CommonsKt.showToast("User must be 18 or above");
               }
            }

         }
      }));
      ((EditText)view.findViewById(id.pref_maxAge)).addTextChangedListener((TextWatcher)(new TextWatcher() {
         public void afterTextChanged(@Nullable Editable s) {
         }

         public void beforeTextChanged(@Nullable CharSequence s, int start, int count, int after) {
         }

         public void onTextChanged(@Nullable CharSequence s, int start, int before, int count) {
            EditText var10000 = (EditText)view.findViewById(id.pref_maxAge);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "pref_maxAge");
            Editable var8 = var10000.getText();
            Intrinsics.checkExpressionValueIsNotNull(var8, "pref_maxAge.text");
            CharSequence var5 = (CharSequence)var8;
            boolean var6 = false;
            if (var5.length() > 0) {
               String var7 = String.valueOf(s);
               var6 = false;
               if (Integer.parseInt(var7) < 18) {
                  CommonsKt.showToast("User must be 18 or above");
               }
            }

         }
      }));
      this.setOnClick();
      this.setValues();
   }

   private final void setValues() {
      TextView var10000 = (TextView)view.findViewById(id.pref_complexion);
      var10000.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getCOLORCOMPLEXION(),""));
    TextView  education = (TextView)view.findViewById(id.pref_education);
    education.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPREF_EDUCATION(),""));
      TextView eyecolor = (TextView)view.findViewById(id.pref_eyes_color);
       eyecolor.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getEYECOLOR(),""));
     TextView hairColor = (TextView)view.findViewById(id.pref_hair_color);
       hairColor.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getHAIRCOLOR(),""));
   }

   private final void getValues() {
      Preferences var1 = Preferences.INSTANCE;
      PrefKeys var10001 = PrefKeys.INSTANCE;
      Intrinsics.checkExpressionValueIsNotNull(var10001, "PrefKeys.INSTANCE");
      String var14 = var10001.getUSERPREFS();
      String key$iv = var14;
      Gson var10000 = new Gson();
      String userPref = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSERPREFS(), "");
      userPreference = var10000.fromJson(userPref, UserPreference.class);
      UserPreference var16;
      List var17;
      EditText prfHeight = (EditText) view.findViewById(id.pref_height);
      if (userPreference != null) {
         //  if(userPreference.getHeight()!=null)
         prfHeight.setText(userPreference.getHeight());
         EditText prefMinAge = (EditText) view.findViewById(id.pref_minAge);
         prefMinAge.setText((CharSequence) (userPreference.getMinAge()));
         EditText maxAge = (EditText) view.findViewById(id.pref_maxAge);
         maxAge.setText(userPreference.getMaxAge());
         TextView prefNationality = (TextView) view.findViewById(id.pref_nationality);
         prefNationality.setText(joinToString((ArrayList) userPreference.getNationalty()));

         EditText prefName = (EditText) view.findViewById(id.pref_name);
         prefName.setText((CharSequence) (userPreference.getName()));
         TextView prefOrigin = (TextView) view.findViewById(id.pref_origin);
         prefOrigin.setText(joinToString((ArrayList) userPreference.getOrigin()));
         TextView prefLanguage = (TextView) view.findViewById(id.pref_languages);
         prefLanguage.setText(joinToString(userPreference.getLanguage()));

         TextView prefHalal = (TextView) view.findViewById(id.pref_halal);
         prefHalal.setText(userPreference.getHalal());
         TextView prefQuran = (TextView) view.findViewById(id.pref_quran);
         prefQuran.setText((CharSequence) (userPreference.getCompletedQuran()));
         TextView prefPrays = (TextView) view.findViewById(id.pref_prays);
         prefPrays.setText((CharSequence) (userPreference.getPrays()));
         TextView prefRevert = (TextView) view.findViewById(id.pref_revert);
         prefRevert.setText((CharSequence) (userPreference.getRevert()));
         TextView prefBeard = (TextView) view.findViewById(id.pref_beard);
         prefBeard.setText((CharSequence) (userPreference.getBeard()));
         TextView prefWear = (TextView) view.findViewById(id.pref_wear);
         prefWear.setText((CharSequence) (userPreference.getWears()));
         TextView livingAfterMarriage = (TextView) view.findViewById(id.pref_living_after_marriage);
         livingAfterMarriage.setText((CharSequence) (userPreference.getLivingArrangementAfterMarriage()));
         TextView marriagePlans = (TextView) view.findViewById(id.pref_marriage_plans);
         marriagePlans.setText((CharSequence) (userPreference.getMarriagePlan()));
         TextView profession = (TextView) view.findViewById(id.pref_profession);
         profession.setText((CharSequence) (userPreference.getProfession()));
         TextView complexion = (TextView) view.findViewById(id.pref_complexion);
         complexion.setText((CharSequence) (userPreference.getComplexion()));
         TextView eyeColor = (TextView) view.findViewById(id.pref_eyes_color);
         eyeColor.setText((CharSequence) (userPreference.getEyeColor()));
         TextView hairColor = (TextView) view.findViewById(id.pref_hair_color);
         hairColor.setText((CharSequence) (userPreference.getHairColor()));
         TextView prefEducation = (TextView) view.findViewById(id.pref_education);
         prefEducation.setText((CharSequence) (userPreference.getEducation()));
      }
   }
   private final void setOnClick() {
      ((TextView)view.findViewById(id.preference_done)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            EditText var10003 = (EditText)view.findViewById(id.pref_name);
            String var10 = var10003.getText().toString();
            EditText var10004 = (EditText)view.findViewById(id.pref_minAge);
            String var11 = var10004.getText().toString();
            EditText var10005 = (EditText)view.findViewById(id.pref_maxAge);
            String var12 = var10005.getText().toString();
            TextView var10006 = (TextView)view.findViewById(id.pref_nationality);
            List var14 = Arrays.asList(var10006.getText().toString().split(","));
            TextView var10007 = (TextView)view.findViewById(id.pref_origin);
            List var16 =Arrays.asList(var10007.getText().toString().split(","));
            ArrayList var10008 = BottomSheetFragment.this.getLanguageList();
            ArrayList var10009 = BottomSheetFragment.this.getCaste();
            ArrayList var10010 = BottomSheetFragment.this.getMarital();
            ArrayList var10011 = BottomSheetFragment.this.getLivingArrangements();
            EditText var10012 = (EditText)view.findViewById(id.pref_height);
            String var17 = var10012.getText().toString();
            ArrayList var10013 = BottomSheetFragment.this.getBuild();
            TextView var10014 = (TextView)view.findViewById(id.pref_complexion);
            String var18 = var10014.getText().toString();
            TextView var10015 = (TextView)view.findViewById(id.pref_eyes_color);
            String var19 = var10015.getText().toString();
            TextView var10016 = (TextView)view.findViewById(id.pref_hair_color);
            String var20 = var10016.getText().toString();
            TextView var10017 = (TextView)view.findViewById(id.pref_education);
            String var21 = var10017.getText().toString();
            TextView var10018 = (TextView)view.findViewById(id.pref_profession);
            String var22 = var10018.getText().toString();
            TextView var10019 = (TextView)view.findViewById(id.pref_marriage_plans);
            String var23 = var10019.getText().toString();
            TextView var10020 = (TextView)view.findViewById(id.pref_living_after_marriage);
            String var24 = var10020.getText().toString();
            TextView var10021 = (TextView)view.findViewById(id.pref_wear);
            String var25 = var10021.getText().toString();
            TextView var10022 = (TextView)view.findViewById(id.pref_beard);
            String var26 = var10022.getText().toString();
            TextView var10023 = (TextView)view.findViewById(id.pref_revert);
            String var27 = var10023.getText().toString();
            TextView var10024 = (TextView)view.findViewById(id.pref_halal);
            String var28 = var10024.getText().toString();
            TextView var10025 = (TextView)view.findViewById(id.pref_prays);
            String var29 = var10025.getText().toString();
            TextView var10026 = (TextView)view.findViewById(id.pref_quran);
            var10000.setUserPreference(new UserPreference(var10, var11, var12, var14, var16, var10008, var10009, var10010, var10011, var17, var10013, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27, var28, var29, var10026.getText().toString()));
            Preferences this_$iv = Preferences.INSTANCE;
            PrefKeys var6 = PrefKeys.INSTANCE;
            String var7 = var6.getUSERPREFS();
            String key$iv = var7;
            Object obj$iv = BottomSheetFragment.this.getUserPreference();
            if (CommonsKt.isNull(obj$iv)) {
               this_$iv.clearPrefKey(key$iv);
            } else {
               this_$iv.setPref(key$iv, (new Gson()).toJson(obj$iv));
            }

            Preferences var8 = Preferences.INSTANCE;
            PrefKeys var10001 = PrefKeys.INSTANCE;
            String var9 = var10001.isDataSaved();
            var8.setPref(var9, true);
           dismiss();
         }
      }));
      ((TextView)view.findViewById(id.pref_nationality)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            CountryPickerDialog var10000 = BottomSheetFragment.this.getNationalityDialog();
            if (var10000 != null) {
               var10000.show();
            }

            BottomSheetFragment.this.getNationalityFromPicker();
         }
      }));
      ((TextView)view.findViewById(id.pref_languages)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment.this.openLanguageDialog();
         }
      }));
      ((TextView)view.findViewById(id.pref_education)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getEDUCATION();
            TextView var10002 = (TextView)view.findViewById(id.pref_education);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_profession)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getPROFESSION();
            TextView var10002 = (TextView)view.findViewById(id.pref_profession);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_origin)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            CountryPickerDialog var10000 = BottomSheetFragment.this.getOriginDialog();
            if (var10000 != null) {
               var10000.show();
            }

            BottomSheetFragment.this.getOriginFromPicker();
         }
      }));
      ((TextView)view.findViewById(id.pref_eyes_color)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getEYECOLOR();
            TextView var10002 = (TextView)view.findViewById(id.pref_eyes_color);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_hair_color)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getHAIRCOLOR();
            TextView var10002 = (TextView)view.findViewById(id.pref_hair_color);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_complexion)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getCOLORCOMPLEXION();
            TextView var10002 = (TextView)view.findViewById(id.pref_complexion);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_marriage_plans)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getMARRIAGEPLANS();
            TextView var10002 = (TextView)view.findViewById(id.pref_marriage_plans);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_living_after_marriage)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE();
            TextView var10002 = (TextView)view.findViewById(id.pref_living_after_marriage);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_prays)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getPRAYS();
            TextView var10002 = (TextView)view.findViewById(id.pref_prays);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_quran)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getQURAN();
            TextView var10002 = (TextView)view.findViewById(id.pref_quran);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_halal)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getHALAL();
            TextView var10002 = (TextView)view.findViewById(id.pref_halal);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_revert)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getREVERT();
            TextView var10002 = (TextView)view.findViewById(id.pref_revert);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_beard)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getBEARD();
            TextView var10002 = (TextView)view.findViewById(id.pref_beard);
            var10000.openDialog(var10001, var10002);
         }
      }));
      ((TextView)view.findViewById(id.pref_wear)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            BottomSheetFragment var10000 = BottomSheetFragment.this;
            String var10001 = ProfileItem.INSTANCE.getWEAR();
            TextView var10002 = (TextView)view.findViewById(id.pref_wear);
            var10000.openDialog(var10001, var10002);
         }
      }));
      this.setMaritalStatusClick();
      this.setBuildClick();
      this.setCasteClick();
      this.setLivingArrangementClick();
   }

   private final void getOriginFromPicker() {
      CountryPickerDialog var10000 = this.originDialog;
      if (var10000 != null) {
         var10000.updateListener((UpdateListener)(new UpdateListener() {
            public final void onUpdateClick(ArrayList it) {
               TextView var10000 = (TextView)view.findViewById(id.pref_origin);
               var10000.setText((joinToString(it)));
            }
         }));
      }

   }

   private final void setMaritalStatusClick() {
      ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Single", "Divorced", "Widowed", "Annulled"});
      RecyclerView var10000 = (RecyclerView)view.findViewById(id.marital_status_recycler);
      Context var10004 = this.getContext();

      String var10005 = PrefType.INSTANCE.getMARITALSTATUS();
      UserPreference var10006 = this.userPreference;
      ArrayList var2 = var10006 != null ? var10006.getMarital():null;

      PrefAdapter var10001 = new PrefAdapter(arraylist, var10004, var10005, var2, (OnPrefChange)this);
      var10000.setAdapter((Adapter)var10001);
   }

   private final void setCasteClick() {
      ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Sunni", "Shia", "Other"});
      RecyclerView var10000 = (RecyclerView)view.findViewById(id.caste_group);

      Context var10004 = this.getContext();
      if (var10004 == null) {
         Intrinsics.throwNpe();
      }

      String var10005 = PrefType.INSTANCE.getSECT();
      UserPreference var10006 = this.userPreference;
      ArrayList var2 = var10006 != null ? var10006.getSect():null;


      PrefAdapter var10001 = new PrefAdapter(arraylist, var10004, var10005, var2, (OnPrefChange)this);
      var10000.setAdapter((Adapter)var10001);
   }

   private final void setLivingArrangementClick() {
      ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Living Alone", "Living with Family", "Living with Friends", "Other"});
      RecyclerView var10000 = (RecyclerView)view.findViewById(id.living_arrangement_group);
      Context var10004 = this.getContext();
      if (var10004 == null) {
         Intrinsics.throwNpe();
      }

      String var10005 = PrefType.INSTANCE.getCURRENTLIVINGARRANGEMENT();
      ArrayList var2 = userPreference != null ? userPreference.getLivingArrangement():null;
      PrefAdapter var10001 = new PrefAdapter(arraylist, var10004, var10005, var2, (OnPrefChange)this);
      var10000.setAdapter((Adapter)var10001);
   }

   private final void setBuildClick() {
      ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Athletic", "Large", "Medium", "Slim"});
      RecyclerView var10000 = (RecyclerView)view.findViewById(id.build_bar);
      Context var10004 = this.getContext();

      String var10005 = PrefType.INSTANCE.getBUILD();
      UserPreference var10006 = this.userPreference;
      ArrayList var2 = var10006 != null ? var10006.getBuild():null;
      PrefAdapter var10001 = new PrefAdapter(arraylist,var10004,var10005,var2,this::selectedPref);
      var10000.setAdapter((Adapter)var10001);
   }

   private final void openDialog(String item, TextView textView) {
      Bundle bundle = new Bundle();
      bundle.putString("item_name", item);
      bundle.putString("item_type", "multiple");
      ProfileItemFragment var5 = new ProfileItemFragment();
      boolean var6 = false;
      boolean var7 = false;
      var5.infoSelectListener((EditInformation)this, textView);
      var5.setArguments(bundle);
      FragmentManager var10001 = this.getFragmentManager();
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }

      var5.show(var10001, "profileItem");
   }

   private final void openLanguageDialog() {
      LanguageDialog var2 = new LanguageDialog();
      boolean var3 = false;
      boolean var4 = false;
      var2.languageSelectListener((OnLanguageSelect)this);
      FragmentManager var10001 = this.getFragmentManager();
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }

      var2.show(var10001, "Language");
   }

   private final void getNationalityFromPicker() {
      CountryPickerDialog var10000 = this.nationalityDialog;
      if (var10000 != null) {
         var10000.updateListener((UpdateListener)(new UpdateListener() {
            public final void onUpdateClick(ArrayList it) {
               TextView var10000 = (TextView)view.findViewById(id.pref_nationality);
               var10000.setText(joinToString(it));
            }
         }));
      }

   }

   public String joinToString(ArrayList arrayList) {
      StringBuilder builder = new StringBuilder("");
      for (int i = 0; i < arrayList.size(); i++) {
         builder.append(arrayList).append(",");
      }
      String strList = builder.toString();
      if (strList.length() > 0)
         strList = strList.substring(0, strList.length() - 1);
      return strList;
   }
   public void editinformation(@NotNull String info, @NotNull TextView textView) {
      textView.setText((CharSequence)info);
   }

}
