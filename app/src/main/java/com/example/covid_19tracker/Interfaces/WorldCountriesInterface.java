package com.example.covid_19tracker.Interfaces;

import com.example.covid_19tracker.Custom_classes.Country;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WorldCountriesInterface {
    @GET("{country}")
    Call<Country> getWorldCountries(@Path("country")String country);

}
