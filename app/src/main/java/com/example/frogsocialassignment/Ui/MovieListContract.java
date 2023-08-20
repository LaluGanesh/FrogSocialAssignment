package com.example.frogsocialassignment.Ui;

import com.example.frogsocialassignment.base.MvpBase;

public interface MovieListContract {

    interface MvpView extends MvpBase {
        void onShowErrorDialog(String title, String message, boolean isFinishActivity);

        void getResponse(MovieListResponseModel movieListResponseModel);

        void showShimmer();
        void hideShimmer(boolean b);
    }

    interface Presnter {
        void onGetMovieList();

    }

}
