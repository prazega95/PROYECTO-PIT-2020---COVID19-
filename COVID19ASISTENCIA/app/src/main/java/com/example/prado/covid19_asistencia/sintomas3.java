package com.example.prado.covid19_asistencia;

import android.app.AlertDialog;
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
           grabLatitud,
           grabLongitud,
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


    String condicion;
    String mensaje="No tiene";
    String mensajeSinSintomas="Sin Ningun Sintoma";
    String mensajeConSintomas="Contiene Sintomas";


    String EMAIL;


    //BARRA DE PROGRESO
    ProgressDialog progreso;

    //METODOS JSON
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



        //RECIBIENDO PARAMETROS DEL SEGUNDO ACTIVITY, RECORDAR QUE EL SEGUNDO RECIBIO ESTOS PARAMETROS DEL PRIMERO
        grab1=getIntent().getStringExtra("edit1x2");
        grab2=getIntent().getStringExtra("edit2x2");
        grab3=getIntent().getStringExtra("edit3x2");
        grab4=getIntent().getStringExtra("edit4x2");
        grabLatitud=getIntent().getStringExtra("edit5x2");
        grabLongitud=getIntent().getStringExtra("edit6x2");

        //RECIBIENDO PARAMETROS DEL SEGUNDO ACTIVITY Y ALMACENANDO EN LA VARIABLE GRAB1.. PARA ASIGNAR Y ALMACENAR A LA BD
        grab5=getIntent().getStringExtra("edit5");
        grab6=getIntent().getStringExtra("edit6");


////////////////////////////////////////////////////////////////////////////////////////////////////////  BOTON DE GRABAR TRIAJE
        btnGrabarSint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //declarando variables para poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
                EMAIL=edt14.getText().toString();

              //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
              if (!EMAIL.isEmpty()){

                // Pasar al activity pantalla registro y cargando el web servis
                 Intent int1 = new Intent(sintomas3.this,pantalla_registro_exitoso.class);
                 cargarWebServis();
                 Toast.makeText(sintomas3.this, "Triaje Registrado !", Toast.LENGTH_SHORT).show();
                  startActivity(int1);
               }
                 else{
                 Toast.makeText(sintomas3.this,"Agrege su Email",Toast.LENGTH_SHORT).show();
               }
            }
     });



        //CHECKBOX "NO TENGO SINTOMAS", QUE INDICA LA FUNCION QUE HARA SEGUN SU CLICKEO
        chk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk7.isChecked()==true){

                    // PUNTO1 - INHABILITA los checkbox, pero si estas estan ya marcadas seguira con su seleccion o marcacion
                    chk1.setEnabled(false);
                    chk2.setEnabled(false);
                    chk3.setEnabled(false);
                    chk4.setEnabled(false);
                    chk5.setEnabled(false);
                    chk6.setEnabled(false);

                    // PUNTO 2 - Esto DESELECCIONA los checkbox MARCADOS EN EL PUNTO 1
                    chk1.setChecked(false);
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                    chk4.setChecked(false);
                    chk5.setChecked(false);
                    chk6.setChecked(false);

                }else{

                    // SI EL CHECK BOX "sin sintomas" DESELECCIONAS, LOS CHECKBOX DEL PUNTO 1 SE ACTIVARAN
                    chk1.setEnabled(true);
                    chk2.setEnabled(true);
                    chk3.setEnabled(true);
                    chk4.setEnabled(true);
                    chk5.setEnabled(true);
                    chk6.setEnabled(true);
                }
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
                    //texto sin sintomas
                    grab13=mensajeSinSintomas.toString();
                }else{
                    //texto con sintomas
                    grab13=mensajeConSintomas.toString();
                }

/////////////////EditText Email

            //capturando valor de email
            grab14=edt14.getText().toString();
            //capturando valor de id
            grab15=textIDx3.getText().toString();




            //guardar texto condicion a mysql segun la seleccion de checkbox
            // && = y
            // || = o

            if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked() && chk4.isChecked() && chk5.isChecked() && chk6.isChecked()){
                condicion="Grave";
                //Toast.makeText(sintomas3.this, "Grave", Toast.LENGTH_SHORT).show();

            }else if(chk1.isChecked() || chk2.isChecked() || chk3.isChecked() || chk4.isChecked() || chk5.isChecked() || chk6.isChecked()){
                condicion="Moderado";
                //Toast.makeText(sintomas3.this, "Leve", Toast.LENGTH_SHORT).show();

            }else if(chk7.isChecked()==true){
                condicion="Saludable";
                //Toast.makeText(sintomas3.this, "Saludable", Toast.LENGTH_SHORT).show();

            }else{
                condicion="Saludable";
                //Toast.makeText(sintomas3.this, "Saludable", Toast.LENGTH_SHORT).show();
            }


            ///////////  ENVIANDO AL PHP CON LOS DATOS OBTENIDOS
                String ip=getString(R.string.ip);
                String url=ip+="/ejemploBDRemota/wsJSONRegistroSintomasCovid.php?Departamento="+grab1+
                                                                               "&Provincia="+grab2+
                                                                               "&Distrito="+grab3+
                                                                               "&Direccion="+grab4+
                                                                               "&Latitud="+grabLatitud+
                                                                               "&Longitud="+grabLongitud+
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
                                                                               "&Condicion="+condicion.toString()+
                                                                               "&cod_usuario="+grab15;


                url=url.replace(" ","%20");

                jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                request.add(jsonObjectRequest);
            }



            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();

            }



}

