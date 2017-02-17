package com.example.admin.bloodbank.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.managers.SPManager;
import com.example.admin.bloodbank.objects.User;
import com.example.admin.bloodbank.utils.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private MaterialBetterSpinner spinnerDistrict, spinnerBloodGroup, spinnerCity;
    public ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private RadioButton radioSexButton;
    private String email;
    private String password;
    private String imagesAvatarUrl;
    private String fullname;
    private RadioGroup radioGroup;

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
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_gender);
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
        setupSpinner();
        toolbarTitle.setText("Đăng ký tài khoản");
        setupDatePickerForDateOfBirth();
        showSpinnerDistictForCity();
        //setup using Firebase
        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
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


    private String getValueGender() {
        int selectId = radioGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectId);
        imagesAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/bloodbank-1e50e.appspot.com/o/avatar_default.jpg?alt=media&token=045117c4-b4b0-45a4-a034-b2f74e3d522c";
        return radioSexButton.getText().toString();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý...");
        progressDialog.show();
    }

    private void setupSpinner() {
        String[] listCity = getResources().getStringArray(R.array.city);
        String[] listBloodGroup = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listCity);
        ArrayAdapter<String> adapterBloodGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listBloodGroup);
        spinnerBloodGroup.setAdapter(adapterBloodGroup);
        spinnerCity.setAdapter(adapterCity);
    }


    private void showSpinnerDistictForCity() { // enable spinner distict on a display spinner city change data
        spinnerDistrict.setEnabled(false);
        spinnerCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerDistrict.setEnabled(true);
                fillDataDistrictWithCity();

            }

        });
    }

    private void fillDataDistrictWithCity() {
        String city = spinnerCity.getText().toString().trim();
        String[] district = null;
        if (city.equals("Đà Nẵng")) {
            district = getResources().getStringArray(R.array.district_danang);
        }
        if (city.equals("Hà Nội")) {
            district = getResources().getStringArray(R.array.district_hanoi);
        }
        if (city.equals("Hồ Chí Minh")) {
            district = getResources().getStringArray(R.array.district_hochiminh);
        }
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, district);
        spinnerDistrict.setAdapter(adapterDistrict);
    }

    @Override
    public void onValidationSucceeded() {
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        fullname = edtFullName.getText().toString().trim();
        showProgressDialog();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressDialog();
                if (task.isSuccessful()) {
                    String distict = spinnerDistrict.getText().toString().trim();
                    String city = spinnerCity.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();
                    String dataOfBirth = edtDateOfBirth.getText().toString().trim();
                    String typeBlood = spinnerBloodGroup.getText().toString().trim();
                    createNewUser("None", "None", Contraint.DECENTRALIZATION_USER, email, password, fullname, dataOfBirth, getValueGender(), phone, city, distict, imagesAvatarUrl, 0, typeBlood, false);
                    // update name, and photo for firebase user
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fullname)
                                .setPhotoUri(Uri.parse(imagesAvatarUrl))
                                .build();
                        user.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(Contraint.TAG, "User profile updated.");

                                }
                            }
                        });
                        ToastUtil.showLong(getContext(), "Đăng ký thành công!");
                        SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_USER);
                        TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, null);
                        finish();
                    }


                } else {
                    ToastUtil.showLong(getContext(), "Đăng ký thất bại! \n Error: " + task.getException().getMessage());
                }
            }
        });
    }


    private void createNewUser(String id_club, String id_discuss, String permission, String email, String password, String fullName, String dateOfBirth, String gender, String phone, String city, String district, String url_images_avatar, int quality_donation, String type_blood, boolean isCheckDonation) {
        User user = new User(id_club, id_discuss, permission, email, password, fullName, dateOfBirth, gender, phone, city, district, url_images_avatar, quality_donation, type_blood, isCheckDonation);
        mFirebaseDatabase.push().setValue(user);
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
