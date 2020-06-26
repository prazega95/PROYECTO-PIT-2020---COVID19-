package com.example.prado.covid19_asistencia;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prado.covid19_asistencia.Adaptador.NoticiasAdapter;
import com.example.prado.covid19_asistencia.app.AppController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

        private GoogleMap mMap;

        MapFragment mapFragment;
        MarkerOptions markerOptions = new MarkerOptions();

        CameraPosition cameraPosition;
        LatLng center, latLng;
        String title;

        /*TRAENDO LOS CAMPOS DE LA TABLA DEL PHP WEB SERVIS*/
        public static final String ID = "cod_sintomas";
        public static final String TITLE = "Resultado";
        public static final String LAT = "Latitud";
        public static final String LNG = "Longitud";




        //////////////////////////////////////////////////////////////URL DONDE SE ALOJA EL WEB SERVIS TANTO REMOTA COMO LOCAL*/

        private String url = "https://proyectocovid19.000webhostapp.com/ejemploBDRemota/ws_JSONListarMapaCoordenadas.php";
       // private String url = "http://192.168.2.2:8080/ejemploBDRemota/ws_JSONListarMapaCoordenadas.php";

        String tag_json_obj = "json_obj_req";




        
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
          SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                   .findFragmentById(R.id.idMapasMarcadores);
           mapFragment.getMapAsync(this);

    }



      @Override
      public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ///////////////////////////////////////////////////////////PARA CENTRAR UN PUNTO EN EL MAPA OPCION ALTERNATIVA*/
        //googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-11.965848, -77.0806777), 16));



        //////////////////////////////////////////////////////////////////////// CENTRAR VISTA EN EL MAPA AL INICIAR
              center = new LatLng(-11.9319224, -77.0479068);
              cameraPosition = new CameraPosition.Builder().target(center).zoom(10).build();
              googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              getMarkers();

         }

             private void addMarker(LatLng latlng, final String title) {
                 markerOptions.position(latlng);
                 markerOptions.title(title);

                 /*CAMBIANDO EL ICONO DEL MARCADOR*/
                    /*DEFAUL*/
                    // mMap.addMarker(markerOptions);

                    /*MODIFICADO*/
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador));
                    mMap.addMarker(markerOptions);



                 /////// CUANDO SE PRESIONA EL MARKER, MUESTRA UN TOAST DE TITLE ="Resultado"
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                 @Override
                 public void onInfoWindowClick(Marker marker) {
                      Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                   }
                });
              }


             /*REALIZANDO EL POST DEL WEB SERVIS*/
              private void getMarkers() {
               StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

               @Override
               public void onResponse(String response) {
               Log.e("Response: ", response.toString());

                  try {
                   JSONObject jObj = new JSONObject(response);
                   String getObject = jObj.getString("wisata");
                   JSONArray jsonArray = new JSONArray(getObject);

                   for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   title = jsonObject.getString(TITLE);
                   latLng = new LatLng(Double.parseDouble(jsonObject.getString(LAT)), Double.parseDouble(jsonObject.getString(LNG)));

                   // AÑADIENDO LOS MARKER EN EL MAPA
                   addMarker(latLng, title);
                   }
                   } catch (JSONException e) {
                       // JSON error
                       e.printStackTrace();
                   }
                   }
                   }, new Response.ErrorListener() {


                   @Override
                   public void onErrorResponse(VolleyError error) {
                    Log.e("Error: ", error.getMessage());
                    Toast.makeText(MapsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                   });

                    AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);



                // Add a marker in Sydney and move the camera (MARCADOR POR DEFECTO DE API)
                /*   LatLng sydney = new LatLng(-34, 151);
                     mMap.addMarker(new MarkerOptions()
                         .position(sydney)
                         .title("Marker in Sydney"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

         }


}