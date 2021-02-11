package com.example.myapplication.retrofit;

import com.example.myapplication.models.PokemonByIdResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon/{id}")
    Call<JsonObject> getPokemonById(@Path("id") String id);
}
