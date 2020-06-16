package com.example.prado.covid19_asistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sintomas2 extends AppCompatActivity {


    EditText edt5,edt6;
    Button btnNext2;

    TextView textID, tx1,tx2,tx3,tx4,tx5,tx6;


    String NUMEROFAMILIAR,
            PROFESION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas2);


        edt5 = (EditText) findViewById(R.id.edtCantFami);
        edt6 = (EditText) findViewById(R.id.edtProfesion);
        btnNext2 =(Button) findViewById(R.id.btnSiguienteSint2);


        ////////LISTA DE TEXTVIEW ARRASTRANDO LOS DATOS DE OTROS ACTIVITYS

        textID = (TextView) findViewById(R.id.textIdUsuario2);
        //PONER INVISIBLE EL TEXT
        textID.setVisibility(View.INVISIBLE);

        tx1=(TextView) findViewById(R.id.textObtenerDEPARTAMENTO);
        //PONER INVISIBLE EL TEXT
        tx1.setVisibility(View.INVISIBLE);

        tx2=(TextView) findViewById(R.id.textObtenerPROVINCIA);
        //PONER INVISIBLE EL TEXT
        tx2.setVisibility(View.INVISIBLE);

        tx3=(TextView) findViewById(R.id.textObtenerDISTRITO);
        //PONER INVISIBLE EL TEXT
        tx3.setVisibility(View.INVISIBLE);

        tx4=(TextView) findViewById(R.id.textObtenerDIRECCION);
        //PONER INVISIBLE EL TEXT
        tx4.setVisibility(View.INVISIBLE);


        tx5=(TextView) findViewById(R.id.textObtenerLatitud);
        //PONER INVISIBLE EL TEXT
        tx5.setVisibility(View.INVISIBLE);

        tx6=(TextView) findViewById(R.id.textObtenerLongitud);
        //PONER INVISIBLE EL TEXT
        tx6.setVisibility(View.INVISIBLE);






        //RECIBIENDO PARAMETROS DEL PRIMER ACTIVYTY Y ALMACENANDO EN LA VARIABLE tx1.. PARA LUEGO CREAR OTRA VARIABLE edit1x2
        // Y PASAR A LA 3 ACTIVITY
        Bundle bundle = getIntent().getExtras();

        //RECIBIENDO PARAMETRO ID CONSULTADO DESDE TRIAJE Y ALMACENADO EN SINTOMAS1
        String dato0 = bundle.getString("editID1x2").toString();
        textID.setText(dato0);

        String dato1 = bundle.getString("edit1").toString();
        //ALMACENANDO EL VALOR CAPTURADO Y PASANDOLO AL Tx1
        tx1.setText(dato1);

        String dato2 = bundle.getString("edit2").toString();
        tx2.setText(dato2);

        String dato3 = bundle.getString("edit3").toString();
        tx3.setText(dato3);

        String dato4 = bundle.getString("edit4").toString();
        tx4.setText(dato4);

        String dato5 = bundle.getString("edit5").toString();
        tx5.setText(dato5);

        String dato6 = bundle.getString("edit6").toString();
        tx6.setText(dato6);


//////////////////////////////////////////////////////////////////////////////////////////////////////// BOTON SIGUIENTE
        btnNext2.setOnClickListener(new View.OnClickListener(){

        @Override
            public void onClick(View view) {


            //declarando variables para poder hacer la condicion de pasar al activity sgte si estos campos no estan completados
            NUMEROFAMILIAR=edt5.getText().toString();
            PROFESION=edt6.getText().toString();

              //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
              if (!NUMEROFAMILIAR.isEmpty()&&
                       !PROFESION.isEmpty()){

                //PASANDO AL OTRO ACTIVITY
                Intent int1 = new Intent(sintomas2.this,sintomas3.class);

                //ASIGNANDO POR SEGUNDA VES LAS VARIABLES editID2x3 DEL "ID" DEL SINTOMAS 2 PARA ENVIAR AL TERCER ACTIVITY
                 int1.putExtra("editID2x3",textID.getText().toString());

               //ASIGNANDO POR SEGUNDA VES LAS VARIABLES edit1x2.. DEL PRIMERO PARA ENVIAR AL TERCER ACTIVITY
                 int1.putExtra("edit1x2",tx1.getText().toString());
                 int1.putExtra("edit2x2",tx2.getText().toString());
                 int1.putExtra("edit3x2",tx3.getText().toString());
                 int1.putExtra("edit4x2",tx4.getText().toString());
                 int1.putExtra("edit5x2",tx5.getText().toString());
                 int1.putExtra("edit6x2",tx6.getText().toString());

                 //ASIGNANDO LAS VARIABLES edit5.. DEL SEGUNDO PARA ENVIAR AL TERCER ACTIVITY
                int1.putExtra("edit5",edt5.getText().toString());
                int1.putExtra("edit6",edt6.getText().toString());

                 //INICIALIZANDO EL INTENT
                startActivity(int1);
               }
                else{
                Toast.makeText(sintomas2.this,"Complete Todos los Campos",Toast.LENGTH_SHORT).show();
              }
            }
          });

   }

}





