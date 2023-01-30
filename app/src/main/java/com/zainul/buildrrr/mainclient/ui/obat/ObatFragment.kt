package com.zainul.buildrrr.mainclient.ui.obat

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
import com.zainul.buildrrr.databinding.FragmentObatBinding
import kotlinx.android.synthetic.main.fragment_obat.*

class ObatFragment : Fragment() {

    private var _binding: FragmentObatBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var database: DatabaseReference

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentObatBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        database = Firebase.database.reference

        binding.ImagePost.setOnClickListener {
            val detail = Intent(activity, Detail::class.java)
            startActivity(detail)
        }
        binding.ImagePost1.setOnClickListener {
            val detail = Intent(activity, Detail2::class.java)
            startActivity(detail)
        }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val bunga = it.child("inpbunga").value

                    binding.Persen.text = bunga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val tipe = it.child("inptipe").value

                    binding.TipeRumah.text = tipe.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
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
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val alamat = it.child("inplokasi").value

                    binding.Alamat.text = alamat.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val harga = it.child("inpharga").value

                    binding.Harga.text = harga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        getImage()

        database.child("Postingan Developer").child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val bunga = it.child("inpbunga").value

                    binding.Persen1.text = bunga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val tipe = it.child("inptipe").value

                    binding.TipeRumah1.text = tipe.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Data Developer").child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val namaperusahhan = it.child("datanama").value

                    binding.NamaPerusahaan1.text = namaperusahhan.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val alamat = it.child("inplokasi").value

                    binding.Alamat1.text = alamat.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        database.child("Postingan Developer").child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val harga = it.child("inpharga").value

                    binding.Harga1.text = harga.toString()
                }
                Log.i("firebase", "Data Ditemukan ${it.value}")
            }.addOnFailureListener {
                Log.e("firebase", "Gagal Memuat Data")
            }
        getImage2()
    }

    private fun getImage() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Postingan Developer")
        databaseReference.child("izRSoeXxJ5W1vbXkrdHk7OQXofu1").get()
            .addOnSuccessListener {
                val image1 = it.child("image").value.toString()

                Glide.with(this).load(image1).into(ImagePost)
            }
    }

    private fun getImage2() {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Postingan Developer")
        databaseReference.child("ImGpdRmwz5OOXqWFdpIsxg5RdKZ2").get()
            .addOnSuccessListener {
                val image = it.child("image").value.toString()

                Glide.with(this).load(image).into(ImagePost1)
            }
    }
}