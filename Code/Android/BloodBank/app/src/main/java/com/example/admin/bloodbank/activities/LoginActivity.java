package com.example.admin.bloodbank.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;

/**
 * Created by Admin on 09/01/2017.
 */

public class LoginActivity extends TemplateActivity {
    Button btnFacebook,btnLogin;
    TextView tvForgotPassword,tvRegister;

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
                Intent intent = new Intent(LoginActivity.this,AddDescriptionUserActivity.class);
                startActivity(intent);
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
