package com.example.admin.bloodbank.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateActivity;

/**
 * Created by Admin on 25/01/2017.
 */

public class ForgotPasswordActivity extends TemplateActivity {
    TextView toolbarTitle;
    Button btnVerify;
    EditText edtEmail;
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forgot_password);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        btnVerify = (Button)findViewById(R.id.btn_verify);
        edtEmail = (EditText)findViewById(R.id.edt_email);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        toolbarTitle.setText("Quên mật khẩu");
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemplateActivity.startActivity(ForgotPasswordActivity.this,LoginActivity.class,null);
                finish();
            }
        });
    }
}
