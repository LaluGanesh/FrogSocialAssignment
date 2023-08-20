package com.example.frogsocialassignment.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.frogsocialassignment.Ui.MovieListActivity;
import com.example.frogsocialassignment.Ui.MovieListPresenter;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

    }

    /**
     * get layout to inflate
     */
    public abstract
    @LayoutRes
    int getLayout();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
