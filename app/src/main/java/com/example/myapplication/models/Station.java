package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class Station {
    @SerializedName("id")
    private String Id = "";
    @SerializedName("stationName")
    private String StationName;
    @SerializedName("address")
    private String Address;
    @SerializedName("telephone")
    private String Telephone;
    @SerializedName("openTime")
    private String OpenTime;
    @SerializedName("closeTime")
    private String CloseTime;
    @SerializedName("imageURL")
    private String ImageURL;
    @SerializedName("noOfPumps")
    private String NoOfPumps;



}
