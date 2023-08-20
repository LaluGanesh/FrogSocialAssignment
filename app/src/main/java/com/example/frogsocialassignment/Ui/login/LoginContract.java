package com.example.frogsocialassignment.Ui.login;

import com.example.frogsocialassignment.base.MvpBase;

public interface LoginContract {

    interface MvpView extends MvpBase {
        void onShowErrorDialog(String title, String message, boolean isFinishActivity);

        void getResponse();

        void showShimmer();
        void hideShimmer(boolean b);

        void checkLogin(String email,String password);
    }

    interface Presnter {
        void onLogin();

    }

}
