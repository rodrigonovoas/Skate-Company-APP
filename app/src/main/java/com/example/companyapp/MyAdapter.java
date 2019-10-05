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
    int[] flags;
    Context mContext;

    public MyAdapter(Context context, String[] countryNames, int[] countryFlags, Double[] prices) {
        super(context, R.layout.listview_item);
        this.names = countryNames;
        this.flags = countryFlags;
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
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.tv_nombre);
            mViewHolder.mPrice = (TextView) convertView.findViewById(R.id.tv_precio);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(flags[position]);
        mViewHolder.mName.setText(names[position]);
        mViewHolder.mPrice.setText(String.valueOf(prices[position]));

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
        TextView mPrice;
    }
}
