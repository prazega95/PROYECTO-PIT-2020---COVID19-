package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class registrardatospersonales extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, AdapterView.OnItemSelectedListener {


    EditText nombre,
             apellido,
             documento,
             documentoRuc,
             login,
             pass;
    /*Se agrego maskedittext para que pueda guardar los 9 digitos a mysql a pesar del formato que le haz dado +51(###)-###-###*/
    MaskEditText telefono;

    TextView obtenerDocument;

    Spinner tipoDoc;
    /*SPINNER DEPARTAMENTO, PROVINCIA, DISTRITO*/
    ArrayAdapter<String> comboTipoDocument;
    int posicion;
    String TIPODOCUMENTO[]={"DNI","RUC"};

    CheckBox mostrarClave;

    private boolean esVisible;

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
        documentoRuc= (EditText) findViewById(R.id.edtDocumentosRUC);
        obtenerDocument = (TextView) findViewById(R.id.obtenerDocumento);
        obtenerDocument.setVisibility(View.INVISIBLE);

        telefono = (MaskEditText) findViewById(R.id.edtTelefono);
        login = (EditText) findViewById(R.id.edtUsuario);
        pass = (EditText) findViewById(R.id.edtClave);
        mostrarClave = (CheckBox)findViewById(R.id.chkmostrarClave);

        btnregistra = (Button) findViewById(R.id.btnRegistrarDatosP);

        request = Volley.newRequestQueue(this);


        //Creando el array con los campos asignados en las variables de String que se colocan despues del ..dropdown_item, ...
        comboTipoDocument=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TIPODOCUMENTO);
        tipoDoc.setAdapter(comboTipoDocument);
        tipoDoc.setOnItemSelectedListener(this);



        /*DARLE CONDICION AL EDITTEXT TELEFONO*/
        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(arg0.length()<16 || arg0.length()>16){
                    telefono.setError("Tu número telefono solo debe tener 9 digitos!");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
            @Override
            public void afterTextChanged(Editable arg0) { }
        });



   ///////Adaptador para poder traer la informacion del values/array que tiene como variable ese array como "ListaDepartamentos"
     /*   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListarTipoDocumento,
                android.R.layout.simple_spinner_dropdown_item);

        //seteando el adapter al spinner a opcionesDepart(Spinner)
        tipoDoc.setAdapter(adapter);*/




///////////condicionales para el checkbox segun su marcacion mostrar la contraseña escrito o no
        mostrarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!esVisible) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    esVisible = true;
                }
                else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    esVisible = false;
                }
            }
        });






