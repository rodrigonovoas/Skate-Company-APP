package com.example.companyapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Image image;
    private List<Post> PostList;
    Dialog myDialog;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_content;
        private ImageView img_recycler;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            //tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            img_recycler = (ImageView) itemView.findViewById(R.id.img_background);
        }
    }

    public RecyclerViewAdapter(List<Post> postList) {
        this.PostList = postList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        image = new Image();
        myDialog = new Dialog(holder.img_recycler.getContext());
        holder.tv_title.setText(PostList.get(position).getTitulo());
        if (image.returnBitmapImageFromURL(PostList.get(position).getImg()) != null) {
            holder.img_recycler.setImageBitmap(image.returnBitmapImageFromURL(PostList.get(position).getImg()));
        } else {
            holder.img_recycler.setImageResource(R.drawable.image_not_found);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TextView txtclose;
                TextView tv_title;
                TextView tv_content;
                ImageView img;

                myDialog.setContentView(R.layout.post_popup);
                txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
                tv_title = (TextView) myDialog.findViewById(R.id.tv_title);
                tv_content = (TextView) myDialog.findViewById(R.id.tv_content);
                img = (ImageView) myDialog.findViewById(R.id.header_img);
                txtclose.setText("X");

                if (image.returnBitmapImageFromURL(PostList.get(position).getImg()) != null) {
                    img.setImageBitmap(image.returnBitmapImageFromURL(PostList.get(position).getImg()));
                } else {
                    img.setImageResource(R.drawable.image_not_found);
                }

                tv_title.setText(PostList.get(position).getTitulo());
                tv_content.setText(PostList.get(position).getContenido());
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });
    }

    @Override

    public int getItemCount() {
        return PostList.size();
    }
}