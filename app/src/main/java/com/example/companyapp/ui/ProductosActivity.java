package com.example.companyapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.companyapp.api.WebService;
import com.example.companyapp.api.WebServiceAPI;
import com.example.companyapp.ui.adapters.ProductosRecycler;
import com.example.companyapp.R;
import com.example.companyapp.model.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Clase que carga los productos a la ListView, la cual está customizada y se asignan sus datos en la clase ProductosRecycler.


public class ProductosActivity extends Fragment {
    private static final String TAG = "ProductosActivity";

    List<Producto> lst_productos;

    LinearLayout ll_warning;
    TextView tv_warning;
    ImageView imv_warning;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productos_activity, container, false);

        ll_warning = getActivity().findViewById(R.id.ll_warninginfo);
        tv_warning = getActivity().findViewById(R.id.tv_warninginfo);
        imv_warning = getActivity().findViewById(R.id.imv_warning);

        asignarDatos(view);

        return view;
    }

    public void asignarDatos(View view) {
        lst_productos = new ArrayList<Producto>();

        Call<List<Producto>> call = WebService.getInstance().createService(WebServiceAPI.class).getListaProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response){
                ll_warning.setVisibility(View.INVISIBLE);
                imv_warning.setImageDrawable(getContext().getDrawable(R.drawable.warning));
                if(response.code() == 200){
                    for(int i=0;i<response.body().size();i++){
                        lst_productos.add(response.body().get(i));
                    }

                    if(lst_productos.size() <= 0){
                        ll_warning.setVisibility(View.VISIBLE);
                        tv_warning.setText("No hay datos disponibles.");
                    }else{
                        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
                        ProductosRecycler myAdapter = new ProductosRecycler(view.getContext(), lst_productos);
                        myrv.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
                        myrv.setAdapter(myAdapter);
                    }

                }else if(response.code() == 404){
                    ll_warning.setVisibility(View.VISIBLE);
                    tv_warning.setText("ERROR CON EL SERVIDOR.");
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                imv_warning.setImageDrawable(getContext().getDrawable(R.drawable.warning));
                ll_warning.setVisibility(View.VISIBLE);
                tv_warning.setText("ERROR AL CONECTARSE AL SERVIDOR.");
            }

        });
    }

}

