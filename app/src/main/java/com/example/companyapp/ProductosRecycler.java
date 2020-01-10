package com.example.companyapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductosRecycler extends RecyclerView.Adapter<ProductosRecycler.MyViewHolder> {

    private Context mContext;
    private List<Producto> mData;


    public ProductosRecycler(Context mContext, List<Producto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.producto_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_producto.setText(mData.get(position).getNombre());
        holder.tv_precio.setText(String.valueOf(mData.get(position).getPrecio()) + " €");
        Picasso.with(holder.tv_producto.getContext()).load(mData.get(position).getImagen()).into(holder.img_producto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformacionProductoActivity.class);

                intent.putExtra("Nom", mData.get(position).getNombre());
                intent.putExtra("Desc", mData.get(position).getDescripcion());
                intent.putExtra("Img", mData.get(position).getImagen());
                intent.putExtra("Precio", String.valueOf(mData.get(position).getPrecio()) + " €");
                intent.putExtra("Codigo", mData.get(position).getCodigo());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_producto, tv_precio;
        ImageView img_producto;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_producto = (TextView) itemView.findViewById(R.id.tv_titulo);
            tv_precio = (TextView) itemView.findViewById(R.id.tv_precio);
            img_producto = (ImageView) itemView.findViewById(R.id.img_producto);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}
