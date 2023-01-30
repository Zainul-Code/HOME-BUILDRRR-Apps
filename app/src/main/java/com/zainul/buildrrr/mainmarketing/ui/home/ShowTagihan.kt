package com.zainul.buildrrr.mainmarketing.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.ActivityShowTagihanBinding
import com.zainul.buildrrr.mainmarketing.Home
import kotlinx.android.synthetic.main.activity_show_tagihan.*

class ShowTagihan : AppCompatActivity() {
    private lateinit var binding: ActivityShowTagihanBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityShowTagihanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        supportActionBar?.hide()
        binding.tambahtagihan.setOnClickListener {
            val home = Intent(this, AdddingTxCleint::class.java)
            startActivity(home)
        }
        binding.back.setOnClickListener {
            val home = Intent(this, Home::class.java)
            startActivity(home)
        }
    database.child("Client").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get()
    .addOnSuccessListener {
        if (it.exists()) {
            val name1 = it.child("email").value

            binding.namacleint.text = name1.toString()
        }
        Log.i("firebase", "Data Ditemukan ${it.value}")
    }.addOnFailureListener {
        Log.e("firebase", "Gagal Memuat Data")
    }
        database.child("Tagihan").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("nama").value

                    binding.shownamatagihan.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Tagihan").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("nominal").value

                    binding.shownominaltagihan.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
        }
        database.child("Tagihan").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("nominal").value

                    binding.nominalakumulasi.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
        }
        getImage()
    }

    private fun getImage() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Bukti Pembayaran")
        databaseReference.child("dsS4LICJtUYjaefi3HVuyaTxjem2").get()
            .addOnSuccessListener {
                val image1 = it.child("buktipembayaran").value.toString()

                Glide.with(this).load(image1).into(buktitf)
            }
    }
}