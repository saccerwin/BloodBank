package com.example.admin.bloodbank.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
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
    private Button btnLogin;
    private TextView tvForgotPassword, tvRegister;
    private EditText edtEmail, edtPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mFirebaseDatabase;
    private CallbackManager mCallbackManager;
    private ProgressDialog progressDialog;
    private String email;
    private String password;
    private LoginButton btnLoginFacebook;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLoginFacebook = (LoginButton) findViewById(R.id.btn_login_facebook);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        // check connection internet
        if (!TemplateUtils.checkInternetConnection(getContext())) {
            ToastUtil.showLong(getContext(), "Lỗi ko truy cập được mạng! Vui lòng kiểm tra lại kết nối!");
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        // handling firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, null);
            finish();
        }
        checkLogin();
        // check user
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    if (AccessToken.getCurrentAccessToken() != null) {
//                        TemplateActivity.startActivity(LoginActivity.this, NavigationDrawerMainActivity.class, null);
//                        finish();
//                    }
//
//                    Log.d(Contraint.TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    mFirebaseDatabase.child(Contraint.FIREBASE_TREE_USER).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                                if (userSnapshot.child("email").getValue().equals(email)) {
//                                    String permissionUser = userSnapshot.child(Contraint.FIREBASE_USERS_PERMISSION).getValue().toString();
////                                            Log.d(Contraint.TAG, "onDataChange permission: " + permission);
//                                    SPManager.getInstance(getContext()).setDecentralization(permissionUser);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString(Contraint.CHECK_LOGIN, permissionUser);
//                                    TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, bundle);
//                                    ToastUtil.showShort(getContext(), getString(R.string.login_success));
//                                    finish();
//                                    break;
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//                } else {
//                    // User is signed out
//                    Log.d(Contraint.TAG, "onAuthStateChanged:signed_out");
//                    signOut();
//                }
//            }
//        };

    }

    private void loginWithFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        btnLoginFacebook.setReadPermissions("email", "public_profile");

        btnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(Contraint.TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void checkLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                showProgressDialog();
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    ToastUtil.showShort(getContext(), "Email và password không được rỗng");
                    hideProgressDialog();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                hideProgressDialog();
                                // get decentralization user
                                mFirebaseDatabase.child(Contraint.FIREBASE_TREE_USER).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                            if (userSnapshot.child("email").getValue().equals(email)) {
                                                String permissionUser = userSnapshot.child(Contraint.FIREBASE_USERS_PERMISSION).getValue().toString();
//                                            Log.d(Contraint.TAG, "onDataChange permission: " + permission);
                                                SPManager.getInstance(getContext()).setDecentralization(permissionUser);
                                                ToastUtil.showLong(getContext(), "Đăng nhập thành công!");
                                                TemplateActivity.startActivity(getContext(), NavigationDrawerMainActivity.class, null);
                                                finish();
                                                break;
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        hideProgressDialog();
                                        ToastUtil.showShort(getContext(), databaseError.getMessage());
                                    }
                                });

                            } else {
                                hideProgressDialog();
                                edtEmail.getText().clear();
                                edtPassword.getText().clear();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!\nError: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithFacebook();
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
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(Contraint.TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(Contraint.TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(Contraint.TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            SPManager.getInstance(getContext()).clear();
                        } else {
                            SPManager.getInstance(getContext()).setDecentralization(Contraint.DECENTRALIZATION_USER);
                            TemplateActivity.startActivity(LoginActivity.this, NavigationDrawerMainActivity.class, null);
                            finish();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });

    }

    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();

//        updateUI(null);
    }

    // [START on_start_add_listener]
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//    // [END on_start_add_listener]
//
//    // [START on_stop_remove_listener]
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
    // [END on_stop_remove_listener]


}
