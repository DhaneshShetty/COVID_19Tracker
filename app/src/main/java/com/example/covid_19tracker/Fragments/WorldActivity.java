package com.example.covid_19tracker.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.covid_19tracker.Custom_classes.Country;
import com.example.covid_19tracker.Interfaces.WorldCountriesInterface;
import com.example.covid_19tracker.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class WorldActivity extends AppCompatActivity {
    private TextInputLayout searchLayout;
    private TextView country, totalCases, activeCases, deaths, todayCase, todayDeaths, critical, recovered;
    private ImageView flag;
    private TextInputEditText search;
    private View root, contain;
    Group group;
    MaterialButton backButton;
    private ProgressBar progressBar;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        final View contain=findViewById(R.id.contains);
        final int x=contain.getRight();
        final int y=contain.getBottom();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/countries/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        initViews();

        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator anima= ViewAnimationUtils.createCircularReveal(contain,x,y, (float) Math.hypot(contain.getWidth(),contain.getHeight()),0);
                anima.setDuration(400);
                anima.setInterpolator(new LinearInterpolator());
                anima.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        group.setVisibility(View.GONE);
                        flag.setVisibility(View.GONE);
                        country.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        searchLayout.setVisibility(View.VISIBLE);
                        country.setText("World");
                        country.setVisibility(View.VISIBLE);



                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anima.start();

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorldActivity.this,MainActivity.class));
            }
        });

        searchLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE));
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (search.getText() != null) {
                        String searchCountry = Objects.requireNonNull(search.getText()).toString();
                        WorldCountriesInterface countriesInterface = retrofit.create(WorldCountriesInterface.class);
                        Call<Country> call = countriesInterface.getWorldCountries(searchCountry);
                        Log.d("World", "call done");
                        call.enqueue(new Callback<Country>() {
                            @Override
                            public void onResponse(Call<Country> call, Response<Country> response) {
                                if (response.body() != null) {
                                    country.setText(String.valueOf(response.body().getCountry()));
                                    totalCases.setText(String.valueOf(response.body().getCases()));
                                    activeCases.setText(String.valueOf(response.body().getActive()));
                                    deaths.setText(String.valueOf(response.body().getDeaths()));
                                    todayDeaths.setText(String.valueOf(response.body().getTodayDeaths()));
                                    recovered.setText(String.valueOf(response.body().getRecovered()));
                                    Glide.with(WorldActivity.this)
                                            .load(response.body().getCountryInfo().getFlag())
                                            .into(flag);
                                    todayCase.setText(String.valueOf(response.body().getTodayCases()));

                                    Animator anim= ViewAnimationUtils.createCircularReveal(contain,x,y,0, (float) Math.hypot(contain.getWidth(),contain.getHeight()));
                                    anim.setDuration(400);
                                    anim.setInterpolator(new LinearInterpolator());
                                    anim.addListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                            searchLayout.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {


                                            group.setVisibility(View.VISIBLE);
                                            flag.setVisibility(View.VISIBLE);


                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    });
                                    anim.start();

                                } else {
                                    View view = root.findViewById(R.id.contains);
                                    Snackbar snackbar = Snackbar.make(view, "Please Type Proper Country Name", Snackbar.LENGTH_LONG);
                                    snackbar.setBackgroundTint(Color.parseColor("#FF0000"));
                                    snackbar.show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Country> call, Throwable throwable) {
                                Log.e("World", "Error", throwable);

                            }
                        });
                    } else {
                        searchLayout.setError("Required");
                    }

                } else {
                    View view = findViewById(R.id.contains);
                    Snackbar snackbar = Snackbar.make(view, "Please Check Your Network Connectivity", Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.parseColor("#FF0000"));
                    snackbar.show();
                }
            }

        });

    }

    private void initViews() {

        country = findViewById(R.id.countryName_world);
        flag = findViewById(R.id.flag);
        backButton=findViewById(R.id.back_button);
        totalCases = findViewById(R.id.total_number_world);
        activeCases = findViewById(R.id.active_number_world);
        deaths = findViewById(R.id.death_number_world);
        todayCase = findViewById(R.id.total_delta_world);
        todayDeaths = findViewById(R.id.death_delta_world);
        recovered =findViewById(R.id.recovered_number_world);
        search = findViewById(R.id.searchbar);
        searchLayout = findViewById(R.id.search_plate);
        group  =findViewById(R.id.group_all);

    }
    public void hideKeyboard(){
        InputMethodManager inputManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        assert inputManager != null;
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }

}