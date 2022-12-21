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
                Toast.makeText(this, "Todo correcto", Toast.LENGTH_SHORT).show()
            }
        }

        registrarBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, registerView::class.java)
            startActivity(intent)
        }
    }

    fun validarCamposVacios(): Boolean {
        if(userTxt.text.isEmpty()){
            userTxt.error = "Debes escribir tu nombre de Usuario"
            return false
        } else if(passwordTxt.text.isEmpty()){
            passwordTxt.error = "Debes escribir tu Constraseña"
            return false
        } else {
            return true
        }
    }
}