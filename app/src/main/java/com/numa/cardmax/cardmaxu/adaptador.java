package com.numa.cardmax.cardmaxu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class adaptador extends RecyclerView.Adapter<viewHolder> {

    List<Fuente> listaObjeto;

    public adaptador(List<Fuente> listaObjeto) {
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
        holder.titulo.setText(listaObjeto.get(position).getTitulo());
        holder.imagen.setImageResource(listaObjeto.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaObjeto.size();
    }
}
