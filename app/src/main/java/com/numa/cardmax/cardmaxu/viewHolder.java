package com.numa.cardmax.cardmaxu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class viewHolder extends RecyclerView.ViewHolder {

    ImageView imagen;
    TextView titulo;


    public viewHolder(View itemView) {
        super(itemView);
        imagen = (ImageView) itemView.findViewById(R.id.img_contenido);
        titulo = (TextView) itemView.findViewById(R.id.txt_titulo_user);
    }
}
