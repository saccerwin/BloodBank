package com.example.admin.bloodbank.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;
import com.example.admin.bloodbank.contraints.Contraint;
import com.example.admin.bloodbank.managers.SPManager;

/**
 * Created by Admin on 09/01/2017.
 */

public class LoginActivity extends TemplateActivity {
    private Button btnFacebook,btnLogin;
    private TextView tvForgotPassword,tvRegister;
    private EditText edtEmail,edtPassword;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        btnFacebook = (Button)findViewById(R.id.btn_facebook);
        btnLogin = (Button)findViewById(R.id.btn_login);
        tvForgotPassword = (TextView)findViewById(R.id.tv_forgot_password);
        tvRegister = (TextView)findViewById(R.id.tv_register);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPassword = (EditText)findViewById(R.id.edt_password);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        actionOnClick();



    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void actionOnClick() {
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,AddDescriptionUserActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String email  = edtEmail.getText().toString().trim();
                String password  = edtPassword.getText().toString().trim();
                if(email.equals("a@gmail.com") && password.equals("123456")) {
                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_USER);
                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_USER);
                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("ab@gmail.com") && password.equals("123456")) {
                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_MEMBER);
                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_MEMBER);
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("abc@gmail.com") && password.equals("123456")) {
                    bundle.putString(Contraint.CHECK_LOGIN,Contraint.DECENTRALIZATION_ADMIN);
                    TemplateActivity.startActivity(getContext(),NavigationDrawerMainActivity.class,bundle);
                    SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_ADMIN);
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this,"Email và password ko được rỗng",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                    edtEmail.getText().clear();
                    edtPassword.getText().clear();
                }


            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
