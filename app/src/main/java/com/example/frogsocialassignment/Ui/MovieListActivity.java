package com.example.frogsocialassignment.Ui;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.frogsocialassignment.R;
import com.example.frogsocialassignment.Room.AppDataBase;
import com.example.frogsocialassignment.Ui.login.LoginActivity;
import com.example.frogsocialassignment.Ui.signup.SignupActivity;
import com.example.frogsocialassignment.base.BaseActivity;
import com.example.frogsocialassignment.shaerdpreference.SharedPreferenceData;
import com.google.gson.Gson;

import java.util.List;

public class MovieListActivity extends BaseActivity implements MovieListContract.MvpView {

    private MovieListPresenter movieListPresenter;
    Context mContext;
    private AppDataBase appDataBase;
    RecyclerView rvMovieList;
    private GridLayoutManager gridLayoutManager;
    private MovieListAdapter movieListAdapter;

    private SharedPreferenceData sharedPreferenceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = MovieListActivity.this;
        appDataBase = AppDataBase.getRoomDatabase(mContext);
        sharedPreferenceData = new SharedPreferenceData(mContext);

        rvMovieList = findViewById(R.id.rvMovieList);
        movieListPresenter = new MovieListPresenter(this, mContext);
        movieListPresenter.onGetMovieList();
        rvMovieList.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        ViewCompat.setNestedScrollingEnabled(rvMovieList, false);
        rvMovieList.setLayoutManager(gridLayoutManager);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    public void onShowErrorDialog(String title, String message, boolean isFinishActivity) {

    }

    @Override
    public void getResponse(MovieListResponseModel movieListResponseModel) {

        // List<MovieListResponseModel> movieListResponseModels=   appDataBase.cartDao().getAll();
        movieListAdapter = new MovieListAdapter(mContext, movieListResponseModel.getSearch());
        rvMovieList.setAdapter(movieListAdapter);


    }

    @Override
    public void showShimmer() {

    }

    @Override
    public void hideShimmer(boolean b) {

    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {

    }

    @Override
    public void onShowToast(String message) {

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                appDataBase.cartDao().deleteAll();
                sharedPreferenceData.deleteAllPreference();
                Intent i = new Intent(MovieListActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Code to be executed when the negative button is clicked
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}