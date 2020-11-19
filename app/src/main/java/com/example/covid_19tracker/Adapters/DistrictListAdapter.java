package com.example.covid_19tracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid_19tracker.Custom_classes.DistrictDatum;
import com.example.covid_19tracker.Custom_classes.DistrictDatum_;
import com.example.covid_19tracker.R;

import java.util.ArrayList;
import java.util.List;

public class DistrictListAdapter extends ArrayAdapter<DistrictDatum_> {
    public DistrictListAdapter(@NonNull Context context, List<DistrictDatum_> districtDatum_arrayList) {
        super(context,0,districtDatum_arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.district_list_item,parent,false);
        }
        TextView districtName=listItemView.findViewById(R.id.district_name);
        TextView dConfirmed=listItemView.findViewById(R.id.district_confirmed);
        TextView dConfirmedDelta=listItemView.findViewById(R.id.district_confirmed_delta);
        TextView dActive=listItemView.findViewById(R.id.district_active);
        TextView dRecovered=listItemView.findViewById(R.id.district_recovered);
        TextView dRecoveredDelta=listItemView.findViewById(R.id.district_recovered_delta);
        TextView dDeath=listItemView.findViewById(R.id.district_death);
        TextView dDeathDelta=listItemView.findViewById(R.id.district_death_delta);
        DistrictDatum_ current=getItem(position);
        assert current != null;
        districtName.setText(current.getDistrict());
        dConfirmed.setText(String.valueOf(current.getConfirmed()));
        dActive.setText(String.valueOf(current.getActive()));
        dDeath.setText(String.valueOf(current.getDeceased()));
        dRecovered.setText(String.valueOf(current.getRecovered()));
        dConfirmedDelta.setText(String.valueOf(current.getDelta().getConfirmed()));
        dRecoveredDelta.setText(String.valueOf(current.getDelta().getRecovered()));
        dDeathDelta.setText(String.valueOf(current.getDelta().getDeceased()));
        return listItemView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
