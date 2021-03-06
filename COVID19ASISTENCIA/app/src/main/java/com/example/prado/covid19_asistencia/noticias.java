package com.example.prado.covid19_asistencia;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.prado.covid19_asistencia.Adaptador.NoticiasAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link noticias.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link noticias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class noticias extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    RecyclerView recyclerNoticias;
    ArrayList<Noticia> listaNoticias;

    ProgressDialog progress;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;






    public noticias() {
        // Required empty public constructor
    }




    // TODO: Rename and change types and number of parameters
    public static noticias newInstance(String param1, String param2) {

        noticias fragment = new noticias();


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
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_noticias, container, false);


        listaNoticias=new ArrayList<>();

        recyclerNoticias= (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerNoticias.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerNoticias.setHasFixedSize(true);

        request= Volley.newRequestQueue(getContext());

        cargarWebService();

        return vista;
    }



    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);

        String url=ip+"/ejemploBDRemota/ws_JSONListarNoticias.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Noticia noticia=null;

        JSONArray json=response.optJSONArray("noticias");

        try {

            for (int i=0;i<json.length();i++){
                noticia=new Noticia();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                noticia.setIdnoticia(jsonObject.optInt("idnoticia"));
                noticia.setTitulo(jsonObject.optString("titulo_noticia"));
                noticia.setDescripcion(jsonObject.optString("contenido_noticia"));

                listaNoticias.add(noticia);
            }
            progress.hide();
            NoticiasAdapter adapter=new NoticiasAdapter(listaNoticias);
            recyclerNoticias.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
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
