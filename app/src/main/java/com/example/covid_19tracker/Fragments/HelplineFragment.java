package com.example.covid_19tracker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covid_19tracker.Adapters.HelplineAdapter;
import com.example.covid_19tracker.Custom_classes.Helpline;
import com.example.covid_19tracker.R;

import java.util.ArrayList;
import java.util.Objects;

public class HelplineFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_helpline, container, false);
        ArrayList<Helpline> helplineArrayList=new ArrayList<>();
        helplineArrayList.add(new Helpline("Central Helpline","+91-11-23978046"));
        helplineArrayList.add(new Helpline("Andhra Pradesh","0866-2410978"));
        helplineArrayList.add(new Helpline("Arunachal Pradesh", "9436055743"));
        helplineArrayList.add(new Helpline("Assam","6913347770"));
        helplineArrayList.add(new Helpline("Bihar","104"));
        helplineArrayList.add(new Helpline("Chhattisgarh","104"));
        helplineArrayList.add(new Helpline("Goa", "104"));
        helplineArrayList.add(new Helpline("Gujarat","104"));
        helplineArrayList.add(new Helpline("Haryana" ,"8558893911"));
        helplineArrayList.add(new Helpline("Himachal Pradesh","104"));
        helplineArrayList.add(new Helpline("Jharkhand","104"));
        helplineArrayList.add(new Helpline("Karnataka" ,"104"));
        helplineArrayList.add(new Helpline("Kerala","0471-2552056"));
        helplineArrayList.add(new Helpline("Madhya Pradesh","104"));
        helplineArrayList.add(new Helpline("Maharashtra" ,"020-26127394"));
        helplineArrayList.add(new Helpline("Manipur", "3852411668"));
        helplineArrayList.add(new Helpline("Meghalaya","108"));
        helplineArrayList.add(new Helpline("Mizoram", "102"));
        helplineArrayList.add(new Helpline("Nagaland", "7005539653"));
        helplineArrayList.add(new Helpline("Odisha", "9439994859"));
        helplineArrayList.add(new Helpline("Punjab" ,"104"));
        helplineArrayList.add(new Helpline("Rajasthan ","0141-2225624"));
        helplineArrayList.add(new Helpline("Sikkim", "104"));
        helplineArrayList.add(new Helpline("Tamil Nadu" ,"044-29510500"));
        helplineArrayList.add(new Helpline("Telangana", "104"));
        helplineArrayList.add(new Helpline("Tripura","0381-2315879"));
        helplineArrayList.add(new Helpline("Uttarakhand", "104"));
        helplineArrayList.add(new Helpline("Uttar Pradesh" ,"18001805145"));
        helplineArrayList.add(new Helpline("West Bengal" ,"1800313444222, 03323412600"));
        helplineArrayList.add(new Helpline("Andaman and Nicobar  Islands","03192-232102"));
        helplineArrayList.add(new Helpline("Chandigarh", "9779558282"));
        helplineArrayList.add(new Helpline("Dadra and Nagar Haveli and Daman & Diu" ,"104"));
        helplineArrayList.add(new Helpline("Delhi" ,"011-22307145"));
        helplineArrayList.add(new Helpline("Jammu & Kashmir" ,"01912520982, 0194-2440283"));
        helplineArrayList.add(new Helpline("Ladakh" ,"01982256462"));
        helplineArrayList.add(new Helpline("Lakshadweep" ,"104"));
        helplineArrayList.add(new Helpline("Puducherry", "104"));
        ListView list=root.findViewById(R.id.helpline_list);
        HelplineAdapter helplineAdapter=new HelplineAdapter(Objects.requireNonNull(getActivity()),helplineArrayList);
        list.setAdapter(helplineAdapter);
        return root;
    }


}