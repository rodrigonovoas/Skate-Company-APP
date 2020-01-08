package com.example.companyapp;

/*
Clase molde Product para poder reutilizar la información más fácilmente, una vez obtenida desde la base de datos.
 */

public class Producto {

    private String nombre;
    private float precio;
    private int productoID;
    private String codigo;
    private String descripcion;
    private String imagen;

    public Producto(String nombre, String cod, String descr, float precio, String img) {
        this.nombre = nombre;
        this.codigo = cod;
        this.descripcion = descr;
        this.precio = precio;
        this.imagen = img;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String code) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
