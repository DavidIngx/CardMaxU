package com.numa.cardmax.cardmaxu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.List;

public class adaptador extends RecyclerView.Adapter<viewHolder> {
    private Context cxt;
    private int countbutton = 0;
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
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {

        holder.megusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (countbutton == 0){
                holder.megusta.setTextColor(Color.parseColor("#0083c2"));
                holder.megusta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_thumb_up_alt_blue_18dp,0,0,0);
                System.out.println(listaObjeto.get(position).getKey());
                   countbutton = 1;
               }else{
                   holder.megusta.setTextColor(Color.parseColor("#63686c"));
                   holder.megusta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_thumb_up_alt_black_18dp,0,0,0);
                   countbutton = 0;

               }


            }
        });



        holder.titulo.setText(listaObjeto.get(position).getTitulo_publicacion());
        holder.fecha.setText(listaObjeto.get(position).getFecha_publicacion());
        holder.contenido.setText(listaObjeto.get(position).getContenido_publicacion());
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menupop = new PopupMenu(holder.options.getContext(), holder.options);
                menupop.getMenuInflater().inflate(R.menu.menu_popup_cardview, menupop.getMenu());
                menupop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                menupop.show();
            }
        });



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


        if (listaObjeto.get(position).getOn_like() == 0){
            holder.megusta.setVisibility(View.GONE);
        }

        if (listaObjeto.get(position).getOn_dislike() == 0){
            holder.nomegusta.setVisibility(View.GONE);
        }


        if (listaObjeto.get(position).getOn_comentarios() == 0){
            holder.comentar.setVisibility(View.GONE);
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
