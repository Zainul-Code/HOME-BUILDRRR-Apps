package com.zainul.buildrrr.mainmarketing.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.ActivityAdddingTxCleintBinding

class AdddingTxCleint : AppCompatActivity() {
    private lateinit var binding: ActivityAdddingTxCleintBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAdddingTxCleintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        supportActionBar?.hide()
        binding.tambahtagihan.setOnClickListener {
            uploadData()
        }
        binding.cekpembayaran.setOnClickListener {
            val home = Intent(this, ShowTagihan::class.java)
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

                    binding.nominalakumulasi.text = name1.toString()
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

    }

    private fun uploadData() {

            val nama = binding.namatagihan.text.toString()
            val nominal = binding.nominaltagihan.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Tagihan")
            val dataClass = Data(nama, nominal)
            database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {


                Toast.makeText(this, "Tagihan diTambahkan", Toast.LENGTH_SHORT).show()
                val home = Intent(this@AdddingTxCleint, AdddingTxCleint::class.java)
                startActivity(home)
                finish()
            }.addOnFailureListener {

                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


            }
    }
}