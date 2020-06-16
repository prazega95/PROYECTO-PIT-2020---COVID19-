package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    Spinner tipoDoc;

    Button btnregistra;


    String  NOMBRE,
            APELLIDO,
            DOCUMENTO,
            TELEFONO,
            USUARIO,
            PASSWORD;

    String NuevoUsuario;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrardatospersonales);

        nombre = (EditText) findViewById(R.id.edtNombre);
        apellido = (EditText) findViewById(R.id.edtApellidos);
        tipoDoc = (Spinner) findViewById(R.id.spinnerTipoDoc);
        documento = (EditText) findViewById(R.id.edtDocumentos);
        telefono = (EditText) findViewById(R.id.edtTelefono);
        login = (EditText) findViewById(R.id.edtUsuario);
        pass = (EditText) findViewById(R.id.edtClave);


        btnregistra = (Button) findViewById(R.id.btnRegistrarDatosP);

        request = Volley.newRequestQueue(this);



        //Adaptador para poder traer la informacion del values/array que tiene como variable ese array como "ListaDepartamentos"
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListarTipoDocumento,
                android.R.layout.simple_spinner_dropdown_item);

        //seteando el adapter al spinner a opcionesDepart(Spinner)
        tipoDoc.setAdapter(adapter);





////////////////////////////////////////////////////////////////////////////////////////////////  REGISTRAR DATOS PERSONALES
        btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //declarando variables para poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
                  NOMBRE=nombre.getText().toString();
                  APELLIDO=apellido.getText().toString();
                  DOCUMENTO=documento.getText().toString();
                  TELEFONO=telefono.getText().toString();
                  USUARIO=login.getText().toString();
                  PASSWORD=pass.getText().toString();

                //Condicion para registrar si estos campos no estan completados o llenados
                if (!NOMBRE.isEmpty()&&
                        !APELLIDO.isEmpty()&&
                          !DOCUMENTO.isEmpty()&&
                            !TELEFONO.isEmpty()&&
                               !USUARIO.isEmpty()&&
                                  !PASSWORD.isEmpty()){

                    Intent int1 = new Intent(registrardatospersonales.this,principal.class);

                    //ENVIANDO DEL WEB SERVIR AL MYSQL
                    cargarWebServis();

                    startActivity(int1);

                }
                 else{
                 Toast.makeText(registrardatospersonales.this,"Complete Todos los Campos", Toast.LENGTH_SHORT).show();
                }


             }
          });


  }



    private void cargarWebServis() {

        progreso=new ProgressDialog(registrardatospersonales.this);
        progreso.setMessage("Cargando....");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+="/ejemploBDRemota/wsJSONRegistroUsuario.php?nom_usuario="+nombre.getText().toString()+
                                                                 "&ape_usuario="+apellido.getText().toString()+
                                                             "&TipoDoc_usuario="+tipoDoc.getSelectedItem().toString()+
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

           //Creando variable concatenando con el edittext nombre para el saludo
           NuevoUsuario=nombre.getText().toString();

           Toast.makeText(registrardatospersonales.this,"Te registraste Exitosamente! : "+NuevoUsuario,Toast.LENGTH_SHORT).show();
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