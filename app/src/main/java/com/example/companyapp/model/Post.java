package com.example.companyapp.model;

import java.util.Date;

/*
Clase molde Post para poder reutilizar la información más fácilmente, una vez obtenida desde la base de datos.
 */

public class Post {

    private int postid;
    private Date fecharegistro;
    private String titulo;
    private String contenido;
    private String imgurl;
    private int usuarioid;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String img) {
        this.imgurl = img;
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

    public String getContenido() {
        return contenido;
    }

    public void setFecharegistro(Date fecha) {
        this.fecharegistro = fecha;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setUsuarioid(int usuarioID) {
        this.usuarioid = usuarioID;
    }

    public int getUsuarioid() {
        return this.usuarioid;
    }

    public void setPostid(int postID) {
        this.postid = postID;
    }

    public int getPostid() {
        return postid;
    }

}

