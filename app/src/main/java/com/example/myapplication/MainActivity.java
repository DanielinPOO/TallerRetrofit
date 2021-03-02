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
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editUser;
        TextView btn_ingresar, mostrar;
        btn_ingresar = findViewById(R.id.btn_ingresar);
        editUser = findViewById(R.id.edit_user);
        mostrar = findViewById(R.id.mostrar);

        btn_ingresar.setOnClickListener(v -> {

            //------------------------- RETROFIT 2 -----------------------------------
            Retrofit2 retrofit2 = new Retrofit2();

            Call<JsonObject> call = retrofit2.getService().getPokemonById(editUser.getText().toString().toLowerCase());

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){

                        //Recuerden que la data puede venir dentro de un jsonArray o un jsonObject de primeras.
                        //JSONArray data = new JSONArray(s);
                        JsonObject data;
                        data = response.body();

                        //utilizar esto para obtener cualquier dato del JsonObject
                        assert data != null;
                        String imprimirInfo =
                                "Habilidad: " + data.getAsJsonArray("abilities").get(1).getAsJsonObject().getAsJsonObject("ability").get("name")
                                        +"\nBase experiencia: " +  data.get("base_experience");
                        mostrar.setText(imprimirInfo);
                        Log.i("detalle", data.toString());
                    } else {
                        Log.e("error", "Hubo un error inesperado!");
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                    Log.e("error", t.toString());
                }
            });

        });

    }
}