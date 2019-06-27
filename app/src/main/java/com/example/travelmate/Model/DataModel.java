package com.example.travelmate.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by praka on 12/24/2017.
 */

public class DataModel {

    @SerializedName("place")
    private String place;
    @SerializedName("url")
    private String url;
    @SerializedName("date")
    private String date;
    @SerializedName("rate")
    private String rate;
    @SerializedName("description")
    private String desc;

    public String getPlace() {
        return place;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }

    public String getDesc() {
        return desc;
    }

    public DataModel(String place, String url, String date, String rate, String desc) {
        this.place = place;
        this.url = url;
        this.date = date;
        this.rate = rate;
        this.desc = desc;
    }


}

