package com.example.frogsocialassignment.Ui;
import android.content.Context;
import android.util.Log;

import com.example.frogsocialassignment.Room.AppDataBase;
import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.data.ApiClient;
import com.example.frogsocialassignment.data.WebApiListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListPresenter implements MovieListContract.Presnter{

    private static final String TAG = "MovieListPresenter";
    private  MovieListContract.MvpView mvpView;
    private Context mContext;
    private AppDataBase appDataBase;
    public MovieListPresenter(MovieListContract.MvpView mvpView, Context mContext) {
        this.mvpView = mvpView;
        this.mContext = mContext;
      //  appDataBase = AppDataBase.getRoomDatabase(mContext.getApplicationContext());

    }





    @Override
    public void onGetMovieList() {

        try {
            WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
            Call<String> call = service.getMovieList();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == Constants.SUCCESS_CODE) {
                        if (response.body() != null) {
                            Type listType = new TypeToken<MovieListResponseModel>() {
                            }.getType();
                            MovieListResponseModel movieListResponseModel = new GsonBuilder().create().fromJson(response.body(), listType);
                           // appDataBase.cartDao().insert(movieListResponseModel);
                            mvpView.getResponse(movieListResponseModel);
                            //  appUpdateResponseModel.getData().get(0).setUser_version_code(10);

                        }
                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
