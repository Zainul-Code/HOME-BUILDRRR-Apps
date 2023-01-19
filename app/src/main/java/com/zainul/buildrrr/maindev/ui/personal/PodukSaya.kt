package com.zainul.buildrrr.maindev.ui.personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.ActivityPodukSayaBinding
import com.zainul.buildrrr.maindev.Home
import kotlinx.android.synthetic.main.activity_poduk_saya.*
import kotlinx.android.synthetic.main.fragment_personal_dev.view.*

class PodukSaya : AppCompatActivity() {

    private lateinit var binding: ActivityPodukSayaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityPodukSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.ImagePost.setOnClickListener {
            val detail = Intent(this, DetailPost::class.java)
            startActivity(detail)
        }
        binding.imageView32.setOnClickListener {
            val home = Intent(this@PodukSaya, Home::class.java)
            startActivity(home)
        }
            database.child("Postingan Developer").child(auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val bunga = it.child("inpbunga").value

                        binding.Persen.text = bunga.toString()
                    }
                    Log.i("firebase", "Data Ditemukan ${it.value}")
                }.addOnFailureListener {
                    Log.e("firebase", "Gagal Memuat Data")
        }
            database.child("Postingan Developer").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val tipe = it.child("inptipe").value

                    binding.TipeRumah.text = tipe.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
            database.child("Data Developer").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val namaperusahhan = it.child("datanama").value

                    binding.NamaPerusahaan.text = namaperusahhan.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
            database.child("Postingan Developer").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val alamat = it.child("inplokasi").value

                    binding.Alamat.text = alamat.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
       }
            database.child("Postingan Developer").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val harga = it.child("inpharga").value

                    binding.Harga.text = harga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        getImage()
    }

    private fun getImage() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Postingan Developer")
        databaseReference.child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val image = it.child("image").value.toString()

                Glide.with(this).load(image).into(ImagePost)
            }
    }
}
