package com.example.companyapp;


public class Product {

    private String name;
    private float price;
    private int productID;
    private int code;
    private int description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
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

}
