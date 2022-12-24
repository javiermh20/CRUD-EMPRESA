package com.javiermejia.empleoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_usuarios.view.*
import kotlinx.android.synthetic.main.activity_listado_view.*

class listadoView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_view)

        LlenarInformacion()

        //FLOAT BUTTON
        fbadd.setOnClickListener{
            val intent = Intent(this@listadoView, registerView::class.java)
            startActivity(intent)
        }
    }

    fun LlenarInformacion(){
        val datasource = ClienteDataSource(this)
        val registros = ArrayList<Cliente>()
        // SE ESTA LLAMANDO AL METODO PARA TRAERNOS TODA LA INFORMACION DE LA BD
        val cursor = datasource.consultarClientes()
        while (cursor.moveToNext())
        {
            val columnas = Cliente(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)
            )
            registros.add(columnas)
        }
        val adaptador = AdpatadorClientes(this, registros)
        listausuarios.adapter = adaptador
        listausuarios.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Cliente
            val intent = Intent(this@listadoView, clienteView::class.java)
            intent.putExtra("matricula", item.matricula)
            intent.putExtra("usuario", item.user)
            intent.putExtra("password",item.password)
            intent.putExtra("nombre", item.nombre)
            intent.putExtra("apellidop", item.apellidoP)
            intent.putExtra("apellidom", item.apellidoM)
            intent.putExtra("genero", item.genero)
            intent.putExtra("fechan", item.fechaN)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        this.LlenarInformacion()
    }

    override fun onResume() {
        super.onResume()
        this.LlenarInformacion()
    }


    internal class AdpatadorClientes(context: Context, datos:List<Cliente>):
        ArrayAdapter<Cliente>(context, R.layout.activity_lista_usuarios, datos) {

        var _datos:List<Cliente>

        init{
            _datos = datos
        }

        @NonNull
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_lista_usuarios, parent, false)
            val currentEntity = getItem(position)
            inflater.LblUsuario.text = currentEntity?.user
            return inflater
        }
    }
}