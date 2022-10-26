package com.example.myapplication.api;

import com.example.myapplication.models.ContactUs;
import com.example.myapplication.models.Station;
import com.example.myapplication.models.Queue;
import com.example.myapplication.models.User;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JasonPlaceHolderAPI {

    @GET("/api/User")
    Call<List<User>> getUsers();

    @GET("/api/User/GetUserByName/{username}")
    Call<User> getUserByID(@Path("username") String username);

    @POST("/api/User")
    Call<User>createUser(@Body User user);

    @PUT("/api/User/{id}")
    Call<User>updateUser(@Path("id") String id ,@Body User user);

    //add station
    @POST("/api/Station")
    Call<Station>createStation(@Body Station station);

    //add contactus
    @POST("/api/ContactUs")
    Call<ContactUs>createContactUs(@Body ContactUs contactUs);


    // Queue
    @GET("/api/Queue")
    Call<List<Queue>> getQueues();

    @GET("/api/Queue/GetByQueueName/{queueName}")
    Call<Queue> getQueueByName(@Path("queueName") String queueName);

    @POST("/api/Queue")
    Call<Queue>createQueue(@Body Queue queue);

    @PUT("/api/Queue/{id}")
    Call<Queue>updateQueue(@Path("id") String id, @Body Queue queue);

    @DELETE("/api/Queue/{id}")
    Call<Void>deleteQueue(@Path("id") String id);



}
