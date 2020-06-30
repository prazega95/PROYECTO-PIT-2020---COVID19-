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
import java.util.regex.Pattern;

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
    TextView extraeDepartamentoGPS;
    TextView extraeProvinciaGPS;
    TextView extraeDistritoGPS;

    TextView UnionDeGet;
    String obtener;


    Switch Boton_OnOff_GPS;
    Button btnNext1;

    String DEPARTAMENTOVACIO,
            PROVINCIAVACIO,
            DISTRITOVACIO,
            DIRECCIONMANUALVACIO;

    /*SPINNER DEPARTAMENTO, PROVINCIA, DISTRITO*/
    ArrayAdapter<String> depar1,provSeleccione,distSeleccione,
                         prov1, dist1, dist2, //"Amazonas"
                         prov2, dist3, dist4, //"Ancash"
                         prov3, dist5, dist6, //"Apurimac"
                         prov4, dist7, dist8, //"Arequipa"
                         prov5, dist9, dist10, //"Ayacucho"
                         prov6, dist11, dist12, //"Cajamarca"
                         prov7, dist13, //"callao"
                         prov8, dist14, dist15, //"Cusco"
                         prov9, dist16, dist17, //"Huancavelica"
                         prov10, dist18, dist19, //"Huanuco"
                         prov11, dist20, dist21, //"Ica"
                         prov12, dist22, dist23, //"Junin"
                         prov13, dist24, dist25, //"La Libertad"
                         prov14, dist26, dist27, //"Lambayeque"
                         prov15, dist28, dist29, //"Lima"
                         prov16, dist30, dist31, //"Loreto"
                         prov17, dist32, dist33, //"Madre De Dios"
                         prov18, dist34, dist35, //"Moquegua"
                         prov19, dist36, dist37, //"Pasco"
                         prov20, dist38, dist39, //"Piura"
                         prov21, dist40, dist41, //"Puno"
                         prov22, dist42, dist43, //"San Martin"
                         prov23, dist44, dist45, //"Tacna"
                         prov24, dist46, dist47, //"Tumbes"
                         prov25, dist48, dist49; //"Ucayali"

    /*MARCANDO LA POSICION DEL ARRAY*/
    int posicion;

    /*String agregando los campos de los array*/
    String DEPARTAMENTO[]={"Seleccione..","Amazonas","Ancash","Apurimac","Arequipa","Ayacucho","Cajamarca","Callao","Cusco","Huancavelica","Huanuco",
                           "Ica","Junin","La Libertad","Lambayeque","Lima","Loreto","Madre De Dios","Moquegua","Pasco","Piura","Puno","San Martin",
                           "Tacna","Tumbes","Ucayali"}; // DEPARTAMENTOS

