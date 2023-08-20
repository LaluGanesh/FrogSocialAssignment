package com.example.frogsocialassignment.Ui;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.frogsocialassignment.Room.Constants;
import com.example.frogsocialassignment.Ui.Search;

import java.util.List;
public class MovieListResponseModel {

    private List<Search> Search;
    private String totalResults;
    private String response;


    public List<Search> getSearch() {
        return Search;
    }

    public void setSearch(List<Search> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}


