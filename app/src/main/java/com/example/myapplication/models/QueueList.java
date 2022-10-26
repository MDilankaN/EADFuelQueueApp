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

    public String getId() {
        return Id;
    }

    public String getUserID() {
        return UserID;
    }

    public String getQueueID() {
        return QueueID;
    }

    public String getJoinTime() {
        return JoinTime;
    }

    public String getLeftTime() {
        return LeftTime;
    }

    public String getPosition() {
        return Position;
    }

    public QueueList(String userID, String queueID, String joinTime, String leftTime, String position) {
        UserID = userID;
        QueueID = queueID;
        JoinTime = joinTime;
        LeftTime = leftTime;
        Position = position;
    }

    public QueueList(String id, String userID, String queueID, String joinTime, String leftTime, String position) {
        Id = id;
        UserID = userID;
        QueueID = queueID;
        JoinTime = joinTime;
        LeftTime = leftTime;
        Position = position;
    }
}
