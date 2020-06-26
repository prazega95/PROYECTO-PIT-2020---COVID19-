package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cifras.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link cifras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cifras extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;


   ///////////////////////////////////////////////////////////////////////////////AGREGANDO LOS TEXTVIEW DE LAS CIFRAS
    TextView titulo,
             cantConfirmados,
             cantHospitalizados,
             cantMuertos,
             cantRecuperados ;

    Button btnCifrarTotal;
    Spinner opcionesLista;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;



    /*MARCANDO LA POSICION DEL ARRAY*/
    int posicion;




    public cifras() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cifras.
     */
    // TODO: Rename and change types and number of parameters
    public static cifras newInstance(String param1, String param2) {
        cifras fragment = new cifras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_cifras, container, false);

        titulo = (TextView) vista.findViewById(R.id.cifrasgenerales);
        cantConfirmados = (TextView) vista.findViewById(R.id.text_confirmados);
        cantHospitalizados = (TextView) vista.findViewById(R.id.text_hospitalizados);
        cantMuertos = (TextView) vista.findViewById(R.id.text_muertes);
        cantRecuperados = (TextView) vista.findViewById(R.id.text_recuperados);

        opcionesLista = (Spinner) vista.findViewById(R.id.spinnerProfesion);
        btnCifrarTotal =(Button) vista.findViewById(R.id.btnCifrasGenerales);


        request= Volley.newRequestQueue(getContext());
       /* contadorGeneral();*/


        //llamando a la animacion
        Animation anim;
        anim = AnimationUtils.loadAnimation(getContext(),R.anim.alpha);
        anim.reset();
        titulo.setAnimation(anim);



   ///////////////////////////////////////////////////////  Colocando el web service contadorGeneral al boton btnCifrarTotal
        btnCifrarTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Cifras Generales",Toast.LENGTH_SHORT).show();

                contadorGeneral();

            }
        });




        //Adaptador para poder traer la informacion del values/array que tiene como variable ese array como "ListaDepartamentos"
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.ListaDepartamentos,
                android.R.layout.simple_spinner_dropdown_item);
        //seteando el adapter al spinner
        opcionesLista.setAdapter(adapter);



        ////////////////////////////////////////////////// Colocando el web service contarXDepartamento al spinner opcionesProf
        opcionesLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long l) {

               contarXDepartamento();
               Toast.makeText(parent.getContext(),"Cifras en: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        return vista;
    }




    private void contadorGeneral() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();


        String ip=getString(R.string.ip);
        String url=ip+"/ejemploBDRemota/ws_JSONConsultaCifras.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }




    private void contarXDepartamento() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();


        String ip=getString(R.string.ip);
        String url=ip+"/ejemploBDRemota/ws_JSONConsultaCifras2.php?Departamento="
        +opcionesLista.getSelectedItem().toString();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }





    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo Consultar "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }



    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();

        //llamando la clase Contador Cifras
        ContadorCifras miContador=new ContadorCifras();


        //seleccionando los json con el nombre de declaracion que se hizo en el php
        JSONArray json=response.optJSONArray("TotalConfirmadas");
        JSONArray json1=response.optJSONArray("TotalHospitalizado");
        JSONArray json2=response.optJSONArray("TotalMuertes");
        JSONArray json3=response.optJSONArray("TotalRecuperado");


        JSONObject jsonObject=null;

        try {

            //Seleccionando el campo cantidad que contiene la declaracion bajo la variable json,json1,json2,json3
            // y seteandolo (setIndicador..) de la clase ContadorCifras

            ///////////////////////////////////////////////////////////////////////////////////// json1
            jsonObject=json.getJSONObject(0);
            miContador.setIndicadorConfirmados(jsonObject.optString("cantidad"));

            ///////////////////////////////////////////////////////////////////////////////////// json2
            jsonObject=json1.getJSONObject(0);
            miContador.setIndicadorHospitalizado(jsonObject.optString("cantidad"));

            ///////////////////////////////////////////////////////////////////////////////////// json3
            jsonObject=json2.getJSONObject(0);
            miContador.setIndicadorMuertes(jsonObject.optString("cantidad"));

            ///////////////////////////////////////////////////////////////////////////////////// json4
            jsonObject=json3.getJSONObject(0);
            miContador.setIndicadorRecuperado(jsonObject.optString("cantidad"));

            //extraendo bajo una variable conta.. el getIndicador.. de la clase ContadorCifras
            // que ya almaceno la cantidad en el json,json1.json2,json3
            String contaConfirmados1 = miContador.getIndicadorConfirmados();
            String contaHospitalizados1 = miContador.getIndicadorHospitalizado();
            String contaMuertes1 = miContador.getIndicadorMuertes();
            String contaRecuperados1 = miContador.getIndicadorRecuperado();

            //mostrandolo bajo settext la variable conta... al TEXTVIEW
            cantConfirmados.setText(contaConfirmados1);
            cantHospitalizados.setText(contaHospitalizados1);
            cantMuertos.setText(contaMuertes1);
            cantRecuperados.setText(contaRecuperados1);

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }












    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
