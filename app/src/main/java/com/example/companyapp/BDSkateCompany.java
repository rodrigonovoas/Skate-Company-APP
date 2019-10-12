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
    String sqlInsertAlmacen = "INSERT INTO Almacen (ProductoID, Stock) VALUES (1,50)";
    String sqlInsertImagenArticulo = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://cdn141.picsart.com/306391855082201.jpg?c256x256','Articulo',1)";
    String sqlInsertImagenPost = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://cdn141.picsart.com/306391855082201.jpg?c256x256','Post',1)";
    String sqlInsertPost = "INSERT INTO Post (Titulo, Contenido, UsuarioID) VALUES ('TITULO DE PRUEBA','CONTENIDO DE PRUEBA ASDF ASDF',1)";
    String sqlInsertUsuario = "INSERT INTO Usuario (Nombre,Contrasena) VALUES ('Carlitos','1234')";
    String sqlInsertEmpresa = "INSERT INTO Empresa (Nombre,Direccion, Telefono, Fax, Email) VALUES ('Novu SA', 'Barcelona c/Pirulate Portal 34','+34 94 365 44 58', '+34 91 54 333 32', 'emailempresa@gmail.com')";


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
        db.execSQL(sqlInsertAlmacen);
        db.execSQL(sqlInsertImagenArticulo);
        db.execSQL(sqlInsertImagenPost);
        db.execSQL(sqlInsertPost);
        db.execSQL(sqlInsertUsuario);
        db.execSQL(sqlInsertEmpresa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String getImageString(String table, int ID, SQLiteDatabase db) {
        String query = "select * from imagen where tablaid = " + ID + " and tabla = '" + table + "'";
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        return cursor.getString(1);
    }

}

