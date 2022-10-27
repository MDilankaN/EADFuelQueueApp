package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String Id = "";
    @SerializedName("userName")
    private String UserName;
    @SerializedName("email")
    private String Email;
    @SerializedName("password")
    private String Password;
    @SerializedName("vehicleNo")
    private String VehicleNo;
    @SerializedName("vehicleType")
    private String VehicleType;
    @SerializedName("fuelType")
    private String FuelType;
    @SerializedName("language")
    private String Language;
    @SerializedName("type")
    private String Type;


    public String getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public String getFuelType() {
        return FuelType;
    }

    public String getLanguage() {
        return Language;
    }

    public String getType() {
        return Type;
    }

    public User(String userName, String email, String password, String vehicleNo, String vehicleType, String fuelType, String language, String type) {
        UserName = userName;
        Email = email;
        Password = password;
        VehicleNo = vehicleNo;
        VehicleType = vehicleType;
        FuelType = fuelType;
        Language = language;
        Type = type;
    }

    public User(String id, String userName, String email, String password, String vehicleNo, String vehicleType, String fuelType, String language, String type) {
        Id = id;
        UserName = userName;
        Email = email;
        Password = password;
        VehicleNo = vehicleNo;
        VehicleType = vehicleType;
        FuelType = fuelType;
        Language = language;
        Type = type;
    }

    public User(String id, String userName, String email, String password, String vehicleNo) {
        Id = id;
        UserName = userName;
        Email = email;
        Password = password;
        VehicleNo = vehicleNo;
    }
}
