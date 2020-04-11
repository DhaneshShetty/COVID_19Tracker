package com.example.covid_19tracker.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covid_19tracker.Adapters.StateListAdapter;
import com.example.covid_19tracker.Custom_classes.ApiData;
import com.example.covid_19tracker.Interfaces.StateDataInterface;
import com.example.covid_19tracker.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private View root;
    private TextView totalCaseIndia, totalCaseIndiaDelta, activeCaseIndia, activeCaseIndiaDelta, recoveredIndia, recoveredIndiaDelta, deathIndia, deathIndiaDelta;
    private ListView listView;
    Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity)
        {
            activity=(Activity)context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || networkInfo.isFailover()) {
            Toast.makeText(getContext(), "Please Check Your Network Connection", Toast.LENGTH_LONG).show();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        initViews();
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
                    StateListAdapter stateListAdapter = new StateListAdapter(activity, response.body().getStatewise());
                    listView.setAdapter(stateListAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiData> call, Throwable throwable) {

            }
        });
        return root;
    }

    private void initViews() {
        totalCaseIndia = root.findViewById(R.id.total_number);
        totalCaseIndiaDelta = root.findViewById(R.id.total_delta);
        activeCaseIndia = root.findViewById(R.id.active_number);
        activeCaseIndiaDelta = root.findViewById(R.id.active_delta);
        recoveredIndia = root.findViewById(R.id.recovered_number);
        recoveredIndiaDelta = root.findViewById(R.id.recovered_delta);
        deathIndia = root.findViewById(R.id.death_number);
        deathIndiaDelta = root.findViewById(R.id.death_delta);
        listView = root.findViewById(R.id.list_states);
    }
}
