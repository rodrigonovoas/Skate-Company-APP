package com.example.companyapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


//Clase que carga los productos a la ListView, la cual está customizada y se asignan sus datos en la clase ProductosRecycler.


public class ProductosActivity extends Fragment {
    private static final String TAG = "ProductosActivity";

    SQLiteDatabase db;
    BDSkateCompany data_base;

    List<Producto> lst_productos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productos_activity, container, false);

        data_base = new BDSkateCompany(view.getContext(), "bdSkate", null, 1);
        db = data_base.getReadableDatabase();

        asignarDatos();

        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        ProductosRecycler myAdapter = new ProductosRecycler(view.getContext(), lst_productos);
        myrv.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        myrv.setAdapter(myAdapter);
        return view;
    }

    public void asignarDatos() {
        lst_productos = new ArrayList<Producto>();

        String img_producto = "";
        String query = "select * from producto";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            img_producto = data_base.getImageString("Articulo", cursor.getInt(0), db);
            if (img_producto.equalsIgnoreCase("")) {
                img_producto = "aaa";
            }

            lst_productos.add(new Producto(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getFloat(4), img_producto));
        }

        db.close();
        data_base.close();
    }

}

