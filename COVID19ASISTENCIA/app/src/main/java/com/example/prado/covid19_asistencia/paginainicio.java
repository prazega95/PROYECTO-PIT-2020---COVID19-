package com.example.prado.covid19_asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class paginainicio extends AppCompatActivity


        //DECLARAR EN PRIMER LUGAR LOS FRAGMENT PARA QUE SE PUEDAN EJECUTAR
        implements NavigationView.OnNavigationItemSelectedListener,
           noticias.OnFragmentInteractionListener,
           cifras.OnFragmentInteractionListener,
           triaje.OnFragmentInteractionListener,
           chat.OnFragmentInteractionListener,
           contactos.OnFragmentInteractionListener

           {


       /*LLAMANDO AL IMAGEN PORTADA INICIO*/
       ImageView img1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        img1 = (ImageView) findViewById(R.id.imagenPortadaInicio);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        //llamando a la animacion
        Animation anim;
        anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        img1.setAnimation(anim);



    }






   /*METODO DE REGRESAR POR EL BOTON DEL CELULAR MAS NO POR EL TEXTVIEW regresar al menu*/
    @Override
    public void onBackPressed() {


        Intent int1 = new Intent(paginainicio.this,paginainicio.class);
        startActivity(int1);


       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toast.makeText(getApplicationContext(),"CIERRE SU SESION PARA SALIR", Toast.LENGTH_SHORT).show();*/

      /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paginainicio, menu);
        return true;
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // PARA PODER CERRAR SESION DESDE LA BOTON DEL COSTADO SUPERIOR DERECHA
        if (id == R.id.action_settings) {

            SharedPreferences preferences=getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
            preferences.edit().clear().commit();

            Intent intent = new Intent(getApplicationContext(),principal.class);
            startActivity(intent);
            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }








    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        Fragment fragment = null;
        boolean fragmentoSeleccionado = false;




        if (id == R.id.nav_inicio) {
            fragment = new Fragment();
            fragmentoSeleccionado=true;




        } else if (id == R.id.nav_noticias) {
            fragment = new noticias();
            fragmentoSeleccionado=true;



        } else if (id == R.id.nav_casos) {

            fragment = new cifras();
            fragmentoSeleccionado=true;



        } else if (id == R.id.nav_triaje) {

            fragment = new triaje();
            fragmentoSeleccionado = true;



        } else if (id == R.id.nav_chat) {

            fragment = new chat();
            fragmentoSeleccionado = true;




        } else if (id == R.id.nav_contactos) {

               fragment = new contactos();
               fragmentoSeleccionado = true;

           }





               //Para que lo seleccionado aparesca dentro del contenedor principal
            if(fragmentoSeleccionado){
                getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
            }





            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
    }

        //para implementar los metodos de los fragment
        @Override
        public void onFragmentInteraction(Uri uri) {

        }

}
