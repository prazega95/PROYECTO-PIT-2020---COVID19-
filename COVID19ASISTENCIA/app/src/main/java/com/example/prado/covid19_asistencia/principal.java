package com.example.prado.covid19_asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class principal extends AppCompatActivity implements View.OnClickListener{


    TextView btnRegistrar;
    Button btt_iniciar;
   /* ImageView img1;*/


    EditText edtUser,edtPass;
    String usuario, password;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


       /* img1=(ImageView) findViewById(R.id.imgCovid19);*/
        btt_iniciar=(Button) findViewById(R.id.btnINICIAR);
        btnRegistrar= findViewById(R.id.textRegistrarme);

        edtUser = (EditText) findViewById(R.id.edtLoginPrincipal);
        edtPass = (EditText) findViewById(R.id.edtPassPrincipal);


        recuperarPreferencias();



        btt_iniciar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);





        btt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario=edtUser.getText().toString();
                password=edtPass.getText().toString();


                if (!usuario.isEmpty()&& !password.isEmpty()){
                  //  validarUsuario( "https://proyectocovid19.000webhostapp.com/ejemploBDRemota/validar_usuarioCovid.php");
                    validarUsuario( "http://192.168.2.2:8080/ejemploBDRemota/validar_usuarioCovid.php");
                }else{
                    Toast.makeText(principal.this,"No se permiten campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }





    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(),paginainicio.class);
                    guardarPreferencias();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext()," BIENVENIDO! :  "+usuario,Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(principal.this,"Correo o contraseña equivocado",Toast.LENGTH_SHORT).show();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(principal.this, error.toString(),Toast.LENGTH_SHORT).show();

            }
        })


        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("usuario",usuario);
                parametros.put("clave",password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.textRegistrarme:
                Intent int2= new Intent(this, registrardatospersonales.class);
                startActivity(int2);
                break;



        }
    }




    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("clave",password);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    private void recuperarPreferencias(){

        SharedPreferences preferences = getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        edtUser.setText(preferences.getString("usuario",""));
        edtPass.setText(preferences.getString("clave",""));
    }















}
