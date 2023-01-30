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
import com.zainul.buildrrr.databinding.ActivityTransaksiClientBinding
import kotlinx.android.synthetic.main.activity_transaksi_client.*

class TransaksiClient : AppCompatActivity() {

    private lateinit var binding: ActivityTransaksiClientBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTransaksiClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

         supportActionBar?.hide()
        binding.tambahtagihan.setOnClickListener {
            val home = Intent(this, AdddingTxCleint::class.java)
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
        database.child("Pemesanan").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("cicilan").value

                    binding.datacicilan.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Pemesanan").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("uangmuka").value

                    binding.datauangmuka.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Pemesanan").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("inptgl").value

                    binding.datasurvey.text = name1.toString()
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
    }
}