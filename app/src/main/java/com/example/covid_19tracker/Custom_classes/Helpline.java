package com.example.covid_19tracker.Custom_classes;

public class Helpline {
    String mState,mNumber;
    public Helpline(String state,String number)
    {
        mState=state;
        mNumber=number;
    }

    public String getmNumber() {
        return mNumber;
    }

    public String getmState() {
        return mState;
    }
}
