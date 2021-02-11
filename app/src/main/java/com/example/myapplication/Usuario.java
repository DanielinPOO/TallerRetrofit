package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Usuario extends AppCompatActivity {
    private Calendar c1, c2;
    private DatePickerDialog datePickerDialog;
    private TextView fecha_inicio, fecha_fin, mostrar_fecha_inicio, mostrar_fecha_fin,btn_guardar;
    private LinearLayout rubros;
    private CheckBox cb_hospedaje, cb_comida, cb_otros;
    private int guardarDiasInicio, guardarDiasFin, contadorRegistros=0;
    public static String guardarSelecciones[][];
    public static JSONArray jsonArrayRubros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        TextView btn_logout, text_user;
        text_user = findViewById(R.id.text_user);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(v -> {
            finish();
        });
        if (jsonArrayRubros.length()==0){
            text_user.setText("No hay rubros");
        }else{
            try {
                if(jsonArrayRubros.getJSONArray(0).getJSONObject(3).has("autorizar")){
                    if (jsonArrayRubros.getJSONArray(0).getJSONObject(3).getBoolean("autorizar")){
                        text_user.setText("viáticos aprobados");
                    }else{
                        text_user.setText("viáticos rechazados");
                    }
                }else{
                    text_user.setText("Pendiente de aprobación");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        fecha_fin = findViewById(R.id.fecha_fin);
        fecha_inicio = findViewById(R.id.fecha_inicio);
        mostrar_fecha_fin = findViewById(R.id.motrar_fecha_fin);
        mostrar_fecha_inicio = findViewById(R.id.mostrar_fecha_inicio);
        rubros = findViewById(R.id.ly_rubros);
        cb_hospedaje = findViewById(R.id.cb_hospedaje);
        cb_comida = findViewById(R.id.cb_comida);
        cb_otros = findViewById(R.id.cb_otros);
        btn_guardar = findViewById(R.id.btn_guardar);
        fecha_fin.setOnClickListener(v ->{
            if(!mostrar_fecha_inicio.getText().equals("dd/mm/aaaa")){
                AbrirCalendarioFin();
            }else{
                Toast.makeText(this, "Debe seleccionar primer una fecha de inicio", Toast.LENGTH_LONG).show();
            }
        });
        fecha_inicio.setOnClickListener(v ->AbrirCalendarioInicio());




    }

    private void iniciarRegistroRubros() {

        int dias = (guardarDiasFin+1) - guardarDiasInicio;
        Log.e("ver", dias +"");
        btn_guardar.setOnClickListener(v->{
            if (contadorRegistros < dias){
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObjectHospedaje = new JSONObject();
                JSONObject jsonObjectComida = new JSONObject();
                JSONObject jsonObjectOtros = new JSONObject();
                try {
                if (cb_hospedaje.isChecked()){
                    jsonObjectHospedaje.put("hospedaje", true);
                }
                if (cb_comida.isChecked()){
                    jsonObjectComida.put("comida", true);
                }
                if (cb_otros.isChecked()){
                    jsonObjectOtros.put("otros", true);
                }
                jsonArray.put(jsonObjectHospedaje);
                jsonArray.put(jsonObjectComida);
                jsonArray.put(jsonObjectOtros);

                jsonArrayRubros.put(jsonArray);
                Log.e("ver", jsonArrayRubros.toString());
                Log.e("ver", String.valueOf(jsonArrayRubros.getJSONArray(0).getJSONObject(0).getBoolean("hospedaje")));
                contadorRegistros++;
                cb_otros.setChecked(false);
                cb_comida.setChecked(false);
                cb_hospedaje.setChecked(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this, "Fin de los rubros", Toast.LENGTH_LONG).show();
                rubros.setVisibility(View.GONE);
                cb_otros.setChecked(false);
                cb_comida.setChecked(false);
                cb_hospedaje.setChecked(false);
                contadorRegistros = 0;
            }

        });

    }

    private void AbrirCalendarioFin() {
        {
            c1 = Calendar.getInstance();
            int dia = c1.get(Calendar.DAY_OF_MONTH);
            int mes = c1.get(Calendar.MONTH);
            int año = c1.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(Usuario.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    guardarDiasFin = dayOfMonth;
                    mostrar_fecha_fin.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    rubros.setVisibility(View.VISIBLE);
                    iniciarRegistroRubros();
                }
            }, dia, mes, año);
            datePickerDialog.show();

        }
    }

    private void AbrirCalendarioInicio() {
        {
            c2 = Calendar.getInstance();
            int dia = c2.get(Calendar.DAY_OF_MONTH);
            int mes = c2.get(Calendar.MONTH);
            int año = c2.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(Usuario.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    guardarDiasInicio = dayOfMonth;
                    mostrar_fecha_inicio.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                }
            }, dia, mes, año);
            datePickerDialog.show();
        }
    }
}