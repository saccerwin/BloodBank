package com.example.admin.bloodbank.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 09/01/2017.
 */

public class LoginActivity extends TemplateActivity {
    private Button btnFacebook, btnLogin;
    private TextView tvForgotPassword, tvRegister;
    private EditText edtEmail, edtPassword;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
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

        // check connection internet
        if (!TemplateUtils.checkInternetConnection(getContext()))  {
            ToastUtil.showLong(getContext(),"Lỗi ko truy cập được mạng! Vui lòng kiểm tra lại kết nối!");
        }
        // handling firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        actionOnClick();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) { // check user  exist not signout
            TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, null);
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
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    ToastUtil.showShort(getContext(),"Email và password không được rỗng");
                    hideProgressDialog();
                }
                else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideProgressDialog();
                            if (task.isSuccessful()) {
                                // get decentralization user
                                mFirebaseDatabase.child(Contraint.FIREBASE_TREE_USER).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                            if(userSnapshot.child("email").getValue().equals(email)) {
                                                String permissionUser = userSnapshot.child(Contraint.FIREBASE_USERS_PERMISSION).getValue().toString();
//                                            Log.d(Contraint.TAG, "onDataChange permission: " + permission);
                                                SPManager.getInstance(getContext()).setDecentralization(permissionUser);
                                                Bundle bundle = new Bundle();
                                                bundle.putString(Contraint.CHECK_LOGIN,permissionUser);
                                                bundle.putString(Contraint.PROFILE_EMAIL,email);
                                                bundle.putString(Contraint.PROFILE_PASSWORD,password);
                                                TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, bundle);
                                                finish();
                                                break;
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        ToastUtil.showShort(getContext(),databaseError.getMessage());
                                    }
                                });

                            } else {
                                edtEmail.getText().clear();
                                edtPassword.getText().clear();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!\nError: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }




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
