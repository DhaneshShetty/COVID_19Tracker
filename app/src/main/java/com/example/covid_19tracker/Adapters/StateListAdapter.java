package com.example.covid_19tracker.Adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid_19tracker.R;
import com.example.covid_19tracker.Custom_classes.Statewise;

import java.util.ArrayList;

public class StateListAdapter extends ArrayAdapter<Statewise>
{
    public StateListAdapter(@NonNull Activity context, @NonNull ArrayList<Statewise> statewiseArrayList) {
        super(context,0 ,statewiseArrayList );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.state_list_item,parent,false);
        }
        Statewise current=getItem(position);
        TextView stateName=listItemView.findViewById(R.id.state_name);
        TextView stateConfirmed=listItemView.findViewById(R.id.state_confirmed);
        TextView stateConfirmedDelta=listItemView.findViewById(R.id.state_confirmed_delta);
        TextView stateActive=listItemView.findViewById(R.id.state_active);
        TextView stateRecovered=listItemView.findViewById(R.id.state_recovered);
        TextView stateRecoveredDelta=listItemView.findViewById(R.id.state_recovered_delta);
        TextView stateDeath=listItemView.findViewById(R.id.state_death);
        TextView stateDeathDelta=listItemView.findViewById(R.id.state_death_delta);
        assert current != null;
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
        return listItemView;
    }
}