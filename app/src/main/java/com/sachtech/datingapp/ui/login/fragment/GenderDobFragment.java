package com.sachtech.datingapp.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sachtech.datingapp.R;
import com.sachtech.datingapp.R.id;
import com.sachtech.datingapp.base.BaseFragment;
import com.sachtech.datingapp.utils.Constants;
import com.sachtech.datingapp.utils.IntentString;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import static com.sachtech.datingapp.extensions.CommonsKt.formatDate;
import static com.sachtech.datingapp.extensions.CommonsKt.getAge;


public final class GenderDobFragment extends Fragment {
    @NotNull
    private String gender=Constants.INSTANCE.getMale();
    private int date = 0;
    private int month = 0;
    private int calendarYear = 0;
    private long day;
    private int age;

    RadioGroup radioGroup;
    RadioButton female, male;
    TextView dobDone;
    DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_select_dob, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.calendarYear -= 18;
        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        calendarYear = calendar.get(Calendar.YEAR);
        radioGroup = view.findViewById(id.gender_group);
        female = view.findViewById(id.gender_female);
        male = view.findViewById(id.gender_male);
        dobDone = view.findViewById(id.dob_done);
        datePicker = view.findViewById(id.datePicker);
        Bundle bundle = new Bundle();
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == id.gender_female) {
                    gender = Constants.INSTANCE.getFemale();
                    female.setBackground(getResources().getDrawable(R.drawable.female_b));
                    male.setBackground(getResources().getDrawable(R.drawable.male_o));
                } else if (checkedId == id.gender_male) {
                    gender = Constants.INSTANCE.getMale();
                    female.setBackground(getResources().getDrawable(R.drawable.female_o));
                    male.setBackground(getResources().getDrawable(R.drawable.male_b));
                }
            }
        });
        dobDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age < 18) {
                    Toast.makeText(getActivity(), "User must be 18 or above", Toast.LENGTH_SHORT).show();
                } else {
                    bundle.putString(IntentString.INSTANCE.getAGE(), String.valueOf(age));
                    bundle.putString((IntentString.INSTANCE.getEMAIL()), getArguments().getString(IntentString.INSTANCE.getEMAIL()));
                    bundle.putString((IntentString.INSTANCE.getNAME()), getArguments().getString(IntentString.INSTANCE.getNAME()));
                    bundle.putString((IntentString.INSTANCE.getPROFILEIMAGE()), getArguments().getString(IntentString.INSTANCE.getPROFILEIMAGE()));
                    bundle.putStringArrayList((IntentString.INSTANCE.getIMAGELIST()), getArguments().getStringArrayList(IntentString.INSTANCE.getIMAGELIST()));
                    bundle.putString(IntentString.INSTANCE.getGENDER(), gender);
                    bundle.putLong(IntentString.INSTANCE.getDOB(), day);
                    BaseFragment.replaceFragment((AppCompatActivity) getActivity(), id.login_container, new VerificationImageFragment(), bundle);

                }
            }
        });
        datePicker.init(calendarYear, month, date, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day = formatDate("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                age = getAge(year, monthOfYear, dayOfMonth);
            }
        });
    }
}
