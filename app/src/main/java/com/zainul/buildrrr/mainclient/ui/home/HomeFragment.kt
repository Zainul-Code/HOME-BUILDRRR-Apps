package com.zainul.buildrrr.mainclient.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.FragmentHomeBinding
import com.zainul.buildrrr.mainclient.ui.obat.Chatmark
import com.zainul.buildrrr.mainclient.ui.obat.Pembayaran


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.keranjang.setOnClickListener {
            val home = Intent(activity, Pembayaran::class.java)
            startActivity(home)
        }
        database.child("Client").child(auth.currentUser!!.uid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("email").value

                binding.email.text = name.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
    }
}