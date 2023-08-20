package com.example.frogsocialassignment.Ui.splashscreen;

import com.example.frogsocialassignment.base.MvpBase;


public interface SplashContract {

    interface MvpView extends MvpBase {
        void onSplashTimerCompleted();
        void onFinishActivity();

    }

    interface Presenter{

        void onStartSplashTimer();


    }


}
