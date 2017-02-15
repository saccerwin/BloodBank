package com.example.admin.bloodbank.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.managers.SPManager;
import com.example.admin.bloodbank.utils.TemplateUtils;
import com.example.admin.bloodbank.utils.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Admin on 09/01/2017.
 */

public class LoginActivity extends TemplateActivity {
    private Button btnFacebook, btnLogin;
    private TextView tvForgotPassword, tvRegister;
    private EditText edtEmail, edtPassword;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private String email;
    private String password;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        btnFacebook = (Button) findViewById(R.id.btn_facebook);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        if (!TemplateUtils.checkInternetConnection(getContext()))  {
            ToastUtil.showLong(getContext(),"Lỗi ko truy cập được mạng! Vui lòng kiểm tra lại kết nối!");
        }

        actionOnClick();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) { // check user  exist not signout
            Bundle bundleCheckLogin = new Bundle();
            bundleCheckLogin.putString(Contraint.CHECK_LOGIN, SPManager.getInstance(getContext()).getDecentralization());
            TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, bundleCheckLogin);
        }
    }

    private void actionOnClick() {
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddDescriptionUserActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                showProgressDialog();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Contraint.CHECK_LOGIN, Contraint.DECENTRALIZATION_USER);
                            SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_USER);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, bundle);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                if(email.equals("a@gmail.com") && password.equals("123456")) {
//                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_USER);
//                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_USER);
//                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
//                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
//                }
//                else if(email.equals("ab@gmail.com") && password.equals("123456")) {
//                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_MEMBER);
//                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
//                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_MEMBER);
//                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
//                }
//                else if(email.equals("abc@gmail.com") && password.equals("123456")) {
//                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_ADMIN);
//                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
//                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_ADMIN);
//                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
//                }
//                else if(email.equals("") || password.equals("")) {
//                    Toast.makeText(LoginActivity.this,"Email và password ko được rỗng",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
//                    edtEmail.getText().clear();
//                    edtPassword.getText().clear();
//                }


            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý...");
        progressDialog.show();
    }
}
