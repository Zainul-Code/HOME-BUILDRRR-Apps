package com.zainul.buildrrr.mainclient.ui.obat

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
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.ActivityBayarBinding
import com.zainul.buildrrr.mainclient.Home
import kotlinx.android.synthetic.main.activity_bayar.*


class Bayar : AppCompatActivity() {

    private lateinit var binding: ActivityBayarBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference
    var imageURL: String? = null
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityBayarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

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


        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.imageView15.setImageURI(uri)
            } else {
                Toast.makeText(this@Bayar, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT).show()

            }
        }
        binding.upload.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/a"
            activityResultLauncher.launch(photoPicker)
        }
        //hanya 1 fungsi yang terpanggil
        binding.kirim.setOnClickListener {
            saveData()
        }
    }
    // fungsi save data 1 dan 2 harus di gabungkan agar hanya 1 fungsi yang di panggil
    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("Bukti Pembayaran")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this@Bayar)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()
        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageURL = urlImage.toString()
            dialog.dismiss()
            uploadData()
        }.addOnFailureListener {
            dialog.dismiss()

        }
    }

    private fun uploadData() {


        database = FirebaseDatabase.getInstance().getReference("Bukti Pembayaran")
        val dataClass = DataImage(imageURL)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {


            Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show()
            val home = Intent(this@Bayar, Home::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }
}