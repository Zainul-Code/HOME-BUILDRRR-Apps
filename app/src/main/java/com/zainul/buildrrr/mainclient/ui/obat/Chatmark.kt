package com.zainul.buildrrr.mainclient.ui.obat

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
import com.zainul.buildrrr.databinding.ActivityChatmarkBinding
import com.zainul.buildrrr.maindev.ui.cari.DataPesan
import kotlinx.android.synthetic.main.activity_chatmark.*

class Chatmark : AppCompatActivity() {

    private lateinit var binding: ActivityChatmarkBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityChatmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.Kirim.setOnClickListener {
            uploadData()
        }

        database.child("Chat").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val pesan = it.child("text").value

                    binding.TextPesan.text = pesan.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Data Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
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

    private fun uploadData() {
        val text = binding.Pesan.text.toString()
        database = FirebaseDatabase.getInstance().getReference("Chat")
        val dataClass = DataPesan(text)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {

            binding.Pesan.text.clear()

            Toast.makeText(this, "Pesan Terkirim", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()

        }
    }


    private fun getMarketingProfile() {
        val databaseReference1 =
            FirebaseDatabase.getInstance().getReference("Data Developer")
        databaseReference1.child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                val profile1 = it.child("profile").value.toString()

                Glide.with(this).load(profile1).into(profilemarketing)
            }
    }
}