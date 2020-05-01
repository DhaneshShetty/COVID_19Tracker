package com.example.covid_19tracker.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.covid_19tracker.Custom_classes.Country;
import com.example.covid_19tracker.Interfaces.WorldCountriesInterface;
import com.example.covid_19tracker.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {
    private TextInputLayout searchLayout;
    private TextView country, totalCases, activeCases, deaths, todayCase, todayDeaths, critical, recovered;
    private ImageView flag;
    private TextInputEditText search;
    private View root, contain;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/v2/countries/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        initViews();

        searchLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (search.getText() != null) {
                        String searchCountry = Objects.requireNonNull(search.getText()).toString();
                        WorldCountriesInterface countriesInterface = retrofit.create(WorldCountriesInterface.class);
                        Call<Country> call = countriesInterface.getWorldCountries(searchCountry);
                        Log.d("World", "call done");
                        progressBar.setVisibility(View.VISIBLE);
                        call.enqueue(new Callback<Country>() {
                            @Override
                            public void onResponse(Call<Country> call, Response<Country> response) {
                                if (response.body() != null) {
                                    country.setText(String.valueOf(response.body().getCountry()));
                                    totalCases.setText(String.valueOf(response.body().getCases()));
                                    activeCases.setText(String.valueOf(response.body().getActive()));
                                    deaths.setText(String.valueOf(response.body().getDeaths()));
                                    todayDeaths.setText(String.valueOf(response.body().getTodayDeaths()));
                                    critical.setText(String.valueOf(response.body().getCritical()));
                                    recovered.setText(String.valueOf(response.body().getRecovered()));
                                    Glide.with(Objects.requireNonNull(getActivity()))
                                            .load(response.body().getCountryInfo().getFlag())
                                            .into(flag);
                                    todayCase.setText(String.valueOf(response.body().getTodayCases()));
                                    contain.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
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
                    View view = root.findViewById(R.id.contains);
                    Snackbar snackbar = Snackbar.make(view, "Please Check Your Network Connectivity", Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.parseColor("#FF0000"));
                    snackbar.show();
                }
            }

        });
        return root;
    }

    private void initViews() {
        contain = root.findViewById(R.id.container);
        country = root.findViewById(R.id.country1);
        flag = root.findViewById(R.id.flag);
        totalCases = root.findViewById(R.id.total_cases);
        activeCases = root.findViewById(R.id.active_cases);
        deaths = root.findViewById(R.id.death);
        todayCase = root.findViewById(R.id.today_cases);
        todayDeaths = root.findViewById(R.id.today_death);
        critical = root.findViewById(R.id.critical);
        recovered = root.findViewById(R.id.recovered);
        search = root.findViewById(R.id.searchbar);
        progressBar = root.findViewById(R.id.pro);
        searchLayout = root.findViewById(R.id.search_plate);

    }

}