package com.numa.cardmax.cardmaxu;

import java.util.HashMap;
import java.util.Map;

public class ObjetoMuro {


    public String titulo_publicacion;
    public String contenido_publicacion;
    public String fecha_publicacion;
    public String imagen_publicacion;
    public String video_publicacion;
    public int comentarios_publicacion;
    public int like_publicacion;
    public int dislike_publicacion;


    public ObjetoMuro() {
    }


    public ObjetoMuro(String titulo_publicacion, String contenido_publicacion, String fecha_publicacion, String imagen_publicacion, String video_publicacion, int comentarios_publicacion, int like_publicacion, int dislike_publicacion) {
        this.titulo_publicacion = titulo_publicacion;
        this.contenido_publicacion = contenido_publicacion;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen_publicacion = imagen_publicacion;
        this.video_publicacion = video_publicacion;
        this.comentarios_publicacion = comentarios_publicacion;
        this.like_publicacion = like_publicacion;
        this.dislike_publicacion = dislike_publicacion;
    }

    public String getTitulo_publicacion() {
        return titulo_publicacion;
    }

    public void setTitulo_publicacion(String titulo_publicacion) {
        this.titulo_publicacion = titulo_publicacion;
    }

    public String getContenido_publicacion() {
        return contenido_publicacion;
    }

    public void setContenido_publicacion(String contenido_publicacion) {
        this.contenido_publicacion = contenido_publicacion;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getImagen_publicacion() {
        return imagen_publicacion;
    }

    public void setImagen_publicacion(String imagen_publicacion) {
        this.imagen_publicacion = imagen_publicacion;
    }

    public String getVideo_publicacion() {
        return video_publicacion;
    }

    public void setVideo_publicacion(String video_publicacion) {
        this.video_publicacion = video_publicacion;
    }

    public int getComentarios_publicacion() {
        return comentarios_publicacion;
    }

    public void setComentarios_publicacion(int comentarios_publicacion) {
        this.comentarios_publicacion = comentarios_publicacion;
    }

    public int getLike_publicacion() {
        return like_publicacion;
    }

    public void setLike_publicacion(int like_publicacion) {
        this.like_publicacion = like_publicacion;
    }

    public int getDislike_publicacion() {
        return dislike_publicacion;
    }

    public void setDislike_publicacion(int dislike_publicacion) {
        this.dislike_publicacion = dislike_publicacion;
    }
}
