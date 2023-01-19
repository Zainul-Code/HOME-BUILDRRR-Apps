package com.zainul.buildrrr.maindev.ui.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.FragmentHomeDevBinding
import com.zainul.buildrrr.maindev.ui.personal.DetailPost
import kotlinx.android.synthetic.main.fragment_home_dev.*
import kotlinx.android.synthetic.main.fragment_home_dev.view.*



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeDevBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeDevBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        view.addmarketing.setOnClickListener {
            val view = View.inflate(activity, R.layout.activity_addmarketing, null)

            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
        }
            binding.profilemarketing.setOnClickListener {
                val detail = Intent(activity, Transaksi::class.java)
                startActivity(detail)
        }
        binding.namamar.setOnClickListener {
                val detail = Intent(activity, Transaksi::class.java)
                startActivity(detail)
        }


        database.child("Data Developer").child(auth.currentUser!!.uid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("datanama").value

                binding.NamaPerusahaan.text = name.toString()
            }
            Log.i("firebase", "Data Ditemukan ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Gagal Memuat Data")
        }
        getUserProfile()

            database.child("Data Marketing").child("XTM0dvSxOnWC2iEyTXRxkIY3nsk2").get().addOnSuccessListener {
            if (it.exists()) {
                val name1 = it.child("datanama").value

                binding.namamar.text = name1.toString()
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

    private fun getUserProfile() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Data Developer")
        databaseReference.child(auth.currentUser!!.uid).get()
        .addOnSuccessListener {
          val profile = it.child("profile").value.toString()

            Glide.with(this).load(profile).into(profile_image)
        }
    }
}