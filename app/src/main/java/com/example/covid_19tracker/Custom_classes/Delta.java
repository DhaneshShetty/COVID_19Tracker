
package com.example.covid_19tracker.Custom_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delta {

    @SerializedName("confirmed")
    @Expose
    private int confirmed;
    @SerializedName("deceased")
    @Expose
    private int deceased;
    @SerializedName("recovered")
    @Expose
    private int recovered;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Delta() {
    }

    /**
     * 
     * @param recovered
     * @param deceased
     * @param confirmed
     */
    public Delta(int confirmed, int deceased, int recovered) {
        super();
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.recovered = recovered;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeceased() {
        return deceased;
    }

    public void setDeceased(int deceased) {
        this.deceased = deceased;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

}
