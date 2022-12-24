package com.javiermejia.empleoapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_view.*
import kotlinx.android.synthetic.main.activity_register_view.view.*

class registerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_view)

        // Spinner
        val lstUsuarios = arrayOf("Cliente","Administrador")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,lstUsuarios)
        userTypeSpn.adapter = adapter

        // Actions Buttons
        fechaRegTxt.setOnClickListener{ showDatePickerDialog() }
        guardarRegBtn.setOnClickListener{
            validarCampos()
            if(validarCampos()==true){
                if(userTypeSpn.selectedItem.toString() =="Administrador"){
                    GuardarAdministrador()
                } else {
                    GuardarCliente()
                }
            } else {
                 Toast.makeText(this,"Verifica los campos vacios",Toast.LENGTH_SHORT).show()
            }
        }
        cancelarRegBtn.setOnClickListener{ finish() }
        limpiarRegBtn.setOnClickListener{ limpiarCampos() }
    }

    // Validación de campos
    fun validarCampos(): Boolean {
        var validador = false
        if(TextUtils.isEmpty(userRegTxt.text.toString())){
            userRegTxt.error = "Usuario esta vacio"
            validador = false
        } else if (TextUtils.isEmpty(passwordRegTxt.text.toString())){
            passwordRegTxt.error = "Password esta vacio"
            validador = false
        } else if (TextUtils.isEmpty(nameRegTxt.text.toString())){
            nameRegTxt.error = "Nombre esta vacio"
            validador = false
        } else if (TextUtils.isEmpty(apellidoPaternoRegTxt.text.toString())){
            apellidoPaternoRegTxt.error = "Apellido Paterno esta vacio"
            validador = false
        } else if (TextUtils.isEmpty(apellidoMaternoRegTxt.text.toString())) {
            apellidoMaternoRegTxt.error = "Apellido Materno esta vacio"
            validador = false
        } else if (hombreRRegBtn.isChecked == false && mujerRRegBtn.isChecked == false) {
            hombreRRegBtn.error = "*"
            hombreRRegBtn.error = "*"
            Toast.makeText(this, "Debes seleccionar un genero", Toast.LENGTH_SHORT).show()
            validador = false
        } else if (TextUtils.isEmpty(fechaRegTxt.text.toString())){
            fechaRegTxt.error = "Debes seleccionar una fecha"
            validador = false
        } else {
            validador = true
        }
        return validador
    }

    // Función para obtener el genero
    fun obtenerGenero(): String{
        val gen: String
        if(hombreRRegBtn.isChecked){
            gen = "Hombre"
            return gen
        } else {
            gen = "Mujer"
            return gen
        }
    }

    // Seleccionador de fecha
    private fun showDatePickerDialog() {
        val vDatePicker = DatePickerFragment{
                day, month, year -> onDateSelected(day,month,year) }
        vDatePicker.show(supportFragmentManager,"Selector de fecha")
    }
    fun onDateSelected(vDay:Int, vMonth:Int, vYear: Int){
        var vResultado = String.format("%02d",vDay)+"-"+String.format("%02d",vMonth+1)+"-"+vYear
        fechaRegTxt.setText(vResultado)
    }

    fun GenerarMatricula(): String{
        // Datos necesarios para la matricula
        val name = nameRegTxt.text?.subSequence(0,2)
        val apeP = apellidoPaternoRegTxt.text?.subSequence(0, 2)
        val apeM = apellidoMaternoRegTxt.text?.subSequence(0, 1)
        val gen = obtenerGenero().subSequence(0,1)
        // Fecha de nacimiento
        val vDay = fechaRegTxt.text?.subSequence(0, 2)
        val vMonth = fechaRegTxt.text?.subSequence(3,5)
        val vYear = fechaRegTxt.text?.subSequence(6, 10)

        // Matricula final
        val matricula ="$apeP$apeM$name$gen$vDay$vMonth$vYear"
        return matricula.toUpperCase()
    }
    // Funcion Limpiar Campos
    fun limpiarCampos(){
        userRegTxt.setText("")
        passwordRegTxt.setText("")
        nameRegTxt.setText("")
        apellidoPaternoRegTxt.setText("")
        apellidoMaternoRegTxt.setText("")
        fechaRegTxt.setText("")
        hombreRRegBtn.isChecked = false
        mujerRRegBtn.isChecked = false
        Toast.makeText(this,"Selecciona de nuevo el genero",Toast.LENGTH_SHORT).show()
    }

    // Funciones Guardar Usuarios
    fun GuardarAdministrador(){
        val admin = AdminSQLiteOpenHelper(this, "Administracion",null,1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        val matricula = GenerarMatricula()
        registro.put("matricula", matricula)
        registro.put("usuario",userRegTxt.text.toString())
        registro.put("password",passwordRegTxt.text.toString())
        registro.put("nombre",nameRegTxt.text.toString())
        registro.put("apellidop",apellidoPaternoRegTxt.text.toString())
        registro.put("apellidom",apellidoMaternoRegTxt.text.toString())
        registro.put("genero", obtenerGenero())
        registro.put("fechan",fechaRegTxt.text.toString())
        bd.insert("administradores",null,registro)
        bd.close()
        Toast.makeText(this,"Se insertó correctamente el administrador", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun GuardarCliente(){
        val cliente = AdminSQLiteOpenHelper(this, "Administracion",null,1)
        val bd = cliente.writableDatabase
        val registro = ContentValues()
        val matricula = GenerarMatricula()
        registro.put("matricula", matricula)
        registro.put("usuario",userRegTxt.text.toString())
        registro.put("password",passwordRegTxt.text.toString())
        registro.put("nombre",nameRegTxt.text.toString())
        registro.put("apellidop",apellidoPaternoRegTxt.text.toString())
        registro.put("apellidom",apellidoMaternoRegTxt.text.toString())
        registro.put("genero", obtenerGenero())
        registro.put("fechan",fechaRegTxt.text.toString())
        bd.insert("clientes",null,registro)
        bd.close()
        Toast.makeText(this,"Se insertó correctamente el cliente", Toast.LENGTH_SHORT).show()
        finish()
    }
}