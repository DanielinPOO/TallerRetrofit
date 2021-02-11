package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Admin extends AppCompatActivity {

    TextView liquidar1, liquidar2, btn_archivo;
    String text_liquidar1="", text_liquidar2="";
    int num_liquidar1 = 0, num_liquidar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView btn_logout;
        TextView txt_admin;
        liquidar1 = findViewById(R.id.liquidar1);
        liquidar2 = findViewById(R.id.liquidar2);
        txt_admin = findViewById(R.id.txt_admin);
        btn_archivo = findViewById(R.id.btn_archivo);
        if (Usuario.jsonArrayRubros.length() == 0) {
            txt_admin.setText("No hay datos para mostrar");
        } else {

            int cantidadDatos = Usuario.jsonArrayRubros.length();
            try {
                for (int i = 0; i < cantidadDatos; i++) {
                    text_liquidar1 = "Items seleccionado: \n";
                    if (Usuario.jsonArrayRubros.getJSONArray(i).getJSONObject(0).getBoolean("hospedaje")) {
                        text_liquidar1 = text_liquidar1 + "Hospedaje \n";
                        num_liquidar1 = 200;
                    }
                    if (Usuario.jsonArrayRubros.getJSONArray(i).getJSONObject(1).getBoolean("comida")) {
                        text_liquidar1 = text_liquidar1 + "Comida \n";
                        num_liquidar1 = num_liquidar1 + 150;
                    }
                    if (Usuario.jsonArrayRubros.getJSONArray(i).getJSONObject(2).getBoolean("otros")) {
                        text_liquidar1 = text_liquidar1 + "Otros \n";
                        num_liquidar1 = num_liquidar1 + 100;
                    }
                    text_liquidar1 = text_liquidar1 + "Total: " + num_liquidar1;
                    liquidar1.setVisibility(View.VISIBLE);
                    liquidar1.setText(text_liquidar1);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        btn_archivo.setOnClickListener(v -> {
            crearArchivo("Este es un archivo");
        });
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(v -> {
            finish();
        });
        liquidar1.setOnClickListener(v->{
            try {
                JSONObject jsonObjectA = new JSONObject();
                jsonObjectA.put("autorizar", true);
                Usuario.jsonArrayRubros.getJSONArray(0).put(jsonObjectA);
                liquidar1.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        liquidar1.setOnLongClickListener(v -> {
            try {
                JSONObject jsonObjectA = new JSONObject();
                jsonObjectA.put("autorizar", false);
                Usuario.jsonArrayRubros.getJSONArray(0).put(jsonObjectA);
                liquidar1.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

    private void crearArchivo(String mensaje) {

        try{
            File nuevoArchivo = new File(Environment.getExternalStorageDirectory(), "CarpetaNueva");
            if (!nuevoArchivo.exists()){
                nuevoArchivo.mkdir();
            }

            try {
                File file = new File(nuevoArchivo, "Archivo" + ".txt");
                file.createNewFile();
            }catch(Exception e){
                Log.e("error", e.toString());
            }


        }catch (Exception e){
            Log.e("error", e.toString());
        }

    }
}