package com.example.companyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        if(image.returnBitmapImageFromURL(images[position]) == null){
            mViewHolder.mImg.setImageResource(R.drawable.image_not_found);
        }else{
            mViewHolder.mImg.setImageBitmap(image.returnBitmapImageFromURL(images[position]));
        }
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
