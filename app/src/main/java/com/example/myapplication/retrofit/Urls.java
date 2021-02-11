package com.example.myapplication.retrofit;


import com.example.myapplication.pojo.ResultNews;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Urls {

    @GET("/api/news")
    Call<List<JsonObject>> wsNoticias();

    /*@GET("/api/information")
    Call<JSONArray> wsEmergencias();

    @GET("/api/covid")
    Call<JSONArray> wsCovid();

    @GET("/api/centres")
    Call<JSONArray> wsCentros();*/

}
