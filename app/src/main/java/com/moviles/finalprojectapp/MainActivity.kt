package com.moviles.finalprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.reference

        myRef.child("user").child("1").setValue(Perfil("David R.", "Bulbasaurior", "5", "My Little Pony Movie"))
        myRef.child("user").child("2").setValue(Perfil("Diego S.", "Simiolón", "3", "HellFire Club Membership"))
        myRef.child("user").child("3").setValue(Perfil("Harry P.", "Siegler", "4", "Clown Outfit"))
    }
}