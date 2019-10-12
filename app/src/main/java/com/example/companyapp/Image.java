package com.example.companyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Image {
    private int ImageID;
    private String url;
    private String table;
    private String tableID;

    public int getImageID() {
        return ImageID;
    }

    public String getURL() {
        return url;
    }

    public String getTable() {
        return table;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public void setURL(String url) {
        this.url = url;
    }


    public Bitmap returnBitmapImageFromURL(String image_url) {
        //internet permissons
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bitmap bitmap = null;
        try {
            URL url = new URL(image_url);
            InputStream in = url.openConnection().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in, 1024 * 8);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = bis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            bis.close();

            byte[] data = out.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
