package com.example.companyapp;

import java.util.Date;

public class Post {

    private String titulo;
    private String contenido;
    private Date fecha;
    private int img;
    private int UsuarioID;
    private int PostID;

    public Post(String title, String content, int img) {
        this.titulo = title;
        this.contenido = content;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

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
