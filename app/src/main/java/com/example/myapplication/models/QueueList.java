package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class QueueList {
    @SerializedName("id")
    private String Id = "";
    @SerializedName("userid")
    private String UserID;
    @SerializedName("queueid")
    private String QueueID;
    @SerializedName("jointime")
    private String JoinTime;
    @SerializedName("lefttime")
    private String LeftTime;
    @SerializedName("position")
    private String Position;

}
