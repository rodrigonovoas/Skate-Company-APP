package com.example.companyapp.model;

import java.util.Date;

public class Empresa {
    private int empresaid;
    private Date fecharegistro;
    private String nombre;
    private String direccion;
    private String telefono;
    private String fax;
    private String email;

    public void setEmpresaid(int id){
        this.empresaid = id;
    }

    public int getEmpresaid(){
        return this.empresaid;
    }

    public void setFecharegistro(Date fecha){
        this.fecharegistro = fecha;
    }

    public Date getFecharegistro(){
        return this.fecharegistro;
    }

    public void setNombre(String nom){
        this.nombre = nom;
    }

    public String getNombre(){
        return  this.nombre;
    }

    public void setDireccion(String dir){
        this.direccion = dir;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void setTelefono(String tfn){
        this.telefono = tfn;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public void setFax(String fax){
        this.fax = fax;
    }

    public String getFax(){
        return this.fax;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

}
