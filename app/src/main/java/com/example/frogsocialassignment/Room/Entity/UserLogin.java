package com.example.frogsocialassignment.Room.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.example.frogsocialassignment.Room.Constants;

import java.util.Date;

@Entity(tableName = Constants.ROOM_DATABASE_USER_LOGIN)
public class UserLogin {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String userName;
    private String email;
    private String passWord;
    private Integer isBiometric;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getIsBiometric() {
        return isBiometric;
    }

    public void setIsBiometric(Integer isBiometric) {
        this.isBiometric = isBiometric;
    }
}
