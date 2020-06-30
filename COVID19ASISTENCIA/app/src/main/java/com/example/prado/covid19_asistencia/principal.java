package com.example.prado.covid19_asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
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
    ImageView img1;


    EditText edtUser,edtPass;
    String usuario, password;

    CheckBox mostrarClave;
    private boolean esVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        img1=(ImageView) findViewById(R.id.imgCovid19);
        btt_iniciar=(Button) findViewById(R.id.btnINICIAR);
        btnRegistrar= findViewById(R.id.textRegistrarme);

        mostrarClave = (CheckBox)findViewById(R.id.chkmostrarClave);

        edtUser = (EditText) findViewById(R.id.edtLoginPrincipal);
        edtPass = (EditText) findViewById(R.id.edtPassPrincipal);

        recuperarPreferencias();


        btt_iniciar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

        //llamando a la animacion
        Animation anim;
        anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        img1.setAnimation(anim);


///////////condicionales para el checkbox segun su marcacion mostrar la contraseña escrito o no
        mostrarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!esVisible) {
                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    esVisible = true;
                }
                else {
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    esVisible = false;
                }
            }
        });











//////////////////////////////////////////////////////////////////////////////////////////////////////INICIANDO EL BOTON SESION
        btt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario=edtUser.getText().toString();
                password=edtPass.getText().toString();
                final String regexUsuario= "^[a-z]+$";
                final String compruebaUsuario = edtUser.getEditableText().toString().trim();
                final String regexClave= "^[a-z0-9]+$";
                final String compruebaClave = edtPass.getEditableText().toString().trim();

                /*NOTA: solo solo coloca IF: en todas las condiciones, si uno no cumple TODAS MOSTRARAN TOAST DE UNO EN UNO,
                *       RECUERDE que el orden de los else if de de acuerdo a como quieres que valide primero i++*/


                if(usuario.isEmpty()&&password.isEmpty()){
                    Toast.makeText(principal.this,"¡No ingreso nada en el logueo!",Toast.LENGTH_SHORT).show();

                }
                else if(usuario.isEmpty()){
                    Toast.makeText(principal.this,"¡No deje el campo usuario vacío!",Toast.LENGTH_SHORT).show();

                }
                else if (edtUser.getText().toString().trim().length() == 0){
                    Toast.makeText(principal.this,"¡No inserte solo espacios en blanco en usuario!",Toast.LENGTH_SHORT).show();

                }
                else if (!compruebaUsuario.matches(regexUsuario)){
                    Toast.makeText(principal.this, "¡Ingresar solo letras minúsculas sin espacios en Usuario!", Toast.LENGTH_LONG).show();

                }
                else if(password.isEmpty()){
                    Toast.makeText(principal.this,"¡No deje el campo contraseña vacío!",Toast.LENGTH_SHORT).show();

                }
                else if (edtPass.getText().toString().trim().length() == 0){
                    Toast.makeText(principal.this,"¡No inserte solo espacios en blanco en contraseña!",Toast.LENGTH_SHORT).show();

                }
                else if (!compruebaClave.matches(regexClave)){
                    Toast.makeText(principal.this, "¡Ingresar solo letras minusculas y/o numeros sin espacios en contraseña!", Toast.LENGTH_LONG).show();

                }
                else if (!usuario.isEmpty()&& !password.isEmpty()){

                    validarUsuario( "https://proyectocovid19.000webhostapp.com/ejemploBDRemota/validar_usuarioCovid.php");
                   // validarUsuario( "http://192.168.2.8:8080/ejemploBDRemota/validar_usuarioCovid.php");

                }
                else{
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
                    Toast.makeText(getApplicationContext(),"¡Bienvenido(a)! : "+usuario,Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(principal.this,"¡Usuario o Clave equivocado!",Toast.LENGTH_SHORT).show();
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
