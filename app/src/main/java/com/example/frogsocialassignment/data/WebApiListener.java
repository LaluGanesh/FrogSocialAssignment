package com.example.frogsocialassignment.data;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebApiListener {

    @Headers("Content-Type: application/json")
    @GET("?i=tt3896198&apikey=2e60c8ff&s=love&y=2000")
    Call<String> getMovieList();

}
