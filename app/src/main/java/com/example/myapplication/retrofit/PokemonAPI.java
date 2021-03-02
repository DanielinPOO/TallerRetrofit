package com.example.myapplication.retrofit;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon/{id}")
    Call<JsonObject> getPokemonById(@Path("id") String id);
}
