package com.example.admin.bloodbank.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.example.admin.bloodbank.activities.ResultSearchBloodGroupActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 10/01/2017.
 */

public class SearchBloodGroupFragment extends TemplateFragment {
    private MaterialBetterSpinner spinnerCity, spinnerDistrict, spinnerTimeDonation, spinnerBloodGroup;
    private RadioButton radioBtnMale, radioBtnFemale;
    private RadioGroup radioGroupGender;
    private Button btnSearch;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_blood_group, null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        radioBtnMale = (RadioButton) view.findViewById(R.id.radio_female);
        radioBtnFemale = (RadioButton) view.findViewById(R.id.radio_male);
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radio_group_gender);

        spinnerBloodGroup = (MaterialBetterSpinner) view.findViewById(R.id.spinner_blood_group);
        spinnerCity = (MaterialBetterSpinner) view.findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) view.findViewById(R.id.spinner_district);
        spinnerTimeDonation = (MaterialBetterSpinner) view.findViewById(R.id.spinner_time_donation);
        btnSearch = (Button)view.findViewById(R.id.btn_search);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        isCheckedGender();
        setupSpinner();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultSearchBloodGroupActivity.class);
                startActivity(intent);
            }
        });
    }



    private void isCheckedGender() {
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radio_male) {

                }
                if (checkedId == R.id.radio_female) {

                }
            }
        });
    }

    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listTimeDonation = getResources().getStringArray(R.array.time_donation);
        String[] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterTimeDonation = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listTimeDonation);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerTimeDonation.setAdapter(adapterTimeDonation);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
    }

}
