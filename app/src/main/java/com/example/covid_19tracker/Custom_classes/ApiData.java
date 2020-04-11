package com.example.covid_19tracker.Custom_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiData {

        @SerializedName("cases_time_series")
        @Expose
        private List<CasesTimeSeries> casesTimeSeries = null;
        @SerializedName("statewise")
        @Expose
        private ArrayList<Statewise> statewise = null;
        @SerializedName("tested")
        @Expose
        private List<Tested> tested = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public ApiData() {
        }

        /**
         *
         * @param tested
         * @param statewise
         * @param casesTimeSeries
         */
        public ApiData(List<CasesTimeSeries> casesTimeSeries, ArrayList<Statewise> statewise, List<Tested> tested) {
            super();
            this.casesTimeSeries = casesTimeSeries;
            this.statewise = statewise;
            this.tested = tested;
        }

        public List<CasesTimeSeries> getCasesTimeSeries() {
            return casesTimeSeries;
        }

        public void setCasesTimeSeries(List<CasesTimeSeries> casesTimeSeries) {
            this.casesTimeSeries = casesTimeSeries;
        }

        public ArrayList<Statewise> getStatewise() {
            return statewise;
        }

        public void setStatewise(ArrayList<Statewise> statewise) {
            this.statewise = statewise;
        }

        public List<Tested> getTested() {
            return tested;
        }

        public void setTested(List<Tested> tested) {
            this.tested = tested;
        }

    }