////////////////////////////////////////////////////////////////////////////////////////////////  REGISTRAR DATOS PERSONALES
        btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //declarando variables para los edittext y asi poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
                  NOMBRE=nombre.getText().toString();
                  APELLIDO=apellido.getText().toString();
                  DOCUMENTO=obtenerDocument.getText().toString();
                  TELEFONO=telefono.getText().toString();
                  USUARIO=login.getText().toString();
                  PASSWORD=pass.getText().toString();

                ////////////////////////// 1ºDA CONDICION ALTERNATIVA: registrar si estos campos no estan completados o llenados
                if (!NOMBRE.isEmpty()&&
                        !APELLIDO.isEmpty()&&
                        !DOCUMENTO.isEmpty()&&
                        !TELEFONO.isEmpty()&&
                        !USUARIO.isEmpty()&&
                        !PASSWORD.isEmpty()){

                    //ENVIANDO DEL WEB SERVIR AL MYSQL DE CUMPLIR CON TODAS LAS CONDICIONES
                    cargarWebServis();
                }
                else{
                    Toast.makeText(registrardatospersonales.this,"Complete Todos los Campos", Toast.LENGTH_SHORT).show();
                }

                ////////////////////////////////*2DA CONDICION ESPECIFICANDO QUE EDITTEXT FALTA COMPLETAR*/
                if(NOMBRE.isEmpty()){
                    nombre.setError("Ingresa tus nombres");
                 //   Toast.makeText(registrardatospersonales.this,"Debes de ingresar tu nombre",Toast.LENGTH_LONG).show();
                }
                if(APELLIDO.isEmpty()){
                    apellido.setError("Ingresa tus apellidos");
                }
                if(DOCUMENTO.isEmpty()){
                    obtenerDocument.setError("Ingresa tu Nº de documento");
                }
                if(TELEFONO.isEmpty()){
                    telefono.setError("Ingresa tu telefono");
                }
                if(USUARIO.isEmpty()){
                    login.setError("Ingresa tu usuario");
                }
                if(PASSWORD.isEmpty()){
                     pass.setError("Ingresa tu clave");
                }
                else{
                }
            }
        });


    }






    /*combbox escoger TIPO DE DOCUMENTO */
    @Override
    public void onItemSelected(AdapterView<?> lista, View v, int p, long l) {
        switch (lista.getId()){
            case R.id.spinnerTipoDoc:
                posicion=p;

                switch (p){
                    case 0: // SELECCIONA DNI

                        documento.setText("");
                        obtenerDocument.setText("");
                        documentoRuc.setText("");
                        documento.setVisibility(View.VISIBLE);
                        documentoRuc.setVisibility(View.INVISIBLE);

                         /*UN VENTANA DE ERROR SI ES QUE NO CUMPLE CON EL PARAMETRO ESTABLECIDO MIENTRAS SE ESCRIBE*/
                         documento.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                                if(arg0.length()>8 /*|| arg0.length()>8*/){
                                    documento.setError("Error: DNI como max 8 caracteres");
                                    //documento.setError("Nota: solo 8 digitos para dni");
                                }
                                  /*El codigo de abajo funciona para obtener automaticamente en el textview lo que escribimos en el edittext*/
                                  obtenerDocument.setText(documento.getText().toString());
                             }
                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
                            @Override
                            public void afterTextChanged(Editable arg0) { }
                        });
                        break;

                    case 1: // SELECCIONA RUC

                        documento.setText("");
                        obtenerDocument.setText("");
                        documentoRuc.setText("");
                        documentoRuc.setVisibility(View.VISIBLE);
                        documento.setVisibility(View.INVISIBLE);

                        documentoRuc.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                                if(arg0.length()>11 /*|| arg0.length()>8*/){
                                    documentoRuc.setError("Error: RUC como min 11 caracteres");
                                    //documentoRuc.setError("Nota: solo 11 digito para RUC");
                                }
                                obtenerDocument.setText(documentoRuc.getText().toString());
                             }
                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
                            @Override
                            public void afterTextChanged(Editable arg0) {
                            }
                        });
                        break;
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }







    private void cargarWebServis() {

        progreso=new ProgressDialog(registrardatospersonales.this);
        progreso.setMessage("Cargando....");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+="/ejemploBDRemota/wsJSONRegistroUsuario.php?nom_usuario="+nombre.getText().toString()+
                "&ape_usuario="+apellido.getText().toString()+
                "&TipoDoc_usuario="+tipoDoc.getSelectedItem().toString()+
                "&doc_usuario="+obtenerDocument.getText().toString()+
                "&tel_usuario="+telefono.getRawText().toString()+
                "&login_usuario="+login.getText().toString()+
                "&pass_usuario="+pass.getText().toString();


        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }



    private void cargarWebServisConsultar() {

        progreso=new ProgressDialog(registrardatospersonales.this);
        progreso.setMessage("Cargando....");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+="/ejemploBDRemota/wsJSONConsultarTelefono.php?tel_usuario="+telefono.getRawText().toString();

        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }




    @Override
    public void onResponse(JSONObject response) {

        //Creando variable concatenando con el edittext nombre para el saludo
        NuevoUsuario=nombre.getText().toString();

        // Toast.makeText(registrardatospersonales.this,"Error : "+response.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(registrardatospersonales.this,"Te registraste Exitosamente! : "+NuevoUsuario,Toast.LENGTH_SHORT).show();
        progreso.hide();

        nombre.setText("");
        apellido.setText("");
        documento.setText("");
        telefono.setText("");
        login.setText("");
        pass.setText("");

        Intent int1 = new Intent(registrardatospersonales.this,principal.class);
        startActivity(int1);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(registrardatospersonales.this,"Nº de documento o celular ya registrado! Verifica nuevamente!",Toast.LENGTH_SHORT).show();

      //  Toast.makeText(registrardatospersonales.this,"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }



















}