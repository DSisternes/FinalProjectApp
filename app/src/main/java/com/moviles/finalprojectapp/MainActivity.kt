package com.moviles.finalprojectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val perfil1 = findViewById<Button>(R.id.bt_perfil1)
        val perfil2 = findViewById<Button>(R.id.bt_perfil2)
        val perfil3 = findViewById<Button>(R.id.bt_perfil3)

        perfil1.setOnClickListener {
            irAPerfil("0")
        }
        perfil2.setOnClickListener {
            irAPerfil("1")
        }

        perfil3.setOnClickListener {
            irAPerfil("2")
        }
    }
fun irAPerfil(opcion: String) {
    val intent = Intent(this, PerfilActivity::class.java)
        .putExtra("opcion", opcion)
    startActivity(intent)
}

}


