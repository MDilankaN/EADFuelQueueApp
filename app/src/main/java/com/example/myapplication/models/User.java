package com.example.myapplication.models;

public class User {

    private String Id = "";
    private String UserName;
    private String Email;
    private String Password;
    private String VehicleNo;
    private String VehicleType;
    private String FuelType;
    private String Language;
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
}
