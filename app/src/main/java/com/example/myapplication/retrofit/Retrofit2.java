package com.example.myapplication.retrofit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2 {

    final private Urls urls;

    public Retrofit2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_BASE2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        urls= retrofit.create(Urls.class);
    }


    public Urls getService() {
        return  urls;
    }
}
