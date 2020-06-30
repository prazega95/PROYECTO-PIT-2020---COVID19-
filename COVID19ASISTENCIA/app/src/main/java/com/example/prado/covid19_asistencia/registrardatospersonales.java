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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class registrardatospersonales extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{


    EditText nombre,
             apellido,
             documento,
             login,
             pass;
    /*Se agrego maskedittext para que pueda guardar los 9 digitos a mysql a pesar del formato que le haz dado +51(###)-###-###*/
    MaskEditText telefono;

    TextView infoFormatosRuc1;
    TextView infoFormatosRuc2;
    TextView infoFormatosRuc3;

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
            NUMERODOCUMENTO,
            TELEFONO,
            USUARIO,
            PASSWORD;

    String selecTIPODOC;
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
        telefono = (MaskEditText) findViewById(R.id.edtTelefono);
        login = (EditText) findViewById(R.id.edtUsuario);
        pass = (EditText) findViewById(R.id.edtClave);
        mostrarClave = (CheckBox)findViewById(R.id.chkmostrarClave);

        btnregistra = (Button) findViewById(R.id.btnRegistrarDatosP);

        request = Volley.newRequestQueue(this);



        infoFormatosRuc1= (TextView) findViewById(R.id.formatoRuc1);
        infoFormatosRuc2= (TextView) findViewById(R.id.formatoRuc2);
        infoFormatosRuc3= (TextView) findViewById(R.id.formatoRuc3);
        infoFormatosRuc1.setVisibility(View.INVISIBLE);
        infoFormatosRuc2.setVisibility(View.INVISIBLE);
        infoFormatosRuc3.setVisibility(View.INVISIBLE);

        //Creando el array con los campos asignados en las variables de String que se colocan despues del ..dropdown_item, ...
        comboTipoDocument=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TIPODOCUMENTO);
        tipoDoc.setAdapter(comboTipoDocument);


///////////////////////////////////////////////////////////////////// MOSTRAR FORMATOS DE RUC CUANDO SELECCIONO EL SPINNER RUC
     tipoDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> lista, View v, int p, long l) {
                switch (lista.getId()){
                    case R.id.spinnerTipoDoc:
                        posicion=p;
                        switch (p){
                            case 0: //POSICION DEL DNI CUANDO SELECCIONO DEL SPINNER

                                infoFormatosRuc1.setVisibility(View.INVISIBLE);
                                infoFormatosRuc2.setVisibility(View.INVISIBLE);
                                infoFormatosRuc3.setVisibility(View.INVISIBLE);

                            break;

                            case 1: //POSICION DEL RUC CUANDO SELECCIONO DEL SPINNER



                                infoFormatosRuc1.setVisibility(View.VISIBLE);
                                infoFormatosRuc2.setVisibility(View.VISIBLE);
                                infoFormatosRuc3.setVisibility(View.VISIBLE);

                            break;
                        }
                  }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



   ///////Adaptador para poder traer la informacion del values/array que tiene como variable ese array como "ListaDepartamentos"
     /*   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListarTipoDocumento,
                android.R.layout.simple_spinner_dropdown_item);

        //seteando el adapter al spinner a opcionesDepart(Spinner)
        tipoDoc.setAdapter(adapter);*/


        /*DARLE CONDICION AL EDITTEXT TELEFONO*/
        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(/*arg0.length()<16 || */arg0.length()>16){
                    telefono.setError("Tu número telefono a sobrepasado los 9 digitos!");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
            @Override
            public void afterTextChanged(Editable arg0) { }
        });



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





