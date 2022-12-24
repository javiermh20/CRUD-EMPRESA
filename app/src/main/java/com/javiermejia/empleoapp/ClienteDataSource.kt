package com.javiermejia.empleoapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class ClienteDataSource(context: Context) {

    private val openHelper: AdminSQLiteOpenHelper = AdminSQLiteOpenHelper(context, "Administracion",null,1)
    private val database: SQLiteDatabase

    object ColumnEventos {
        var matricula = BaseColumns._ID
        var usuario = "usuario"
        var password = "password"
        var nombre = "nombre"
        var apellidop = "apellidop"
        var apellidom = "apellidom"
        var genero = "genero"
        var fechan = "fechan"
    }

    init {
        database = openHelper.writableDatabase
    }

    fun consultarClientes(): Cursor {
        return database.rawQuery("SELECT matricula, usuario, password, nombre, apellidop, apellidom, genero, fechan" +
            " FROM clientes", null)
    }

}