package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
 * {@link triaje.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link triaje#newInstance} factory method to
 * create an instance of this fragment.
 */
public class triaje extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;


    /////////////declararas variales de los botones e imagen
    EditText campoDocumento;
    ImageView img1;
    Button btn1;

    //STRING PARA LA CONDICION SI EDITTEXT ESTA VACIA y quieres continuar
    String DOCUMENTO;

    //  TextView textid,textnombre;
    TextView textid,textnombre;

    //BARRA DE PROGRESO
    ProgressDialog progreso;

    //METODOS JSON
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;




    public triaje() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment triaje.
     */

    // TODO: Rename and change types and number of parameters
    public static triaje newInstance(String param1, String param2) {
        triaje fragment = new triaje();
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
        // aqui es para llamar al activy main
        View vista = inflater.inflate(R.layout.fragment_triaje,container,false);

        textid=(TextView) vista.findViewById(R.id.textid);
        textid.setVisibility(View.INVISIBLE);
        textnombre=(TextView) vista.findViewById(R.id.textnombre);
        textnombre.setVisibility(View.INVISIBLE);

        img1=(ImageView) vista.findViewById(R.id.imageTriajee);
        campoDocumento= (EditText) vista.findViewById(R.id.campoDocumento);
        btn1 =(Button) vista.findViewById(R.id.btnIniciarRegistro);

        request= Volley.newRequestQueue(getContext());



        //llamando a la animacion
        Animation anim;
        anim = AnimationUtils.loadAnimation(getContext(),R.anim.alpha);
        anim.reset();
        img1.setAnimation(anim);




///////////////////////////////////////////////////////////////////////////// BOTON BUSCAR DNI, WEB SERVIS Y PASAR AL SGTE ACTIVITY
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //declarando variables para poder hacer la condicion de pasar al activity sgte si este campo no esta completado
               DOCUMENTO=campoDocumento.getText().toString();

                //Condicion para poder pasar al activity sgte si estos campos no estan completados o llenados
                if (!DOCUMENTO.isEmpty()){

/////////////////////si el text tiene datos escritos, procedera a cargar el webservis
                    cargarWebService();

                }else{
                    //caso contrario mostrara un toast de alerta
                    Toast.makeText(getContext(),"Completa el campo de Documento",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return vista;
    }



    private void cargarWebService() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+"/ejemploBDRemota/wsJSONConsultarUsuario.php?doc_usuario="
                +campoDocumento.getText().toString();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        //VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
        //Toast.makeText(getContext(),"ERROR VOLLEY : "+error.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),"¡Documento NO encontrado ó Registrado!",Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();

        //Toast.makeText(getContext(),"Mensaje:"+response,Toast.LENGTH_SHORT).show();

         //LLAMANDO A LA CLASE USUARIO CREADO
        Usuario miUsuario=new Usuario();

        //LLAMANDO AL JSON DECLARADO EN EL PHP CON LA VARIABLE "usuario"
        JSONArray json=response.optJSONArray("usuario");
        JSONObject jsonObject=null;

        try {
            //EXTRAENDO EL JSON DECLARADO EN EL PHP Y TRAENDO LOS RESULTADOS DE LOS CAMPOS DE LA TABLA A VISUALIZAR
            jsonObject=json.getJSONObject(0);
            miUsuario.setId(jsonObject.optString("cod_usuario"));
            miUsuario.setNombre(jsonObject.optString("nom_usuario"));


            // SI ES CORRECTO ENVÍA LOS DATOS DEL DNI CONSULTADO AL ACTIVITYSINTOMAS 1 CON LA VARIABLE ID
            // Y NOMBRE EN MODOD PUBLIC STATIC FINAL PREVIAMENTE DECLARADO EN SINTOMAS1
            Intent int1 = new Intent(getActivity(), sintomas1.class);
            int1.putExtra(sintomas1.ID,miUsuario.getId());
            int1.putExtra(sintomas1.NOMBRE,miUsuario.getNombre());
            getActivity().startActivity(int1);


        } catch (JSONException e) {
            e.printStackTrace();

        }


        textid.setText("ID :"+miUsuario.getId());
        textnombre.setText("Profesion :"+miUsuario.getNombre());



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
