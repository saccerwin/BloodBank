package com.example.admin.bloodbank.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.contraints.Contraint;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 25/01/2017.
 */

public class RegisterActivity extends TemplateActivity {
    private Button btnRegister;
    private MaterialBetterSpinner spinnerCity, spinnerDistrict, spinnerTimeDonation, spinnerBloodGroup;
    private RadioButton radioBtnMale, radioBtnFemale;
    RadioGroup radioGroupGender;
    EditText edtDateOfBirth;
    TextView toolbarTitle;
    final Calendar calendar = Calendar.getInstance();
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        btnRegister = (Button)findViewById(R.id.btn_register);

        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);
        spinnerCity = (MaterialBetterSpinner) findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        spinnerTimeDonation = (MaterialBetterSpinner) findViewById(R.id.spinner_time_donation);

        radioBtnMale = (RadioButton) findViewById(R.id.radio_female);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_male);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        edtDateOfBirth = (EditText)findViewById(R.id.edt_date_of_birth);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {


        setupSpinner();
        isCheckedGender();
        toolbarTitle.setText("Đăng ký tài khoản");
        setupDatePickerForDateOfBirth();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemplateActivity.startActivity(RegisterActivity.this,AddDescriptionUserActivity.class,null);
            }
        });
    }
    private void setupDatePickerForDateOfBirth() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(final DatePicker view, final int year, final int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);
                updateLabel();

            }
        };

        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, date, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                String dateMin = "01/01/1955";
                dialog.getDatePicker().setMinDate(new Date(dateMin).getTime());
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });
    }

    private void updateLabel() {
        String fomatDate = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(fomatDate, Locale.US);
        edtDateOfBirth.setText(sdf.format(calendar.getTime()));
    }


    private Bundle setDataBundelForIntent(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(Contraint.CHECK_LOGIN,text);
        return bundle;
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
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterTimeDonation = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listTimeDonation);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerTimeDonation.setAdapter(adapterTimeDonation);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
    }
}
