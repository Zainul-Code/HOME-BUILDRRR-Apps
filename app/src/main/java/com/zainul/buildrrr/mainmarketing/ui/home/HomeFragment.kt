package com.zainul.buildrrr.mainmarketing.ui.home

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
import com.zainul.buildrrr.databinding.FragmentHomeMarketingBinding
import kotlinx.android.synthetic.main.fragment_home_marketing.*
import kotlinx.android.synthetic.main.fragment_home_marketing.profilemarketing

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeMarketingBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeMarketingBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.profilclient.setOnClickListener {
            val detail = Intent(activity, TransaksiClient::class.java)
            startActivity(detail)
        }
        database.child("Data Marketing").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("datanama").value

                    binding.namamar.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
            getMarketingProfile()

        database.child("Data Marketing").child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get().addOnSuccessListener {
            if (it.exists()) {
                val name1 = it.child("datanama").value

                binding.namacleint.text = name1.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        getCleintProfile()

    }

    private fun getCleintProfile() {
        val databaseReference1 =
            FirebaseDatabase.getInstance().getReference("Data Marketing")
        databaseReference1.child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get()
            .addOnSuccessListener {
                val profile1 = it.child("profile").value.toString()

                Glide.with(this).load(profile1).into(profilclient)
            }
    }

    private fun getMarketingProfile() {
        val databaseReference1 =
            FirebaseDatabase.getInstance().getReference("Data Marketing")
        databaseReference1.child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val profile1 = it.child("profile").value.toString()

                Glide.with(this).load(profile1).into(profilemarketing)
            }
    }
}