package com.example.prado.covid19_asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class CargarActividad extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_actividad);

        img1=(ImageView) findViewById(R.id.portadaimagencarga);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
                boolean sesion=preferences.getBoolean("sesion", false);
                if (sesion){
                    Intent intent = new Intent(getApplicationContext(),paginainicio.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(),principal.class);
                    startActivity(intent);
                    finish();
                }

            }
        },1000);




    }
}
