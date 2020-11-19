
package com.example.covid_19tracker.Custom_classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictDatum {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("statecode")
    @Expose
    private String statecode;
    @SerializedName("districtData")
    @Expose
    private List<DistrictDatum_> districtData = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DistrictDatum() {
    }

    /**
     * 
     * @param districtData
     * @param state
     * @param statecode
     */
    public DistrictDatum(String state, String statecode, List<DistrictDatum_> districtData) {
        super();
        this.state = state;
        this.statecode = statecode;
        this.districtData = districtData;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public List<DistrictDatum_> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(List<DistrictDatum_> districtData) {
        this.districtData = districtData;
    }

}
