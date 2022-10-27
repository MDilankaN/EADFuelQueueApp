package com.example.myapplication.api;

import com.example.myapplication.models.ContactUs;
import com.example.myapplication.models.QueueList;
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

    //station
    @GET("/api/Station")
    Call<List<Station>> getStations();

    @POST("/api/Station")
    Call<Station>createStation(@Body Station station);

    //get by station name
    @GET("/api/Station/GetStationByName/{stationName}")
    Call<List<Station>>getStationByName(@Path("stationName") String stationName);

    @GET("/api/Station/GetStationByName/{stationName}")
    Call<Station> getStationByName2(@Path("stationName") String stationName);

    @PUT("/api/Station/{id}")
    Call<Station>updateStation(@Path("id") String id, @Body Station station);

    @DELETE("/api/Station/{id}")
    Call<Void>deleteStation(@Path("id") String id);



    //contactus
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


    //QueueList
    @GET("/api/QueueList/GetQueueListByQueue/{queueID}")
    Call<List<QueueList>> QueueListOrder(@Path("queueID") String queueID);

    @POST("/api/QueueList")
    Call<QueueList>joinQueueList(@Body QueueList list);

    @DELETE("/api/QueueList/{id}")
    Call<Void>RemoveFromList(@Path("id")String id);



}
