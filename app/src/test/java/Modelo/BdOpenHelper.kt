package Modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "Empleo.db"
val DATABASE_VERSION = 1

class BdOpenHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(AdminDataSource.CREATE_ADMINISTRADORES_SCRIPT)
        db.execSQL(AdminDataSource.INSERT_ADMINISTRADORES_SCRIPT)
        db!!.execSQL(ClienteDataSource.CREATE_CLIENTES_SCRIPT)
        db.execSQL(ClienteDataSource.INSERT_CLIENTES_SCRIPT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
