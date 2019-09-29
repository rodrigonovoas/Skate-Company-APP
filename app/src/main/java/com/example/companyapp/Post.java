package com.example.companyapp;

import java.util.Date;

public class Post {

    private String titulo;
    private String contenido;
    private Date fecha;
    private int UsuarioID;
    private int PostID;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public int getPostID() {
        return PostID;
    }

}
