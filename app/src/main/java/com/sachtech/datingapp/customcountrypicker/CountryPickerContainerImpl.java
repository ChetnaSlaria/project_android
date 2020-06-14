package com.sachtech.datingapp.customcountrypicker;

import android.app.Dialog;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;




class CountryPickerContainerImpl implements CountryPickerContainer {

    private final Dialog dialog;
    private final CountryPicker picker;

    CountryPickerContainerImpl(Dialog dialog, Context context) {
        this.dialog = dialog;
        this.picker = new CountryPicker(context);
    }

    @NonNull
    @Override
    public CountryPicker getPicker() {
        return picker;
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setItems(countries);
    }

    @Override
    public void setFlagShown(boolean shown) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setFlagShown(shown);
    }

    @Override
    public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        picker.getAdapter()
                .setListener(listener == null ? null : (CountryPicker.OnSelectedListener) country -> {
                    listener.onSelected(country);
                    //dialog.dismiss();
                });
    }

    @Override
    public void updateListener(@Nullable UpdateListener updateListener) {
        picker.setUpdateListener(updateListener==null ?null:(UpdateListener) updateList->{
            updateListener.onUpdateClick(updateList);
            dialog.dismiss();
                }
                );
    }


}