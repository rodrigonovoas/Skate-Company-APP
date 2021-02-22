package com.example.companyapp.api;

import com.example.companyapp.model.Empresa;
import com.example.companyapp.model.Post;
import com.example.companyapp.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    /*
    funciones a realizar:
    - metodo post empresa
    - metodo post usuario
    - metodo get empresa (por id)
    - metodo get usuario (por id)

     */

    @GET("/empresa/{id}")
    Call<Empresa> getEmpresaPorId(@Path("id") int id);

    @GET("/producto/listar")
    Call<List<Producto>> getListaProductos();

    @GET("/post/listar")
    Call<List<Post>> getListaPosts();
}
