package com.example.frogsocialassignment.Ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;

import com.example.frogsocialassignment.R;
import com.example.frogsocialassignment.Room.AppDataBase;
import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.Room.Entity.UserLogin;
import com.example.frogsocialassignment.Ui.MainThreadExecutor;
import com.example.frogsocialassignment.Ui.MovieListActivity;
import com.example.frogsocialassignment.Ui.signup.SignupActivity;
import com.example.frogsocialassignment.base.BaseActivity;
import com.example.frogsocialassignment.shaerdpreference.SharedPreferenceData;
import com.example.frogsocialassignment.util.AppUtils;

import java.util.concurrent.Executor;


public class LoginActivity extends BaseActivity implements LoginContract.MvpView {

    private EditText et_email;
    private EditText et_passWord;
    private Button bt_loginButton;
    private TextView tv_signUP;

    private Context mContext;
    private AppDataBase appDataBase;

    private BiometricPrompt biometricPrompt = null;
    private Executor executor = new MainThreadExecutor();
    private SharedPreferenceData sharedPreferenceData;

    @Override
    public int getLayout() {
        return R.layout.layout_login;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = LoginActivity.this;
        appDataBase = AppDataBase.getRoomDatabase(mContext);
        sharedPreferenceData = new SharedPreferenceData(mContext);
        et_email = findViewById(R.id.username);
        et_passWord = findViewById(R.id.password);
        bt_loginButton = findViewById(R.id.loginButton);
        tv_signUP = findViewById(R.id.signupText);

        bt_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin(et_email.getText().toString(), et_passWord.getText().toString());
            }
        });

        tv_signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {

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
        if (email != null && !email.trim().equals("") && password != null && !password.trim().equals("")) {
                if (appDataBase.cartDao().getAccount(email.toString()) != null) {
                    UserLogin userLogin = appDataBase.cartDao().userLogin(email.toString(), password.toString());
                    if (userLogin != null) {
                        sharedPreferenceData.setBool(Constants.IS_LOGIN.toString(), true);
                        sharedPreferenceData.setString(Constants.USER_EMAIL.toString(), email);
                        if (userLogin.getIsBiometric() == 1) {
                            sharedPreferenceData.setBool(Constants.IS_BIOMETRIC.toString(), true);
                            bioMetric();
                        } else {
                            Intent i = new Intent(this, MovieListActivity.class);
                            startActivity(i);
                        }
                    } else {
                        onShowToast(getString(R.string.please_enter_correct_password));
                    }

                } else {
                    onShowToast(getString(R.string.sorry_you_don_t_have_user_account_please_register));
                }


        } else {
            onShowToast(getString(R.string.please_enter_email_and_password));
        }

    }

    public void bioMetric() {

        if (biometricPrompt == null) {
            biometricPrompt = new BiometricPrompt(this, executor, callback);
        }
        BiometricPrompt.PromptInfo promptInfo = buildBiometricPrompt();
        biometricPrompt.authenticate(promptInfo);

    }

    private BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON && biometricPrompt != null)
                biometricPrompt.cancelAuthentication();
            toast(errString.toString());
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            toast("Authentication succeed");
            Intent i = new Intent(LoginActivity.this, MovieListActivity.class);
            startActivity(i);
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            toast("Application did not recognize the placed finger print. Please try again!");
        }

    };

    private BiometricPrompt.PromptInfo buildBiometricPrompt() {
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setSubtitle("Login into your account")
                .setDescription("Touch your finger on the finger print sensor to authorise your account.")
                .setNegativeButtonText("Cancel")
                .build();
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
