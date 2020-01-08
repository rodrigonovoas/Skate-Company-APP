package com.example.companyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/*
Clase que representa la pestaña de Contactos de la Aplicación.
Se alimenta con la información de la empresa desde la BBDD, e incluye funciones
como el poder redirigirte hacia la aplicación de llamadas, hacia la aplicación
de Google Maps, y hacia la aplicación que elijas de correo electrónico.
 */

public class ContactoActivity extends Fragment {
    private static final String TAG = "ContactoActivity";

    private Image image;

    private ImageView img;

    SQLiteDatabase db;
    BDSkateCompany data_base;

    TextView tv_nombre;
    TextView tv_direccion;
    TextView tv_tlfn;
    TextView tv_fax;
    TextView tv_email;

    ImageView img_email;
    ImageView img_tlfn;
    ImageView img_localizacion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Estas dos línesa son necesarias para que te deje cargar imágenes desde interner y te deje acceder a algunas aplicaciones externas
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        image = new Image();
        View view = inflater.inflate(R.layout.contacto_activity, container, false);
        img = (ImageView) view.findViewById(R.id.imageView3);

        tv_nombre = (TextView) view.findViewById(R.id.tv_nombre);
        tv_direccion = (TextView) view.findViewById(R.id.tv_direccion);
        tv_tlfn = (TextView) view.findViewById(R.id.tv_telefono);
        tv_fax = (TextView) view.findViewById(R.id.tv_fax);
        tv_email = (TextView) view.findViewById(R.id.tv_email);

        img_email = (ImageView) view.findViewById(R.id.img_email);
        img_tlfn = (ImageView) view.findViewById(R.id.img_tlfn);
        img_localizacion = (ImageView) view.findViewById(R.id.img_localizacion);

        //Se instancia la base de datos, de nombre bdSkate
        data_base = new BDSkateCompany(getContext(), "bdSkate", null, 1);
        db = data_base.getReadableDatabase();

        //Aquí ponemos un Click Listener a la imagen de Email, y cuando hacemos click en ella, nos redirige a la aplicación que elijamos de correo electrónico.
        img_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                emailIntent.setType("vnd.android.cursor.dir/email");
                String to[] = {"ejemplo@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Envio");
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            }
        });

        //Aquí ponemos un Click Listener a la imagen del Teléfono, y nos abre la aplicación nativa de Android de llamada, con el número que pasamos como parámetro
        img_tlfn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "1234567890", null)));
            }
        });

        //Aquí ponemos un Click Listener a la imagen del Mundo, y nos abre la app de Google Maps ocn las coordenadas que le pasemos.
        img_localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=28.5675,77.3260");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        assignPostData();

        Picasso.with(getContext()).load("https://comofuncionaque.com/wp-content/uploads/2016/10/skate-normal.jpg").into(img);

        return view;
    }

    //Función que retorna un URL en formato bitmap, para poder asignárselo a una View tipo ImageView y poder cargar una imagen desde internet.


    //Cargo los datos desde la bbdd a las distintas vistas
    public void assignPostData() {
        String query = "select * from empresa";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        tv_nombre.setText(cursor.getString(1));
        tv_direccion.setText(cursor.getString(2));
        tv_tlfn.setText("Tlf. " + cursor.getString(3));
        tv_fax.setText("Fax: " + cursor.getString(4));
        tv_email.setText("Email: " + cursor.getString(5));

        db.close();
        data_base.close();
    }

}
