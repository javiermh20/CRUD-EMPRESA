package com.javiermejia.empleoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarBtn.setOnClickListener{
            validarCamposVacios()
            if(validarCamposVacios() == true){
                buscarUsuario()
            }
        }

        registrarBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, registerView::class.java)
            startActivity(intent)
        }
    }

    fun validarCamposVacios(): Boolean {
        if(userTxt.text.isEmpty()){
            userTxt.error = "Debes escribir su nombre de Usuario"
            return false
        }  else if(passwordTxt.text.isEmpty()){
            passwordTxt.error = "Debes escribir su Constraseña"
            return false
        }  else {
            return true
        }
    }

    fun buscarUsuario(){
        val admin = AdminSQLiteOpenHelper(this, "Administracion",null,1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("Select matricula, usuario, password, nombre, apellidop, apellidom, genero, fechan from " +
                "administradores where usuario='${userTxt.text}' AND password='${passwordTxt.text}'",null)

        val filaCliente = bd.rawQuery("Select matricula, usuario, password, nombre, apellidop, apellidom, genero, fechan from " +
                "clientes where usuario='${userTxt.text}' AND password='${passwordTxt.text}'",null)

        if(fila.moveToFirst()){
            val matricula = fila.getString(0)
            val usuario = fila.getString(1)
            val password = fila.getString(2)
            val nombre = fila.getString(3)
            val apellidop = fila.getString(4)
            val apellidom = fila.getString(5)
            val genero = fila.getString(6)
            val fechan = fila.getString(7)
            val intento = Intent(this, adminView::class.java);

            intento.putExtra("matricula", matricula)
            intento.putExtra("usuario", usuario)
            intento.putExtra("password", password)
            intento.putExtra("nombre", nombre)
            intento.putExtra("apellidop", apellidop)
            intento.putExtra("apellidom", apellidom)
            intento.putExtra("genero", genero)
            intento.putExtra("fechan", fechan)
            startActivity(intento)
        } else if(filaCliente.moveToFirst()){
            val matricula = filaCliente.getString(0)
            val usuario = filaCliente.getString(1)
            val password = filaCliente.getString(2)
            val nombre = filaCliente.getString(3)
            val apellidop = filaCliente.getString(4)
            val apellidom = filaCliente.getString(5)
            val genero = filaCliente.getString(6)
            val fechan = filaCliente.getString(7)
            val intento = Intent(this, clienteView::class.java);

            intento.putExtra("matricula", matricula)
            intento.putExtra("usuario", usuario)
            intento.putExtra("password", password)
            intento.putExtra("nombre", nombre)
            intento.putExtra("apellidop", apellidop)
            intento.putExtra("apellidom", apellidom)
            intento.putExtra("genero", genero)
            intento.putExtra("fechan", fechan)
            startActivity(intento)
        }
        else {
            Toast.makeText(this,"No hubo coincidencias con este usuario: ${userTxt.text} y esa contraseña",Toast.LENGTH_SHORT).show()
        }
    }
}