package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.retrofit.ApiRest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

            ApiRest apiRest =  new ApiRest();

            apiRest.getService().wsGetPokemon(editUser.getText().toString(),new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    if (response.getStatus()==200){

                        try {
                            //Recuerden que la data puede venir dentro de un jsonArray o un jsonObject de primeras.
                            //JSONArray data = new JSONArray(s);
                            JSONObject data = new JSONObject(s);
                            String imprimirInfo =
                                    "Habilidad: " + data.optJSONArray("abilities").optJSONObject(1).getJSONObject("ability").optString("name")
                                    +"\nBase experiencia: " +  data.optString("base_experience");
                            mostrar.setText(imprimirInfo);
                            Log.i("detalle", data.toString());
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

        });

    }
}