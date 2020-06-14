// ProfileItemListAdapter.java
package com.sachtech.datingapp.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.ui.profile.listener.OnItemSelected;
import com.sachtech.datingapp.utils.PrefKeys;
import com.sachtech.datingapp.utils.Preferences;
import com.sachtech.datingapp.utils.ProfileItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;


public class ProfileItemListAdapter extends RecyclerView.Adapter<ProfileItemListAdapter.ViewHolder> {
    private int selected_position;
    @Nullable
    private ArrayList itemList;
    @NotNull
    private final Context context;
    @NotNull
    private final ArrayList stringlist;
    @Nullable
    private final String item;
    @Nullable
    private final String selectedType;
    @NotNull
    private final com.sachtech.datingapp.ui.profile.listener.OnItemSelected onItemSelected;

    @Nullable
    public final ArrayList getItemList() {
        return this.itemList;
    }

    public final void setItemList(@Nullable ArrayList var1) {
        this.itemList = var1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_items, parent, false));
    }

   public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {
        this.itemList = new ArrayList();
        View var10000 = holder.itemView;
        TextView var3 = (TextView) var10000.findViewById(id.item_profile);
        var3.setText((CharSequence) this.stringlist.get(position));
        if (Intrinsics.areEqual(this.selectedType, "single")) {
            if (holder.getAdapterPosition() == this.selected_position) {
                var10000 = holder.itemView;
                ((TextView) var10000.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_background);
                var10000 = holder.itemView;
                ((TextView) var10000.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorWhite
                ));
            } else {
                var10000 = holder.itemView;
                ((TextView) var10000.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_unselected);
                var10000 = holder.itemView;
                ((TextView) var10000.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorGrey));
            }

            holder.itemView.setOnClickListener((OnClickListener) (new OnClickListener() {
                public final void onClick(View it) {
                    String var2 = ProfileItemListAdapter.this.getItem();
                    Preferences var10000;
                    String var10001;
                    if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getORIGIN())) {
                        var10000 = Preferences.INSTANCE;
                        var10001 = PrefKeys.INSTANCE.getORIGIN();
                        var10000.setPref(var10001, ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getCOLORCOMPLEXION())) {
                        var10000 = Preferences.INSTANCE;
                        var10001 = PrefKeys.INSTANCE.getCOLORCOMPLEXION();
                        var10000.setPref(var10001, ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHAIRCOLOR())) {
                        var10000 = Preferences.INSTANCE;
                        var10001 = PrefKeys.INSTANCE.getHAIRCOLOR();
                        var10000.setPref(var10001, ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEYECOLOR())) {
                        var10000 = Preferences.INSTANCE;
                        var10001 = PrefKeys.INSTANCE.getEYECOLOR();
                        var10000.setPref(var10001, ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPROFESSION())) {
                        var10000 = Preferences.INSTANCE;
                        var10001 = PrefKeys.INSTANCE.getPROFESSION();
                        var10000.setPref(var10001, ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEDUCATION())) {
                        Preferences.INSTANCE.setPref("education", ProfileItemListAdapter.this.getStringlist().get(position));
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getNATIONALITY())) {
                        Preferences.INSTANCE.setPref("nationality", ProfileItemListAdapter.this.getStringlist().get(position));
                    }

                    com.sachtech.datingapp.ui.profile.listener.OnItemSelected var3 = ProfileItemListAdapter.this.getOnItemSelected();
                    Object var4 = ProfileItemListAdapter.this.getStringlist().get(position);
                    var3.selectedItem((String) var4);
                    ProfileItemListAdapter.this.selected_position = holder.getAdapterPosition();
                    ProfileItemListAdapter.this.notifyDataSetChanged();
                }
            }));
        } else if (Intrinsics.areEqual(this.selectedType, "multiple")) {
            var10000 = holder.itemView;
           ((TextView) var10000.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_unselected);
            var10000 = holder.itemView;
            ((TextView) var10000.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(this.context, R.color.colorGrey));
            holder.itemView.setOnClickListener((OnClickListener) (new OnClickListener() {
                public final void onClick(View it) {
                    View var10000 = holder.itemView;
                    ((TextView) var10000.findViewById(id.item_profile)).setBackgroundResource(R.drawable.dialog_background);
                    var10000 = holder.itemView;
                    ((TextView) var10000.findViewById(id.item_profile)).setTextColor(ContextCompat.getColor(ProfileItemListAdapter.this.getContext(), R.color.colorWhite));
                    ArrayList var3 = ProfileItemListAdapter.this.getItemList();
                    if (var3 == null) {
                        Intrinsics.throwNpe();
                    }

                    View var10001 = holder.itemView;
                    TextView var5 = (TextView) var10001.findViewById(id.item_profile);
                    var3.add(var5.getText().toString());
                    String var2 = ProfileItemListAdapter.this.getItem();
                    ArrayList var10002;
                    Preferences var4;
                    String var6;
                    if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getCOLORCOMPLEXION())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_COLORCOMPLEXION();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHAIRCOLOR())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_HAIRCOLOR();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEYECOLOR())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_EYECOLOR();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPROFESSION())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_PROFESSION();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getEDUCATION())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_EDUCATION();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getQURAN())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_QURAN();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getPRAYS())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_PRAYS();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getHALAL())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_HALAL();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getWEAR())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_WEAR();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getLIFEAFTERMARRIAGE())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_LIFE_AFTER_MARRIAGE();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ? joinToString(var10002): null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getMARRIAGEPLANS())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_MARRIAGE_PLANS();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ?joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getREVERT())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_REVERT();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                                var4.setPref(var6, var10002 != null ?joinToString(var10002) : null);
                    } else if (Intrinsics.areEqual(var2, ProfileItem.INSTANCE.getBEARD())) {
                        var4 = Preferences.INSTANCE;
                        var6 = PrefKeys.INSTANCE.getPREF_BEARD();
                        var10002 = ProfileItemListAdapter.this.getItemList();
                        var4.setPref(var6, var10002 != null ?joinToString(var10002) : null);
                    }

                    OnItemSelected var7 = ProfileItemListAdapter.this.getOnItemSelected();
                    ArrayList var8 = ProfileItemListAdapter.this.getItemList();
                    var6 = var8 != null ? joinToString(var8):null;
                    var7.selectedItem(var6);
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

    public int getItemCount() {
        return this.stringlist.size();
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final ArrayList getStringlist() {
        return this.stringlist;
    }

    @Nullable
    public final String getItem() {
        return this.item;
    }

    @Nullable
    public final String getSelectedType() {
        return this.selectedType;
    }

    @NotNull
    public final com.sachtech.datingapp.ui.profile.listener.OnItemSelected getOnItemSelected() {
        return this.onItemSelected;
    }

    public ProfileItemListAdapter(@NotNull Context context, @NotNull ArrayList stringlist, @Nullable String item, @Nullable String selectedType, @NotNull com.sachtech.datingapp.ui.profile.listener.OnItemSelected onItemSelected) {
        super();
        this.context = context;
        this.stringlist = stringlist;
        this.item = item;
        this.selectedType = selectedType;
        this.onItemSelected = onItemSelected;
        this.selected_position = -1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


