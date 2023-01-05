package com.zainul.buildrrr.mainmarketing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.ActivityUploadMarketingBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadMarketingBinding
    private lateinit var database: DatabaseReference
    var imageURL: String? = null
    var uri: Uri? = null
    var imageURL2: String? = null
    var uri2: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityUploadMarketingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.uploadImage.setImageURI(uri)
            } else {
                Toast.makeText(this@UploadActivity, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT).show()
            }
        }
            val activityResultLauncher1 = registerForActivityResult<Intent, ActivityResult>(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data1 = result.data
                    uri2 = data1!!.data
                    binding.uploadImage2.setImageURI(uri2)
                } else {
                    Toast.makeText(this@UploadActivity, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT)
                        .show()
            }
        }
        binding.uploadImage2.setOnClickListener {
            val photoPicker1 = Intent(Intent.ACTION_PICK)
            photoPicker1.type = "image/a"
            activityResultLauncher1.launch(photoPicker1)
        }
        binding.uploadImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/b"
            activityResultLauncher.launch(photoPicker)
        }
        //hanya 1 fungsi yang terpanggil
        binding.send.setOnClickListener {
            saveData()
        }
    }
    // fungsi save data 1 dan 2 harus di gabungkan agar hanya 1 fungsi yang di panggil
    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("PROOF SIUP")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this@UploadActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()
        storageReference.putFile(uri2!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageURL2 = urlImage.toString()
            uploadData()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
        }
        val storageReference1 = FirebaseStorage.getInstance().reference.child("KTP")
            .child(uri2!!.lastPathSegment!!)
        val builder1 = AlertDialog.Builder(this@UploadActivity)
        builder1.setCancelable(false)
        builder1.setView(R.layout.progress_layout)
        val dialog1 = builder1.create()
        dialog1.show()
        storageReference1.putFile(uri!!).addOnSuccessListener { taskSnapshot1 ->
                val uriTask1 = taskSnapshot1.storage.downloadUrl
                while (!uriTask1.isComplete);
                val urlImage1 = uriTask1.result
                imageURL = urlImage1.toString()
                uploadData()
                dialog.dismiss()
            }.addOnFailureListener {
                dialog.dismiss()
            }
    }
    private fun uploadData(){
        val title = binding.uploadnama.text.toString()
        val desc = binding.uploademail.text.toString().trim()
        database = FirebaseDatabase.getInstance().getReference("Data Marketing")
        val dataClass = DataClass(title, desc, imageURL, imageURL2)
        database.child(title).setValue(dataClass).addOnSuccessListener {

            binding.uploadnama.text.clear()
            binding.uploademail.text.clear()

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
            val home = Intent(this@UploadActivity, Home::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }
}
