package com.example.companyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class ContactActivity extends Fragment {
    private static final String TAG = "ContactActivity";

    private Button btnTEST;
    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contactactivity_layout, container, false);
        btnTEST = (Button) view.findViewById(R.id.btnTEST3);
        img = (ImageView) view.findViewById(R.id.imageView3);

        //internet permissons
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 3", Toast.LENGTH_SHORT).show();
            }
        });


        loadImageFromURL();
        return view;
    }

    public void loadImageFromURL() {
        try {
            URL url = new URL("https://cdn141.picsart.com/306391855082201.jpg?c256x256");
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
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            img.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
