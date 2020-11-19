
package com.example.covid_19tracker.Custom_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictDatum_ {

    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("confirmed")
    @Expose
    private int confirmed;
    @SerializedName("deceased")
    @Expose
    private int deceased;
    @SerializedName("recovered")
    @Expose
    private int recovered;
    @SerializedName("delta")
    @Expose
    private Delta delta;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DistrictDatum_() {
    }

    /**
     * 
     * @param recovered
     * @param notes
     * @param deceased
     * @param district
     * @param delta
     * @param active
     * @param confirmed
     */
    public DistrictDatum_(String district, String notes, int active, int confirmed, int deceased, int recovered, Delta delta) {
        super();
        this.district = district;
        this.notes = notes;
        this.active = active;
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.recovered = recovered;
        this.delta = delta;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

}
