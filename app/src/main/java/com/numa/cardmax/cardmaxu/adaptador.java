package com.numa.cardmax.cardmaxu;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class adaptador extends RecyclerView.Adapter<viewHolder> {
    private Context cxt;

    List<ObjetoMuro> listaObjeto;


    public adaptador(List<ObjetoMuro> listaObjeto) {
        this.listaObjeto = listaObjeto;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_publicacion_muro, parent, false);

        return new viewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.titulo.setText(listaObjeto.get(position).getTitulo_publicacion());
        holder.fecha.setText(listaObjeto.get(position).getFecha_publicacion());
        holder.contenido.setText(listaObjeto.get(position).getContenido_publicacion());

        Glide.with(holder.imagen.getContext())
                .load(listaObjeto.get(position).getImagen_publicacion())
                .into(holder.imagen);
        holder.info.setText(listaObjeto.get(position).getLike_publicacion()+" Likes - "+listaObjeto.get(position).getComentarios_publicacion()+" Comentarios");




    }

    @Override
    public int getItemCount() {
        return listaObjeto.size();
    }



    public void setfilter(List<ObjetoMuro> newlistaObjeto){
        listaObjeto = new ArrayList<>();
        listaObjeto.addAll(newlistaObjeto);
        notifyDataSetChanged();
    }


}
