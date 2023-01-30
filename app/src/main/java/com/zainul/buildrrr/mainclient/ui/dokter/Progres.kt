package com.zainul.buildrrr.mainclient.ui.dokter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.ActivityProgresBinding
import com.zainul.buildrrr.mainclient.ui.obat.DataImage
import kotlinx.android.synthetic.main.activity_progres.*


class Progres : AppCompatActivity() {

    private lateinit var binding: ActivityProgresBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProgresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        getImageTf()
        database.child("Progres").child("aQW7WP1f3BOc2xYiAXjCTP832g33").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val namaperusahhan = it.child("buktitf").value

                    binding.namaprogres.text = namaperusahhan.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Data Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val namaperusahhan = it.child("datanama").value

                    binding.NamaPerusahaan.text = namaperusahhan.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }

        getImage()
    }
    private fun getImageTf() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Progres")
        databaseReference.child("aQW7WP1f3BOc2xYiAXjCTP832g33").get()
            .addOnSuccessListener {
                val image1 = it.child("namaprogres").value.toString()

                Glide.with(this).load(image1).into(imageprogres)
            }
    }
    private fun getImage() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Postingan Developer")
        databaseReference.child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                val image1 = it.child("image").value.toString()

                Glide.with(this).load(image1).into(profile_image)
            }
    }
}