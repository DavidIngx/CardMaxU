package com.numa.cardmax.cardmaxu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class viewHolder extends RecyclerView.ViewHolder {

    ImageView imagen;
    VideoView video;
    TextView titulo, fecha, contenido, info, nameuser;
    Button play, options, megusta, nomegusta, comentar;



    public viewHolder(View itemView) {
        super(itemView);
        megusta = (Button)itemView.findViewById(R.id.btn_like);
        nomegusta = (Button)itemView.findViewById(R.id.btn_dislike);
        comentar = (Button)itemView.findViewById(R.id.btn_comentar);
        options = (Button)itemView.findViewById(R.id.menu_publicacion);
        imagen = (ImageView) itemView.findViewById(R.id.img_contenido);
        titulo = (TextView) itemView.findViewById(R.id.txt_titulo_user);
        fecha = (TextView) itemView.findViewById(R.id.txt_fecha_publicacion);
        contenido = (TextView) itemView.findViewById(R.id.txt_contenido_publicacion);
        info = (TextView)itemView.findViewById(R.id.txt_likes_comentarios);
        nameuser = (TextView)itemView.findViewById(R.id.txt_user);
        video=(VideoView)itemView.findViewById(R.id.video_publicacion);
        play=(Button)itemView.findViewById(R.id.btn_play);
    }
}
