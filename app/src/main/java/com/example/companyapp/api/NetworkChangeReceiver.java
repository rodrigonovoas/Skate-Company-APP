package com.example.companyapp.api;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.companyapp.R;

public class NetworkChangeReceiver extends BroadcastReceiver {

    LinearLayout ll_warning;
    TextView tv_warning;
    ImageView imv_warning;

    @Override
    public void onReceive(Context context, Intent intent) {
        Activity activity = (Activity)context;

        ll_warning = activity.findViewById(R.id.ll_warninginfo);
        tv_warning = activity.findViewById(R.id.tv_warninginfo);
        imv_warning = activity.findViewById(R.id.imv_warning);

        try
        {
            if (isOnline(context)) {
                ll_warning.setVisibility(View.INVISIBLE);
                Log.e("keshav", "Online Connect Intenet ");
            } else {

                imv_warning.setImageDrawable(context.getDrawable(R.drawable.wifi_disc));
                ll_warning.setVisibility(View.VISIBLE);
                tv_warning.setText("No hay conexión a internet.");
                Log.e("keshav", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}