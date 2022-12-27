package com.zainul.buildrrr

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.zainul.buildrrr.databinding.ActivityRegisterMitraBinding
import kotlinx.android.synthetic.main.activity_register_mitra.*


class RegisterMitra : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var _binding: ActivityRegisterMitraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = ActivityRegisterMitraBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth= FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btnRegis.setOnClickListener {
            findNavController().navigate(R.id.action_masuk_to_loginmitra)

        }
        bawahMasuk.setOnClickListener {
            findNavController().navigate(R.id.action_masuk_to_loginmitra)

        }

        binding.btnRegis.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPasword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                if(email.isEmpty()){
                    binding.editEmail.error = "Email Harus Diisi"
                    binding.editEmail.requestFocus()
                    return@setOnClickListener
                }

                //Validasi password
                if (password.isEmpty()) {
                    binding.editPasword.error = "Password Harus Diisi"
                    binding.editPasword.requestFocus()
                    return@setOnClickListener
                }

                //Validasi panjang password
                if (password.length < 6) {
                    binding.editPasword.error = "Password Minimal 6 Karakter"
                    binding.editPasword.requestFocus()
                    return@setOnClickListener
                }
                Toast.makeText(activity, "Masukan Detail Yang Valid", Toast.LENGTH_SHORT).show()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmail.error = "Email Tidak Valid"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val databaseRef =
                                database.reference.child("Mitra").child(auth.currentUser!!.uid)
                            val user : User = User(email, password, auth.currentUser!!.uid)

                            databaseRef.setValue(user).addOnCompleteListener{
                                if(it.isSuccessful){
                                    Toast.makeText(activity,"Register Berhasil" ,Toast.LENGTH_SHORT).show();
                                    findNavController().navigate(R.id.action_masuk_to_loginmitra)

                                }
                            }
                        } else {
                            Toast.makeText(
                                activity,
                                "${it.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}