/////////////////////////////////////////////////////////////////////////////////////////////////////  REGISTRAR DATOS PERSONALES Y VALIDACIONES
  btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String regex= "^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+$";
                final String compruebaNombre = nombre.getEditableText().toString().trim();
                final String compruebaApellido = apellido.getEditableText().toString().trim();

                final String regex1= "^[+][5][1][(][9][0-9]{2}[)] [0-9]{3} [0-9]{3}$";
                final String compruebatelefono = telefono.getEditableText().toString().trim();

                final String regexDNI= "^[0-9]{8}$";
                final String regexRUC= "^(10|20|15|17)[0-9]{9}$";
                final String compruebaNºDocumento = documento.getEditableText().toString().trim();

                final String regexUsuario= "^[a-z]+$";
                final String compruebaUsuario = login.getEditableText().toString().trim();
                final String regexClave= "^[a-z0-9]+$";
                final String compruebaClave = pass.getEditableText().toString().trim();

      /*Interpretacion de regex1 : el valor que esta en [] individualmente, tiene que existir o escribir ese caracter obligatorio antes del siguiente grupo [](){},
                                  (si deseas ó poner un 8 y/o 9 obligatorio antes de sgte grupo [](){}) debes de poner asi [89][0-9]..
           FUENTES REGEX: https://platzi.com/tutoriales/1339-fundamentos-javascript/1770-expresiones-regulares-te-enseno-todo-lo-basico-con-un-solo-ejemplo/*/

      /*Interpretacion de regexRUC : los valores dentro de (10|20|15|17) son valores predefinidos que si o si tiene que ir uno de ellos, luego
      *                              se adjuntan los digitos siguientes[0-9]{9}, no olvidar que {9} es la cantidad de caracteres permitidos en [0-9]*/


       //declarando variables para los edittext y asi poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
           selecTIPODOC=tipoDoc.getSelectedItem().toString();
             NOMBRE=nombre.getText().toString();
             APELLIDO=apellido.getText().toString();
             NUMERODOCUMENTO=documento.getText().toString();
             TELEFONO=telefono.getText().toString();
             USUARIO=login.getText().toString();
             PASSWORD=pass.getText().toString();


        //SE EMPIEZE CON EQUALS "DNI" O "RUC" estos estan en el array String TIPODOCUMENTO[]={"DNI","RUC"} al comienzo del activity
        //El equal nos sirve si selecciono DNI cumplir con los demas parametros, priorizando el length de <8 en edittext Documento
        // El equal nos sirve si selecciono RUC cumplir con los demas parametros, priorizando el length de <11 en edittext Documento
              if(selecTIPODOC.equals("DNI")){

                    /*RECUERDE PONER LOS ELSE IF EN ORDEN DE ACUERDO A COMO QUIERES QUE VALIDE PRIMERO*/
                     if(NOMBRE.isEmpty()){
                        nombre.setError("¡Campo Vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingrese su(s) nombre(s)!",Toast.LENGTH_SHORT).show();

                    }
                    else if (nombre.getText().toString().trim().length() == 0){
                        nombre.setError("¡No escribas espacios en blanco!");
                        Toast.makeText(registrardatospersonales.this,"¡ALERTA En nombre(s)!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebaNombre.matches(regex)){
                        nombre.setError("¡Carácter(es) no permitido(s), verificalo!");
                        Toast.makeText(registrardatospersonales.this, "¡ALERTA En nombre(s)!!", Toast.LENGTH_LONG).show();

                    }
                    else if(APELLIDO.isEmpty()){
                        apellido.setError("¡Campo Vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingrese su(s) apellidos(s)!",Toast.LENGTH_SHORT).show();

                    }
                    else if (apellido.getText().toString().trim().length() == 0){
                        apellido.setError("¡No escribas espacios en blanco!");
                        Toast.makeText(registrardatospersonales.this,"¡ALERTA En apellido(s)!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebaApellido.matches(regex)){
                        apellido.setError("¡Carácter(es)  no permitido(s), verifique!");
                        Toast.makeText(registrardatospersonales.this, "¡ALERTA En apellido(s)!", Toast.LENGTH_LONG).show();

                    }
                    else if(NUMERODOCUMENTO.isEmpty()){
                        documento.setError("¡Campo Vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingrese su Nº de Documento!",Toast.LENGTH_SHORT).show();

                    }
                    else if(documento.length()<8){
                        documento.setError("¡No escribas menos de 8 dígitos¡");
                        Toast.makeText(registrardatospersonales.this,"¡ALERTA en DNI!",Toast.LENGTH_SHORT).show();

                    }
                    else if(documento.length()>8){
                         documento.setError("¡Existen más de 8 dígitos, verifique!");
                         Toast.makeText(registrardatospersonales.this,"¡ALERTA en DNI!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebaNºDocumento.matches(regexDNI)){
                         documento.setError("¡El formato de DNI es incorrecto!, verifique!");
                         Toast.makeText(registrardatospersonales.this, "¡ALERTA en DNI!", Toast.LENGTH_LONG).show();

                    }
                    else if(TELEFONO.isEmpty()){
                        telefono.setError("¡Campo vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingresa tu celular!",Toast.LENGTH_SHORT).show();

                    }
                    else if(telefono.length()<16){
                        /*+51(###)-###-### NOTA: los digitos son contados con los caracteres incluidos como uno mas */
                        telefono.setError("¡Como mínimo son 9 dígitos!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingresa correctamente tu Nº celular!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebatelefono.matches(regex1)){
                        telefono.setError("¡No cumple el formato especificado, verifique!");
                        Toast.makeText(registrardatospersonales.this, "¡ALERTA en Nº celular", Toast.LENGTH_LONG).show();

                    }
                    else if(USUARIO.isEmpty()){
                        login.setError("¡Campo vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingrese su Usuario!",Toast.LENGTH_SHORT).show();

                    }
                    else if (login.getText().toString().trim().length() == 0){
                        login.setError("¡No escribas espacios en blanco!");
                        Toast.makeText(registrardatospersonales.this,"¡ALERTA En Usuario!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebaUsuario.matches(regexUsuario)){
                         login.setError("¡Solo ingrese letras minúsculas sin espacios!");
                         Toast.makeText(registrardatospersonales.this, "¡ALERTA en Usuario", Toast.LENGTH_LONG).show();

                    }
                    else if(PASSWORD.isEmpty()){
                        pass.setError("¡Campo vacio!");
                        Toast.makeText(registrardatospersonales.this,"¡Ingrese su contraseña!",Toast.LENGTH_SHORT).show();

                    }
                    else if (pass.getText().toString().trim().length() == 0){
                         pass.setError("¡No escribas espacios en blanco!");
                         Toast.makeText(registrardatospersonales.this,"¡ALERTA En Contraseña!",Toast.LENGTH_SHORT).show();

                    }
                    else if (!compruebaClave.matches(regexClave)){
                         pass.setError("¡Solo ingrese letras minúsculas y/o números sin espacios!");
                         Toast.makeText(registrardatospersonales.this, "¡ALERTA en Contraseña", Toast.LENGTH_LONG).show();

                    }
                    else{
                        //ENVIANDO DEL WEB SERVIR AL MYSQL DE CUMPLIR CON TODAS LAS CONDICIONES
                        cargarWebServis();
                    }
               }

           else if(selecTIPODOC.equals("RUC")){


                  /*RECUERDE PONER LOS ELSE IF EN ORDEN DE ACUERDO A COMO QUIERES QUE VALIDE PRIMERO*/
                  if(NOMBRE.isEmpty()){
                      nombre.setError("¡Campo Vacio!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingrese su(s) nombre(s)!",Toast.LENGTH_SHORT).show();

                  }
                  else if (nombre.getText().toString().trim().length() == 0){
                      nombre.setError("¡No escribas espacios en blanco!");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA En nombre(s)!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebaNombre.matches(regex)){
                      nombre.setError("¡Carácter(es) no permitido(s), verificalo!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA En nombre(s)!!", Toast.LENGTH_LONG).show();

                  }
                  else if(APELLIDO.isEmpty()){
                      apellido.setError("¡Campo Vacio!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingrese su(s) apellidos(s)!",Toast.LENGTH_SHORT).show();

                  }
                  else if (apellido.getText().toString().trim().length() == 0){
                      apellido.setError("¡No escribas espacios en blanco!");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA En apellido(s)!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebaApellido.matches(regex)){
                      apellido.setError("¡Carácter(es) no permitido(s), verificalo!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA En apellido(s)!", Toast.LENGTH_LONG).show();

                  }
                  else if(NUMERODOCUMENTO.isEmpty()){
                      documento.setError("¡Campo Vacio!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingrese su Nº de Documento!",Toast.LENGTH_SHORT).show();

                  }
                  else if(documento.length()<11){
                      documento.setError("¡No escribas menos de 11 dígitos!");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA en RUC!",Toast.LENGTH_SHORT).show();

                  }
                  else if(documento.length()>11){
                      documento.setError("¡Existen más de 11 dígitos, verifique");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA en RUC!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebaNºDocumento.matches(regexRUC)){
                      documento.setError("¡El formato de RUC es incorrecto, verifique!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA en RUC!", Toast.LENGTH_LONG).show();

                  }
                  else if(TELEFONO.isEmpty()){
                      telefono.setError("¡Campo vacio!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingresa tu celular!",Toast.LENGTH_SHORT).show();

                  }
                  else if(telefono.length()<16){
                      /*+51(###)-###-### NOTA: los digitos son contados con los caracteres incluidos como uno mas */
                      telefono.setError("¡Como mínimo son 9 dígitos!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingresa correctamente tu Nº celular!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebatelefono.matches(regex1)){
                      telefono.setError("¡No cumple el formato especificado, verifique!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA en Nº celular!", Toast.LENGTH_LONG).show();

                  }
                  else if(USUARIO.isEmpty()){
                      login.setError("¡Campo vacio!");
                      Toast.makeText(registrardatospersonales.this,"¡Ingrese su Usuario!",Toast.LENGTH_SHORT).show();

                  }
                  else if (login.getText().toString().trim().length() == 0){
                      login.setError("¡No escribas espacios en blanco!");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA En Usuario!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebaUsuario.matches(regexUsuario)){
                      login.setError("¡Solo ingrese letras minúsculas sin espacios!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA en Usuario!", Toast.LENGTH_LONG).show();

                  }
                  else if(PASSWORD.isEmpty()){
                      pass.setError("¡Campo vacio¡");
                      Toast.makeText(registrardatospersonales.this,"¡Ingrese su contraseña!",Toast.LENGTH_SHORT).show();

                  }
                  else if (pass.getText().toString().trim().length() == 0){
                      pass.setError("¡No escribas espacios en blanco!");
                      Toast.makeText(registrardatospersonales.this,"¡ALERTA En Contraseña!",Toast.LENGTH_SHORT).show();

                  }
                  else if (!compruebaClave.matches(regexClave)){
                      pass.setError("¡Solo ingrese letras y/o números sin espacios!");
                      Toast.makeText(registrardatospersonales.this, "¡ALERTA en Contraseña!", Toast.LENGTH_LONG).show();

                  }
                  else{
                      //ENVIANDO DEL WEB SERVIR AL MYSQL DE CUMPLIR CON TODAS LAS CONDICIONES
                      cargarWebServis();

                  }

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
                "&tel_usuario="+telefono.getRawText().toString()+
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

     //Toast.makeText(registrardatospersonales.this,"ERROR RESPONSE : "+response.toString(),Toast.LENGTH_SHORT).show();
       Toast.makeText(registrardatospersonales.this,"¡Te registraste Exitosamente! : "+NuevoUsuario,Toast.LENGTH_SHORT).show();
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
        Toast.makeText(registrardatospersonales.this,"¡Tu DNI/RUC, Celular y/o Usuario, registran existentes aún!",Toast.LENGTH_SHORT).show();
      //  Toast.makeText(registrardatospersonales.this,"ERROR VOLLEY : "+error,Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }



















}