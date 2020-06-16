package com.example.prado.covid19_asistencia.Adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prado.covid19_asistencia.Noticia;
import com.example.prado.covid19_asistencia.R;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.NoticiasHolder>{

    List<Noticia> listaNoticia;


    public NoticiasAdapter(List<Noticia> listaNoticia) {
        this.listaNoticia = listaNoticia;
    }

    @Override
    public NoticiasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_noticias,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new NoticiasHolder(vista);
    }


    @Override
    public void onBindViewHolder(NoticiasHolder holder, int position) {
        holder.txtTitulo.setText(listaNoticia.get(position).getTitulo().toString());
        holder.txtDescripcion.setText(listaNoticia.get(position).getDescripcion().toString());

    }

    @Override
    public int getItemCount() {
        return listaNoticia.size();
    }



    public class NoticiasHolder extends RecyclerView.ViewHolder{

        TextView txtTitulo,txtDescripcion;

        public NoticiasHolder(View itemView) {
            super(itemView);
            txtTitulo= (TextView) itemView.findViewById(R.id.txtTitulo);
            txtDescripcion= (TextView) itemView.findViewById(R.id.txtDescripcion);

        }
    }


}
