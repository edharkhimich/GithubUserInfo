package com.edgar.restapiapplication.api;


import com.edgar.restapiapplication.model.Repo;
import com.edgar.restapiapplication.model.User;


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Api {

    private static Api api;

    private Api(){

    }

    public static Api getInstance(){
        if(api!=null) return api;
        api = new Api();

        return api;
    }

    public OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        return client.build();
    }

    public ApiInterface getApiInterface(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }


    public interface ApiInterface {
        @GET("/users/{username}")
        Call<User> getUserInformation(@Path("username") String username);

        @GET("/users/{username}/repos")
        Call<List<Repo>> getUserInformationForRepo(@Path("username") String username);

    }
}
