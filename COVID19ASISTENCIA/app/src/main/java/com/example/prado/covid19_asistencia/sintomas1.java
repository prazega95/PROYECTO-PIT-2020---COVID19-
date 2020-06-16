package com.example.prado.covid19_asistencia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class sintomas1 extends AppCompatActivity {


   //LOS DATOS ID Y NOMBRE QUE ESTAN SIN COMILLAS ESTAN DECLARADAS Y LLAMADAS EN TRIAJE Y LOS DATOS "id" y
   //"nombre" EN LA CLASE JAVA Usuario donde tienen los set y get
    public  static final String ID="id";
    public  static final String NOMBRE="nombre";

    TextView textId,textNombre;

    Spinner opcionesDepart;
    EditText provinciaManual,distritoManual,direccionManual,direccionGps;
    TextView latitud,longitud,gpsEstado;

    Switch Boton_OnOff_GPS;
    Button btnNext1;


    String DEPARTAMENTOVACIO,
            PROVINCIAVACIO,
            DISTRITOVACIO,
            DIRECCIONMANUALVACIO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas1);

        textId=(TextView) findViewById(R.id.textIdUsuario1);
        textNombre=(TextView) findViewById(R.id.textNombreUsuario);

        opcionesDepart = (Spinner) findViewById(R.id.spinnerDepart);
        provinciaManual = (EditText) findViewById(R.id.edtProvincia2);
        distritoManual = (EditText) findViewById(R.id.edtDistrito3);

        Boton_OnOff_GPS = (Switch)findViewById(R.id.onOffManualGps);
        gpsEstado = (TextView) findViewById(R.id.txtGPS);

        direccionGps = (EditText) findViewById(R.id.edtDireccionGPS);
        direccionManual = (EditText) findViewById(R.id.edtDireccionManual);

        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);

        btnNext1 =(Button) findViewById(R.id.btnSiguienteSint1);

        latitud.setVisibility(View.INVISIBLE);
        longitud.setVisibility(View.INVISIBLE);



        //RECIBIENDO LOS DATOS EXTRAIDOS DE TRIAJE, LOS DATOS ENTRECOMILLAS "id" y "nombre"
        //FUERON ESTABLECIDOS EN LA CLASE JAVA Usuario, dentro de ella contiene los set y get
        String idusuario=getIntent().getStringExtra("id");
        textId.setText(idusuario);
        textId.setVisibility(View.INVISIBLE);

        String nombreusuario=getIntent().getStringExtra("nombre");
        textNombre.setText(nombreusuario);


        //Adaptador para poder traer la informacion del values/array que tiene como variable ese array como "ListaDepartamentos"
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ListaDepartamentos,
                android.R.layout.simple_spinner_dropdown_item);

        //seteando el adapter al spinner a opcionesDepart(Spinner)
        opcionesDepart.setAdapter(adapter);




        //condicionales para el checkbox segun su marcacion que deshabilitar o habilitar para el registro
        Boton_OnOff_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Boton_OnOff_GPS.isChecked()==true) {

                    ////////////////////////////////////////// 1 - ACTIVO la habilitacion de UBICACION DE PLAY SERVICES
                    //////////////////////////////////////////     (solo si el gps esta desactivado)
                    locationStart();

                    ////////////////////////////////////////// 2 - Habilito la visibilidad de los textview latitud y longitud
                    latitud.setVisibility(View.VISIBLE);
                    longitud.setVisibility(View.VISIBLE);

                    ////////////////////////////////////////// 3 - VISIBILIDAD al edittext de direccion por gps
                    /////////////////////////////////////////      INVISIBILIDAD al edittext de direccion Manual
                    direccionGps.setEnabled(false);
                    direccionGps.setVisibility(View.VISIBLE);
                    direccionManual.setVisibility(View.INVISIBLE);

                    ////////////////////////////////////////// 4 - Habilito textview "gps activado"
                    gpsEstado.setText("GPS Activado");
                    Toast.makeText(sintomas1.this, "Coordenadas Obtenidas!", Toast.LENGTH_LONG).show();


                }else if(Boton_OnOff_GPS.isChecked()==false){

                    ////////////////////////////////////////// 1 - ACTIVO Y LLAMO UNA ALERTA TOAST
                    gpsEstado.setText("Desactiva tu GPS!");

                    ////////////////////////////////////////// 2 - APLICO INVISIBILIDAD a los textview de coordenadas
                    direccionGps.setVisibility(View.INVISIBLE);
                    direccionManual.setEnabled(true);
                    direccionManual.setVisibility(View.VISIBLE);

                    ////////////////////////////////////////// 3 - APLICO INVISIBILIDAD a los textview de latitud y longitud
                    latitud.setVisibility(View.INVISIBLE);
                    longitud.setVisibility(View.INVISIBLE);
                }
            }
        });




