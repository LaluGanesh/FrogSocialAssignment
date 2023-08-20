package com.example.frogsocialassignment.Ui.splashscreen;



import android.app.job.JobScheduler;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;


import com.example.frogsocialassignment.shaerdpreference.SharedPreferenceData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.MvpView mvpView;
    private String TAG = "SplashPresenter";
    private Context mContext;
    SharedPreferenceData sharedPreferenceData;


    public SplashPresenter(SplashContract.MvpView mvpView, Context mContext) {
        this.mvpView = mvpView;
        this.mContext = mContext;
        sharedPreferenceData = new SharedPreferenceData(mContext);
    }

    @Override
    public void onStartSplashTimer() {

    }













}
