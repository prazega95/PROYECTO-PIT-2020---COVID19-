package com.example.prado.covid19_asistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sintomas1 extends AppCompatActivity {


   //LOS DATOS ID Y NOMBRE QUE ESTAN SIN COMILLAS ESTAN DECLARADAS Y LLAMADAS EN TRIAJE Y LOS DATOS "id" y
   //"nombre" EN LA CLASE JAVA Usuario donde tienen los set y get
    public  static final String ID="id";
    public  static final String NOMBRE="nombre";


    EditText edt1,edt2,edt3,edt4;
    Button btnNext1;
    TextView textId,textNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas1);


        edt1 = (EditText) findViewById(R.id.edtDepartamento1);
        edt2 = (EditText) findViewById(R.id.edtProvincia2);
        edt3 = (EditText) findViewById(R.id.edtDistrito3);
        edt4 = (EditText) findViewById(R.id.edtDireccion4);

        textId=(TextView) findViewById(R.id.textIdUsuario1);
        textNombre=(TextView) findViewById(R.id.textNombreUsuario);

        btnNext1 =(Button) findViewById(R.id.btnSiguienteSint1);


        //RECIBIENDO LOS DATOS EXTRAIDOS DE TRIAJE, LOS DATOS ENTRECOMILLAS "id" y "nombre"
        //FUERON ESTABLECIDOS EN LA CLASE JAVA Usuario, dentro de ella contiene los set y get
        String idusuario=getIntent().getStringExtra("id");
        textId.setText(idusuario);
        textId.setVisibility(View.INVISIBLE);

        String nombreusuario=getIntent().getStringExtra("nombre");
        textNombre.setText(nombreusuario);




        btnNext1.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View view) {


            Intent int1 = new Intent(sintomas1.this,sintomas2.class);

            //ASIGNANDO LA VARIABLE editID1x2.. DEL TRIAJE CON EL ID PARA ENVIAR AL SEGUNDO ACTIVITY
              int1.putExtra("editID1x2",textId.getText().toString());

            //asignando en variables los edittext para llevarlas al ultimo gui y almacenar a la basededatos
             int1.putExtra("edit1",edt1.getText().toString());
             int1.putExtra("edit2",edt2.getText().toString());
             int1.putExtra("edit3",edt3.getText().toString());
             int1.putExtra("edit4",edt4.getText().toString());startActivity(int1);
          }
       });

    }

}
