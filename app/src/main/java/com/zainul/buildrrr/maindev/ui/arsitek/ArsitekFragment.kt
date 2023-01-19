package com.zainul.buildrrr.maindev.ui.arsitek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.databinding.FragmentArsitekDevBinding
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.R
import com.zainul.buildrrr.maindev.Home
import com.zainul.buildrrr.maindev.ui.home.DataClassPost
import com.zainul.buildrrr.maindev.ui.personal.PodukSaya

class ArsitekFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private var imageURL: String? = null
    private var uri: Uri? = null
    private var _binding: FragmentArsitekDevBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArsitekDevBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.image.setImageURI(uri)
            } else {
                Toast.makeText(activity, "Tidak ada Gambar Terpilih", Toast.LENGTH_SHORT).show()

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
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setCancelable(false)
        builder?.setView(R.layout.progress_layout)
        val dialog = builder?.create()
        dialog?.show()
        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageURL = urlImage.toString()
            dialog?.dismiss()
            uploadData()
        }.addOnFailureListener {
            dialog?.dismiss()

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

            Toast.makeText(activity, "Succes Posting", Toast.LENGTH_SHORT).show()
            val home = Intent(activity, PodukSaya::class.java)
            startActivity(home)
            activity?.finish()
        }.addOnFailureListener {

            Toast.makeText(activity, "Gagal", Toast.LENGTH_SHORT).show()


        }

    }
}
