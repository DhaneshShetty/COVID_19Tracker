package com.example.covid_19tracker.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.covid_19tracker.Adapters.StateListAdapter;
import com.example.covid_19tracker.Custom_classes.ApiData;
import com.example.covid_19tracker.Custom_classes.Statewise;
import com.example.covid_19tracker.Interfaces.StateDataInterface;
import com.example.covid_19tracker.R;
import com.example.covid_19tracker.StateActivity;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView totalCaseIndia, totalCaseIndiaDelta, activeCaseIndia, activeCaseIndiaDelta, recoveredIndia, recoveredIndiaDelta, deathIndia, deathIndiaDelta;
MaterialButton worldButton,infoButton;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || networkInfo.isFailover()) {
            Toast.makeText(MainActivity.this, "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        initViews();
        MaterialButton learnMore=findViewById(R.id.learn_more_button);
        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StateActivity.class));
            }
        });
        worldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorldActivity.class));
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PrecautionsFragment.class));
            }
        });
        StateDataInterface dataInterface = retrofit.create(StateDataInterface.class);
        Call<ApiData> apiDataCall = dataInterface.getStatesData();
        apiDataCall.enqueue(new Callback<ApiData>() {
            @Override
            public void onResponse(Call<ApiData> call, Response<ApiData> response) {
                if (response != null) {
                    totalCaseIndia.setText(response.body().getStatewise().get(0).getConfirmed());
                    totalCaseIndiaDelta.setText(response.body().getStatewise().get(0).getDeltaconfirmed());
                    activeCaseIndia.setText(response.body().getStatewise().get(0).getActive());
                    activeCaseIndiaDelta.setText(String.valueOf(Integer.parseInt(response.body().getStatewise().get(0).getDeltaconfirmed()) - Integer.parseInt(response.body().getStatewise().get(0).getDeltarecovered()) - Integer.parseInt(response.body().getStatewise().get(0).getDeltadeaths())));
                    recoveredIndia.setText(response.body().getStatewise().get(0).getRecovered());
                    recoveredIndiaDelta.setText(response.body().getStatewise().get(0).getDeltarecovered());
                    deathIndia.setText(response.body().getStatewise().get(0).getDeaths());
                    deathIndiaDelta.setText(response.body().getStatewise().get(0).getDeltadeaths());
                }
            }

            @Override
            public void onFailure(Call<ApiData> call, Throwable throwable) {

            }
        });

    }

    private void initViews() {
        totalCaseIndia = findViewById(R.id.total_number);
        totalCaseIndiaDelta = findViewById(R.id.total_delta);
        activeCaseIndia =findViewById(R.id.active_number);
        activeCaseIndiaDelta = findViewById(R.id.active_delta);
        recoveredIndia = findViewById(R.id.recovered_number);
        recoveredIndiaDelta =findViewById(R.id.recovered_delta);
        deathIndia = findViewById(R.id.death_number);
        deathIndiaDelta = findViewById(R.id.death_delta);
        worldButton=findViewById(R.id.world_button);
        infoButton=findViewById(R.id.info_button);

    }
}
