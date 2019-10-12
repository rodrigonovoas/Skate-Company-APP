package com.example.companyapp;

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

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends Fragment {
    private static final String TAG = "NewsActivity";
    List<Post> post;

    private RecyclerView recyclerViewNews;
    private RecyclerViewAdapter newsAdapter;

    SQLiteDatabase db;
    BDSkateCompany data_base;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsactivity_layout, container, false);

        recyclerViewNews = (RecyclerView) view.findViewById(R.id.news_recycler);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));

        data_base = new BDSkateCompany(getContext(), "bdSkate", null, 1);
        db = data_base.getReadableDatabase();


        newsAdapter = new RecyclerViewAdapter(getNews());
        recyclerViewNews.setAdapter(newsAdapter);

        return view;
    }

    public List<Post> getNews() {
        post = new ArrayList<>();
        assignPostData();

        return post;
    }

    public void assignPostData() {
        String query = "select * from post";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            post.add(new Post(cursor.getString(1), cursor.getString(2), data_base.getImageString("Post", cursor.getInt(0), db)));
        }

        db.close();
        data_base.close();
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
