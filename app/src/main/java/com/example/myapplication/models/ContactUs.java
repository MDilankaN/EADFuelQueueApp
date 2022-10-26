package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class ContactUs {
    @SerializedName("id")
    private String Id = "";
    @SerializedName("name")
    private String Name;
    @SerializedName("email")
    private String Email;
    @SerializedName("subject")
    private String Subject;
    @SerializedName("message")
    private String Message;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }

    public ContactUs(String name, String email, String subject, String message) {
        Name = name;
        Email = email;
        Subject = subject;
        Message = message;
    }

    public ContactUs(String id, String name, String email, String subject, String message) {
        Id = id;
        Name = name;
        Email = email;
        Subject = subject;
        Message = message;
    }
}
