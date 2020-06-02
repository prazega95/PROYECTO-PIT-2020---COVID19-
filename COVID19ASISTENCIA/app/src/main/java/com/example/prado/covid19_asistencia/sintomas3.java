package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class sintomas3 extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText edt14;
    TextView textIDx3;

    CheckBox chk1,
             chk2,
             chk3,
             chk4,
             chk5,
             chk6,
             chk7;

    String grab1,
           grab2,
           grab3,
           grab4,
           grab5,
           grab6,
           grab7,
           grab8,
           grab9,
           grab10,
           grab11,
           grab12,
           grab13,
           grab14,
           grab15;


    Button btnGrabarSint;
    String mensaje="No tiene";

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas3);


        textIDx3 = (TextView) findViewById(R.id.textIdUsuario3);


        chk1 = (CheckBox) findViewById(R.id.chksintoma1);
        chk2 = (CheckBox) findViewById(R.id.chksintoma2);
        chk3 = (CheckBox) findViewById(R.id.chksintoma3);
        chk4 = (CheckBox) findViewById(R.id.chksintoma4);
        chk5 = (CheckBox) findViewById(R.id.chksintoma5);
        chk6 = (CheckBox) findViewById(R.id.chksintoma6);
        chk7 = (CheckBox) findViewById(R.id.chksintoma7);


        edt14 = (EditText) findViewById(R.id.edtCorreo14);

        btnGrabarSint = (Button) findViewById(R.id.btnGrabarSint);
        request = Volley.newRequestQueue(this);


        //RECIBIENDO PARAMETROS ID DEL SEGUNDO ACTIVITY, RECORDAR QUE EL SEGUNDO RECIBIO ESTOS PARAMETROS DEL PRIMERO
        Bundle bundle = getIntent().getExtras();
        String dato0= bundle.getString("editID2x3").toString();
        textIDx3.setText(dato0);
        //PONER INVISIBLE EL TEXT
        textIDx3.setVisibility(View.INVISIBLE);

       /* grab15=getIntent().getStringExtra("editID2x3");*/




        //RECIBIENDO PARAMETROS DEL SEGUNDO ACTIVITY, RECORDAR QUE EL SEGUNDO RECIBIO ESTOS PARAMETROS DEL PRIMERO
        grab1=getIntent().getStringExtra("edit1x2");
        grab2=getIntent().getStringExtra("edit2x2");
        grab3=getIntent().getStringExtra("edit3x2");
        grab4=getIntent().getStringExtra("edit4x2");

        //RECIBIENDO PARAMETROS DEL SEGUNDO ACTIVITY Y ALMACENANDO EN LA VARIABLE GRAB1.. PARA ASIGNAR Y ALMACENAR A LA BD
        grab5=getIntent().getStringExtra("edit5");
        grab6=getIntent().getStringExtra("edit6");



        btnGrabarSint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent int1 = new Intent(sintomas3.this,pantalla_registro_exitoso.class);
                cargarWebServis();
                startActivity(int1);

            }
        });

    }



        private void cargarWebServis() {

            progreso=new ProgressDialog(sintomas3.this);
            progreso.setMessage("Cargando....");
            progreso.show();

/////////////////checkbox1
                if(chk1.isChecked()){
                    grab7=chk1.getText().toString();
                }else{
                    grab7=mensaje.toString();
                }

/////////////////checkbox2
                if(chk2.isChecked()){
                    grab8=chk2.getText().toString();
                }else{
                    grab8=mensaje.toString();
                }

/////////////////checkbox3
                if(chk3.isChecked()){
                    grab9=chk3.getText().toString();
                }else{
                    grab9=mensaje.toString();
                }

/////////////////checkbox4
                if(chk4.isChecked()){
                    grab10=chk4.getText().toString();
                }else{
                    grab10=mensaje.toString();
                }

/////////////////checkbox5
                if(chk5.isChecked()){
                    grab11=chk5.getText().toString();
                }else{
                    grab11=mensaje.toString();
                }

/////////////////checkbox6
                if(chk6.isChecked()){
                    grab12=chk6.getText().toString();
                }else{
                    grab12=mensaje.toString();
                }

/////////////////checkbox7
                if(chk7.isChecked()){
                    grab13=chk7.getText().toString();
                }else{
                    grab13=mensaje.toString();
                }

/////////////////EditText Email

            //capturando valor de email
            grab14=edt14.getText().toString();
            //capturando valor de id
            grab15=textIDx3.getText().toString();





                String ip=getString(R.string.ip);
                String url=ip+="/ejemploBDRemota/wsJSONRegistroSintomasCovid.php?Departamento="+grab1+
                                                                               "&Provincia="+grab2+
                                                                               "&Distrito="+grab3+
                                                                               "&Direccion="+grab4+
                                                                               "&NumeroFamiliar="+grab5+
                                                                               "&Profesion="+grab6+
                                                                               "&PrimerSintoma="+grab7+
                                                                               "&SegundoSintoma="+grab8+
                                                                               "&TercerSintoma="+grab9+
                                                                               "&CuartoSintoma="+grab10+
                                                                               "&QuintoSintoma="+grab11+
                                                                               "&SextoSintoma="+grab12+
                                                                               "&Ninguna="+grab13+
                                                                               "&Email="+grab14+
                                                                               "&cod_usuario="+grab15;




                url=url.replace(" ","%20");

                jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                request.add(jsonObjectRequest);
            }


            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(sintomas3.this,"SINTOMAS GRABADOS! ",Toast.LENGTH_SHORT).show();
                progreso.hide();

                Intent int1 = new Intent(sintomas3.this,pantalla_registro_exitoso.class);
                startActivity(int1);


     /* //esto es para limpiar las cajas despues de guardar
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");*/

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
              //  Toast.makeText(sintomas3.this,"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
              //  Log.i("ERROR", error.toString());



            }


}

