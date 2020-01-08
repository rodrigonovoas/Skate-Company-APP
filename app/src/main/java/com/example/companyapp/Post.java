package com.example.companyapp;

import java.util.Date;

/*
Clase molde Post para poder reutilizar la información más fácilmente, una vez obtenida desde la base de datos.
 */

public class Post {

    private String titulo;
    private String contenido;
    private Date fecha;
    private String img;
    private int UsuarioID;
    private String img_usuario;
    private String nombre_usuario;
    private int PostID;

    public Post(String title, String content, String img, String img_usuario, String nombre_usuario) {
        this.titulo = title;
        this.contenido = content;
        this.img = img;
        this.img_usuario = img_usuario;
        this.nombre_usuario = nombre_usuario;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

    public String getImg_usuario() {
        return img_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

}
