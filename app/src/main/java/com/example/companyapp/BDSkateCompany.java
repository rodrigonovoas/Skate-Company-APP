package com.example.companyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Clase SQLite que sirve como molde para poder crear la bbdd y utilizar funciones relacionadas con ella (insertar y eliminar datos, por ejemplo)
 */
public class BDSkateCompany extends SQLiteOpenHelper {

    String sqlCreateProducto = "CREATE TABLE Producto (ProductoID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20) NOT NULL, Codigo VARCHAR(20) NOT NULL, Descripcion VARCHAR(20) NOT NULL, Precio DOUBLE NOT NULL,AlmacenID INTEGER,FOREIGN KEY(AlmacenID) REFERENCES Almacen(AlmacenID))";
    String sqlCreateAlmacen = "CREATE TABLE Almacen (AlmacenID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, ProductoID INTEGER, Stock INTEGER  NOT NULL, FOREIGN KEY(ProductoID) REFERENCES Producto(ProductoID))";
    String sqlCreateImagen = "CREATE TABLE Imagen (ImagenID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,  URL VARCHAR(200) NOT NULL,  Tabla VARCHAR(100) NOT NULL, TablaID INTEGER  NOT NULL)";
    String sqlCreatePost = "CREATE TABLE Post (PostID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR(300) NOT NULL, Contenido VARCHAR(1000) NOT NULL, UsuarioID INTEGER, FOREIGN KEY(UsuarioID) REFERENCES Usuario(UsuarioID))";
    String sqlCreateUsuario = "CREATE TABLE Usuario(UsuarioID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR(50) NOT NULL, Contrasena VARCHAR(100) NOT NULL)";
    String sqlCreateEmpresa = "CREATE TABLE Empresa(EmpresaID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre VARCHAR(50) NOT NULL, Direccion VARCHAR(50) NOT NULL, Telefono VARCHAR(50) NOT NULL, Fax VARCHAR(50), Email VARCHAR(50))";


    //Insertando datos de ejemplo
    String sqlInsertProducto = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('Skate SLK','SKT1','Skate de alta gama de tabla de grafeno', 19.00,1)";
    String sqlInsertProducto2 = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('Skate Tony Hawk X','SKTHX','Skate de la franquicia Tony Hawk Series X',49.99,1)";
    String sqlInsertProducto3 = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('NKX Skateboard','NKXS','Skate de alta gama de la serie NKX.',49.99,1)";
    String sqlInsertProducto4 = "INSERT INTO Producto (Nombre, Codigo, Descripcion, Precio, AlmacenID) VALUES ('NKD Skateboard','NKDS','Skate de alta gama de tabla de la serie NKDS',69.99,1)";

    String sqlInsertAlmacen = "INSERT INTO Almacen (ProductoID, Stock) VALUES (1,200)";

    String sqlInsertImagenUsuario = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg','Usuario',1)";
    String sqlInsertImagenArticulo = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://comofuncionaque.com/wp-content/uploads/2016/10/skate-normal.jpg','Articulo',1)";
    String sqlInsertImagenArticulo2 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.pexels.com/photos/1018484/pexels-photo-1018484.jpeg','Articulo',2)";
    String sqlInsertImagenArticulo3 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.pexels.com/photos/2529187/pexels-photo-2529187.jpeg','Articulo',3)";
    String sqlInsertImagenArticulo4 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.pexels.com/photos/3039183/pexels-photo-3039183.jpeg','Articulo',4)";
    String sqlInsertImagenPost = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://images.pexels.com/photos/2734777/pexels-photo-2734777.jpeg','Post',1)";
    String sqlInsertImagenPost2 = "INSERT INTO Imagen (URL, Tabla, TablaID) VALUES ('https://cdn.vox-cdn.com/thumbor/Ipzvxc3GmjuBy1tzFEBMUheY5So=/0x0:3477x2376/1200x675/filters:focal(1645x350:2201x906)/cdn.vox-cdn.com/uploads/chorus_image/image/65215463/918380600.jpg.0.jpg','Post',2)";

    String sContenidoPost = "Se está convirtiendo en el medio de transporte de moda entre los veinteañeros y en la excusa para echarle nostalgia a los desplazamientos urbanos entre los cuarentañeros. Es cómodo, rápido, vistoso y sencillo de utilizar. Quizá por todas estas razones, el uso del Penny skateboard, un patinete compacto de plástico y  grandes ruedas, se está extendiendo desde los paseos marítimos hasta la ciudad y el secreto de su éxito es muy sencillo: cabe en un bolso.";
    String sContenidoPost2 = "Tony Hawk entró al mundo en skate gracias a su hermano Dani Hawk. El propio Hawk se describe en su biografía como una pesadilla. Era hiperactivo y muy exigente con él mismo. Sus padres, preocupados, hasta pidieron la ayuda de un psicólogo escolar, que les dijo que su hijo tenía la mente de un adulto atrapada en un cuerpo de un niño de ocho años. A los nueve años y medio, su hermano le regaló un monopatín que le cambiaría la vida, haciendo que Tony se dedique al deporte. (...)";

    String sqlInsertPost = "INSERT INTO Post (Titulo, Contenido, UsuarioID) VALUES ('¿Longboard? La nueva obsesión sobre ruedas se llama ‘Penny’','" + sContenidoPost + "',1)";
    String sqlInsertPost2 = "INSERT INTO Post (Titulo, Contenido, UsuarioID) VALUES ('Biografía de Tony Hawk','" + sContenidoPost2 + "',1)";

    String sqlInsertUsuario = "INSERT INTO Usuario (Nombre,Contrasena) VALUES ('Carlitos','1234')";
    String sqlInsertEmpresa = "INSERT INTO Empresa (Nombre,Direccion, Telefono, Fax, Email) VALUES ('Novu SA', 'Barcelona c/Piruleta Portal 34','+34 94 365 44 58', '+34 91 54 333 32', 'emailempresa@gmail.com')";


    public BDSkateCompany(Context contexto, String nombre,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    //se ejecutan estos códigos sql la primera vez que se crea la bbdd
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Aquí se ejecuta el código SQL, creándose las tablas e insertándose los registros.
        db.execSQL(sqlCreateProducto);
        db.execSQL(sqlCreateAlmacen);
        db.execSQL(sqlCreateImagen);
        db.execSQL(sqlCreatePost);
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateEmpresa);

        db.execSQL(sqlInsertProducto);
        db.execSQL(sqlInsertProducto2);
        db.execSQL(sqlInsertProducto3);
        db.execSQL(sqlInsertProducto4);
        db.execSQL(sqlInsertAlmacen);
        db.execSQL(sqlInsertImagenUsuario);
        db.execSQL(sqlInsertImagenArticulo);
        db.execSQL(sqlInsertImagenArticulo2);
        db.execSQL(sqlInsertImagenArticulo3);
        db.execSQL(sqlInsertImagenArticulo4);
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

    //Función que he creado para obtener el URL de la bbdd relacionado con la tabla Imagen
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

    public String obtenerNombreUsuario(int usuarioID, SQLiteDatabase db) {
        String nombre = "";
        String query = "select * from usuario where usuarioid = " + usuarioID;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            nombre = cursor.getString(1);
        } else {
            nombre = "";
        }
        return nombre;
    }

}

