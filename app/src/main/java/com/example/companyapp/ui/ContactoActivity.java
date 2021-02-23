package com.example.companyapp.ui;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.companyapp.R;
import com.example.companyapp.api.WebService;
import com.example.companyapp.api.WebServiceAPI;
import com.example.companyapp.model.Empresa;
import com.example.companyapp.model.Post;
import com.example.companyapp.ui.adapters.RecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Clase que representa la pestaña de Contactos de la Aplicación.
Se alimenta con la información de la empresa desde la BBDD, e incluye funciones
como el poder redirigirte hacia la aplicación de llamadas, a la aplicación
de Google Maps, y a la aplicación que elijas de correo electrónico.
 */

public class ContactoActivity extends Fragment {
    private static final String TAG = "ContactoActivity";

    private ImageView img;

    TextView tv_nombre;
    TextView tv_direccion;
    TextView tv_tlfn;
    TextView tv_fax;
    TextView tv_email;

    TextView tv_titulotlfn;
    TextView tv_tituloemail;
    TextView tv_titulofax;

    ImageView img_email;
    ImageView img_tlfn;
    ImageView img_localizacion;

    LinearLayout ll_warning;
    TextView tv_warning;
    ImageView imv_warning;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Estas dos línesa son necesarias para que te deje cargar imágenes desde interner y te deje acceder a algunas aplicaciones externas
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View view = inflater.inflate(R.layout.contacto_activity, container, false);
        img = (ImageView) view.findViewById(R.id.imageView3);

        tv_nombre = (TextView) view.findViewById(R.id.tv_nombre);
        tv_direccion = (TextView) view.findViewById(R.id.tv_direccion);
        tv_tlfn = (TextView) view.findViewById(R.id.tv_telefono);
        tv_fax = (TextView) view.findViewById(R.id.tv_fax);
        tv_email = (TextView) view.findViewById(R.id.tv_email);

        tv_titulotlfn = view.findViewById(R.id.tv_titulotelefono);
        tv_tituloemail = view.findViewById(R.id.tv_tituloemail);
        tv_titulofax = view.findViewById(R.id.tv_titulofax);

        img_email = (ImageView) view.findViewById(R.id.img_email);
        img_tlfn = (ImageView) view.findViewById(R.id.img_tlfn);
        img_localizacion = (ImageView) view.findViewById(R.id.img_localizacion);

        ll_warning = getActivity().findViewById(R.id.ll_warninginfo);
        tv_warning = getActivity().findViewById(R.id.tv_warninginfo);
        imv_warning = getActivity().findViewById(R.id.imv_warning);

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

        //Aquí ponemos un Click Listener a la imagen del Mundo, y nos abre la app de Google Maps con las coordenadas que le pasemos.
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

        return view;
    }

    //Cargo los datos desde la bd a las distintas vistas
    public void assignPostData() {

        viewsInvisible(false);
        Call<Empresa> call = WebService.getInstance().createService(WebServiceAPI.class).getEmpresaPorId(1);

        call.enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response){
                ll_warning.setVisibility(View.INVISIBLE);
                imv_warning.setImageDrawable(getContext().getDrawable(R.drawable.warning));

                if(response.code() == 200){
                    viewsInvisible(true);
                    Empresa empresa = response.body();

                    if(empresa == null){
                        ll_warning.setVisibility(View.VISIBLE);
                        tv_warning.setText("No hay datos disponibles.");
                    }else{
                        tv_nombre.setText(empresa.getNombre());
                        tv_direccion.setText(empresa.getDireccion());
                        tv_tlfn.setText(empresa.getTelefono());
                        tv_fax.setText(empresa.getFax());
                        tv_email.setText(empresa.getEmail());

                        cargarImagen(img.getContext(),img,"https://www.uc3m.es/ss/Satellite?blobcol=urldata&blobkey=id&blobtable=MungoBlobs&blobwhere=1371557659282&ssbinary=true");
                    }

                }else if(response.code() == 404){
                    ll_warning.setVisibility(View.VISIBLE);
                    tv_warning.setText("ERROR CON EL SERVIDOR.");
                }
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                imv_warning.setImageDrawable(getContext().getDrawable(R.drawable.warning));
                ll_warning.setVisibility(View.VISIBLE);
                tv_warning.setText("ERROR AL CONECTARSE AL SERVIDOR.");
            }

        });
    }


    private static void cargarImagen(Context context, ImageView img, String url){
        Glide.with(context)
                .load(url)
                .into(img);
    }

    private void viewsInvisible(Boolean visible){

        if(visible == false){
            tv_nombre.setVisibility(View.INVISIBLE);
            tv_direccion.setVisibility(View.INVISIBLE);
            tv_tlfn.setVisibility(View.INVISIBLE);
            tv_fax.setVisibility(View.INVISIBLE);
            tv_email.setVisibility(View.INVISIBLE);

            tv_tituloemail.setVisibility(View.INVISIBLE);
            tv_titulofax.setVisibility(View.INVISIBLE);
            tv_titulotlfn.setVisibility(View.INVISIBLE);
        }else{
            tv_nombre.setVisibility(View.VISIBLE);
            tv_direccion.setVisibility(View.VISIBLE);
            tv_tlfn.setVisibility(View.VISIBLE);
            tv_fax.setVisibility(View.VISIBLE);
            tv_email.setVisibility(View.VISIBLE);

            tv_tituloemail.setVisibility(View.VISIBLE);
            tv_titulofax.setVisibility(View.VISIBLE);
            tv_titulotlfn.setVisibility(View.VISIBLE);
        }
    }

}
