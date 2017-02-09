package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class SearchMemberGroupActivity extends TemplateActivity {
    private MaterialBetterSpinner spinnerDistrict, spinnerBloodGroup;
    private RadioButton radioBtnMale, radioBtnFemale;
    private RadioGroup radioGroupGender;
    private Button btnSearch;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private MaterialAutoCompleteTextView tvAutocompleteCity;


    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_member_group);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        radioBtnMale = (RadioButton) findViewById(R.id.radio_female);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_male);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);

        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        btnSearch = (Button)findViewById(R.id.btn_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        tvAutocompleteCity = (MaterialAutoCompleteTextView)findViewById(R.id.autocomplete_tv_city);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        isCheckedGender();
        setupSpinner();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemplateActivity.startActivity(SearchMemberGroupActivity.this,ResultSearchBloodGroupActivity.class,null);
            }
        });
        setupToobar();
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

    private void setupToobar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarTitle.setText(getString(R.string.toolbar_search_member_group));
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }



    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        tvAutocompleteCity.setAdapter(adapterCity);
    }
}
