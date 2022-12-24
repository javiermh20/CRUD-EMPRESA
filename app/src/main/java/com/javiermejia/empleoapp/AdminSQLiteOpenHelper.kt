package com.javiermejia.empleoapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context,name,factory,version)
{
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE administradores (" +
                "matricula text primary key, " +
                "usuario text," +
                "password text," +
                "nombre text," +
                "apellidop text," +
                "apellidom text," +
                "genero text," +
                "fechan text)")

        db.execSQL("CREATE TABLE clientes (" +
                "matricula text primary key, " +
                "usuario text," +
                "password text," +
                "nombre text," +
                "apellidop text," +
                "apellidom text," +
                "genero text," +
                "fechan text)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}