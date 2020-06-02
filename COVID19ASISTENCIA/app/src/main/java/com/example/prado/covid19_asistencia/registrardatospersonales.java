package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class registrardatospersonales extends AppCompatActivity implements   Response.Listener<JSONObject>,Response.ErrorListener{


    EditText nombre,
             apellido,
             documento,
             telefono,
             login,
             pass;



    Button btnregistra;


    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrardatospersonales);

        nombre = (EditText) findViewById(R.id.edtNombre);
        apellido = (EditText) findViewById(R.id.edtApellidos);
        documento = (EditText) findViewById(R.id.edtDocumentos);
        telefono = (EditText) findViewById(R.id.edtTelefono);
        login = (EditText) findViewById(R.id.edtUsuario);
        pass = (EditText) findViewById(R.id.edtClave);


        btnregistra = (Button) findViewById(R.id.btnRegistrarDatosP);


        request = Volley.newRequestQueue(this);







        btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebServis();
            }
        });



    }






    private void cargarWebServis() {

        progreso=new ProgressDialog(registrardatospersonales.this);
        progreso.setMessage("Cargando....");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+="/ejemploBDRemota/wsJSONRegistroCovid.php?nom_usuario="+nombre.getText().toString()+
                "&ape_usuario="+apellido.getText().toString()+
                "&doc_usuario="+documento.getText().toString()+
                "&tel_usuario="+telefono.getText().toString()+
                "&login_usuario="+login.getText().toString()+
                "&pass_usuario="+pass.getText().toString();


        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
       }


        @Override
          public void onResponse(JSONObject response) {

           Toast.makeText(registrardatospersonales.this,"REGISTRO EXITOSO, Por favor iniciar sesion! ",Toast.LENGTH_SHORT).show();
           progreso.hide();

           nombre.setText("");
           apellido.setText("");
           documento.setText("");
           telefono.setText("");
           login.setText("");
           pass.setText("");
       }

       @Override
         public void onErrorResponse(VolleyError error) {
          progreso.hide();
          Toast.makeText(registrardatospersonales.this,"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
          Log.i("ERROR", error.toString());
       }



}