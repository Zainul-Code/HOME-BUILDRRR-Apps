package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.ActivityPickRoleUserBinding
import kotlinx.android.synthetic.main.activity_pick_role_user.view.*

class PickRoleUser : Fragment(R.layout.activity_pick_role_user) {
    private var _binding: ActivityPickRoleUserBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityPickRoleUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.Role1.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_Regisclient)
        }
        view.Role2.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_RegisDeveloper)
        }
        view.Role3.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_RegisMitra)
        }
        view.bawahMasuk.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_PickRoleLogin)
        }
    }
}
