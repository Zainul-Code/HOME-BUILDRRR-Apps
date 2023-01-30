package com.zainul.buildrrr.mainmarketing.ui.arsitek

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.ActivityProgres2Binding

class Progres : AppCompatActivity() {

    private lateinit var binding: ActivityProgres2Binding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProgres2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.tambahprogress.setOnClickListener {
            val home = Intent(this, AddProgres::class.java)
            startActivity(home)
        }
        database.child("Client").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get().addOnSuccessListener {
            if (it.exists()) {
                val name1 = it.child("email").value

                binding.namamar.text = name1.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val tipe = it.child("inptipe").value

                    binding.tiperumah.text = tipe.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
    }
}