package com.example.myapplication.api;

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
    Call<List<User>> getUserByID(@Path("username") String username);

    @POST("/api/User")
    Call<User>createUser(@Body User user);

}
