package com.zainul.buildrrr.mainclient.ui.obat

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
import com.zainul.buildrrr.databinding.ActivityPemesananBinding
import com.zainul.buildrrr.mainclient.Home
import kotlinx.android.synthetic.main.activity_pemesanan.ImagePost

class Pemesanan : AppCompatActivity() {

    private lateinit var binding: ActivityPemesananBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.imageView32.setOnClickListener {
            val home = Intent(this@Pemesanan, Home::class.java)
            startActivity(home)
        }
        binding.pesanskrng.setOnClickListener {
            uploadData()
        }
        binding.chat.setOnClickListener {
            val home = Intent(this@Pemesanan, Chatmark::class.java)
            startActivity(home)
        }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val bunga = it.child("inpbunga").value

                    binding.Persen.text = bunga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val tipe = it.child("inptipe").value

                    binding.TipeRumah.text = tipe.toString()
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
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val alamat = it.child("inplokasi").value

                    binding.Alamat.text = alamat.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
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

    private fun uploadData() {
        val cicilan = binding.checkBox.text.toString()
        val uangmuka = binding.checkBox02.text.toString()
        val inptgl = binding.survey.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Pemesanan")
        val dataClass = Data(cicilan, uangmuka, inptgl)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {


            Toast.makeText(this, "Pesanan Berhasil", Toast.LENGTH_SHORT).show()
            val home = Intent(this@Pemesanan, TxSucces::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }

    private fun getImage() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Postingan Developer")
        databaseReference.child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                val image1 = it.child("image").value.toString()

                Glide.with(this).load(image1).into(ImagePost)
            }
    }
}