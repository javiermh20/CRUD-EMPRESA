package Modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class ClienteDataSource(context: Context) {
    private val openHelper:BdOpenHelper = BdOpenHelper(context)
    private val database: SQLiteDatabase
    object ColumnCliente {
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

    fun consultarClientes(): Cursor {
        return database.rawQuery("select _id,user,password,nombre,apellidoP,apellidoM,genero,fechaN from $CLIENTES_TABLE_NAME ", null)
    }

    fun consultarClientes(matricula:String): Cursor {
        return database.rawQuery("select _id,user,password,nombre,apellidoP,apellidoM,genero,fechaN from $CLIENTES_TABLE_NAME where _id = $matricula ", null)
    }

    fun guardarCliente(_id:String,user:String,password:String,nombre:String,apellidoP:String,apellidoM:String,genero:String,fechaN:String) {
        val values = ContentValues()
        values.put(ColumnCliente.MATRICULA, _id)
        values.put(ColumnCliente.USER, user)
        values.put(ColumnCliente.PASSWORD, password)
        values.put(ColumnCliente.NOMBRE, nombre)
        values.put(ColumnCliente.APELLIDOP, apellidoP)
        values.put(ColumnCliente.APELLIDOM, apellidoM)
        values.put(ColumnCliente.GENERO, genero)
        values.put(ColumnCliente.FECHAN, fechaN)
        database.insert(CLIENTES_TABLE_NAME, null, values)
    }

    fun modificarCliente(user:String,password:String,nombre:String,apellidoP:String,apellidoM:String,genero:String,fechaN:String, matricula: String) {
        val values = ContentValues()
        values.put(ColumnCliente.USER, user)
        values.put(ColumnCliente.PASSWORD, password)
        values.put(ColumnCliente.NOMBRE, nombre)
        values.put(ColumnCliente.APELLIDOP, apellidoP)
        values.put(ColumnCliente.APELLIDOM, apellidoM)
        values.put(ColumnCliente.GENERO, genero)
        values.put(ColumnCliente.FECHAN, fechaN)
        val a = arrayOf("" + matricula)
        database.update(CLIENTES_TABLE_NAME, values, "_id=?", a)
    }

    fun eliminarCliente(matricula: String):Boolean {
        val whereArgs = arrayOf("" + matricula)
        try
        {
            database.delete(CLIENTES_TABLE_NAME, "_id=?", whereArgs)
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
        val CLIENTES_TABLE_NAME = "Clientes"
        val STRING_TYPE = "text"
        val CREATE_CLIENTES_SCRIPT = (
                "create table " + CLIENTES_TABLE_NAME + "(" +
                        ColumnCliente.MATRICULA + " " + STRING_TYPE + " primary key," +
                        ColumnCliente.USER + " " + STRING_TYPE + " not null," +
                        ColumnCliente.PASSWORD + " " + STRING_TYPE + " not null," +
                        ColumnCliente.NOMBRE + " " + STRING_TYPE + " not null," +
                        ColumnCliente.APELLIDOP + " " + STRING_TYPE + " not null," +
                        ColumnCliente.APELLIDOM + " " + STRING_TYPE + " not null," +
                        ColumnCliente.GENERO + " " + STRING_TYPE + " not null," +
                        ColumnCliente.FECHAN + " " + STRING_TYPE + " not null)")
        val INSERT_CLIENTES_SCRIPT = (
                "insert into " + CLIENTES_TABLE_NAME + " values " +
                        "('SJDNAJS','crespo','1234','Ivan','Crespo','Hernandez','Hombre','26-12-2001')," +
                        "('NSFSFGD','nao','1234','Naomi','Barrera','Bautista','Mujer','28-01-2004')," +
                        "('DFGDGSS','vane','1234','Vanessa','Maite','Mejia','Mujer','25-07-2008')")
    }
}