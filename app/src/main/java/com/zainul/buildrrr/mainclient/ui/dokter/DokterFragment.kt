package com.zainul.buildrrr.mainclient.ui.dokter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.FragmentDokterBinding
import com.zainul.buildrrr.mainmarketing.ui.cari.ChatingClient
import kotlinx.android.synthetic.main.activity_pembayaran.*

class DokterFragment : Fragment() {

    private var _binding: FragmentDokterBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDokterBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.lihatprogres.setOnClickListener {
            val chat = Intent(activity, Progres::class.java)
            startActivity(chat)
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