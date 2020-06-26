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
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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

public class sintomas1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   //LOS DATOS ID Y NOMBRE QUE ESTAN SIN COMILLAS ESTAN DECLARADAS Y LLAMADAS EN TRIAJE Y LOS DATOS "id" y
   //"nombre" EN LA CLASE JAVA Usuario donde tienen los set y get
    public  static final String ID="id";
    public  static final String NOMBRE="nombre";

    TextView textId,textNombre;
    TextView extraerDepaa,extraerProvv,extraerDistt;

    Spinner opcionesDepart,opcionesProvin,opcionesDistri;
    EditText provinciaManual,distritoManual,direccionManual,direccionGps;
    TextView latitud,longitud,gpsEstado;

    Switch Boton_OnOff_GPS;
    Button btnNext1;

    String DEPARTAMENTOVACIO,
            PROVINCIAVACIO,
            DISTRITOVACIO,
            DIRECCIONMANUALVACIO;

    /*SPINNER DEPARTAMENTO, PROVINCIA, DISTRITO*/
    ArrayAdapter<String> depar1,provSeleccione,distSeleccione,
                         prov1, dist1, dist2,
                         prov2, dist3, dist4,
                         prov3, dist5, dist6,
                         prov4, dist7,
                         prov5, dist8, dist9,
                         prov6, dist10, dist11,
                         prov7, dist12, dist13,
                         prov8, dist14, dist15,
                         prov9, dist16, dist17,
                         prov10, dist18, dist19,
                         prov11, dist20, dist21,
                         prov12, dist22, dist23,
                         prov13, dist24, dist25;

    /*MARCANDO LA POSICION DEL ARRAY*/
    int posicion;

    /*String agregando los campos de los array*/
    String DEPARTAMENTO[]={"Seleccione..","Amazonas","Ancash","Apurimac","Arequipa","Ayacucho","Cajamarca","Callao","Cusco","Huancavelica","Huanuco",
                           "Ica","Junin","La Libertad","Lambayeque","Lima","Loreto","Madre De Dios","Moquegua","Pasco","Piura","Puno","San Martin",
                           "Tacna","Tumbes","Ucayali"}; // DEPARTAMENTOS

    String PROV0[]={"Seleccione.."}; //PROVINCIA EN BLANCO (SIN ESCOGER)
    String DISTRI0[]={""}; //DISTRITO EN BLANCO (SIN ESCOGER)

    String PROVAmazonas[]={"Seleccione..","Bagua","Chachapoyas"}; //PROVINCIAS DE Amazonas
    //DISTRITOS
    String Bagua[]={"","ARAMANGO","BAGUA","COPALLÍN","EL PARCO","IMAZA","LA PECA"};
    String Chachapoyas[]={"","Chachapoyas","Condorcanqui","Luya","Utcubamba","Rodriguez de Mendoza","Bagua"};

    String PROVAncash[]={"Seleccione..","Huaraz","Carhuaz"}; //PROVINCIAS DE Ancash
    //DISTRITOS
    String Huaraz[]={"","Cochabamba","Colcabamba","Huanchay","Olleros","Pariacoto"};
    String Canta[]={"","Arahuay","Huamantanga"};

    String PROVArequipa[]={"Seleccione..","Caylloma","Caravelí"}; //PROVINCIAS DE Arequipa
    //DISTRITOS
    String Caylloma[]={"","Chivay","Achoma","Cabanaconde","Coporaque","Huambo"};
    String Caravelí[]={"","Yauca","Acarí","Lomas","Jaquí","Atiquipa","Cháparra"};

    String PROVCallao[]={"Seleccione..","Callao"}; //PROVINCIAS DE Callao
    //DISTRITOS
    String Callao[]={"","Bellavista","La Perla","La Punta","Ventanilla","Carmen de La Legua","Callao"};

    String PROVCusco[]={"Seleccione..","Urubamba","Cuzco"}; //PROVINCIAS DE Cusco
    //DISTRITOS
    String Urubamba[]={"","Chinchero","Huayllabamba","Machupicchu"};
    String Cuzco[]={"","CCORCA","CUSCO","POROY","SANTIAGO","SAN JERÓNIMO"};

    String PROVLaLibertad[]={"Seleccione..","Chepén","Otuzco"}; // PROVINCIAS DE LaLibertad
    //DISTRITOS
    String Chepen[]={"","Chepén"};
    String Otuzco[]={"","Sinsicap","Huaranchal","Charat","Usquil","Otuzco"};

    String PROVLambayeque[]={"Seleccione..","LAMBAYEQUE","MÓRROPE"}; // PROVINCIAS DE Lambayeque
    //DISTRITOS
    String LAMBAYEQUE[]={"","ILLIMO","CHÓCHOPE","JAYANCA","MÓRROPE","MOTUPE"};
    String MORROPE[]={"","Mórrope"};

    String PROVLima[]={"Seleccione..","Lima Metropolitana","Cañete"}; // PROVINCIAS DE Lima
    //DISTRITOS
    String LimaMetropolitana[]={"","ANCÓN","ATE","BARRANCO","BREÑA","CARABAYLLO","CHACLACAYO","CHORRILLOS","CIENEGUILLA","CIENEGUILLA","COMAS","EL AGUSTINO","INDEPENDENCIA","JESÚS MARÍA","LA MOLINA",
                               "LA VICTORIA","LIMA","LINCE","LOS OLIVOS","LURIGANCHO-CHOSICA","LURÍN","MAGDALENA DEL MAR","MIRAFLORES","PACHACÁMAC","PUCUSANA","PUEBLO LIBRE","PUENTE PIEDRA","PUNTA HERMOSA",
                               "PUNTA NEGRA","RÍMAC","SAN BARTOLO","SAN BORJA","SAN ISIDRO","SAN JUAN DE LURIGANCHO","SAN JUAN DE MIRAFLORES","SAN LUIS","SAN MARTIN DE PORRES","SAN MIGUEL","SANTA ANITA","SANTA MARÍA DEL MAR",
                               "SANTA ROSA","SANTIAGO DE SURCO","SURQUILLO","VILLA EL SALVADOR","VILLA MARIA DEL TRIUNFO"};
    String Cañete[]={"","CERRO AZUL","ASIA","CHILCA","LUNAHUANÁ","MALA","SAN ANTONIO","SAN VICENTE DE CAÑETE","ZUÑIGA","NUEVO IMPERIAL","CALANGO","SANTA CRUZ DE FLORES","IMPERIAL"};


    String PROVLoreto[]={"Seleccione..","Loreto","Maynas"}; // PROVINCIAS DE Loreto
    //DISTRITOS
    String Loreto[]={"","Putumayo","Yaguas","Rosa Panduro"};
    String Maynas[]={"","Mazan","Putumayo","Belen","Napo","Las Amazonas"};

    String PROVPiura[]={"Seleccione..","Morropón","Lambayeque "}; // PROVINCIAS DE Piura
    //DISTRITOS
    String Morropón[]={"","Buenos Aires ","Chulucanas ","Chalaco","Salitral","Santo Domingo"};
    String Lambayeque[]={"","ILLIMO","LAMBAYEQUE","MOCHUMÍ","MOTUPE","OLMOS"};

    String PROVPuno[]={"Seleccione..","ACORA","AMANTANI"}; // PROVINCIAS DE Puno
    //DISTRITOS
    String ACORA[]={"","Ácora"};
    String AMANTANI[]={"","Amantaní "};

    String PROVTumbes[]={"Seleccione..","Tumbes","Zarumilla"}; // PROVINCIAS DE Tumbes
    //DISTRITOS
    String Tumbes[]={"","Corrales","San Jacinto","Casitas ","Zorritos","Punta Sal"," La Cruz"};
    String Zarumilla[]={"","Zarumilla","Aguas Verdes","Mapatalo"};

    String PROVUcayali[]={"Seleccione..","Atalaya","Purús"}; // PROVINCIAS DE Ucayali
    //DISTRITOS
    String Atalaya[]={"","RAYMONDI","SEPAHUA","TAHUANÍA","YURÚA"};
    String Purús[]={"","Puerto Esperanza"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas1);

        textId=(TextView) findViewById(R.id.textIdUsuario1);
        textNombre=(TextView) findViewById(R.id.textNombreUsuario);

        opcionesDepart = (Spinner) findViewById(R.id.spinnerDepart);
        opcionesProvin = (Spinner) findViewById(R.id.spinnerProvin);
        opcionesDistri = (Spinner) findViewById(R.id.spinnerDistri);

        extraerDepaa = (TextView) findViewById(R.id.extraerDepartamento);
        extraerProvv = (TextView) findViewById(R.id.extraerProvincia);
        extraerDistt = (TextView) findViewById(R.id.extraerDistrito);

        Boton_OnOff_GPS = (Switch)findViewById(R.id.onOffManualGps);
        gpsEstado = (TextView) findViewById(R.id.txtGPS);

        direccionGps = (EditText) findViewById(R.id.edtDireccionGPS);
        direccionManual = (EditText) findViewById(R.id.edtDireccionManual);

        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);

        btnNext1 =(Button) findViewById(R.id.btnSiguienteSint1);

        latitud.setVisibility(View.INVISIBLE);
        longitud.setVisibility(View.INVISIBLE);
        extraerDepaa.setVisibility(View.INVISIBLE);
        extraerProvv.setVisibility(View.INVISIBLE);
        extraerDistt.setVisibility(View.INVISIBLE);





    //RECIBIENDO LOS DATOS EXTRAIDOS DE TRIAJE, LOS DATOS ENTRECOMILLAS "id" y "nombre"
        //FUERON ESTABLECIDOS EN LA CLASE JAVA Usuario, dentro de ella contiene los set y get
        String idusuario=getIntent().getStringExtra("id");
        textId.setText(idusuario);
        textId.setVisibility(View.INVISIBLE);

        String nombreusuario=getIntent().getStringExtra("nombre");
        textNombre.setText(nombreusuario);




        //Creando el array con los campos asignados en las variables de String que se colocan despues del ..dropdown_item, ...
        depar1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,DEPARTAMENTO);

        provSeleccione=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROV0);
        distSeleccione=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,DISTRI0);

        prov1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVAmazonas);
        dist1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Bagua);
        dist2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Chachapoyas);

        prov2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVAncash);
        dist3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Huaraz);
        dist4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Canta);

        prov3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVArequipa);
        dist5=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Caylloma);
        dist6=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Caravelí);

        prov4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVCallao);
        dist7=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Callao);

        prov5=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVCusco);
        dist8=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Urubamba);
        dist9=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Cuzco);

        prov6=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLaLibertad);
        dist10=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Chepen);
        dist11=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Otuzco);

        prov7=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLambayeque);
        dist12=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,LAMBAYEQUE);
        dist13=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,MORROPE);

        prov8=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLima);
        dist14=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,LimaMetropolitana);
        dist15=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Cañete);

        prov9=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLoreto);
        dist16=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Loreto);
        dist17=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Maynas);

        prov10=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVPiura);
        dist18=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Morropón);
        dist19=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Lambayeque);

        prov11=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVPuno);
        dist20=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ACORA);
        dist21=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,AMANTANI);

        prov12=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVTumbes);
        dist22=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Tumbes);
        dist23=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Zarumilla);

        prov13=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVUcayali);
        dist24=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Atalaya);
        dist25=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Purús);


        //traendo el array a cada spinner, solo en el opcionesDepart tendra los datos de departamentos,
        //opcionesProvin y opcionesDistri tendra como argunmento seleccione nada mas, a la espera de escoger el depar.
        opcionesDepart.setAdapter(depar1);
        opcionesProvin.setAdapter(provSeleccione);
        opcionesDistri.setAdapter(distSeleccione);


        //Funcion de seleccionar el item de cada spinner o combobox
        opcionesDepart.setOnItemSelectedListener(this);
        opcionesProvin.setOnItemSelectedListener(this);


        /*extraendo el distrito del spinner*/
        opcionesDistri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,int position, long id) {
                // Reflejando o seteando el textview lo seleccionado
                extraerDistt.setText(opcionesDistri.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });











        /*INGENIERIA SOCIAL: Se activa el gps apenas abres la pantalla sintomas1 pero las cajas aparecerar ocultas
        * extraendo tus coordenadas, ya que obligatoriamente tiene que registrar las coordenadas
        * para que el maps carge sino dara error si un campo de la tabla este vacio, todos los row
        * coordenadas tienen que estar llenas*/
        locationStart();
        direccionGps.setVisibility(View.INVISIBLE);
        longitud.setVisibility(View.INVISIBLE);
        latitud.setVisibility(View.INVISIBLE);



        //condicionales para el checkbox segun su marcacion que deshabilitar o habilitar para el registro
        Boton_OnOff_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Boton_OnOff_GPS.isChecked()==true) {

                    ////////////////////////////////////////// 1 - ACTIVO la habilitacion de UBICACION DE PLAY SERVICES
                    //////////////////////////////////////////     (solo si el gps esta desactivado)
                    locationStart();

                    ////////////////////////////////////////// 2 - Habilito la visibilidad de los textview latitud y longitud
                    longitud.setVisibility(View.VISIBLE);
                    latitud.setVisibility(View.VISIBLE);


                    ////////////////////////////////////////// 3 - VISIBILIDAD al edittext de direccion por gps
                    /////////////////////////////////////////      INVISIBILIDAD al edittext de direccion Manual
                    direccionGps.setEnabled(false);
                    direccionGps.setVisibility(View.VISIBLE);
                    direccionManual.setText("");
                    direccionManual.setVisibility(View.INVISIBLE);

                    ////////////////////////////////////////// 4 - Habilito textview "gps activado"
                    gpsEstado.setText("GPS Activado");
                    Toast.makeText(sintomas1.this, "Coordenadas Obtenidas..", Toast.LENGTH_LONG).show();

                }
                if(Boton_OnOff_GPS.isChecked()==false){

                    ////////////////////////////////////////// 1 - ACTIVO Y LLAMO UNA ALERTA TOAST
                    gpsEstado.setText("Desactiva tu GPS!");


                    ////////////////////////////////////////// 2 - APLICO INVISIBILIDAD a los textview de coordenadas
                    direccionGps.setVisibility(View.INVISIBLE);
                    direccionManual.setText("");
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
            DEPARTAMENTOVACIO=extraerDepaa.getText().toString();
            PROVINCIAVACIO=extraerProvv.getText().toString();
            DISTRITOVACIO=extraerDistt.getText().toString();
            DIRECCIONMANUALVACIO=direccionManual.getText().toString();

           /*//Esto es para que salga error en los spinner, solo se uso de ejemplo
            TextView errorText = (TextView)opcionesDepart.getSelectedView();
            errorText.setError("");
            errorText.setText("Registre su Departamento");
            TextView errorText1 = (TextView)opcionesProvin.getSelectedView();
            errorText1.setError("");
            errorText1.setText("Registre su Provincia");
            TextView errorText2 = (TextView)opcionesDistri.getSelectedView();
            errorText2.setError("");
            errorText2.setText("Registre su Distrito");*/


               //Condicion si selecciono el boton de registrar mi direccion por GPS
               if (Boton_OnOff_GPS.isChecked()==true) {

                   //Condicion si esta ya con el campo escrito "Direccion Automatica" pero faltan los campos depar,prov,dis
                        if(DEPARTAMENTOVACIO.isEmpty()){
                            Toast.makeText(sintomas1.this,"Registre su Departamento",Toast.LENGTH_SHORT).show();
                        }
                        if(PROVINCIAVACIO.isEmpty()){
                            Toast.makeText(sintomas1.this,"Registre su Provincia",Toast.LENGTH_SHORT).show();
                        }
                        if(DISTRITOVACIO.isEmpty()){
                            Toast.makeText(sintomas1.this,"Registre su Distrito",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //LLamando al siguiente activity
                            Intent int1 = new Intent(sintomas1.this,sintomas2.class);

                            //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
                            int1.putExtra("editID1x2",textId.getText().toString());

                            //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
                            int1.putExtra("edit1",extraerDepaa.getText().toString());
                            int1.putExtra("edit2",extraerProvv.getText().toString());
                            int1.putExtra("edit3",extraerDistt.getText().toString());
                            int1.putExtra("edit4",direccionGps.getText().toString());
                            int1.putExtra("edit5",latitud.getText().toString());
                            int1.putExtra("edit6",longitud.getText().toString());
                            startActivity(int1);
                        }
                   }
                  //Condicion si deselecciono o nunca lo eh seleccionado el boton de registrar mi direccion por GPS
                   if(Boton_OnOff_GPS.isChecked()==false){

                        //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
                        // con los parametros manuales en particular "direccion manual"
                      if(DEPARTAMENTOVACIO.isEmpty()){
                       Toast.makeText(sintomas1.this,"Registre su Departamento",Toast.LENGTH_SHORT).show();
                      }
                      if(PROVINCIAVACIO.isEmpty()){
                       Toast.makeText(sintomas1.this,"Registre su Provincia",Toast.LENGTH_SHORT).show();
                      }
                      if(DISTRITOVACIO.isEmpty()){
                       Toast.makeText(sintomas1.this,"Registre su Distrito",Toast.LENGTH_SHORT).show();
                      }
                      if(DIRECCIONMANUALVACIO.isEmpty()){
                       Toast.makeText(sintomas1.this,"Registre su Dirección",Toast.LENGTH_SHORT).show();
                      }
                      else{
                       //LLamando al siguiente activity
                       Intent int2 = new Intent(sintomas1.this,sintomas2.class);

                       //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
                       int2.putExtra("editID1x2",textId.getText().toString());

                       //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
                       int2.putExtra("edit1",extraerDepaa.getText().toString());
                       int2.putExtra("edit2",extraerProvv.getText().toString());
                       int2.putExtra("edit3",extraerDistt.getText().toString());
                       int2.putExtra("edit4",direccionManual.getText().toString());
                       int2.putExtra("edit5",latitud.getText().toString());
                       int2.putExtra("edit6",longitud.getText().toString());
                       startActivity(int2);
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







    @Override
    public void onItemSelected(AdapterView<?> lista, View v, int p, long id) {
        //Toast.makeText(this,"Posicion:"+p,Toast.LENGTH_LONG).show();
        switch (lista.getId()){
            case R.id.spinnerDepart:
                /*se agrego la variable posicion int (int posicion;)*/
                /*esto es para que el textview extraiga los datos de los combobox o spinner segun su seleccion*/
                posicion=p;
                switch (p){

                    case 0: // Selecccione
                        /*Enviar a los textvie sin datos sin la posicion del array es cero*/
                        extraerDepaa.setText("");
                        extraerProvv.setText("");
                        extraerDistt.setText("");

                        opcionesProvin.setAdapter(provSeleccione); //en el spinner provincia listara seleccione.. nada mas
                        break;
                    case 1: // Departamento Amazonas a su Provincia

                        opcionesProvin.setAdapter(prov1);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());

                        break;
                    case 2: // Departamento Ancash a su Provincia

                        opcionesProvin.setAdapter(prov2 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());

                        break;
                    case 4: // Departamento Arequipa a su Provincia

                        opcionesProvin.setAdapter(prov3 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 7: // Departamento Callao a su Provincia

                        opcionesProvin.setAdapter(prov4 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 8: // Departamento Cusco a su Provincia

                        opcionesProvin.setAdapter(prov5 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 13: // Departamento La Libertad a su Provincia

                        opcionesProvin.setAdapter(prov6 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 14: // Departamento Lambayeque a su Provincia

                        opcionesProvin.setAdapter(prov7 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 15: // Departamento Lima a su Provincia

                        opcionesProvin.setAdapter(prov8 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 16: // Departamento Loreto a su Provincia

                        opcionesProvin.setAdapter(prov9 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 20: // Departamento Piura a su Provincia

                        opcionesProvin.setAdapter(prov10 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 21: // Departamento Puno a su Provincia

                        opcionesProvin.setAdapter(prov11 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 24: // Departamento tumbes a su Provincia

                        opcionesProvin.setAdapter(prov12 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 25: // Departamento Ucayali a su Provincia

                        opcionesProvin.setAdapter(prov13 );
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                   }
                  break;


            case R.id.spinnerProvin:
                switch (posicion){
                    case 0:
                        // Seleccione
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                extraerDistt.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                          }
                          break;

                    case 1: // Provincia Amazonas  a sus Distrito

                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist1);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());

                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist2);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());

                                break;
                        }
                        break;

                    case 2: // Provincia Ancash  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist3);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist4);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 4: // Provincia Arequipa  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist5);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist6);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 7: // Provincia Callao  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist7);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 8: // Provincia Cusco  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist8);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist9);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 13: // Provincia La Libertad a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist10);
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist11);
                                break;
                        }
                        break;


                    case 14: // Provincia Lambayeque  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist12);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist13);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 15: // Provincia Lima  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist14);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist15);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 16: // Provincia Loreto  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist16);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist17);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 20: // Provincia Piura  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist18);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist19);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 21: // Provincia Puno  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist20);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist21);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 24: // Provincia tumbes  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist22);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist23);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 25: // Provincia Ucayali  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                extraerDistt.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist24);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist25);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;
                  }
                  break;

            case R.id.spinnerDistri:
            break;
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }





}
