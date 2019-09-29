package com.example.companyapp;

import java.sql.Blob;

public class Image {
    private int ImageID;
    private Blob image;
    private String table;
    private String tableID;

    public int getImageID() {
        return ImageID;
    }

    public Blob getImage() {
        return image;
    }

    public String getTable() {
        return table;
    }

    public String getTableID() {
        return tableID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

}
