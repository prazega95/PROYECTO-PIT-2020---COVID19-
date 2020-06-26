package com.example.prado.covid19_asistencia;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class pantalla_registro_exitoso extends AppCompatActivity {

    TextView btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro_exitoso);




        btnRegresar= findViewById(R.id.txtRegresarMenu);




        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent int1 = new Intent(pantalla_registro_exitoso.this,paginainicio.class);
                startActivity(int1);
            }
          });
        }


    /*METODO DE REGRESAR POR EL BOTON DEL CELULAR MAS NO POR EL TEXTVIEW regresar al menu*/
    @Override
    public void onBackPressed()
    {
        Intent int1 = new Intent(pantalla_registro_exitoso.this,paginainicio.class);
        startActivity(int1);
    }


}
