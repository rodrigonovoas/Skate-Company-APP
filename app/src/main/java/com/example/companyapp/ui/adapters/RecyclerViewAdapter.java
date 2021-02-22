package com.example.companyapp.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.companyapp.R;
import com.example.companyapp.model.Post;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
Clase AdapterView, que sirve para personalizar el RecyclerView de BlogActivity.
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
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        myDialog = new Dialog(holder.tv_title.getContext());
        holder.tv_title.setText(PostList.get(position).getTitulo());
        //holder.tv_nombreusuario.setText(PostList.get(position).getNombre_usuario());
        //cargar imagen usuario

        final Bitmap[] chefBitmap = {null};

        Glide.with(holder.tv_title.getContext())
                .asBitmap()
                .load(PostList.get(position).getImgurl())
                .into(new BitmapImageViewTarget(holder.img_usuario) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        chefBitmap[0] = resource;
                        super.setResource(resource);


                        if(chefBitmap[0] != null){
                            BitmapDrawable ob = new BitmapDrawable(holder.tv_title.getContext().getResources(), chefBitmap[0]);

                            holder.layout.setBackground(ob);
                        }
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

                cargarImagen(txtclose.getContext(),img,PostList.get(position).getImgurl());

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


    private static void cargarImagen(Context context, ImageView img, String url){
        Glide.with(context)
                .load(url)
                .into(img);
    }
}