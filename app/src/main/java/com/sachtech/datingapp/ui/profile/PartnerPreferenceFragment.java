package com.sachtech.datingapp.ui.profile;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.gson.Gson;
import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.customcountrypicker.CountryPickerDialog;
import com.sachtech.datingapp.customcountrypicker.UpdateListener;
import com.sachtech.datingapp.data.UserPreference;
import com.sachtech.datingapp.extensions.CommonsKt;
import com.sachtech.datingapp.ui.home.listener.OnFragmentChange;
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

import static org.jitsi.meet.sdk.JitsiMeetActivityDelegate.onBackPressed;


public final class PartnerPreferenceFragment extends Fragment implements OnLanguageSelect, OnPrefChange, EditInformation {
    private boolean stateChanged;
    @Nullable
    private CountryPickerDialog originDialog;
    @Nullable
    private CountryPickerDialog nationalityDialog;
    private final long dob=0;
    private final int age=0;
    @Nullable
    private UserPreference userPreference;
    private OnFragmentChange onFragmentChange;

    public void selectedPref(@NotNull String pref_type, @NotNull ArrayList arraylist) {
        Intrinsics.checkParameterIsNotNull(pref_type, "pref_type");
        Intrinsics.checkParameterIsNotNull(arraylist, "arraylist");
        if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getMARITALSTATUS())) {
            this.getMarital().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getMARRIAGEPLANS())) {
            this.getMarriageplan().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getLIVINGARRANGEMENTSAFTERMARRIAGE())) {
            this.getArrangements().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getPRAYS())) {
            this.getPrays().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getSECT())) {
            this.getCaste().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getQURAN())) {
            this.getCompletedQuran().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getCURRENTLIVINGARRANGEMENT())) {
            this.getLivingArrangements().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getHALAL())) {
            this.getHalal().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getREVERT())) {
            this.getRevert().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getBUILD())) {
            this.getBuild().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getBEARD())) {
            this.getBeard().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getWEAR())) {
            this.getWear().addAll((Collection) arraylist);
        } else if (Intrinsics.areEqual(pref_type, PrefType.INSTANCE.getPROFESSION())) {
            this.getProfession().addAll((Collection) arraylist);
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
        return new ArrayList();
    }



    @NotNull
    public final ArrayList getPrays() {
        return new ArrayList();
    }

    @NotNull
    public final ArrayList getCompletedQuran() {
        return new ArrayList();
    }

    @NotNull
    public final ArrayList getArrangements() {
        return new ArrayList();
    }

    @NotNull
    public final ArrayList getMarital() {
        return new ArrayList();
    }

    @NotNull
    public final ArrayList getMarriageplan() {
       return new ArrayList();
    }

    @NotNull
    public final ArrayList getCaste() {
        return new ArrayList();
    }

    @NotNull
    public final ArrayList getLivingArrangements() {
       return new ArrayList();
    }

    @NotNull
    public final ArrayList getBuild() {
     return new ArrayList();
    }

    @NotNull
    public final ArrayList getHalal() {
       return new ArrayList();
    }

    @NotNull
    public final ArrayList getRevert() {
      return new ArrayList();
    }

    @NotNull
    public final ArrayList getBeard() {
     return new ArrayList();
    }

    @NotNull
    public final ArrayList getWear() {

        return new ArrayList();
    }

    @NotNull
    public final ArrayList getProfession() {
        return new ArrayList();
    }

    @Nullable
    public final OnFragmentChange getOnFragmentChange() {
        return this.onFragmentChange;
    }

    public final void setOnFragmentChange(@Nullable OnFragmentChange var1) {
        this.onFragmentChange = var1;
    }

    public void onAttach(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        OnFragmentChange var10000 = this.onFragmentChange;
        if (var10000 != null) {
            var10000.selectedPos(2);
        }

    }

    public final void getLanguage(@Nullable ArrayList language) {
        if (language != null) {
            this.getLanguageList().addAll((Collection) language);
        }

        TextView var10000 = (TextView) view.findViewById(id.pref_languages);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "pref_languages");
        var10000.setText((CharSequence) (language != null ? joinToString(language): null));
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context var10000 = this.getContext();
        if (var10000 == null) {
            Intrinsics.throwNpe();
        }

        return LayoutInflater.from(var10000).inflate(R.layout.fragment_partner_preference, container, false);
    }

    View view;

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        OnFragmentChange var10000 = this.onFragmentChange;
        if (var10000 != null) {
            var10000.selectedPos(4);
        }

        CountryPickerDialog var10001 = new CountryPickerDialog(getContext());
        this.nationalityDialog = var10001;
        ((ImageView) view.findViewById(id.prefCancel)).setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
              if(getActivity()!=null)
               getActivity().onBackPressed();
           }
        });
        var10001 = new CountryPickerDialog(getContext());
        this.originDialog = var10001;
        if(Preferences.INSTANCE.getPrefs().getBoolean(PrefKeys.INSTANCE.isDataSaved(),false)==true)
        {
           getValues();
        }

        ((EditText) view.findViewById(id.pref_minAge)).addTextChangedListener((TextWatcher) (new TextWatcher() {
            public void afterTextChanged(@Nullable Editable s) {
            }

            public void beforeTextChanged(@Nullable CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(@Nullable CharSequence s, int start, int before, int count) {
                EditText var10000 = (EditText) view.findViewById(id.pref_minAge);
                Editable var8 = var10000.getText();
                CharSequence var5 = (CharSequence) var8;
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
        ((EditText) view.findViewById(id.pref_maxAge)).addTextChangedListener((TextWatcher) (new TextWatcher() {
            public void afterTextChanged(@Nullable Editable s) {
            }

            public void beforeTextChanged(@Nullable CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(@Nullable CharSequence s, int start, int before, int count) {
                EditText var10000 = (EditText) view.findViewById(id.pref_maxAge);
                Editable var8 = var10000.getText();
                CharSequence var5 = (CharSequence) var8;
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
        TextView var10000 = (TextView) view.findViewById(id.pref_complexion);
        var10000.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPREF_COLORCOMPLEXION(), ""));
        var10000 = (TextView) view.findViewById(id.pref_education);
        var10000.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPREF_EDUCATION(), ""));
        var10000 = (TextView) view.findViewById(id.pref_eyes_color);
        var10000.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getPREF_EYECOLOR(), ""));
        var10000 = (TextView) view.findViewById(id.pref_hair_color);
        var10000.setText(Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getHAIRCOLOR(), ""));
    }

    private final void getValues() {
        String preString = Preferences.INSTANCE.getPrefs().getString(PrefKeys.INSTANCE.getUSERPREFS(), "");
        userPreference = new Gson().fromJson(preString, UserPreference.class);
        List var17;
        EditText var22;
        TextView var23;

        var22 = (EditText) view.findViewById(id.pref_height);
        var22.setText((CharSequence) (userPreference != null ? userPreference.getHeight() : null));
        var22 = (EditText) view.findViewById(id.pref_minAge);

        var22.setText((CharSequence) (userPreference != null ? userPreference.getMinAge() : null));
        var22 = (EditText) view.findViewById(id.pref_maxAge);

        var22.setText((CharSequence) (userPreference != null ? userPreference.getMaxAge() : null));
        var23 = (TextView) view.findViewById(id.pref_nationality);
        var23.setText(joinToString((ArrayList) userPreference.getNationalty()));
        var22 = (EditText) view.findViewById(id.pref_name);

        var22.setText((CharSequence) (userPreference != null ? userPreference.getName() : null));
        var23 = (TextView) view.findViewById(id.pref_origin);
        var23.setText(joinToString((ArrayList) userPreference.getOrigin()));
        var23 = (TextView) view.findViewById(id.pref_languages);
        var23.setText(joinToString(userPreference.getLanguage()));
        var23 = (TextView) view.findViewById(id.pref_halal);
        var23.setText((CharSequence) (userPreference != null ? userPreference.getHalal() : null));
        var23 = (TextView) view.findViewById(id.pref_quran);
        var23.setText((CharSequence) (userPreference != null ? userPreference.getCompletedQuran() : null));
        var23 = (TextView) view.findViewById(id.pref_prays);
        var23.setText((CharSequence) (userPreference != null ? userPreference.getPrays() : null));
        var23 = (TextView) view.findViewById(id.pref_revert);
        var23.setText((CharSequence) (userPreference != null ? userPreference.getRevert() : null));
        var23 = (TextView) view.findViewById(id.pref_beard);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getBeard() : null));
        var23 = (TextView) view.findViewById(id.pref_wear);
        Intrinsics.checkExpressionValueIsNotNull(var23, "pref_wear");

        var23.setText((CharSequence) (userPreference != null ? userPreference.getWears() : null));
        var23 = (TextView) view.findViewById(id.pref_living_after_marriage);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getLivingArrangementAfterMarriage() : null));
        var23 = (TextView) view.findViewById(id.pref_marriage_plans);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getMarriagePlan() : null));
        var23 = (TextView) view.findViewById(id.pref_profession);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getProfession() : null));
        var23 = (TextView) view.findViewById(id.pref_complexion);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getComplexion() : null));
        var23 = (TextView) view.findViewById(id.pref_eyes_color);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getEyeColor() : null));
        var23 = (TextView) view.findViewById(id.pref_hair_color);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getHairColor() : null));
        var23 = (TextView) view.findViewById(id.pref_education);

        var23.setText((CharSequence) (userPreference != null ? userPreference.getEducation() : null));
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

    private final void setOnClick() {
        ((TextView) view.findViewById(id.preference_done)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                EditText var10003 = (EditText) view.findViewById(id.pref_name);
                String var10 = var10003.getText().toString();
                EditText var10004 = (EditText) view.findViewById(id.pref_minAge);
                String var11 = var10004.getText().toString();
                EditText var10005 = (EditText) view.findViewById(id.pref_maxAge);
                String var12 = var10005.getText().toString();
                TextView var10006 = (TextView) view.findViewById(id.pref_nationality);
                CharSequence var13 = var10006.getText();
                List var14 = Arrays.asList(var13.toString().split(","));
                TextView var10007 = (TextView) view.findViewById(id.pref_origin);
                CharSequence var15 = var10007.getText();
                List origin;
                if(!var10007.getText().toString().isEmpty()) {
                    origin = Arrays.asList(var10007.getText().toString().split(","));
                }

                else origin=Arrays.asList("India",",");
                ArrayList var10008 = PartnerPreferenceFragment.this.getLanguageList();
                ArrayList var10009 = PartnerPreferenceFragment.this.getCaste();
                ArrayList var10010 = PartnerPreferenceFragment.this.getMarital();
                ArrayList var10011 = PartnerPreferenceFragment.this.getLivingArrangements();
                EditText var10012 = (EditText) view.findViewById(id.pref_height);
                Intrinsics.checkExpressionValueIsNotNull(var10012, "pref_height");
                String var17 = var10012.getText().toString();
                ArrayList var10013 = PartnerPreferenceFragment.this.getBuild();
                TextView var10014 = (TextView) view.findViewById(id.pref_complexion);
                String var18 = var10014.getText().toString();
                TextView var10015 = (TextView) view.findViewById(id.pref_eyes_color);
                String var19 = var10015.getText().toString();
                TextView var10016 = (TextView) view.findViewById(id.pref_hair_color);
                String var20 = var10016.getText().toString();
                TextView var10017 = (TextView) view.findViewById(id.pref_education);
                String var21 = var10017.getText().toString();
                TextView var10018 = (TextView) view.findViewById(id.pref_profession);
                String var22 = var10018.getText().toString();
                TextView var10019 = (TextView) view.findViewById(id.pref_marriage_plans);
                String var23 = var10019.getText().toString();
                TextView var10020 = (TextView) view.findViewById(id.pref_living_after_marriage);

                String var24 = var10020.getText().toString();
                TextView var10021 = (TextView) view.findViewById(id.pref_wear);
                String var25 = var10021.getText().toString();
                TextView var10022 = (TextView) view.findViewById(id.pref_beard);
                String var26 = var10022.getText().toString();
                TextView var10023 = (TextView) view.findViewById(id.pref_revert);
                String var27 = var10023.getText().toString();
                TextView var10024 = (TextView) view.findViewById(id.pref_halal);
                String var28 = var10024.getText().toString();
                TextView var10025 = (TextView) view.findViewById(id.pref_prays);
                String var29 = var10025.getText().toString();
                TextView var10026 = (TextView) view.findViewById(id.pref_quran);
                userPreference=new UserPreference(var10, var11, var12, var14, origin, var10008, var10009, var10010, var10011, var17, var10013, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27, var28, var29, var10026.getText().toString());
                Preferences var2 = Preferences.INSTANCE;
                PrefKeys var6 = PrefKeys.INSTANCE;
                String var7 = var6.getUSERPREFS();
              //  if(userPreference!=null){
                var2.setPref(PrefKeys.INSTANCE.getUSERPREFS(),new Gson().toJson(userPreference));
                PrefKeys var10001 = PrefKeys.INSTANCE;
                String var9 = var10001.isDataSaved();
                var2.setPref(var9, true);
                getActivity().onBackPressed();

        }
        }));

        ((TextView) view.findViewById(id.pref_nationality)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                CountryPickerDialog var10000 = PartnerPreferenceFragment.this.getNationalityDialog();
                if (var10000 != null) {
                    var10000.show();
                }

                PartnerPreferenceFragment.this.getNationalityFromPicker();
            }
        }));
        ((TextView) view.findViewById(id.pref_languages)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment.this.openLanguageDialog();
            }
        }));
        ((TextView) view.findViewById(id.pref_education)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getEDUCATION();
                TextView var10002 = (TextView) view.findViewById(id.pref_education);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_education");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_profession)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getPROFESSION();
                TextView var10002 = (TextView) view.findViewById(id.pref_profession);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_profession");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_origin)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                CountryPickerDialog var10000 = PartnerPreferenceFragment.this.getOriginDialog();
                if (var10000 != null) {
                    var10000.show();
                }

                PartnerPreferenceFragment.this.getOriginFromPicker();
            }
        }));
        ((TextView) view.findViewById(id.pref_eyes_color)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getEYECOLOR();
                TextView var10002 = (TextView) view.findViewById(id.pref_eyes_color);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_eyes_color");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_hair_color)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getHAIRCOLOR();
                TextView var10002 = (TextView) view.findViewById(id.pref_hair_color);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_hair_color");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_complexion)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getCOLORCOMPLEXION();
                TextView var10002 = (TextView) view.findViewById(id.pref_complexion);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_complexion");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_marriage_plans)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getMARRIAGEPLANS();
                TextView var10002 = (TextView) view.findViewById(id.pref_marriage_plans);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_marriage_plans");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_living_after_marriage)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE();
                TextView var10002 = (TextView) view.findViewById(id.pref_living_after_marriage);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_living_after_marriage");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_prays)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getPRAYS();
                TextView var10002 = (TextView) view.findViewById(id.pref_prays);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_prays");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_quran)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getQURAN();
                TextView var10002 = (TextView) view.findViewById(id.pref_quran);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_quran");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_halal)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getHALAL();
                TextView var10002 = (TextView) view.findViewById(id.pref_halal);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_halal");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_revert)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getREVERT();
                TextView var10002 = (TextView) view.findViewById(id.pref_revert);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_revert");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_beard)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getBEARD();
                TextView var10002 = (TextView) view.findViewById(id.pref_beard);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_beard");
                var10000.openDialog(var10001, var10002);
            }
        }));
        ((TextView) view.findViewById(id.pref_wear)).setOnClickListener((OnClickListener) (new OnClickListener() {
            public final void onClick(View it) {
                PartnerPreferenceFragment var10000 = PartnerPreferenceFragment.this;
                String var10001 = ProfileItem.INSTANCE.getWEAR();
                TextView var10002 = (TextView) view.findViewById(id.pref_wear);
                Intrinsics.checkExpressionValueIsNotNull(var10002, "pref_wear");
                var10000.openDialog(var10001, var10002);
            }
        }));
        this.setMaritalStatusClick();
        this.setBuildClick();
        this.setCasteClick();
        this.setLivingArrangementClick();
    }

    private final void setMarriagePlansClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Small", "Destination", "Traditional"});
    }

    private final void setAfterMarriageArrangementsClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Stay with Parents", "Willing to relocate", "Live Separately"});
    }

    private final void setPrayClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Always", "Sometimes"});
    }

    private final void getOriginFromPicker() {
        CountryPickerDialog var10000 = this.originDialog;
        if (var10000 != null) {
            var10000.updateListener((UpdateListener) (new UpdateListener() {
                public final void onUpdateClick(ArrayList it) {
                    TextView var10000 = (TextView) view.findViewById(id.pref_origin);
                    Intrinsics.checkExpressionValueIsNotNull(var10000, "pref_origin");
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    var10000.setText((CharSequence) joinToString(it));
                }
            }));
        }

    }

    private final void setMaritalStatusClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Single", "Divorced", "Widowed", "Annulled"});
        RecyclerView var10000 = (RecyclerView) view.findViewById(id.marital_status_recycler);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "marital_status_recycler");
        ArrayList var10001 = arraylist;
        Context var10002 = this.getContext();
        if (var10002 == null) {
            Intrinsics.throwNpe();
        }

        ArrayList var15;
        String var10003;
        label15:
        {
            var10003 = PrefType.INSTANCE.getMARITALSTATUS();
            UserPreference var10004 = this.userPreference;
            if (var10004 != null) {
                var15 = var10004.getMarital();
                if (var15 != null) {
                    break label15;
                }
            }

            String var8 = var10003;
            Context var7 = var10002;
            RecyclerView var3 = var10000;
            boolean var2 = false;
            ArrayList var9 = new ArrayList();
            var10000 = var3;
            var10001 = arraylist;
            var10002 = var7;
            var10003 = var8;
            var15 = var9;
        }

        OnPrefChange var10 = (OnPrefChange) this;
        ArrayList var11 = var15;
        String var12 = var10003;
        Context var13 = var10002;
        ArrayList var14 = var10001;
        var10000.setAdapter((Adapter) (new PrefAdapter(var14, var13, var12, var11, var10)));
    }

    private final void setCasteClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Sunni", "Shia", "Other"});
        RecyclerView var10000 = (RecyclerView) view.findViewById(id.caste_group);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "caste_group");
        ArrayList var10001 = arraylist;
        Context var10002 = this.getContext();
        if (var10002 == null) {
            Intrinsics.throwNpe();
        }

        ArrayList var15;
        String var10003;
        label15:
        {
            var10003 = PrefType.INSTANCE.getSECT();
            UserPreference var10004 = this.userPreference;
            if (var10004 != null) {
                var15 = var10004.getSect();
                if (var15 != null) {
                    break label15;
                }
            }

            String var8 = var10003;
            Context var7 = var10002;
            RecyclerView var3 = var10000;
            boolean var2 = false;
            ArrayList var9 = new ArrayList();
            var10000 = var3;
            var10001 = arraylist;
            var10002 = var7;
            var10003 = var8;
            var15 = var9;
        }

        OnPrefChange var10 = (OnPrefChange) this;
        ArrayList var11 = var15;
        String var12 = var10003;
        Context var13 = var10002;
        ArrayList var14 = var10001;
        var10000.setAdapter((Adapter) (new PrefAdapter(var14, var13, var12, var11, var10)));
    }

    private final void setLivingArrangementClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Living Alone", "Living with Family", "Living with Friends", "Other"});
        RecyclerView var10000 = (RecyclerView) view.findViewById(id.living_arrangement_group);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "living_arrangement_group");
        ArrayList var10001 = arraylist;
        Context var10002 = this.getContext();
        if (var10002 == null) {
            Intrinsics.throwNpe();
        }

        ArrayList var15;
        String var10003;
        label15:
        {
            var10003 = PrefType.INSTANCE.getCURRENTLIVINGARRANGEMENT();
            UserPreference var10004 = this.userPreference;
            if (var10004 != null) {
                var15 = var10004.getLivingArrangement();
                if (var15 != null) {
                    break label15;
                }
            }

            String var8 = var10003;
            Context var7 = var10002;
            RecyclerView var3 = var10000;
            boolean var2 = false;
            ArrayList var9 = new ArrayList();
            var10000 = var3;
            var10001 = arraylist;
            var10002 = var7;
            var10003 = var8;
            var15 = var9;
        }

        OnPrefChange var10 = (OnPrefChange) this;
        ArrayList var11 = var15;
        String var12 = var10003;
        Context var13 = var10002;
        ArrayList var14 = var10001;
        var10000.setAdapter((Adapter) (new PrefAdapter(var14, var13, var12, var11, var10)));
    }

    private final void setBuildClick() {
        ArrayList arraylist = CollectionsKt.arrayListOf(new String[]{"Athletic", "Large", "Medium", "Slim"});
        RecyclerView var10000 = (RecyclerView) view.findViewById(id.build_bar);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "build_bar");
        ArrayList var10001 = arraylist;
        Context var10002 = this.getContext();
        if (var10002 == null) {
            Intrinsics.throwNpe();
        }

        ArrayList var15;
        String var10003;
        label15:
        {
            var10003 = PrefType.INSTANCE.getBUILD();
            UserPreference var10004 = this.userPreference;
            if (var10004 != null) {
                var15 = var10004.getBuild();
                if (var15 != null) {
                    break label15;
                }
            }

            String var8 = var10003;
            Context var7 = var10002;
            RecyclerView var3 = var10000;
            boolean var2 = false;
            ArrayList var9 = new ArrayList();
            var10000 = var3;
            var10001 = arraylist;
            var10002 = var7;
            var10003 = var8;
            var15 = var9;
        }

        OnPrefChange var10 = (OnPrefChange) this;
        ArrayList var11 = var15;
        String var12 = var10003;
        Context var13 = var10002;
        ArrayList var14 = var10001;
        var10000.setAdapter((Adapter) (new PrefAdapter(var14, var13, var12, var11, var10)));
    }


    public   void openDialog(String item, TextView textView) {
        Bundle bundle = new Bundle();
        bundle.putString("item_name", item);
        bundle.putString("item_type", "multiple");
        ProfileItemFragment var5 = new ProfileItemFragment();
        boolean var6 = false;
        boolean var7 = false;
        var5.infoSelectListener(this, textView);
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
        boolean var6 = false;
        FragmentManager var10001 = this.getFragmentManager();
        if (var10001 == null) {
            Intrinsics.throwNpe();
        }

        var2.show(var10001, "Language");
    }

    private final void getNationalityFromPicker() {
        CountryPickerDialog var10000 = this.nationalityDialog;
        if (var10000 != null) {
            var10000.updateListener((UpdateListener) (new UpdateListener() {
                public final void onUpdateClick(ArrayList it) {
                    TextView var10000 = (TextView) view.findViewById(id.pref_nationality);
                    var10000.setText((CharSequence) joinToString(it));
                }
            }));
        }

    }

    public void editinformation(@NotNull String info, @NotNull TextView textView) {
        textView.setText((CharSequence) info);
    }

}
