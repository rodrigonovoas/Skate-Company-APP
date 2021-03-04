package com.example.companyapp.api;

import com.example.companyapp.model.Empresa;
import com.example.companyapp.model.Post;
import com.example.companyapp.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("/empresa/{id}")
    Call<Empresa> getEmpresaPorId(@Path("id") int id);

    @GET("/producto/listar")
    Call<List<Producto>> getListaProductos();

    @GET("/post/listar")
    Call<List<Post>> getListaPosts();
}
