package com.sachtech.datingapp.ui.profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.ui.profile.listener.OnEditInformation;

import org.jetbrains.annotations.NotNull;

public class EditAboutDialog extends DialogFragment {
    @Nullable
    private TextView textView;
    @Nullable
    private OnEditInformation onEditInformation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView titleText = view.findViewById(R.id.title);
        EditText aboutMessage = view.findViewById(R.id.about_message);
        TextView save = view.findViewById(R.id.about_save);
        Bundle bundle = getArguments();
        titleText.setText(bundle.getString("edit_type"));
        aboutMessage.setText(textView.getText().toString());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!aboutMessage.getText().toString().isEmpty()) {
                    assert onEditInformation != null;
                    onEditInformation.information(aboutMessage.getText().toString(), textView);
                    dismiss();
                } else showToast("Add Something");
            }

            private void showToast(String add_something) {
            }
        });
    }

    public final void infoSelectListener(@NotNull OnEditInformation onEditInformation, @NotNull TextView textView) {
        this.onEditInformation = onEditInformation;
        this.textView = textView;
    }
}
