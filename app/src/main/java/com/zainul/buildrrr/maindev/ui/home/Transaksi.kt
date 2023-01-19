package com.zainul.buildrrr.maindev.ui.home

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
import com.zainul.buildrrr.databinding.ActivityTransaksiBinding
import com.zainul.buildrrr.maindev.Home
import kotlinx.android.synthetic.main.fragment_home_dev.*

class Transaksi : AppCompatActivity() {

    private lateinit var binding: ActivityTransaksiBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference


        binding.back.setOnClickListener {
            val home = Intent(this, Home::class.java)
            startActivity(home)
        }
        database.child("Data Marketing").child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("datanama").value

                    binding.namamar.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        getMarketingProfile()

    }

    private fun getMarketingProfile() {
        val databaseReference1 =
            FirebaseDatabase.getInstance().getReference("Data Marketing")
        databaseReference1.child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get()
            .addOnSuccessListener {
                val profile1 = it.child("profile").value.toString()

                Glide.with(this).load(profile1).into(profilemarketing)
            }
    }
}