////////////////////////////////////////////////////////////////////////////////////////////////////////////// BOTON SIGUIENTE
        btnNext1.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {

           //declarando variables para poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
            DEPARTAMENTOVACIO=opcionesDepart.getSelectedItem().toString();
            PROVINCIAVACIO=provinciaManual.getText().toString();
            DISTRITOVACIO=distritoManual.getText().toString();
            DIRECCIONMANUALVACIO=direccionManual.getText().toString();

               //Condicion si selecciono el boton de registrar mi direccion por GPS
               if (Boton_OnOff_GPS.isChecked()==true) {

                   //Condicion si esta ya con el campo escrito "Direccion Automatica" pero faltan los campos depar,prov,dist
                    if (!DEPARTAMENTOVACIO.isEmpty()&&
                           !PROVINCIAVACIO.isEmpty()&&
                             !DISTRITOVACIO.isEmpty()){

                      //LLamando al siguiente activity
                      Intent int1 = new Intent(sintomas1.this,sintomas2.class);

                      //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
                      int1.putExtra("editID1x2",textId.getText().toString());

                      //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
                      int1.putExtra("edit1",opcionesDepart.getSelectedItem().toString());
                      int1.putExtra("edit2",provinciaManual.getText().toString());
                      int1.putExtra("edit3",distritoManual.getText().toString());
                      int1.putExtra("edit4",direccionGps.getText().toString());
                      int1.putExtra("edit5",latitud.getText().toString());
                      int1.putExtra("edit6",longitud.getText().toString());
                      startActivity(int1);
                    }
                     else{
                       Toast.makeText(sintomas1.this,"Complete Todos los Campos",Toast.LENGTH_SHORT).show();
                    }
                 }
                  //Condicion si deselecciono o nunca lo eh seleccionado el boton de registrar mi direccion por GPS
                  else if(Boton_OnOff_GPS.isChecked()==false){

                        //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
                        // con los parametros manuales en particular "direccion manual"
                        if (!DEPARTAMENTOVACIO.isEmpty()&&
                                !PROVINCIAVACIO.isEmpty()&&
                                    !DISTRITOVACIO.isEmpty()&&
                                       !DIRECCIONMANUALVACIO.isEmpty()){

                      //LLamando al siguiente activity
                      Intent int2 = new Intent(sintomas1.this,sintomas2.class);

                      //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
                      int2.putExtra("editID1x2",textId.getText().toString());

                      //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
                      int2.putExtra("edit1",opcionesDepart.getSelectedItem().toString());
                      int2.putExtra("edit2",provinciaManual.getText().toString());
                      int2.putExtra("edit3",distritoManual.getText().toString());
                      int2.putExtra("edit4",direccionManual.getText().toString());
                      int2.putExtra("edit5",latitud.getText().toString());
                      int2.putExtra("edit6",longitud.getText().toString());
                      startActivity(int2);
                    }
                      else{
                      Toast.makeText(sintomas1.this,"Complete Todos los Campos",Toast.LENGTH_SHORT).show();
                    }
                }

       }
     });


        //SOLICITANDO PERMISO DE UBICACION AL PLAY SERVICE Y ACTIVAR AUTOMATICAMENTE EL GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
          }

   }


   //METODO PARA UBICARME Y EXTRAER LAS COORDENADAS DEL GPS SIN ABRIR EL MAPS
    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
         }
     }

    //Obtener la direccion de la calle a partir de la latitud y la longitud
    public void setLocation(Location loc) {
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccionGps.setText(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Aqui empieza la Clase Localizacion
    public class Localizacion implements LocationListener {
        sintomas1 sintomas1;
        public sintomas1 getMainActivity() {
            return sintomas1;
        }
        public void setMainActivity(sintomas1 mainActivity) {
            this.sintomas1 = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();

            String sLatitud = String.valueOf(loc.getLatitude());
            String sLongitud = String.valueOf(loc.getLongitude());

            latitud.setText(sLatitud);
            longitud.setText(sLongitud);

            this.sintomas1.setLocation(loc);
        }


        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            gpsEstado.setText("GPS DESACTIVADO");
            latitud.setText("Latitud No Encontrado");
            longitud.setText("longitud No Encontrado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            gpsEstado.setText("GPS ACTIVADO");
        }

        //FUNCION PARA VER EN QUE ESTADO SE ENCUENTRA EL SERVICIO DE GPS UBICACION
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
          }
        }






}
