package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.retrofit.ApiRest;
import com.example.myapplication.retrofit.PokemonAPI;
import com.example.myapplication.retrofit.Retrofit2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editUser, editPassword;
        TextView btn_ingresar;
        btn_ingresar = findViewById(R.id.btn_ingresar);
        editPassword = findViewById(R.id.edit_password);
        editUser = findViewById(R.id.edit_user);

        Usuario.jsonArrayRubros = new JSONArray();

        btn_ingresar.setOnClickListener(v -> {

            //------------------------- RETROFIT 2 -----------------------------------
            /*Retrofit2 retrofit2 = new Retrofit2();

            Call<List<JsonObject>> call = retrofit2.getService().getPokemonById("ditto");

            call.enqueue(new Callback<List<JsonObject>>() {
                @Override
                public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    if(response.isSuccessful()){
                        List<JsonObject> pokemon = response.body();
                        JsonArray habilidades = pokemon.getAsJsonArray("abilities");
                        Log.i("pokemon", habilidades.toString());
                    } else {
                        Log.e("error", "Hubo un error inesperado!");
                    }
                }

                @Override
                public void onFailure(Call<List<JsonObject>> call, Throwable t) {

                }
            });*/



            //------------------------- RETROFIT 1 --------------------------------------
            ApiRest apiRest =  new ApiRest();

            apiRest.getService().wsNoticias(new Callback<String>() {
                @Override
                public void success(String s, Response response) {


                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        });

    }

    private void abrirPantallaAdmin() {

        Intent i = new Intent(MainActivity.this, Admin.class);
        startActivity(i);

    }
    private void abrirPantallaUsuario() {

        Intent i = new Intent(MainActivity.this, Usuario.class);
        startActivity(i);

    }
}