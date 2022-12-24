package com.javiermejia.empleoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_admin_view.*
import kotlinx.android.synthetic.main.activity_cliente_view.*
import kotlinx.android.synthetic.main.activity_main.*

class adminView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view)
        // Recibimos el intent del MainActivity
        val bundle = intent.extras
        val matricula = bundle?.getString("matricula")
        val usuario = bundle?.getString("usuario")
        val password = bundle?.getString("password")
        val nombre = bundle?.getString("nombre")
        val apellidop = bundle?.getString("apellidop")
        val apellidom = bundle?.getString("apellidom")
        val genero = bundle?.getString("genero")
        val fechan = bundle?.getString("fechan")

        if (genero == "Mujer") {
            Picasso.get()
                .load("https://cdn3.iconfinder.com/data/icons/glypho-generic-icons/64/user-woman-circle-512.png")
                .into(imgUserA)
        } else {
            Picasso.get()
                .load("https://cdn3.iconfinder.com/data/icons/glypho-generic-icons/64/user-man-circle-512.png")
                .into(imgUserA)
        }

        // Actions Buttons
        usuariosABtn.setOnClickListener{
            val intent = Intent(this@adminView, listadoView::class.java)
            startActivity(intent)
        }
        eliminarABtn.setOnClickListener {
            eliminarAdministrador(usuario.toString())
        }
        cancelarABtn.setOnClickListener {
            finish()
        }


        matriculaATxt.text = "MATRICULA: $matricula \n USUARIO: $usuario"
        descripcionATxt.text = "NOMBRE: \n $nombre $apellidop $apellidom \n SEXO: $genero \n FECHA: \n $fechan"
    }

    fun eliminarAdministrador(user: String) {
        val admin = AdminSQLiteOpenHelper(this, "Administracion",null,1)
        val bd = admin.writableDatabase
        val cant = bd.delete("administradores","usuario='${user}'",null)
        bd.close()
        if(cant == 1){
            Toast.makeText(this,"El Administrador fue eliminado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
        else {
            Toast.makeText(this,"No se pudo eliminar el usuario administrador: $user", Toast.LENGTH_SHORT).show()
        }
    }
}