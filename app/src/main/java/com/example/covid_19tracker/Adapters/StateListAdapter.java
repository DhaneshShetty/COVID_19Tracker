package com.example.covid_19tracker.Adapters;


import android.app.Activity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid_19tracker.Custom_classes.DistrictDatum;
import com.example.covid_19tracker.Custom_classes.DistrictDatum_;
import com.example.covid_19tracker.R;
import com.example.covid_19tracker.Custom_classes.Statewise;

import java.util.ArrayList;

public class StateListAdapter extends ArrayAdapter<Statewise>
{
    ArrayList<DistrictDatum> DistrictList;
    ArrayList<Statewise> statewiseArrayList;
    public StateListAdapter(@NonNull Activity context) {
        super(context,0 );
    }

    public void setDistrictList(ArrayList<DistrictDatum> districtList) {
        DistrictList = districtList;
    }



    public void setStatewiseArrayList(ArrayList<Statewise> statewiseArrayList) {
        statewiseArrayList.remove(0);
        this.statewiseArrayList=statewiseArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.state_list_item,parent,false);
        }
        final ListView districtList=listItemView.findViewById(R.id.district);
        DistrictListAdapter districtListAdapter;
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(districtList.getVisibility()==View.VISIBLE)
                {
                    districtList.setVisibility(View.GONE);
                }
                else{
                    Slide enter=new Slide(Gravity.TOP);
                    enter.addTarget(districtList);
                    enter.setInterpolator(new LinearInterpolator() );
                    districtList.setVisibility(View.VISIBLE);
                }
            }
        });
        Statewise current=statewiseArrayList.get(position);
        for(int i=0;i<DistrictList.size();i++)
        {
            if((DistrictList.get(i).getState()).equals(current.getState()))
            {
                districtListAdapter=new DistrictListAdapter(getContext(), DistrictList.get(i).getDistrictData());
                districtList.setAdapter(districtListAdapter);
            }
        }
        TextView stateName=listItemView.findViewById(R.id.state_name);
        TextView stateConfirmed=listItemView.findViewById(R.id.state_confirmed);
        TextView stateConfirmedDelta=listItemView.findViewById(R.id.state_confirmed_delta);
        TextView stateActiveDelta=listItemView.findViewById(R.id.state_active_delta);
        TextView stateActive=listItemView.findViewById(R.id.state_active);
        TextView stateRecovered=listItemView.findViewById(R.id.state_recovered);
        TextView stateRecoveredDelta=listItemView.findViewById(R.id.state_recovered_delta);
        TextView stateDeath=listItemView.findViewById(R.id.state_death);
        TextView stateDeathDelta=listItemView.findViewById(R.id.state_death_delta);
        ImageView stateImage=listItemView.findViewById(R.id.state_image);
        int activecasesdelta=Integer.parseInt(current.getDeltaconfirmed())-Integer.parseInt(current.getDeltadeaths())-Integer.parseInt(current.getDeltarecovered());
        String activedelta;
        if(activecasesdelta>=0) {
            activedelta = "+" + String.valueOf(activecasesdelta);
        }
        else
        {
            activedelta = String.valueOf(activecasesdelta);
        }
        stateActiveDelta.setText(activedelta);
        stateName.setText(current.getState());
        stateConfirmed.setText(current.getConfirmed());
        stateActive.setText(current.getActive());
        stateRecovered.setText(current.getRecovered());
        stateDeath.setText(current.getDeaths());
        String deltaConfirm="+"+current.getDeltaconfirmed();
        stateConfirmedDelta.setText(deltaConfirm);
        String deltaRecover="+"+current.getDeltarecovered();
        stateRecoveredDelta.setText(deltaRecover);
        String deltaDeath="+"+current.getDeltadeaths();
        stateDeathDelta.setText(deltaDeath);
        if(current.getState().equals("Karnataka"))
            stateImage.setImageResource(R.drawable.ka1);
        else if(current.getState().equals("Kerala"))
            stateImage.setImageResource(R.drawable.kl);
        else if(current.getState().equals("Tamil Nadu"))
            stateImage.setImageResource(R.drawable.tn);
        else if(current.getState().equals("Telangana"))
            stateImage.setImageResource(R.drawable.ap);
        else if(current.getState().equals("Assam"))
            stateImage.setImageResource(R.drawable.as);
        else if(current.getState().equals("Andhra Pradesh"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Sikkim"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Delhi"))
            stateImage.setImageResource(R.drawable.dl);
        else if(current.getState().equals("Maharashtra"))
            stateImage.setImageResource(R.drawable.mh);
        else if(current.getState().equals("Gujarat"))
            stateImage.setImageResource(R.drawable.tn);
        else if(current.getState().equals("Rajasthan"))
            stateImage.setImageResource(R.drawable.rj);
        else if(current.getState().equals("Madhya Pradesh"))
            stateImage.setImageResource(R.drawable.mp);
        else if(current.getState().equals("Uttar Pradesh"))
            stateImage.setImageResource(R.drawable.up);
        else if(current.getState().equals("West Bengal"))
            stateImage.setImageResource(R.drawable.wb);
        else if(current.getState().equals("Punjab"))
            stateImage.setImageResource(R.drawable.tn);
        else if(current.getState().equals("Jammu and Kasmir"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Bihar"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Haryana"))
            stateImage.setImageResource(R.drawable.hr);
        else if(current.getState().equals("Odisha"))
            stateImage.setImageResource(R.drawable.as);
        else if(current.getState().equals("Jharkhand"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Chandigarh"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Uttarakhand"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Chhattisgarh"))
            stateImage.setImageResource(R.drawable.tn);
        else if(current.getState().equals("Ladakh"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Himachal Pradesh"))
            stateImage.setImageResource(R.drawable.as);
        else if(current.getState().equals("Andaman and Nicobar Islands"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Tripura"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Meghalaya"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Puducherry"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Goa"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Manipur"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Arunachal Pradesh"))
            stateImage.setImageResource(R.drawable.as);
        else if(current.getState().equals("Mizoram"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Nagaland"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Dadra and Nagar Haveli"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Daman and Diu"))
            stateImage.setImageResource(R.drawable.ts);
        else if(current.getState().equals("Lakshadweep"))
            stateImage.setImageResource(R.drawable.ts);
        return listItemView;
    }

    @Override
    public int getCount() {
        return statewiseArrayList.size();
    }
}