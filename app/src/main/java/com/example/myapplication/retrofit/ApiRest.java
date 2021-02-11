package com.example.myapplication.retrofit;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class ApiRest {
    final private Urls urls;
    public ApiRest()
    {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(45, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(45, TimeUnit.SECONDS);
        RestAdapter restAdapter= new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Utils.URL_BASE2)
                .setConverter(new StringConverter2())
                .setClient(new OkClient(okHttpClient))
                .build();
        urls= restAdapter.create(Urls.class);
    }

    static class StringConverter2 implements Converter {

        @Override
        public Object fromBody(TypedInput typedInput, Type type) {
            try {
                Scanner scanner = new Scanner(typedInput.in()).useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
            catch(Exception ex) {
                Objects.requireNonNull(ex.getCause()).printStackTrace();
                return null;
            }
        }
        @Override
        public TypedOutput toBody(Object o) {
            return null;
        }
    }
    public Urls getService() {
        return  urls;
    }
}