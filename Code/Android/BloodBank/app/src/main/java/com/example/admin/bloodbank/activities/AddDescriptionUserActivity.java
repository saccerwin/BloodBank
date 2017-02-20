package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.managers.SPManager;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * Created by Admin on 09/01/2017.
 */

public class AddDescriptionUserActivity extends TemplateActivity {
    private MaterialBetterSpinner spinnerCity, spinnerDistrict, spinnerBloodGroup;
    private RadioButton radioBtnMale, radioBtnFemale;
    private Button btnAdd, btnGuest,btnMember,btnAdmin;
    RadioGroup radioGroupGender;
    TextView toolbarTitle;
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_description_user);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);
        spinnerCity = (MaterialBetterSpinner) findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);

        radioBtnMale = (RadioButton) findViewById(R.id.radio_female);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_male);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnGuest = (Button) findViewById(R.id.btn_add_guest);
        btnMember = (Button) findViewById(R.id.btn_add_member);
        btnAdmin = (Button) findViewById(R.id.btn_add_admin);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupSpinner();
        toolbarTitle.setText("Thêm thông tin người dùng");
        addEventCheckLogin();
    }

    private void addEventCheckLogin() {

    }

    private Bundle setDataBundelForIntent(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(Contraint.CHECK_LOGIN,text);
        return bundle;
    }


    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
    }

}
