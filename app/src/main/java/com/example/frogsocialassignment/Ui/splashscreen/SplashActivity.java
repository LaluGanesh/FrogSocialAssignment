package com.example.frogsocialassignment.Ui.splashscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.frogsocialassignment.R;
import com.example.frogsocialassignment.Room.AppDataBase;
import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.Room.Entity.UserLogin;
import com.example.frogsocialassignment.Ui.MainThreadExecutor;
import com.example.frogsocialassignment.Ui.MovieListActivity;
import com.example.frogsocialassignment.Ui.login.LoginActivity;
import com.example.frogsocialassignment.base.BaseActivity;
import com.example.frogsocialassignment.shaerdpreference.SharedPreferenceData;
import com.example.frogsocialassignment.util.NetworkUtil;

import java.util.concurrent.Executor;

public  class SplashActivity extends BaseActivity implements SplashContract.MvpView {

    private SplashPresenter splashPresenter;
    private String TAG = "SplashActivity";
    private Context mContext;
    private SharedPreferenceData sharedPreferenceData;

    private BiometricPrompt biometricPrompt = null;
    private Executor executor = new MainThreadExecutor();
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=SplashActivity.this;
        sharedPreferenceData=new SharedPreferenceData(mContext);
        appDataBase = AppDataBase.getRoomDatabase(mContext);
        splashPresenter = new SplashPresenter(this,SplashActivity.this);
        onSplashTimerCompleted();
    }


    @Override
    public int getLayout() {
        return R.layout.layout_splash;
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

    private void init() {

    }




    @Override
    public void onRetry() {
        onCheckInternetConnection();
    }

    @Override
    public void onSplashTimerCompleted() {

        new Handler().postDelayed(() -> checkLogin(),2000);

    }

    private void checkLogin() {
        if(sharedPreferenceData.getBool(Constants.IS_LOGIN.toString())){
            UserLogin userLogin = appDataBase.cartDao().getAccount(sharedPreferenceData.getString(Constants.USER_EMAIL.toString()));
            if(userLogin.getIsBiometric() == 1){
                bioMetric();
            }else {
                Intent intent = new Intent(this, MovieListActivity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    public void bioMetric(){

        if (biometricPrompt == null) {
            biometricPrompt = new BiometricPrompt(this, executor, callback);
        }
        BiometricPrompt.PromptInfo promptInfo = buildBiometricPrompt();
        biometricPrompt.authenticate(promptInfo);

    }



    @Override
    protected void onResume() {
        super.onResume();

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
            Intent i = new Intent(SplashActivity.this, MovieListActivity.class);
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
    protected void onPause() {
        super.onPause();

    }
    @Override
    public void onFinishActivity() {
        this.finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        onFinishActivity();
    }





}