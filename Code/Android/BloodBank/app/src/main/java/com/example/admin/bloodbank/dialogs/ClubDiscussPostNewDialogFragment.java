package com.example.admin.bloodbank.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateCustomDialogFragment;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 18/01/2017.
 */

public class ClubDiscussPostNewDialogFragment extends TemplateCustomDialogFragment {
    private MaterialAutoCompleteTextView tvAutoCompleteNameHospital;
    private MaterialBetterSpinner spinnerUnitDonationBlood, spinnerBloodGroup,spinnerDay;
    private EditText edtQuality,edtInfomationOther;
    private RadioButton radioBtnMale, radioBtnFemale,radioGenderAll;
    RadioGroup radioGroupGender;
    Button btnCancel,btnDone;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_post_new_discuss,null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        tvAutoCompleteNameHospital = (MaterialAutoCompleteTextView)view.findViewById(R.id.tv_auto_completed_name_hospital);
        spinnerUnitDonationBlood = (MaterialBetterSpinner)view.findViewById(R.id.spinner_units_donation_blood);
        spinnerBloodGroup = (MaterialBetterSpinner)view.findViewById(R.id.spinner_blood_group);
        edtQuality = (EditText) view.findViewById(R.id.edt_quality);
        radioBtnFemale = (RadioButton) view.findViewById(R.id.radio_female);
        radioBtnMale = (RadioButton) view.findViewById(R.id.radio_male);
        radioGenderAll = (RadioButton) view.findViewById(R.id.radio_gender_all);
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radio_group_gender);
        btnCancel = (Button)view.findViewById(R.id.btn_cancel);
        btnDone = (Button)view.findViewById(R.id.btn_done);
        edtInfomationOther = (EditText)view.findViewById(R.id.edt_infomation_other);
        spinnerDay = (MaterialBetterSpinner)view.findViewById(R.id.spinner_day);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupSpinner();
        setSizeDialog();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dissMissDialog();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void dissMissDialog() {
        getDialog().dismiss();
    }
    private void isCheckedGender() {
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radio_male) {

                }
                if (checkedId == R.id.radio_female) {

                }
                if (checkedId == R.id.radio_gender_all) {

                }
            }
        });
    }

    private void setupSpinner() {
        String[] listUnitDonation = getResources().getStringArray(R.array.units_donation_blood_items);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        String[] listNameHospital = getResources().getStringArray(R.array.name_hospital);
        String[] listDay = getResources().getStringArray(R.array.array_time_day);
        ArrayAdapter<String> adapterUnitBlood = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listUnitDonation);
        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listDay);
        ArrayAdapter<String> adapterNameHospital = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listNameHospital);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        spinnerUnitDonationBlood.setAdapter(adapterUnitBlood);
        tvAutoCompleteNameHospital.setAdapter(adapterNameHospital);
        spinnerDay.setAdapter(adapterDay);
    }

    private void setSizeDialog() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        getDialog().getWindow().setLayout(width,height);
    }

}
