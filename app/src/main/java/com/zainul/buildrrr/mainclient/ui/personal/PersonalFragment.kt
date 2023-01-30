package com.zainul.buildrrr.mainclient.ui.personal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.FragmentPersonalBinding
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.maindev.ui.personal.PodukSaya
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.fragment_personal.view.*

class PersonalFragment : Fragment() {


    private var _binding: FragmentPersonalBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        view.Produksaya.setOnClickListener {
            val produksaya = Intent(activity, PodukSaya::class.java)
            startActivity(produksaya)//
        }

        database.child("Client").child(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name = it.child("email").value

                    binding.NamaPersonal.text = name.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
    }
    }