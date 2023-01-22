package com.zainul.buildrrr.mainmarketing.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.FragmentPersonalMarketingBinding
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_personal_marketing.*


class PersonalFragment : Fragment() {

    private var _binding: FragmentPersonalMarketingBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalMarketingBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        database.child("Data Marketing").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name = it.child("datanama").value

                    binding.NamaPersonal.text = name.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }

        database.child("Data Marketing").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name1 = it.child("dataemail").value

                    binding.EmailPersonal.text = name1.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        getUserProfile()
    }

    private fun getUserProfile() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Data Marketing")
        databaseReference.child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val profile = it.child("profile").value.toString()

                Glide.with(this).load(profile).into(profile_image2)
            }
    }
}