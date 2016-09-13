package com.edgar.restapiapplication.api;


import com.edgar.restapiapplication.model.Repo;
import com.edgar.restapiapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/users/{username}")
    Call<User> getUserInformation(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<Repo>> getUserInformationForRepo(@Path("username") String username);

}
