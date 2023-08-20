package com.example.frogsocialassignment.Ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.frogsocialassignment.R;
import com.example.frogsocialassignment.Room.AppDataBase;
import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.Room.Entity.UserLogin;
import com.example.frogsocialassignment.Ui.login.LoginActivity;
import com.example.frogsocialassignment.base.BaseActivity;
import com.example.frogsocialassignment.shaerdpreference.SharedPreferenceData;
import com.example.frogsocialassignment.util.AppUtils;

public class SignupActivity extends BaseActivity implements SignupContract.MvpView {

    private EditText et_userName;
    private EditText et_email;
    private EditText et_passWord;
    private EditText et_confirmPassword;
    private Switch st_bioMetric;

    private Button bt_sinUp;
    private Context mContext;
    private AppDataBase appDataBase;
    private SharedPreferenceData sharedPreferenceData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SignupActivity.this;
        et_userName = findViewById(R.id.fullname);
        et_email = findViewById(R.id.email);
        et_passWord = findViewById(R.id.password);
        et_confirmPassword = findViewById(R.id.conform_password);
        st_bioMetric = findViewById(R.id.biometric);
        bt_sinUp = findViewById(R.id.loginButton);
        appDataBase = AppDataBase.getRoomDatabase(mContext);
        sharedPreferenceData = new SharedPreferenceData(mContext);
        bt_sinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_userName.getText().toString().trim().equals("") ||
                        et_userName.getText().toString().trim().equals("") ||
                        et_email.getText().toString().trim().equals("") ||
                        et_passWord.getText().toString().trim().equals("") ||
                        et_confirmPassword.getText().toString().trim().equals("")) {
                    onShowToast(getString(R.string.required_all_fields));
                    return;
                }
                if (!et_passWord.getText().toString().trim().equals(et_confirmPassword.getText().toString().trim())) {
                    onShowToast(getString(R.string.password_and_conform_password_must_be_same));
                    return;
                }
                if (appDataBase.cartDao().getAccount(et_email.toString()) != null) {
                    onShowToast(getString(R.string.email_already_exist));
                    return;
                } else {
                    UserLogin userLogin = new UserLogin();
                    userLogin.setEmail(et_email.getText().toString().trim());
                    userLogin.setUserName(et_userName.getText().toString().trim());
                    userLogin.setPassWord(et_passWord.getText().toString().trim());
                    if (st_bioMetric.isChecked()) {
                        userLogin.setIsBiometric(1);
                        sharedPreferenceData.setBool(Constants.IS_BIOMETRIC.toString(), true);
                    } else {
                        sharedPreferenceData.setBool(Constants.IS_BIOMETRIC.toString(), false);
                        userLogin.setIsBiometric(0);
                    }
                    appDataBase.cartDao().insertUserRegister(userLogin);
                    onShowToast(getString(R.string.register_done_successfully));

                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.layout_signup;
    }

    @Override
    public void onShowErrorDialog(String title, String message, boolean isFinishActivity) {

    }

    @Override
    public void getResponse() {

    }

    @Override
    public void showShimmer() {

    }

    @Override
    public void hideShimmer(boolean b) {

    }

    @Override
    public void checkLogin(String email, String password) {

    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {

    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShowSnackBar(String message) {

    }

    @Override
    public void onShowAlertDialog(String title, String message, boolean isLoginRequired) {

    }

    @Override
    public void onCheckInternetConnection() {

    }

    @Override
    public void onRetry() {

    }
}
