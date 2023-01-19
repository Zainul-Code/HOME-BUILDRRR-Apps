package com.zainul.buildrrr.maindev

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
import com.zainul.buildrrr.databinding.ActivityUpload2Binding
import com.zainul.buildrrr.loading.Loadingdevlogin

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpload2Binding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    var imageURL: String? = null
    var uri: Uri? = null
    var imageURL2: String? = null
    var uri2: Uri? = null
    var uri3: Uri? = null
    var profile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityUpload2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

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
        val activityResultLauncher3 = registerForActivityResult<Intent, ActivityResult>(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data3 = result.data
                    uri3 = data3!!.data
                    binding.profile.setImageURI(uri3)
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
        binding.profile.setOnClickListener {
            val photoPicker3 = Intent(Intent.ACTION_PICK)
            photoPicker3.type = "image/c"
            activityResultLauncher3.launch(photoPicker3)
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
            dialog.dismiss()
            uploadData()
        }.addOnFailureListener {
            dialog.dismiss()
        }

        val storageReference1 = FirebaseStorage.getInstance().reference.child("KTP")
            .child(uri2!!.lastPathSegment!!)
        storageReference1.putFile(uri!!).addOnSuccessListener { taskSnapshot1 ->
            val uriTask1 = taskSnapshot1.storage.downloadUrl
            while (!uriTask1.isComplete);
            val urlImage1 = uriTask1.result
            imageURL = urlImage1.toString()
            dialog.dismiss()
            uploadData()
        }.addOnFailureListener {
            dialog.dismiss()
        }
        val storageReference3 = FirebaseStorage.getInstance().reference.child("Profile")
            .child(uri2!!.lastPathSegment!!)
        storageReference3.putFile(uri3!!).addOnSuccessListener { taskSnapshot1 ->
            val uriTask3 = taskSnapshot1.storage.downloadUrl
            while (!uriTask3.isComplete);
            val urlImage3 = uriTask3.result
            profile = urlImage3.toString()
            dialog.dismiss()
            uploadData()
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun uploadData(){
        val title = binding.uploadnama.text.toString()
        val desc = binding.uploademail.text.toString().trim()
        database = FirebaseDatabase.getInstance().getReference("Data Developer")
        val dataClass = DataClass(title, desc, imageURL, imageURL2, profile)
        database.child(auth.currentUser!!.uid).setValue(dataClass).addOnSuccessListener {

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
            val home = Intent(this@UploadActivity, Home::class.java)
            startActivity(home)
            finish()
        }.addOnFailureListener {

            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()

        }
    }
}
