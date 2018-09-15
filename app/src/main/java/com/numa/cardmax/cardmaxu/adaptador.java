package com.numa.cardmax.cardmaxu;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

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
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {

        holder.titulo.setText(listaObjeto.get(position).getTitulo_publicacion());
        holder.fecha.setText(listaObjeto.get(position).getFecha_publicacion());
        holder.contenido.setText(listaObjeto.get(position).getContenido_publicacion());

        String buscar = listaObjeto.get(position).getImagen_publicacion();
        final int buscando = buscar.indexOf("video");
        if(buscando != -1) {
            holder.imagen.setBackgroundColor(0);
            holder.play.setVisibility(View.VISIBLE);
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.video.start();
                    holder.play.setVisibility(View.INVISIBLE);
                                    }
            });
            holder.video.setVisibility(View.VISIBLE);
            holder.video.setVideoURI(Uri.parse(listaObjeto.get(position).getImagen_publicacion().toString()));


            holder.video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.video.pause();
                    holder.play.setVisibility(View.VISIBLE);
                }
            });
        }else{

            Glide.with(holder.imagen.getContext())
                    .load(listaObjeto.get(position).getImagen_publicacion())
                    .into(holder.imagen);
            holder.info.setText(listaObjeto.get(position).getLike_publicacion()+" Likes - "+listaObjeto.get(position).getComentarios_publicacion()+" Comentarios");

        }







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
