package com.zainul.buildrrr.maindev.ui.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.ActivityPostingBinding
import com.zainul.buildrrr.maindev.DataClass
import com.zainul.buildrrr.maindev.Home

class Posting : AppCompatActivity() {
    private lateinit var binding: ActivityPostingBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    var imageURL: String? = null
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.image.setImageURI(uri)
            } else {
                Toast.makeText(this@Posting, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT).show()

            }
        }
        binding.image.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/a"
            activityResultLauncher.launch(photoPicker)
       }
        //hanya 1 fungsi yang terpanggil
        binding.send.setOnClickListener {
            saveData()
        }
    }
    // fungsi save data 1 dan 2 harus di gabungkan agar hanya 1 fungsi yang di panggil
    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("Foto Rumah")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this@Posting)
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

    private fun uploadData(){
        val tipe = binding.inptipe.text.toString()
        val lokasi = binding.inplokasi.text.toString()
         val harga = binding.inpharga.text.toString()
        val bunga = binding.inpbunga.text.toString()
        val desk = binding.inpdeskripsi.text.toString()
        val detail = binding.inpdetail.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Postingan Developer")
        val dataClass = DataClassPost(tipe, lokasi, harga, bunga, desk,detail,imageURL)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {

            binding.inptipe.text.clear()
            binding.inplokasi.text.clear()
            binding.inpharga.text.clear()
            binding.inpbunga.text.clear()
            binding.inpdeskripsi.text.clear()
            binding.inpdetail.text.clear()

            Toast.makeText(this, "Succes Posting", Toast.LENGTH_SHORT).show()
            val home = Intent(this@Posting, Home::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }
}
