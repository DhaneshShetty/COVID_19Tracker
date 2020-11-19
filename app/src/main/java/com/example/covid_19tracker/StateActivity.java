package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.covid_19tracker.Adapters.StateListAdapter;
import com.example.covid_19tracker.Custom_classes.ApiData;
import com.example.covid_19tracker.Custom_classes.DistrictDatum;
import com.example.covid_19tracker.Fragments.MainActivity;
import com.example.covid_19tracker.Interfaces.StateDataInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || networkInfo.isFailover()) {
            Toast.makeText(StateActivity.this, "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StateDataInterface dataInterface = retrofit.create(StateDataInterface.class);
        final Call<ApiData> apiDataCall = dataInterface.getStatesData();
        Call<ArrayList<DistrictDatum>> listCall=dataInterface.getDistrictData();
        final StateListAdapter adapter=new StateListAdapter(StateActivity.this);
        listCall.enqueue(new Callback<ArrayList<DistrictDatum>>() {
            @Override
            public void onResponse(Call<ArrayList<DistrictDatum>> call, Response<ArrayList<DistrictDatum>> response) {

                adapter.setDistrictList(response.body());
                Log.e("DIstrict","District Data set");
                apiDataCall.enqueue(new Callback<ApiData>() {
                    @Override
                    public void onResponse(Call<ApiData> call, Response<ApiData> response) {
                        if (response != null) {
                            ListView listView=findViewById(R.id.list_view);
                            adapter.setStatewiseArrayList(response.body().getStatewise());
                            listView.setAdapter(adapter);
                            Log.e("State","State Data set");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiData> call, Throwable throwable) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<DistrictDatum>> call, Throwable throwable) {

            }
        });





    }
}

