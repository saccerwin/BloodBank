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
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 10/01/2017.
 */

public class SearchBloodGroupFragment extends TemplateFragment {
    private MaterialBetterSpinner spinnerDistrict, spinnerBloodGroup;
    private RadioButton radioBtnMale, radioBtnFemale,radioBtnAll;
    private RadioGroup radioGroupGender;
    private Button btnSearch;
    private MaterialAutoCompleteTextView tvAutocompleteCity;

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
        radioBtnAll = (RadioButton) view.findViewById(R.id.radio_gender_all);

        spinnerBloodGroup = (MaterialBetterSpinner) view.findViewById(R.id.spinner_blood_group);
        spinnerDistrict = (MaterialBetterSpinner) view.findViewById(R.id.spinner_district);
        btnSearch = (Button)view.findViewById(R.id.btn_search);

        tvAutocompleteCity = (MaterialAutoCompleteTextView)view.findViewById(R.id.autocomplete_tv_city);

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
                if(checkedId == R.id.radio_gender_all) {

                }
            }
        });
    }

    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        tvAutocompleteCity.setAdapter(adapterCity);
    }

}
