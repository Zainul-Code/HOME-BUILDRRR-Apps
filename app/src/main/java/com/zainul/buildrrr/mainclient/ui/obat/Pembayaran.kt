package com.zainul.buildrrr.mainclient.ui.obat

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
import com.zainul.buildrrr.databinding.ActivityPembayaranBinding
import com.zainul.buildrrr.mainmarketing.ui.home.ShowTagihan
import kotlinx.android.synthetic.main.activity_pembayaran.*


class Pembayaran : AppCompatActivity() {

    private lateinit var binding: ActivityPembayaranBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.bayar.setOnClickListener {
            val home = Intent(this, Bayar::class.java)
            startActivity(home)
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
         database.child("Tagihan").child("aQW7WP1f3BOc2xYiAXjCTP832g33").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("nominal").value

                    binding.nominaltagihan.text = name1.toString()
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
            databaseReference.child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
                .addOnSuccessListener {
                    val image1 = it.child("image").value.toString()

                    Glide.with(this).load(image1).into(profile_image)
                }
    }
}