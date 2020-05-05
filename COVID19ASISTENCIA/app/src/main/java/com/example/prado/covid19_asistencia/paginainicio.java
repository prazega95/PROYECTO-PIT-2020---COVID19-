package com.example.prado.covid19_asistencia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class paginainicio extends AppCompatActivity


        //DECLARAR EN PRIMER LUGAR LOS FRAGMENT PARA QUE SE PUEDAN EJECUTAR
        implements NavigationView.OnNavigationItemSelectedListener,
           noticias.OnFragmentInteractionListener,
           cifras.OnFragmentInteractionListener,
           triaje.OnFragmentInteractionListener,
           datospersonales.OnFragmentInteractionListener
           {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }







    @Override
    public void onBackPressed() {

       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toast.makeText(getApplicationContext(),"CIERRE SU SESION PARA SALIR", Toast.LENGTH_SHORT).show();*/

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paginainicio, menu);
        return true;
    }






  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/








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

        } else if (id == R.id.nav_datospersonales) {

            fragment = new datospersonales();
            fragmentoSeleccionado = true;



        } else if (id == R.id.nav_noticias) {
            fragment = new noticias();
            fragmentoSeleccionado=true;



        } else if (id == R.id.nav_casos) {

            fragment = new cifras();
            fragmentoSeleccionado=true;



        } else if (id == R.id.nav_triaje) {

            fragment = new triaje();
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
