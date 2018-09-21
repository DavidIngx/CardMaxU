package com.numa.cardmax.cardmaxu;

import java.util.HashMap;
import java.util.Map;

public class ObjetoMuro {

    public String key;
    public String titulo_publicacion;
    public String contenido_publicacion;
    public String fecha_publicacion;
    public String imagen_publicacion;
    public int comentarios_publicacion;
    public int like_publicacion;
    public int dislike_publicacion;
    public int on_like;
    public int on_dislike;
    public int on_comentarios;
    public int on_compartir;

    public ObjetoMuro(String key, String titulo_publicacion, String contenido_publicacion, String fecha_publicacion, String imagen_publicacion, int comentarios_publicacion, int like_publicacion, int dislike_publicacion, int on_like, int on_dislike, int on_comentarios, int on_compartir) {
        this.key = key;
        this.titulo_publicacion = titulo_publicacion;
        this.contenido_publicacion = contenido_publicacion;
        this.fecha_publicacion = fecha_publicacion;
        this.imagen_publicacion = imagen_publicacion;
        this.comentarios_publicacion = comentarios_publicacion;
        this.like_publicacion = like_publicacion;
        this.dislike_publicacion = dislike_publicacion;
        this.on_like = on_like;
        this.on_dislike = on_dislike;
        this.on_comentarios = on_comentarios;
        this.on_compartir = on_compartir;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public int getOn_like() {
        return on_like;
    }

    public void setOn_like(int on_like) {
        this.on_like = on_like;
    }

    public int getOn_dislike() {
        return on_dislike;
    }

    public void setOn_dislike(int on_dislike) {
        this.on_dislike = on_dislike;
    }

    public int getOn_comentarios() {
        return on_comentarios;
    }

    public void setOn_comentarios(int on_comentarios) {
        this.on_comentarios = on_comentarios;
    }

    public int getOn_compartir() {
        return on_compartir;
    }

    public void setOn_compartir(int on_compartir) {
        this.on_compartir = on_compartir;
    }

    public ObjetoMuro() {
    }


}
