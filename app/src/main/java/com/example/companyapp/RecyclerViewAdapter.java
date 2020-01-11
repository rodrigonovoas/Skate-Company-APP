package com.example.companyapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/*
Clase AdapterView, que sirve para personalizar el RecyclerView de PostActivity.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Post> PostList;
    Dialog myDialog;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_nombreusuario;
        private ImageView img_usuario;
        private RelativeLayout layout;

        //Aquí asignamos las disintas Views mediante el constructor.
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_nombreusuario = (TextView) itemView.findViewById(R.id.tv_username);
            img_usuario = (ImageView) itemView.findViewById(R.id.img_user);
            layout = (RelativeLayout) itemView.findViewById(R.id.relativeid);
        }
    }

    public RecyclerViewAdapter(List<Post> postList) {
        this.PostList = postList; //Asignamos la lista de la clase molde Post, previamente pasada como parámetro
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        myDialog = new Dialog(holder.tv_title.getContext());
        holder.tv_title.setText(PostList.get(position).getTitulo());
        holder.tv_nombreusuario.setText(PostList.get(position).getNombre_usuario());
        Picasso.with(holder.tv_title.getContext()).load(PostList.get(position).getImg_usuario()).into(holder.img_usuario);

        Picasso.with(holder.tv_title.getContext()).load(PostList.get(position).getImg()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.layout.setBackground(new BitmapDrawable(holder.tv_title.getContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {

                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /*
            Aquí creamos la ventana emergente que hará visualizar el contenido del Post. Le asignamos
            las views correspondientes junto con sus datos, obtenidos desde la clase molde Post.
             */

            @Override
            public void onClick(View arg0) {
                TextView txtclose;
                TextView tv_title;
                TextView tv_content;
                ImageView img;
                ScrollView scroll;


                myDialog.setContentView(R.layout.post_popup);
                txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
                tv_title = (TextView) myDialog.findViewById(R.id.tv_title);
                tv_content = (TextView) myDialog.findViewById(R.id.tv_content);
                img = (ImageView) myDialog.findViewById(R.id.header_img);
                scroll = (ScrollView) myDialog.findViewById(R.id.scroll_view);
                txtclose.setText("X");

                Picasso.with(txtclose.getContext()).load(PostList.get(position).getImg()).into(img);

                tv_title.setText(PostList.get(position).getTitulo());
                tv_content.setText(PostList.get(position).getContenido());
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });


                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(tv_content, 1, 17, 1,
                        TypedValue.COMPLEX_UNIT_DIP);


                myDialog.show();
            }
        });
    }

    @Override

    public int getItemCount() {
        return PostList.size();
    }
}