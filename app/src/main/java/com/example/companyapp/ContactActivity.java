package com.example.companyapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ContactActivity extends Fragment {
    private static final String TAG = "ContactActivity";

    private Button btnTEST;
    private ImageView img;

    SQLiteDatabase db;
    BDSkateCompany data_base;

    TextView tv_name;
    TextView tv_address;
    TextView tv_tlfn;
    TextView tv_fax;
    TextView tv_email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View view = inflater.inflate(R.layout.contactactivity_layout, container, false);
        //btnTEST = (Button) view.findViewById(R.id.btnTEST3);
        img = (ImageView) view.findViewById(R.id.imageView3);

        tv_name = (TextView) view.findViewById(R.id.tv_nombre);
        tv_address = (TextView) view.findViewById(R.id.tv_direccion);
        tv_tlfn = (TextView) view.findViewById(R.id.tv_telefono);
        tv_fax = (TextView) view.findViewById(R.id.tv_fax);
        tv_email = (TextView) view.findViewById(R.id.tv_email);

        data_base = new BDSkateCompany(getContext(), "bdSkate", null, 1);
        db = data_base.getReadableDatabase();


        /*
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 3", Toast.LENGTH_SHORT).show();
            }
        });
        */

        assignPostData();
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

    public void assignPostData() {
        String query = "select * from empresa";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        tv_name.setText(cursor.getString(1));
        tv_address.setText(cursor.getString(2));
        tv_tlfn.setText(cursor.getString(3));
        tv_fax.setText(cursor.getString(4));
        tv_email.setText(cursor.getString(5));

        db.close();
        data_base.close();
    }

}
