package com.example.companyapp.model;

import java.util.Date;

public class Usuario {
    private int usuarioid;
    private Date fecharegistro;
    private String username;
    private String contrasena;
    private String email;

    private void setUsuarioid(int id){
        this.usuarioid = id;
    }

    private int getUsuarioid(){
        return this.usuarioid;
    }

    private void setFecharegistro(Date fecha){
        this.fecharegistro = fecha;
    }

    private Date getFecharegistro(){
        return this.fecharegistro;
    }

    private void setUsername(String user){
        this.username = user;
    }

    private String getUsername(){
        return this.username;
    }

    private void setContrasena(String contra){
        this.contrasena = contra;
    }

    private String getContrasena(){
        return this.contrasena;
    }

    private void setEmail(String email){
        this.email = email;
    }

    private String getEmail(){
        return this.email;
    }


}
