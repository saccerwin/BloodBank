package com.example.admin.bloodbank.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
 * Created by Admin on 17/01/2017.
 */

public class EditProfileActivity extends TemplateActivity {
    MaterialBetterSpinner spinnerCity, spinnerDistrict, spinnerBloodGroup;
    RadioButton radioBtnMale, radioBtnFemale;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText edtFullName, edtPhone, edtPassword, edtDateOfBirth;
    private RadioGroup radioGroupGender;
    private final  Calendar calendar = Calendar.getInstance();

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        radioBtnMale = (RadioButton) findViewById(R.id.radio_male);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_female);
        spinnerCity = (MaterialBetterSpinner) findViewById(R.id.spinner_city);
        spinnerDistrict = (MaterialBetterSpinner) findViewById(R.id.spinner_district);
        spinnerBloodGroup = (MaterialBetterSpinner) findViewById(R.id.spinner_blood_group);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        edtFullName = (EditText) findViewById(R.id.edt_fullname);
        edtPhone = (EditText) findViewById(R.id.edt_phone_number);
        edtDateOfBirth = (EditText) findViewById(R.id.edt_date_of_birth);
        edtPassword = (EditText) findViewById(R.id.edt_password);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupToobar();
        setupSpinner();
        setupDataField();
        setupDatePickerForDateOfBirth();

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
                int mYear = Integer.parseInt(edtDateOfBirth.getText().toString().split("/")[2]);
                int mMonth = Integer.parseInt(edtDateOfBirth.getText().toString().split("/")[1]);
                int mDay = Integer.parseInt(edtDateOfBirth.getText().toString().split("/")[0]);
                DatePickerDialog dialog = new DatePickerDialog(EditProfileActivity.this, date, mYear, mMonth, mDay);
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

    private void setupDataField() {
        Bundle bundle = getIntent().getExtras();
        String fullName = bundle.getString(Contraint.PROFILE_FULLNAME);
        String phone = bundle.getString(Contraint.PROFILE_PHONE);
        String password = bundle.getString(Contraint.PROFILE_PASSWORD);
        String dateOfBirth = bundle.getString(Contraint.PROFILE_DATEOFBIRTH);
        String gender = bundle.getString(Contraint.PROFILE_GENDER);
        String typeBlood = bundle.getString(Contraint.PROFILE_TYPEBLOOD);
        String distict = bundle.getString(Contraint.PROFILE_DISTICT);
        String city = bundle.getString(Contraint.PROFILE_CITY);
        edtFullName.setText(fullName);
        edtPassword.setText(password);
        edtPhone.setText(phone);
        edtDateOfBirth.setText(dateOfBirth);
        spinnerCity.setText(city);
        spinnerBloodGroup.setText(typeBlood);
        if (distict.equals("2")) {
            spinnerDistrict.setText("Quận Cẩm Lệ");
            spinnerCity.setText("Đà Nẵng");
        }
        if (gender.equals("Nữ")) {
            radioBtnFemale.setChecked(true);
            radioBtnMale.setChecked(false);
        } else {
            radioBtnFemale.setChecked(false);
            radioBtnMale.setChecked(true);
        }


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
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return false;
    }


    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listDistrict = getResources().getStringArray(R.array.district);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listDistrict);
        spinnerCity.setAdapter(adapterCity);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);

    }
}
