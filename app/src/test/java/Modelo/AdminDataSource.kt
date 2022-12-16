package Modelo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns

class AdminDataSource(context:Context) {

    private val openHelper:BdOpenHelper = BdOpenHelper(context)
    private val database: SQLiteDatabase
    object ColumnAdmin {
        var MATRICULA = BaseColumns._ID
        var USER = "user"
        var PASSWORD = "password"
        var NOMBRE = "nombre"
        var APELLIDOP = "apellidoP"
        var APELLIDOM = "apellidoM"
        var GENERO = "genero"
        var FECHAN = "fechaN"
    }

    init{
        database = openHelper.writableDatabase
    }

    fun consultarAdministradores(): Cursor {
        return database.rawQuery("select _id,user,password,nombre,apellidoP,apellidoM,genero,fechaN from $ADMINISTRADORES_TABLE_NAME ", null)
    }

    fun consultarAdministrador(matricula:String): Cursor {
        return database.rawQuery("select _id,user,password,nombre,apellidoP,apellidoM,genero,fechaN from $ADMINISTRADORES_TABLE_NAME where _id = $matricula ", null)
    }

    fun guardarAdmin(_id:String,user:String,password:String,nombre:String,apellidoP:String,apellidoM:String,genero:String,fechaN:String) {
        val values = ContentValues()
        values.put(ColumnAdmin.MATRICULA, _id)
        values.put(ColumnAdmin.USER, user)
        values.put(ColumnAdmin.PASSWORD, password)
        values.put(ColumnAdmin.NOMBRE, nombre)
        values.put(ColumnAdmin.APELLIDOP, apellidoP)
        values.put(ColumnAdmin.APELLIDOM, apellidoM)
        values.put(ColumnAdmin.GENERO, genero)
        values.put(ColumnAdmin.FECHAN, fechaN)
        database.insert(ADMINISTRADORES_TABLE_NAME, null, values)
    }

    fun modificarAdministrador(user:String,password:String,nombre:String,apellidoP:String,apellidoM:String,genero:String,fechaN:String, matricula: String) {
        val values = ContentValues()
        values.put(ColumnAdmin.USER, user)
        values.put(ColumnAdmin.PASSWORD, password)
        values.put(ColumnAdmin.NOMBRE, nombre)
        values.put(ColumnAdmin.APELLIDOP, apellidoP)
        values.put(ColumnAdmin.APELLIDOM, apellidoM)
        values.put(ColumnAdmin.GENERO, genero)
        values.put(ColumnAdmin.FECHAN, fechaN)
        val a = arrayOf("" + matricula)
        database.update(ADMINISTRADORES_TABLE_NAME, values, "_id=?", a)
    }

    fun eliminarAdministrador(matricula: String):Boolean {
        val whereArgs = arrayOf("" + matricula)
        try
        {
            database.delete(ADMINISTRADORES_TABLE_NAME, "_id=?", whereArgs)
            return true
        }
        catch (ex:Exception) {
            return false
        }
        finally
        {
            database.close()
        }
    }

    companion object {
        val ADMINISTRADORES_TABLE_NAME = "Administradores"
        val STRING_TYPE = "text"
        val CREATE_ADMINISTRADORES_SCRIPT = (
                "create table " + ADMINISTRADORES_TABLE_NAME + "(" +
                        ColumnAdmin.MATRICULA + " " + STRING_TYPE + " primary key," +
                        ColumnAdmin.USER + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.PASSWORD + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.NOMBRE + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.APELLIDOP + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.APELLIDOM + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.GENERO + " " + STRING_TYPE + " not null," +
                        ColumnAdmin.FECHAN + " " + STRING_TYPE + " not null)")
        val INSERT_ADMINISTRADORES_SCRIPT = (
                "insert into " + ADMINISTRADORES_TABLE_NAME + " values " +
                        "('SKMDKAS','javierm','1234','Javier','Mejia','Hernandez','Hombre','30-05-2002')," +
                        "('NDSSFSF','rayozk','1234','Jose','Rangel','Mora','Hombre','19-07-2002')," +
                        "('SKMDKAS','pancri','1234','Pancracio','Sanchez','Diaz','Hombre','15-06-2002')")
    }
}