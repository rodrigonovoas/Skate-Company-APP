package com.example.companyapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
Clase que carga los productos a la ListView, la cual está customizada y se asignan sus datos en la clase MyAdapter.
 */

public class ProductsActivity extends Fragment {
    private static final String TAG = "ProductsActivity";

    SQLiteDatabase db;
    BDSkateCompany data_base;


    ListView mListView;

    List<String> names;
    List<Double> prices;
    List<String> images;

    String[] productNames;
    String[] productImages;
    Double[] productPrices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productsactivity_layout, container, false);
        mListView = (ListView) view.findViewById(R.id.listview);

        data_base = new BDSkateCompany(getContext(), "bdSkate", null, 1);
        db = data_base.getReadableDatabase();

        assignProductData();

        //Se le asigna el adaptador al listivew, para poder personalizarlo
        MyAdapter myAdapter = new MyAdapter(getContext(), productNames, productImages, productPrices);
        mListView.setAdapter(myAdapter);

        //Los productos tienen un Click Listener que les permite agregar una funcionalidad en caso de pulsar en uno de ellos.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "TOAST DE PRUEBA", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //Se asignan los datos desde la BBDD
    public void assignProductData() {
        String query = "select * from producto";
        Cursor cursor = db.rawQuery(query, null);

        names = new ArrayList<String>();
        prices = new ArrayList<Double>();
        images = new ArrayList<String>();

        while (cursor.moveToNext()) {
            names.add(cursor.getString(1));
            prices.add(cursor.getDouble(4));
            images.add(data_base.getImageString("Articulo", cursor.getInt(0), db));
        }

        productNames = new String[names.size()];
        productImages = new String[images.size()];
        productPrices = new Double[prices.size()];

        names.toArray(productNames);
        prices.toArray(productPrices);
        images.toArray(productImages);

        db.close();
        data_base.close();
    }

}

