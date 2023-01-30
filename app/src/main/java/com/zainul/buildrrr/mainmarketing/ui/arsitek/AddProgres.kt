package com.zainul.buildrrr.mainmarketing.ui.arsitek

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.ActivityAddProgresBinding
import com.zainul.buildrrr.mainmarketing.Home

class AddProgres : AppCompatActivity() {

    private lateinit var binding: ActivityAddProgresBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference
    var imageURL: String? = null
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAddProgresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.tambahprogress.setOnClickListener {

        }
        database.child("Client").child("dsS4LICJtUYjaefi3HVuyaTxjem2").get().addOnSuccessListener {
            if (it.exists()) {
                val name1 = it.child("email").value

                binding.namamar.text = name1.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
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

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.imageprogres.setImageURI(uri)
            } else {
                Toast.makeText(this@AddProgres, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT).show()

            }
        }
        binding.imageView18.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/a"
            activityResultLauncher.launch(photoPicker)
        }
        //hanya 1 fungsi yang terpanggil
        binding.tambahprogress.setOnClickListener {
            saveData()
        }
    }
    // fungsi save data 1 dan 2 harus di gabungkan agar hanya 1 fungsi yang di panggil
    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("Progres")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this@AddProgres)
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

        val nama = binding.namaprogres.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Progres")
        val dataClass = DataProgres(nama,imageURL)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {


            Toast.makeText(this, "Progres Terkirim", Toast.LENGTH_SHORT).show()
            val home = Intent(this@AddProgres, Home::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }
}
