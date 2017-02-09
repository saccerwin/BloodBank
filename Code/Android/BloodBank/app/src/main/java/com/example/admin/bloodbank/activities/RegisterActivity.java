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
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
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
    private MaterialBetterSpinner spinnerDistrict, spinnerBloodGroup;
    private MaterialAutoCompleteTextView tvAutocompleteCity;
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
        btnRegister = (Button) findViewById(R.id.btn_register);
        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        tvAutocompleteCity = (MaterialAutoCompleteTextView) findViewById(R.id.autocomplete_tv_city);
        radioBtnMale = (RadioButton) findViewById(R.id.radio_male);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_female);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        edtDateOfBirth = (EditText) findViewById(R.id.edt_date_of_birth);
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
                TemplateActivity.startActivity(RegisterActivity.this, LoginActivity.class, null);
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


            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, date, mYear, mMonth, mDay);
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
