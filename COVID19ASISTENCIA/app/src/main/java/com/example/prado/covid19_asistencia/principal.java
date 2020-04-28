package com.example.prado.covid19_asistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class principal extends AppCompatActivity implements View.OnClickListener{

    Button btt_iniciar;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        img1=(ImageView) findViewById(R.id.imgCovid19);
        btt_iniciar=(Button) findViewById(R.id.btnIniciar);
        btt_iniciar.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnIniciar:
                Intent int1= new Intent(this, paginainicio.class);
                startActivity(int1);
                break;



        }
    }



}
