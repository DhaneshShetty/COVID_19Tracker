package com.example.covid_19tracker.Interfaces;

import com.example.covid_19tracker.Custom_classes.ApiData;
import com.example.covid_19tracker.Custom_classes.DistrictDatum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StateDataInterface {
    @GET("data.json")
    Call<ApiData> getStatesData();
    @GET("/v2/state_district_wise.json")
    Call<ArrayList<DistrictDatum>> getDistrictData();
}
