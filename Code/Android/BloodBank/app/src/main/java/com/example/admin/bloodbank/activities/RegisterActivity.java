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
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.utils.TemplateUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 25/01/2017.
 */

public class RegisterActivity extends TemplateActivity implements Validator.ValidationListener {
    private Validator validator;
    private Button btnRegister;
    private MaterialBetterSpinner spinnerDistrict, spinnerBloodGroup,spinnerCity;
    private RadioButton radioBtnMale, radioBtnFemale;
    private RadioGroup radioGroupGender;

    @Order(1)
    @NotEmpty(message = "Email không được bỏ trống", sequence = 1)
    @Email(message = "Email phải đúng định dạng", sequence = 2)
    private EditText edtEmail;
    @NotEmpty(message = "Mật khẩu không được bỏ trống", sequence = 1)
    @Password(min = 6, message = "Mật khẩu phải trên 6 kí tự", sequence = 2)
    @Order(2)
    private EditText edtPassword;
    @Order(3)
    @ConfirmPassword(message = "Phải trùng với mật khẩu!")
    private EditText edtConfirmPassword;
    @Order(4)
    @Pattern(regex = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ\" +            \"ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ\" +            \"ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s]+$", message = "Họ và Tên không chứa ký tự số")
    private EditText edtFullName;
    @Pattern(regex = "^((\\+){0,1}((841[0-9]{9})|(849[0-9]{8})))$|^(09[0-9]{8})$|^(01[0-9]{9})$", message = "Số điện thoại sai định dạng")
    @Order(5)
    private EditText edtPhone;
    private EditText edtDateOfBirth;
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
        spinnerCity = (MaterialBetterSpinner) findViewById(R.id.spinner_city);
        radioBtnMale = (RadioButton) findViewById(R.id.radio_male);
        radioBtnFemale = (RadioButton) findViewById(R.id.radio_female);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        edtDateOfBirth = (EditText) findViewById(R.id.edt_date_of_birth);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
        edtFullName = (EditText) findViewById(R.id.edt_fullname);
        edtPhone = (EditText) findViewById(R.id.edt_phone_number);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        TemplateUtils.hideSoftKeyboard(getContext());
        setupSpinner();
        isCheckedGender();
        toolbarTitle.setText("Đăng ký tài khoản");
        setupDatePickerForDateOfBirth();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
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
        spinnerCity.setAdapter(adapterCity);
    }

    @Override
    public void onValidationSucceeded() {
//        TemplateActivity.startActivity(RegisterActivity.this, LoginActivity.class, null);
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
