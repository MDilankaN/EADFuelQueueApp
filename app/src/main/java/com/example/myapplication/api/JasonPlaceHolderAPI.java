package com.example.myapplication.api;

import com.example.myapplication.models.ContactUs;
import com.example.myapplication.models.Station;
import com.example.myapplication.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JasonPlaceHolderAPI {

    @GET("/api/User")
    Call<List<User>> getUsers();

    @GET("/api/User/GetUserByName/{username}")
    Call<User> getUserByID(@Path("username") String username);

    @POST("/api/User")
    Call<User>createUser(@Body User user);

    //add station
    @POST("/api/Station")
    Call<Station>createStation(@Body Station station);

    //add contactus
    @POST("/api/ContactUs")
    Call<ContactUs>createContactUs(@Body ContactUs contactUs);

}
