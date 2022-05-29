package com.moviles.finalprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val database = Firebase.database
        val myRef = database.reference

        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvNickname = findViewById<TextView>(R.id.tv_nickname)
        val tvLevel = findViewById<TextView>(R.id.tv_level)

        val opcion = intent.getStringExtra("opcion")

        myRef.child("user").child(opcion.toString()).get().addOnSuccessListener {

            val gson = Gson()
            val json = gson.toJson(it.value)
            val user : User = gson.fromJson(json, User::class.java)

            tvName.text = user.name
            tvNickname.text = user.nickname
            tvLevel.text = user.level

            Log.d("RESULTADO: ", user.wishlist[0].toString())
        }.addOnFailureListener{
            Log.d("ERROR", "error")
        }

    }
}