/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROV0[]={"Seleccione.."}; //PROVINCIA EN BLANCO (SIN ESCOGER)
    String DISTRI0[]={""}; //DISTRITO EN BLANCO (SIN ESCOGER)
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVAmazonas[]={"Seleccione..","Bagua","Chachapoyas"}; //PROVINCIAS DE Amazonas
    //DISTRITOS
    String Bagua[]={"","ARAMANGO","BAGUA","COPALLÍN","EL PARCO","IMAZA","LA PECA"};
    String Chachapoyas[]={"","Chachapoyas","Condorcanqui","Luya","Utcubamba","Rodriguez de Mendoza","Bagua"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVAncash[]={"Seleccione..","Huaraz","Carhuaz"}; //PROVINCIAS DE Ancash
    //DISTRITOS
    String Huaraz[]={"","Cochabamba","Colcabamba","Huanchay","Olleros","Pariacoto"};
    String Canta[]={"","Arahuay","Huamantanga"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVApurimac[]={"Seleccione..","Abancay","Andahuaylas"}; //PROVINCIAS DE Apurimac
    //DISTRITOS
    String Abancay[]={"","Abancay","Chacoche","Curahuasi","Huanipaca","Lambrama"};
    String Andahuaylas[]={"","Andarapa","Chiara"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVArequipa[]={"Seleccione..","Caylloma","Caravelí"}; //PROVINCIAS DE Arequipa
    //DISTRITOS
    String Caylloma[]={"","Chivay","Achoma","Cabanaconde","Coporaque","Huambo"};
    String Caravelí[]={"","Yauca","Acarí","Lomas","Jaquí","Atiquipa","Cháparra"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVAyacucho[]={"Seleccione..","Parinacochas","Huamanga"}; //PROVINCIAS DE Ayacucho
    //DISTRITOS
    String Parinacochas[]={"","Coracora","Chumpi","Coronel Castañeda","Pacapausa","Pullo"};
    String Huamanga[]={"","Acocro","Acos Vinchos","Carmen Alto","Chiara","Jesús Nazareno","Ocros"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVCajamarca[]={"Seleccione..","Cajabamba","Celendin"}; //PROVINCIAS DE Cajamarca
    //DISTRITOS
    String Cajabamba[]={"","Cajabamba","Cachachi","Condebamba","Sitacocha"};
    String Celendin[]={"","Chumuch","Cortegana","Huasmin","Jorge Chávez","José Gálvez"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVCallao[]={"Seleccione..","Callao"}; //PROVINCIAS DE Callao
    //DISTRITOS
    String Callao[]={"","Bellavista","La Perla","La Punta","Ventanilla","Carmen de La Legua","Callao"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVCusco[]={"Seleccione..","Urubamba","Cuzco"}; //PROVINCIAS DE Cusco
    //DISTRITOS
    String Urubamba[]={"","Chinchero","Huayllabamba","Machupicchu"};
    String Cuzco[]={"","CCORCA","CUSCO","POROY","SANTIAGO","SAN JERÓNIMO"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVHuancavelica[]={"Seleccione..","Acobamba","Churcampa"}; //PROVINCIAS DE "Huancavelica"
    //DISTRITOS
    String Acobamba[]={"","Acobamba","Andabamba","Anta","Marcas","Paucará"};
    String Churcampa[]={"","Chinchihuasi","Pachamarca","Churcampa","Paucarbambilla","Locroja"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVHuanuco[]={"Seleccione..","Huacaybamba","Lauricocha"}; //PROVINCIAS DE Huanuco
    //DISTRITOS
    String Huacaybamba[]={"","Huacaybamba","Canchabamba","Cochabamba","Pinra"};
    String Lauricocha[]={"","Jesús","Queropalca","Rondos","San Francisco de Asís","San Miguel de Cauri"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVIca[]={"Seleccione..","Pisco","Nazca"}; //PROVINCIAS DE Ica
    //DISTRITOS
    String Pisco[]={"","Huancano","Independencia","Paracas","San Andrés","San Clemente"};
    String Nazca[]={"","Changuillo","El Ingenio","Marcona","Vista Alegre","Nazca"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVJunin[]={"Seleccione..","Satipo","Chanchamayo"}; //PROVINCIAS DE Junin
    //DISTRITOS
    String Satipo[]={"","Coviriali","Llaylla","Mazamari","Pampa Hermosa","Pangoa"};
    String Chanchamayo[]={"","Chanchamayo","San Luis de Shuaro","Perené","Pichanaqui","San Ramón","Vítoc"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVLaLibertad[]={"Seleccione..","Chepén","Otuzco"}; // PROVINCIAS DE LaLibertad
    //DISTRITOS
    String Chepen[]={"","Chepén"};
    String Otuzco[]={"","Sinsicap","Huaranchal","Charat","Usquil","Otuzco"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVLambayeque[]={"Seleccione..","LAMBAYEQUE","MÓRROPE"}; // PROVINCIAS DE Lambayeque
    //DISTRITOS
    String LAMBAYEQUE[]={"","ILLIMO","CHÓCHOPE","JAYANCA","MÓRROPE","MOTUPE"};
    String MORROPE[]={"","Mórrope"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVLima[]={"Seleccione..","Lima Metropolitana","Cañete"}; // PROVINCIAS DE Lima
    //DISTRITOS
    String LimaMetropolitana[]={"","ANCÓN","ATE","BARRANCO","BREÑA","CARABAYLLO","CHACLACAYO","CHORRILLOS","CIENEGUILLA","CIENEGUILLA","COMAS","EL AGUSTINO","INDEPENDENCIA","JESÚS MARÍA","LA MOLINA",
                               "LA VICTORIA","LIMA","LINCE","LOS OLIVOS","LURIGANCHO-CHOSICA","LURÍN","MAGDALENA DEL MAR","MIRAFLORES","PACHACÁMAC","PUCUSANA","PUEBLO LIBRE","PUENTE PIEDRA","PUNTA HERMOSA",
                               "PUNTA NEGRA","RÍMAC","SAN BARTOLO","SAN BORJA","SAN ISIDRO","SAN JUAN DE LURIGANCHO","SAN JUAN DE MIRAFLORES","SAN LUIS","SAN MARTIN DE PORRES","SAN MIGUEL","SANTA ANITA","SANTA MARÍA DEL MAR",
                               "SANTA ROSA","SANTIAGO DE SURCO","SURQUILLO","VILLA EL SALVADOR","VILLA MARIA DEL TRIUNFO"};
    String Cañete[]={"","CERRO AZUL","ASIA","CHILCA","LUNAHUANÁ","MALA","SAN ANTONIO","SAN VICENTE DE CAÑETE","ZUÑIGA","NUEVO IMPERIAL","CALANGO","SANTA CRUZ DE FLORES","IMPERIAL"};

/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVLoreto[]={"Seleccione..","Loreto","Maynas"}; // PROVINCIAS DE Loreto
    //DISTRITOS
    String Loreto[]={"","Putumayo","Yaguas","Rosa Panduro"};
    String Maynas[]={"","Mazan","Putumayo","Belen","Napo","Las Amazonas"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVMadreDeDios[]={"Seleccione..","Tambopata","Manu"}; // PROVINCIAS DE Madre De Dios
    //DISTRITOS
    String Tambopata[]={"","Tambopata","Inambari","Las Piedras","Laberinto"};
    String Manu[]={"","Manu","Fitzcarrald","Madre de Dios","Huepetuhe"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVMoquegua[]={"Seleccione..","Ilo","Mariscal Nieto"}; // PROVINCIAS DE Moquegua
    //DISTRITOS
    String Ilo[]={"","El Algarrobal","Ilo","Pacocha"};
    String MariscalNieto[]={"","Carumas","Cuchumbaya","Moquegua","Torata","Samegua","San Cristobal de Calacoa"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVPasco[]={"Seleccione..","Pasco","Oxapampa"}; // PROVINCIAS DE Pasco
    //DISTRITOS
    String Pasco[]={"","Chaupimarca","Huachón","Huariaca","Huayllay","Huariaca","Ninacaca","Pallanchacra","Paucartambo"};
    String Oxapampa[]={"","Chontabamba","Constitución","Huancabamba","Palcazu","Pozuzo","Puerto Bermúdez","Villa Rica"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVPiura[]={"Seleccione..","Morropón","Lambayeque "}; // PROVINCIAS DE Piura
    //DISTRITOS
    String Morropón[]={"","Buenos Aires ","Chulucanas ","Chalaco","Salitral","Santo Domingo"};
    String Lambayeque[]={"","ILLIMO","LAMBAYEQUE","MOCHUMÍ","MOTUPE","OLMOS"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVPuno[]={"Seleccione..","ACORA","AMANTANI"}; // PROVINCIAS DE Puno
    //DISTRITOS
    String ACORA[]={"","Ácora"};
    String AMANTANI[]={"","Amantaní "};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVSanMartin[]={"Seleccione..","Huallaga","Lamas"}; // PROVINCIAS DE San Martin
    //DISTRITOS
    String Huallaga[]={"","Saposoa","Alto Saposoa","El Eslabón ","Piscoyacu","Sacanche","Tingo de Saposoa"};
    String Lamas[]={"","BARRANQUITA","CUÑUMBUQUI","LAMAS","SAN ROQUE DE CUMBAZA","ALONSO DE ALVARADO"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVTacna[]={"Seleccione..","Locumba","Candarave"}; // PROVINCIAS DE Tacna
    //DISTRITOS
    String Locumba[]={"","Locumba","Pampa Sitana","Alto Camiara"};
    String Candarave[]={"","Curibaya","Camilaca","Huanuara","Quilahuani","Cairani"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVTumbes[]={"Seleccione..","Tumbes","Zarumilla"}; // PROVINCIAS DE Tumbes
    //DISTRITOS
    String Tumbes[]={"","Corrales","San Jacinto","Tumbes","Casitas","Zorritos","Punta Sal","La Cruz"};
    String Zarumilla[]={"","Zarumilla","Aguas Verdes","Mapatalo"};
/*-------------------------------------------------------------------------------------------------------------------------------*/
    String PROVUcayali[]={"Seleccione..","Atalaya","Purús"}; // PROVINCIAS DE Ucayali
    //DISTRITOS
    String Atalaya[]={"","RAYMONDI","SEPAHUA","TAHUANÍA","YURÚA"};
    String Purús[]={"","Puerto Esperanza"};
/*-------------------------------------------------------------------------------------------------------------------------------*/


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


        extraeDepartamentoGPS = (TextView) findViewById(R.id.txtGPSdepartamento);
        extraeProvinciaGPS = (TextView) findViewById(R.id.txtGPSprovincia);
        extraeDistritoGPS = (TextView) findViewById(R.id.txtGPSdistrito);


        Boton_OnOff_GPS = (Switch)findViewById(R.id.onOffManualGps);
        gpsEstado = (TextView) findViewById(R.id.txtGPS);

        direccionGps = (EditText) findViewById(R.id.edtDireccionGPS);
        direccionManual = (EditText) findViewById(R.id.edtDireccionManual);

        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);

        btnNext1 =(Button) findViewById(R.id.btnSiguienteSint1);




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

        prov3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVApurimac);
         dist5=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Abancay);
         dist6=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Andahuaylas);

        prov4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVArequipa);
         dist7=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Caylloma);
         dist8=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Caravelí);

        prov5=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVAyacucho);
         dist9=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Parinacochas);
         dist10=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Huamanga);

        prov6=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVCajamarca);
         dist11=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Cajabamba);
         dist12=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Celendin);


        prov7=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVCallao);
         dist13=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Callao);


        prov8=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVCusco);
         dist14=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Urubamba);
         dist15=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Cuzco);

        prov9=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVHuancavelica);
         dist16=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Acobamba);
         dist17=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Churcampa);

        prov10=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVHuanuco);
         dist18=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Huacaybamba);
         dist19=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Lauricocha);

        prov11=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVIca);
         dist20=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Pisco);
         dist21=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Nazca);

        prov12=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVJunin);
         dist22=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Satipo);
         dist23=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Chanchamayo);

        prov13=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLaLibertad);
         dist24=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Chepen);
         dist25=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Otuzco);

        prov14=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLambayeque);
         dist26=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,LAMBAYEQUE);
         dist27=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,MORROPE);

        prov15=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLima);
         dist28=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,LimaMetropolitana);
         dist29=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Cañete);

        prov16=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVLoreto);
         dist30=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Loreto);
         dist31=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Maynas);

        prov17=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVMadreDeDios);
         dist32=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Tambopata);
         dist33=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Manu);

        prov18=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVMoquegua);
         dist34=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Ilo);
         dist35=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,MariscalNieto);

        prov19=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVPasco);
         dist36=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Pasco);
         dist37=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Oxapampa);

        prov20=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVPiura);
         dist38=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Morropón);
         dist39=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Lambayeque);

        prov21=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVPuno);
         dist40=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ACORA);
         dist41=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,AMANTANI);

        prov22=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVSanMartin);
         dist42=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Huallaga);
         dist43=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Lamas);

        prov23=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVTacna);
         dist44=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Locumba);
         dist45=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Candarave);

        prov24=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVTumbes);
         dist46=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Tumbes);
         dist47=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Zarumilla);

        prov25=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PROVUcayali);
         dist48=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Atalaya);
         dist49=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Purús);


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

        extraerDepaa.setVisibility(View.INVISIBLE);
        extraerProvv.setVisibility(View.INVISIBLE);
        extraerDistt.setVisibility(View.INVISIBLE);

        extraeDepartamentoGPS.setVisibility(View.INVISIBLE);
        extraeProvinciaGPS.setVisibility(View.INVISIBLE);
        extraeDistritoGPS.setVisibility(View.INVISIBLE);
        direccionGps.setVisibility(View.INVISIBLE);


        //condicionales para el checkbox segun su marcacion que deshabilitar o habilitar para el registro
        Boton_OnOff_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Boton_OnOff_GPS.isChecked()==true) {

                    ////////////////////////////////////////// 1 - ACTIVO habilitacion de UBICACION DE PLAY SERVICES (solo si el gps esta desactivado)
                    locationStart();

                    ////////////////////////////////////////// 2 - Habilito la visibilidad de los textview latitud y longitud
                    /////////////////////////////////////////      y el edittext direccion gps automatico
                    direccionGps.setVisibility(View.VISIBLE);
                    longitud.setVisibility(View.VISIBLE);
                    latitud.setVisibility(View.VISIBLE);

                    ////////////////////////////////////////// 3 - Se INHABILITA los combobox de depart,prov y dist.
                    direccionGps.setEnabled(false);
                    opcionesDepart.setEnabled(false);
                    opcionesProvin.setEnabled(false);
                    opcionesDistri.setEnabled(false);

                    ////////////////////////////////////////// 4 - Limpia la caja de edittext manual y lo pone en invisible
                    direccionManual.setText("");
                    direccionManual.setVisibility(View.INVISIBLE);

                    ////////////////////////////////////////// 4 - Habilito textview "gps activado"
                    gpsEstado.setText("GPS Activado");
                    Toast.makeText(sintomas1.this, "Obteniendo Coordenadas..", Toast.LENGTH_LONG).show();
                }

              if(Boton_OnOff_GPS.isChecked()==false){

                    ////////////////////////////////////////// 1 - Activo un mensaje en textview gpsEstado que esta al lado del switch ON/off
                    gpsEstado.setText("Desactiva tu GPS!");

                    ////////////////////////////////////////// 2 - Habilito los combovox
                    opcionesDepart.setEnabled(true);
                    opcionesProvin.setEnabled(true);
                    opcionesDistri.setEnabled(true);

                    ////////////////////////////////////////// 3 - APLICO VISIBILIDAD al edittext direccion manual, la caja vacia por el settext(")
                    direccionManual.setText("");
                    direccionManual.setEnabled(true);
                    direccionManual.setVisibility(View.VISIBLE);

                    ////////////////////////////////////////// 4 - APLICO INVISIBILIDAD a los textview de latitud y longitud y edittext DireccionGPS
                    direccionGps.setVisibility(View.INVISIBLE);
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


            /*Expresion regular para direccion manual*/
            final String compruebaEditTextDireccion = direccionManual.getEditableText().toString().trim();
            final String regex= "^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+([a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.,# ]+)$";
            /*INTERPRETACION DE REGEX = "^[Esta primera[] no acepta ningun caracter extraño, solo letras minus, mayus, Ññ, tildes y solo 1 espacio no seguidos]+
                                          ([Este segundo[] si acepta # y letras mayus, min, ñÑ, tildes, y un espacio en blanco entre texto]+)$";
                                          RECORDAR: que los signos + es para poder agregar mas [] y/o ([]) y/o caso contrario sea ilimitado los caracteres de ese []+*/



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

                        //LLamando al siguiente activity
                        Intent int1 = new Intent(sintomas1.this,sintomas2.class);

                        //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
                        int1.putExtra("editID1x2",textId.getText().toString());

                        //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
                        int1.putExtra("edit1",extraeDepartamentoGPS.getText().toString());
                        int1.putExtra("edit2",extraeProvinciaGPS.getText().toString());
                        int1.putExtra("edit3",extraeDistritoGPS.getText().toString());
                        int1.putExtra("edit4",direccionGps.getText().toString());
                        int1.putExtra("edit5",latitud.getText().toString());
                        int1.putExtra("edit6",longitud.getText().toString());
                        startActivity(int1);
                     }

               //Condicion si deselecciono o nunca lo eh seleccionado el boton de registrar mi direccion por GPS
               if(Boton_OnOff_GPS.isChecked()==false){

                     //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
                     // con los parametros manuales en particular "direccion manual" (Recuerda poner el ELSE IF sino solo validara por uno)
                     if(DEPARTAMENTOVACIO.isEmpty()){
                         Toast.makeText(sintomas1.this,"¡Registre su Departamento!",Toast.LENGTH_SHORT).show();
                     }
                     else if(PROVINCIAVACIO.isEmpty()){
                         Toast.makeText(sintomas1.this,"¡Registre su Provincia!",Toast.LENGTH_SHORT).show();
                     }
                     else if(DISTRITOVACIO.isEmpty()){
                         Toast.makeText(sintomas1.this,"¡Registre su Distrito!",Toast.LENGTH_SHORT).show();
                     }
                     else if(DIRECCIONMANUALVACIO.isEmpty()){
                         Toast.makeText(sintomas1.this,"¡Registre su Dirección!",Toast.LENGTH_SHORT).show();

                     }else if (direccionManual.getText().toString().trim().length() == 0){
                         Toast.makeText(sintomas1.this,"¡No inserte solo espacios en blanco!",Toast.LENGTH_SHORT).show();

                     }else if (!compruebaEditTextDireccion.matches(regex)){
                         Toast.makeText(sintomas1.this, "¡Formato no válido, verifique!", Toast.LENGTH_LONG).show();

                     }else if (Pattern.compile(" {2,}").matcher(direccionManual.getText().toString()).find()){
                         Toast.makeText(sintomas1.this,"¡Prohibido más de 2 espacios seguidos en blanco!",Toast.LENGTH_SHORT).show();
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
                       int2.putExtra("edit4",direccionManual.getText().toString().trim());
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

                    direccionGps.setText(DirCalle.getAddressLine(0)+" , "+ DirCalle.getSubLocality()); //getAddressLine() ESTO EXTRAIRA LA DIRECCION y getSubLocality obtiene la Urb. (+","+=concatenacion)
                    extraeDepartamentoGPS.setText(DirCalle.getAdminArea()); // getAdminArea() ESTO EXTRAIRA EL DEPARTAMENTO
                    extraeProvinciaGPS.setText(DirCalle.getSubAdminArea()); // getSubAdminArea() ESTO EXTRAIRA LA PROVINCIA
                    extraeDistritoGPS.setText(DirCalle.getLocality()); // getLocality() ESTO EXTRAIRA EL DISTRITO
                    //PARA VER MAS get Y VER QUE EXTRAER del gps esta en LA SIGUIENTE FUENTE:https://stackoverflow.com/questions/22096011/what-does-each-androids-location-address-method-return

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
                    case 1: // Departamento "Amazonas" a su Provincia

                        opcionesProvin.setAdapter(prov1);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 2: // Departamento "Ancash" a su Provincia

                        opcionesProvin.setAdapter(prov2);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 3: // Departamento "Apurimac" a su Provincia

                        opcionesProvin.setAdapter(prov3);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 4: // Departamento "Arequipa" a su Provincia

                        opcionesProvin.setAdapter(prov4);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 5: // Departamento "Ayacucho" a su Provincia

                        opcionesProvin.setAdapter(prov5);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 6: // Departamento "Cajamarca" a su Provincia

                        opcionesProvin.setAdapter(prov6);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 7: // Departamento La "callao" a su Provincia

                        opcionesProvin.setAdapter(prov7);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;
                    case 8: // Departamento "Cusco" a su Provincia

                        opcionesProvin.setAdapter(prov8);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 9: // Departamento "Huancavelica" a su Provincia

                        opcionesProvin.setAdapter(prov9);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 10: // Departamento "Huanuco" a su Provincia

                        opcionesProvin.setAdapter(prov10);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 11: // Departamento "Ica" a su Provincia

                        opcionesProvin.setAdapter(prov11);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 12: // Departamento "Junin" a su Provincia

                        opcionesProvin.setAdapter(prov12);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 13: // Departamento "La Libertad" a su Provincia

                        opcionesProvin.setAdapter(prov13);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 14: // Departamento "Lambayeque" a su Provincia

                        opcionesProvin.setAdapter(prov14);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 15: // Departamento "Lima" a su Provincia

                        opcionesProvin.setAdapter(prov15);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 16: // Departamento "Loreto" a su Provincia

                        opcionesProvin.setAdapter(prov16);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 17: // Departamento "Madre De Dio a su Provincia

                        opcionesProvin.setAdapter(prov17);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 18: // Departamento "Moquegua" a su Provincia

                        opcionesProvin.setAdapter(prov18);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 19: // Departamento "Pasco" a su Provincia

                        opcionesProvin.setAdapter(prov19);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 20: // Departamento "Piura" a su Provincia

                        opcionesProvin.setAdapter(prov20);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 21: // Departamento "Puno" a su Provincia

                        opcionesProvin.setAdapter(prov21);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 22: // Departamento "San Martin" a su Provincia

                        opcionesProvin.setAdapter(prov22);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 23: // Departamento "Tacna" a su Provincia

                        opcionesProvin.setAdapter(prov23);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 24: // Departamento "Tumbes" a su Provincia

                        opcionesProvin.setAdapter(prov24);
                        extraerDepaa.setText(opcionesDepart.getSelectedItem().toString());
                        break;

                    case 25: // Departamento "Ucayali" a su Provincia

                        opcionesProvin.setAdapter(prov25);
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

                    case 1: // Provincia "Amazonas"  a sus Distrito

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

                    case 2: // Provincia "Ancash"  a sus Distrito
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

                    case 3: // Provincia "Apurimac"  a sus Distrito
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

                    case 4: // Provincia "Arequipa"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist7);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist8);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 5: // Provincia "Ayacucho"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist9);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist10);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 6: // Provincia "Cajamarca" a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist11);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist12);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 7: // Provincia "callao"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist13);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 8: // Provincia "Cusco"  a sus Distrito
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

                    case 9: // Provincia "Huancavelica"  a sus Distrito
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


                    case 10: // Provincia "Huanuco"  a sus Distrito
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

                    case 11: // Provincia "Ica"  a sus Distrito
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

                    case 12: // Provincia "Junin"  a sus Distrito
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



                    case 13: // Provincia "La Libertad"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
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

                    case 14: // Provincia "Lambayeque"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist26);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist27);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 15: // Provincia La "Lima" a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist28);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist29);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 16: // Provincia "Loreto"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist30);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist31);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 17: // Provincia "Madre De Dio  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist32);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist33);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 18: // Provincia "Moquegua"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist34);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist35);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 19: // Provincia "Pasco"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist36);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist37);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 20: // Provincia "Piura"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist38);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist39);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 21: // Provincia "Puno"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist40);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist41);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 22: // Provincia "San Martin"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist42);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist43);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 23: // Provincia "Tacna"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist44);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist45);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;

                    case 24: // Provincia "Tumbes"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist46);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist47);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                        }
                        break;


                    case 25: // Provincia "Ucayali"  a sus Distrito
                        switch (p){
                            case 0:
                                extraerProvv.setText("");
                                extraerDistt.setText("");
                                opcionesDistri.setAdapter(distSeleccione); //en el spinner distrito listara seleccione.. nada mas
                                break;
                            case 1:
                                opcionesDistri.setAdapter(dist48);
                                extraerProvv.setText(opcionesProvin.getSelectedItem().toString());
                                break;
                            case 2:
                                opcionesDistri.setAdapter(dist49);
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
