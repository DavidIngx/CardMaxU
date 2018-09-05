package com.numa.cardmax.cardmaxu;

public class Fuente {

    String titulo;
    String imagen;
    int estado;

    public Fuente(String titulo, String imagen, int estado) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
