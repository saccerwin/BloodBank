package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 13/02/2017.
 */

public class AddNewDiscussActivity extends TemplateActivity {
    private EditText edtNameHospital,edtQualityUnitsDonation;
    private MaterialBetterSpinner spinnerUnitsDonationBlood,spinnerTime,spinnerBloodGroup;
    private MaterialAutoCompleteTextView tvAutoCompleteNameHospital;
    private RadioButton radioBtnMale, radioBtnFemale,radioGenderAll;
    private Toolbar toolbar;
    private RadioGroup radioGroupGender;
    private TextView toolbarTitle;

    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_new_discuss);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        edtQualityUnitsDonation = (EditText)findViewById(R.id.edt_quality);
        spinnerUnitsDonationBlood = (MaterialBetterSpinner)findViewById(R.id.spinner_units_donation_blood);
        spinnerTime = (MaterialBetterSpinner)findViewById(R.id.spinner_day);
        spinnerBloodGroup = (MaterialBetterSpinner)findViewById(R.id.spinner_blood_group);
        tvAutoCompleteNameHospital = (MaterialAutoCompleteTextView)findViewById(R.id.tv_auto_completed_name_hospital);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        radioBtnMale = (RadioButton) findViewById(R.id.radio_male);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_female);
        radioGenderAll= (RadioButton)findViewById(R.id.radio_gender_all);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        setupSpinner();
       // String decentralization = SPManager.getInstance(getContext()).getDecentralization();
       // Toast.makeText(getContext(),decentralization,Toast.LENGTH_LONG).show();
    }

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(R.string.text_add_new_discuss);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menuDone: {
                finish();
            }
            break;
        }
        return false;
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
        String[] listUnitsDonationBlood = getResources().getStringArray(R.array.units_donation_blood_items);
        String[] listNameHospital = getResources().getStringArray(R.array.name_hospital);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        String[] listTime = getResources().getStringArray(R.array.array_time_day);
        ArrayAdapter<String> adapterUnitBlood = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listUnitsDonationBlood);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        ArrayAdapter<String> adapterTime = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listTime);
        ArrayAdapter<String> adapterNameHospital = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, listNameHospital);
        spinnerUnitsDonationBlood.setAdapter(adapterUnitBlood);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        spinnerTime.setAdapter(adapterTime);
        tvAutoCompleteNameHospital.setAdapter(adapterNameHospital);
    }



}
