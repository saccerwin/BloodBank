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
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Admin on 17/01/2017.
 */

public class EditProfileActivity extends TemplateActivity {
    MaterialBetterSpinner spinnerCity,spinnerDistrict,spinnerTimeDonation,spinnerBloodGroup;
    RadioButton radioBtnMale, radioBtnFemale;
    private  Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText edtFullName,edtPhone,edtPassword,edtDateOfBirth;
    RadioGroup radioGroupGender;

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        radioBtnMale = (RadioButton)findViewById(R.id.radio_female);
        radioBtnFemale = (RadioButton)findViewById(R.id.radio_male);
        spinnerCity = (MaterialBetterSpinner)findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        spinnerTimeDonation = (MaterialBetterSpinner) findViewById(R.id.spinner_time_donation);
        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        radioGroupGender = (RadioGroup)findViewById(R.id.radio_group_gender);

        edtFullName = (EditText)findViewById(R.id.edt_fullname);
        edtPhone = (EditText)findViewById(R.id.edt_phone_number);
        edtDateOfBirth = (EditText)findViewById(R.id.edt_date_of_birth);
        edtPassword = (EditText)findViewById(R.id.edt_password);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        setupSpinner();
        isCheckedGender();
    }


    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.toobar_title_profile));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: finish();
        }
        return false;
    }

    private void isCheckedGender() {
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.radio_male) {

                }
                if(checkedId == R.id.radio_female) {

                }
            }
        });
    }

    private void setupSpinner() {
        String [] listCity = getResources().getStringArray(R.array.city);
        String [] listTimeDonation = getResources().getStringArray(R.array.time_donation);
        String [] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);

        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterTimeDonation = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listTimeDonation);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerTimeDonation.setAdapter(adapterTimeDonation);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);

    }
}
