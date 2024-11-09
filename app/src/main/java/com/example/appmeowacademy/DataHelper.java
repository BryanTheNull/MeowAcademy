package com.example.appmeowacademy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DataHelper extends SQLiteOpenHelper{

    // Variables estaticas
    private static final String DATABASE_NAME = "MeowAcademy.db";  // Nombre de la base de datos
    private static final int DATABASE_VERSION = 1;  // Versión de la base de datos

    // Consulta para crear la tabla de cursos
    private static final String CREATE_TABLE_COURSES =
            "CREATE TABLE Cursos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "descripcion TEXT NOT NULL, " +
                    "categoria TEXT NOT NULL, " +
                    "duracion TEXT NOT NULL, " +
                    "dificultad TEXT NOT NULL);";

    // Constructor de la clase
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Metodo para crear la tabla cuando se cree la base de datos
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_COURSES);
    }

    // Metodo para actualziar la
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Cursos");
        onCreate(db);
    }
}
