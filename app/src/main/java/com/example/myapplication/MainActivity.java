package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.PokemonByIdResponse;
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

            // Prueba fail, por que al parecer el converter afecta a como se consumira el ws
            /*Retrofit2 retrofit2 = new Retrofit2();

            retrofit2.getService().wsNoticias(new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    if (response.getStatus()==200){

                        JSONArray data = null;
                        try {
                            data = new JSONArray(s);
                            Log.i("Esta es la información", data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Ocurrió un problema con el servidor", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });*/
            //------------------------- RETROFIT 2 -----------------------------------
            /*Retrofit2 retrofit2 = new Retrofit2();

            Call<JsonObject> call = retrofit2.getService().getPokemonById("ditto");

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        JsonObject pokemon = response.body();
                        JsonArray habilidades = pokemon.getAsJsonArray("abilities");
                        Log.i("pokemon", habilidades.toString());
                    } else {
                        Log.e("error", "Hubo un error inesperado!");
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });*/



            //------------------------- RETROFIT 1 --------------------------------------
            ApiRest apiRest =  new ApiRest();

            apiRest.getService().wsNoticias(new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    if (response.getStatus()==200){

                        try {
                            JSONArray data = new JSONArray(s);
                            data.optJSONObject(0).optString("detail");
                            Log.i("detalle", data.optJSONObject(0).optString("detail"));
                            Log.i("Esta es la información", data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Ocurrió un problema con el servidor", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

            //---------------------- Login xd -----------------------------
            /*if (editPassword.getText().toString().equals("12") && editUser.getText().toString().equals("daniel")){
                abrirPantallaAdmin();
            }else if(editPassword.getText().toString().equals("123") && editUser.getText().toString().equals("josue")){
                abrirPantallaUsuario();
            }else{
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
            }*/

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