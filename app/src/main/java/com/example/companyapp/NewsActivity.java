package com.example.companyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */

public class NewsActivity extends Fragment {
    private static final String TAG = "NewsActivity";

    private RecyclerView recyclerViewNews;
    private RecyclerViewAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsactivity_layout, container, false);

        recyclerViewNews = (RecyclerView) view.findViewById(R.id.news_recycler);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));

        newsAdapter = new RecyclerViewAdapter(getNews());
        recyclerViewNews.setAdapter(newsAdapter);

        return view;
    }

    public List<Post> getNews() {
        List<Post> post = new ArrayList<>();
        String prueba = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        post.add(new Post("Nuevas tiendas abiertas en el municipio de Zarautz.", prueba, R.drawable.giorno));

        return post;
    }

    /*
    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsactivity_layout,container,false);

        return view;
    }
    */
}
