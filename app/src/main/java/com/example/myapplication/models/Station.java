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

    public String getId() {
        return Id;
    }

    public String getStationName() {
        return StationName;
    }

    public String getAddress() {
        return Address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public String getCloseTime() {
        return CloseTime;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public String getNoOfPumps() {
        return NoOfPumps;
    }

    public Station(String stationName, String address, String telephone, String openTime, String closeTime, String imageURL, String noOfPumps) {
        StationName = stationName;
        Address = address;
        Telephone = telephone;
        OpenTime = openTime;
        CloseTime = closeTime;
        ImageURL = imageURL;
        NoOfPumps = noOfPumps;
    }

    public Station(String id, String stationName, String address, String telephone, String openTime, String closeTime, String imageURL, String noOfPumps) {
        Id = id;
        StationName = stationName;
        Address = address;
        Telephone = telephone;
        OpenTime = openTime;
        CloseTime = closeTime;
        ImageURL = imageURL;
        NoOfPumps = noOfPumps;
    }
}

