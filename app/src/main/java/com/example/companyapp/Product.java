package com.example.companyapp;

/*
Clase molde Product para poder reutilizar la información más fácilmente, una vez obtenida desde la base de datos.
 */

public class Product {

    private String name;
    private float price;
    private int productID;
    private String code;
    private String description;
    private String imagen;

    public Product(String name, String code, String description, float price, String imagen) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.imagen = imagen;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProducID() {
        return productID;
    }

    public void setProductoID(int productoID) {
        this.productID = productoID;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float precio) {
        this.price = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.name = imagen;
    }

}
