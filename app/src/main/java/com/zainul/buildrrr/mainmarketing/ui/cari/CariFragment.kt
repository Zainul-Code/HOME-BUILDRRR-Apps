package com.zainul.buildrrr.mainmarketing.ui.cari

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.databinding.FragmentCariMarketingBinding
import com.zainul.buildrrr.maindev.ui.cari.ChatingActivity
import kotlinx.android.synthetic.main.fragment_home_marketing.*

class CariFragment : Fragment() {

    private var _binding: FragmentCariMarketingBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCariMarketingBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.profilemarketing.setOnClickListener {
            val chat = Intent(activity, ChatingActivity::class.java)
            startActivity(chat)
        }
        database.child("Data Marketing").child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get().addOnSuccessListener {
            if (it.exists()) {
                val name1 = it.child("datanama").value

                binding.namamar.text = name1.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        database.child("Chat").child(auth.currentUser!!.uid).get().addOnSuccessListener {
            if (it.exists()) {
                val text = it.child("text").value

                binding.pesanterbaru.text = text.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        getMarketingProfile()

    }

    private fun getMarketingProfile() {
        val databaseReference1 =
            FirebaseDatabase.getInstance().getReference("Data Marketing")
        databaseReference1.child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get()
            .addOnSuccessListener {
                val profile1 = it.child("profile").value.toString()

                Glide.with(this).load(profile1).into(profilemarketing)
            }
    }
}