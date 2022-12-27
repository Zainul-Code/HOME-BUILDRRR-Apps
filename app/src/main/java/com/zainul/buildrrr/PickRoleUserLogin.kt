package com.zainul.buildrrr


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.ActivityPickRoleUserLoginBinding
import kotlinx.android.synthetic.main.activity_pick_role_user.view.*
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.*
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role1
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role2
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role3

class PickRoleUserLogin : Fragment(R.layout.activity_pick_role_user_login) {
    private var _binding: ActivityPickRoleUserLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityPickRoleUserLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.Role1.setOnClickListener {
            findNavController().navigate(R.id.action_masuk_to_loginclient)
        }
        view.Role2.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_LoginDeveloper)
        }
        view.Role3.setOnClickListener {
            findNavController().navigate(R.id.action_masuk_to_loginmitra)

        }
        view.bawahdaftar.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_PickRole)
        }
    }
}