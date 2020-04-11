package com.example.covid_19tracker.Interfaces;

import com.example.covid_19tracker.Custom_classes.ApiData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StateDataInterface {
    @GET("data.json")
    Call<ApiData> getStatesData();
}
