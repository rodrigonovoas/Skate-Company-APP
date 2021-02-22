package com.example.companyapp.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.companyapp.R;
import com.example.companyapp.api.WebService;
import com.example.companyapp.api.WebServiceAPI;
import com.example.companyapp.ui.adapters.RecyclerViewAdapter;
import com.example.companyapp.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Pestaña de Noticias. Aquí se carga la información de la tabla Post de la bd, y le damos un estilo
mediante ReyclerViewAdapter y sus layouts, intentando imitar los post de un blog.
También damos la opción a abrir la noticia en una ventana emergente (dialog) gracias al click listener y la creación del dialog con su respectivo layout.
 */

public class BlogActivity extends Fragment {
    private static final String TAG = "BlogActivity";
    List<Post> post;

    private RecyclerView recyclerViewBlog;
    private RecyclerViewAdapter blogAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blog_activity, container, false);

        recyclerViewBlog = (RecyclerView) view.findViewById(R.id.news_recycler);
        recyclerViewBlog.setLayoutManager(new LinearLayoutManager(getContext()));

        obtenerPost();

        return view;
    }

    //Hacemos una lista de la clase molde Post, asignandole los datos con el método asignarDatos

    public void obtenerPost() {
        post = new ArrayList<>();
        asignarDatos();
    }

    //Obtenemos los post y sus información de la bd; posteriormente, rellenamos la lista del objeto Post con los datos sacados de la bd
    public void asignarDatos() {
        Call<List<Post>> call = WebService.getInstance().createService(WebServiceAPI.class).getListaPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response){
                if(response.code() == 200){
                    for(int i=0;i<response.body().size();i++){
                        //reponse.body(i).getNombre()
                        post.add(response.body().get(i));
                    }

                    blogAdapter = new RecyclerViewAdapter(post);
                    recyclerViewBlog.setAdapter(blogAdapter);

                }else if(response.code() == 404){
                    //codigo error
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //codigo

            }

        });
    }
}
