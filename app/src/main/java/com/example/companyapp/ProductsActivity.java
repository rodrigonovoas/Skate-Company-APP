package com.example.companyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ProductsActivity extends Fragment {
    private static final String TAG = "ProductsActivity";

    ListView mListView;

    String[] countryNames = {"Skate RX Street 10"};


    int[] countryFlags = {R.drawable.flag_australia};

    Double[] countryPrices = {22.45};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productsactivity_layout, container, false);
        mListView = (ListView) view.findViewById(R.id.listview);

        MyAdapter myAdapter = new MyAdapter(getContext(), countryNames, countryFlags, countryPrices);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent mIntent = new Intent(getContext(), DetailActivity.class);
                //mIntent.putExtra("countryName", countryNames[i]);
                //mIntent.putExtra("countryFlag", countryFlags[i]);
                //startActivity(mIntent);
                Toast.makeText(getContext(), "TOAST DE PRUEBA", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}

