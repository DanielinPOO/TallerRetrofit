package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.retrofit.Retrofit2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        btn_ingresar.setOnClickListener(v -> {

            //------------------------- RETROFIT 2 -----------------------------------
            Retrofit2 retrofit2 = new Retrofit2();

            Call<List<JsonObject>> call = retrofit2.getService().wsNoticias();

            call.enqueue(new Callback<List<JsonObject>>() {
                @Override
                public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    if(response.isSuccessful()){

                        List<JsonObject> data = response.body();
                        Log.i("información", data.toString());


                        // TODO: para obtener datos apiPokemon
                        /*JsonObject pokemon = response.body();
                        JsonArray habilidades = pokemon.getAsJsonArray("abilities");
                        Log.i("pokemon", habilidades.toString());*/
                    } else {
                        Log.e("error", "Hubo un error inesperado!");
                    }
                }

                @Override
                public void onFailure(Call<List<JsonObject>> call, Throwable t) {

                    Log.e("error", t.toString());
                }
            });

        });

    }
}