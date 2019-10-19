package com.example.companyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
Clase SQLite que sirve como molde para poder crear la bbdd y utilizar funciones relacionadas con ella (insertar y eliminar datos, por ejemplo)
 Las funciones son autoaclaratorias.
 */
public class BDSkateCompany extends SQLiteOpenHelper {

    String sqlCreateProducto = "CREATE TABLE Producto (ProductoID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20) NOT NULL, Codigo VARCHAR(20) NOT NULL, Descripcion VARCHAR(20) NOT NULL, Precio DOUBLE NOT NULL,AlmacenID INTEGER,FOREIGN KEY(AlmacenID) REFERENCES Almacen(AlmacenID))";
    String sqlCreateAlmacen = "CREATE TABLE Almacen (AlmacenID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, ProductoID INTEGER, Stock INTEGER  NOT NULL, FOREIGN KEY(ProductoID) REFERENCES Producto(ProductoID))";
    String sqlCreateImagen = "CREATE TABLE Imagen (ImagenID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,  URL VARCHAR(200) NOT NULL,  Tabla VARCHAR(100) NOT NULL, TablaID INTEGER  NOT NULL)";
    String sqlCreatePost = "CREATE TABLE Post (PostID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR(300) NOT NULL, Contenido VARCHAR(1000) NOT NULL, UsuarioID INTEGER, FOREIGN KEY(UsuarioID) REFERENCES Usuario(UsuarioID))";
    String sqlCreateUsuario = "CREATE TABLE Usuario(UsuarioID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR(50) NOT NULL, Contrasena VARCHAR(100) NOT NULL)";
    String sqlCreateEmpresa = "CREATE TABLE Empresa(EmpresaID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR(50) NOT NULL, Direccion VARCHAR(50) NOT NULL, Telefono VARCHAR(50) NOT NULL, Fax VARCHAR(50), Email VARCHAR(50))";

    String sqlInsertProducto = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('Skate SLK','SKT1','Skate de alta gama de tabla de grafeno',23,1)";
    String sqlInsertProducto2 = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('Skate SLK2','SKT12','Skate de alta gama de tabla de grafeno 2',23,1)";
    String sqlInsertAlmacen = "INSERT INTO Almacen (ProductoID, Stock) VALUES (1,50)";
    String sqlInsertImagenArticulo = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.blue-tomato.com/is/image/bluetomato/303838465_front.jpg-9OAxqJ7zqUgpwTTUDY1RCc8PFlE/Shut+Up+Skate+8+0+Complete.jpg?$b8$','Articulo',1)";
    String sqlInsertImagenPost = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://monarksupply.com/blog/wp-content/uploads/2019/01/C%C3%B3mo-Construir-una-Mini-Ramp-de-Skate-Fabricaci%C3%B3n-Meseta-y-Colocaci%C3%B3n-Coping-2.jpg','Post',1)";
    String sqlInsertImagenArticulo2 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://comofuncionaque.com/wp-content/uploads/2016/10/skate-normal.jpg','Articulo',2)";
    String sqlInsertImagenPost2 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://cdn.vox-cdn.com/thumbor/Ipzvxc3GmjuBy1tzFEBMUheY5So=/0x0:3477x2376/1200x675/filters:focal(1645x350:2201x906)/cdn.vox-cdn.com/uploads/chorus_image/image/65215463/918380600.jpg.0.jpg','Post',2)";
    String sqlInsertPost = "INSERT INTO Post (Titulo, Contenido, UsuarioID) VALUES ('TITULO DE PRUEBA','CONTENIDO DE PRUEBA ASDF ASDF',1)";

    String sqlInsertPost2 = "INSERT INTO Post (Titulo, Contenido, UsuarioID) VALUES ('TITULO DE PRUEBA 2','CONTENIDO DE PRUEBA ASDF ASDF aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',1)";
    String sqlInsertUsuario = "INSERT INTO Usuario (Nombre,Contrasena) VALUES ('Carlitos','1234')";
    String sqlInsertEmpresa = "INSERT INTO Empresa (Nombre,Direccion, Telefono, Fax, Email) VALUES ('Novu SA', 'Barcelona c/Piruleta Portal 34','+34 94 365 44 58', '+34 91 54 333 32', 'emailempresa@gmail.com')";


    public BDSkateCompany(Context contexto, String nombre,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    //se ejecutan estos códigos sql la primera vez que se crea la bbdd
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateProducto);
        db.execSQL(sqlCreateAlmacen);
        db.execSQL(sqlCreateImagen);
        db.execSQL(sqlCreatePost);
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateEmpresa);

        db.execSQL(sqlInsertProducto);
        db.execSQL(sqlInsertProducto2);
        db.execSQL(sqlInsertAlmacen);
        db.execSQL(sqlInsertImagenArticulo);
        db.execSQL(sqlInsertImagenArticulo2);
        db.execSQL(sqlInsertImagenPost);
        db.execSQL(sqlInsertImagenPost2);
        db.execSQL(sqlInsertPost);
        db.execSQL(sqlInsertPost2);
        db.execSQL(sqlInsertUsuario);
        db.execSQL(sqlInsertEmpresa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String getImageString(String table, int ID, SQLiteDatabase db) {
        String url;
        String query = "select * from imagen where tablaid = " + ID + " and tabla = '" + table + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            url = cursor.getString(1);
        } else {
            url = "";
        }
        return url;
    }

}

