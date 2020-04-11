package com.example.covid_19tracker.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid_19tracker.Custom_classes.Helpline;
import com.example.covid_19tracker.R;

import java.util.ArrayList;

public class HelplineAdapter extends ArrayAdapter<Helpline> {

    public HelplineAdapter(@NonNull Activity context, ArrayList<Helpline> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.helpline_listitem,parent,false);
        }
        Helpline current=getItem(position);
        TextView state=listItemView.findViewById(R.id.helpline_state_name);
        TextView num=listItemView.findViewById(R.id.helpline_number);
        assert current != null;
        state.setText(current.getmState());
        num.setText(current.getmNumber());
        return listItemView;
    }
}
