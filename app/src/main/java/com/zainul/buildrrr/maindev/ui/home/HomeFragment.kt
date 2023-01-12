package com.zainul.buildrrr.maindev.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.zainul.buildrrr.databinding.FragmentHomeDevBinding
import com.zainul.buildrrr.maindev.DataClass
import kotlinx.android.synthetic.main.fragment_home_dev.*
import java.io.File


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeDevBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var user: DataClass
    private lateinit var uis : String

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

        auth= FirebaseAuth.getInstance()
        uis = auth.currentUser!!.uid

        posting.setOnClickListener{
            val intent = Intent(activity, Posting::class.java)
            startActivity(intent)
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Data Developer")
        if ( uis.isNotEmpty()) {
            getUserData()
        }
    }

    private fun getUserData() {
        databaseReference.child(uis).addValueEventListener(object : ValueEventListener {
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                  user = snapshot.getValue(DataClass::class.java)!!
                    binding.NamaPerusahaan.setText(user.datanama)
                    getUserProfile()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Gagal Menampilkan User Data", Toast.LENGTH_SHORT).show()

            }

            private fun getUserProfile() {
                storageReference =
                    FirebaseStorage.getInstance().reference.child("$uis/profile")
                val localFile = File.createTempFile("tempimage", "jpg")
                storageReference.getFile(localFile).addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    binding.profileImage.setImageBitmap(bitmap)
                }.addOnFailureListener {
                    Toast.makeText(activity, "Gagal Menampilkan Gambar", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }
}