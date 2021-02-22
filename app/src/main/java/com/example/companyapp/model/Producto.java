package com.example.companyapp.model;


//Clase molde Producto para poder reutilizar la información más fácilmente, una vez obtenida desde la base de datos.


import java.util.Date;

public class Producto {

    private int productoid;
    private Date fecharegistro;
    private String nombre;
    private String descripcion;
    private float precio;
    private String codigo;
    private String imgurl;

    public Producto(int productoid, Date fecha, String nombre, String descr, float precio, String cod, String img) {
        this.productoid = productoid;
        this.fecharegistro = fecha;
        this.nombre = nombre;
        this.descripcion = descr;
        this.precio = precio;
        this.codigo = cod;
        this.imgurl = img;
    }

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoID) {
        this.productoid = productoID;
    }

    private Date getFecharegistro(){
        return this.fecharegistro;
    }

    private void setDate(Date fecha){
        this.fecharegistro = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String code) {
        this.codigo = codigo;
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imagen) {
        this.imgurl = imagen;
    }

}
