package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class Queue {

    @SerializedName("id")
    private String Id = "";
    @SerializedName("queueName")
    private String QueueName;
    @SerializedName("stationName")
    private String StationName;
    @SerializedName("queueListID")
    private String QueueListID;
    @SerializedName("startingTime")
    private String StartingTime;
    @SerializedName("fuelStatus")
    private boolean FuelStatus;


    public String getId() {
        return Id;
    }

    public String getQueueName() {
        return QueueName;
    }

    public String getStationName() {
        return StationName;
    }

    public String getQueueListID() {
        return QueueListID;
    }

    public String getStartingTime() {
        return StartingTime;
    }

    public boolean getFuelStatus() {
        return FuelStatus;
    }

    public Queue(String queueName, String stationName, String queueListID, String startingTime, boolean fuelStatus) {
        QueueName = queueName;
        StationName = stationName;
        QueueListID = queueListID;
        StartingTime = startingTime;
        FuelStatus = fuelStatus;
    }

    public Queue(String id, String queueName, String stationName, String queueListID, String startingTime, boolean fuelStatus) {
        Id = id;
        QueueName = queueName;
        StationName = stationName;
        QueueListID = queueListID;
        StartingTime = startingTime;
        FuelStatus = fuelStatus;
    }
}
