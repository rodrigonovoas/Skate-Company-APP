package com.example.companyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/*
En esta clase persoanlizamos el listview de ProductsActivity.
Lo hacemos mediante los distintos arrays que rellenamos desde la bbdd y posteriormente los asignamos
a las Views del layout listview_item
 */

public class MyAdapter extends ArrayAdapter<String> {

    String[] names;
    Double[] prices;
    String[] images;
    Context mContext;

    public MyAdapter(Context context, String[] productNames, String[] images, Double[] prices) {
        super(context, R.layout.listview_item);
        this.names = productNames;
        this.images = images;
        this.mContext = context;
        this.prices = prices;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        Image image;

        image = new Image();

        //Aquí usamos un LayoutInflater para personalizar las distintas vistas usadas en el ListView de Productos
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.mImg = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.tv_nombre);
            //mViewHolder.mPrice = (TextView) convertView.findViewById(R.id.tv_precio);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        //Aseguramos que, si no encuentra imagen, asigne una por defecto cargada desde el propio proyecto.
        Picasso.with(getContext()).load(images[position]).into(mViewHolder.mImg);

        mViewHolder.mName.setText(names[position]);
        //mViewHolder.mPrice.setText(String.valueOf(prices[position]));

        return convertView;
    }

    static class ViewHolder {
        ImageView mImg;
        TextView mName;
        TextView mPrice;
    }

}
