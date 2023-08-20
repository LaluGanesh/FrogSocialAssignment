package com.example.frogsocialassignment.Room.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogsocialassignment.MovieListResponseModel;
import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.Room.Entity.UserLogin;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * from " + Constants.ROOM_DATABASE_USER_LOGIN + " WHERE email = (:email) AND passWord = (:password)")
    UserLogin userLogin(String email, String password);

    @Query("SELECT * FROM " + Constants.ROOM_DATABASE_USER_LOGIN + " WHERE email = (:email)")
    UserLogin getAccount(String email);

    @Insert
    void insertUserRegister(UserLogin userLogin);

    @Insert
    void insert(MovieListResponseModel movieListResponseModel);

    @Query("SELECT * FROM "+ Constants.ROOM_DATABASE_MOVIE_LIST)
    List<MovieListResponseModel> getAll();

    @Query("DELETE FROM "+ Constants.ROOM_DATABASE_USER_LOGIN)
    void deleteAll();
}
