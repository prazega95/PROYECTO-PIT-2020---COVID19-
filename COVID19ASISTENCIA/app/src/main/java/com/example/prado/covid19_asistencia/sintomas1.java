package com.example.prado.covid19_asistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sintomas1 extends AppCompatActivity implements View.OnClickListener{


    Button btn1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas1);


        btn1 =(Button) findViewById(R.id.btnSiguienteSintoma1);
        btn1.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnSiguienteSintoma1:
                Intent int1= new Intent(this, sintomas2.class);
                startActivity(int1);
                break;


        }
    }


}
