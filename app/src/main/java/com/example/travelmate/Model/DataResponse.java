package com.example.travelmate.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("cust_name")
    private String name;
    @SerializedName("locations")
    private List<DataModel> locations;

    public String getCustName() {
        return name;
    }

    public void getCustName(String name) {
        this.name = name;
    }

    public List<DataModel> getResults() {
        return locations;
    }

    public void setResults(List<DataModel> locations) {
        this.locations = locations;
    }


}
