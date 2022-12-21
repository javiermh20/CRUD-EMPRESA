package com.javiermejia.empleoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editar_view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_view.*
import kotlinx.android.synthetic.main.activity_register_view.view.*

class registerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_view)

        // Spinner
        val lstEstados = arrayOf("Cliente","Administrador")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,lstEstados)
        userTypeSpn.adapter = adapter

        // Actions Buttons
        fechaRegTxt.setOnClickListener{ showDatePickerDialog() }
        guardarRegBtn.setOnClickListener{
            // Tenemos que hacer todas las validaciones
            validarGenero()
            if(validarGenero()==true){
                GenerarMatricula()
            }
        }
        cancelarRegBtn.setOnClickListener{ finish() }
        limpiarRegBtn.setOnClickListener{ limpiarCampos() }
    }

    // Validacion de campos - pendiente
    /* fun validarCampos(): Boolean {
        if(userTxt.text.isEmpty()){
            userTxt.error = "Usuario esta vacio"
            return false
        } else if (passwordTxt.text.isEmpty()){
            passwordTxt.error = "Password esta vacio"
            return false
        } else if (nameRegTxt.text.isEmpty()){
            nameRegTxt.error == "Nombre esta vacio"
            return false
        } else if (apellidoPaternoRegTxt.text.isEmpty()){
            apellidoPaternoRegTxt.error = "Apellido Paterno esta vacio"
            return false
        } else if (apellidoMaternoRegTxt.text.isEmpty()) {
            apellidoPaternoRegTxt.error = "Apellido Paterno esta vacio"
            return false
        } else if (fechaRegTxt.text.isNullOrBlank()){
            fechaRegTxt.error == "Debes seleccionar una fecha"
            return false
        } else{
            return true
        }
    } */

    fun validarGenero(): Boolean {
        if (hombreRRegBtn.isChecked == false && mujerRRegBtn.isChecked == false) {
            hombreRRegBtn.error = "*"
            hombreRRegBtn.error = "*"
            Toast.makeText(this, "Debes seleccionar un genero", Toast.LENGTH_SHORT).show()
            return false
        } else{
            return true
        }
    }

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

    // DATE PICKER
    private fun showDatePickerDialog() {
        val vDatePicker = DatePickerFragment{
                day, month, year -> onDateSelected(day,month,year) }
        vDatePicker.show(supportFragmentManager,"Selector de fecha")
    }
    fun onDateSelected(vDay:Int, vMonth:Int, vYear: Int){
        var vResultado = String.format("%02d",vDay)+"-"+String.format("%02d",vMonth+1)+"-"+vYear
        fechaRegTxt.setText(vResultado)
    }

    fun GenerarMatricula(){
        val usuario = userRegTxt.text
        val pass = passwordRegTxt.text
        val name = nameRegTxt.text
        val apeP = apellidoPaternoRegTxt.text
        val apeM = apellidoMaternoRegTxt.text
        val gen = obtenerGenero()
        val fecha = fechaRegTxt.text

        Toast.makeText(this,"Esta es tu matricula: $usuario , $pass , $name , $apeP , $apeM , $gen, $fecha", Toast.LENGTH_LONG ).show()
    }

    // Funcion Limpiar
    fun limpiarCampos(){
        userRegTxt.setText("")
        passwordRegTxt.setText("")
        nameRegTxt.setText("")
        apellidoPaternoRegTxt.setText("")
        apellidoMaternoRegTxt.setText("")
        hombreRRegBtn.isSelected == false
        mujerRRegBtn.isSelected == false
        fechaRegTxt.setText("")
    }
}