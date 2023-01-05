package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.ActivityPickRoleUserBinding
import kotlinx.android.synthetic.main.activity_pick_role_user.view.*
import kotlinx.android.synthetic.main.activity_popup_regis_dev.view.*
import kotlinx.android.synthetic.main.activity_popup_regist_mitra.view.*

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
            val view = View.inflate(activity, R.layout.activity_popup_regis_dev, null)

            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            view.developer.setOnClickListener {
                findNavController().navigate(R.id.action_Daftar_to_RegisDeveloper)
                dialog.dismiss()
            }
            view.marketing.setOnClickListener {
                findNavController().navigate(R.id.action_masuk_to_Registmarketing)
                dialog.dismiss()
            }
        }
        view.Role3.setOnClickListener {
            val view = View.inflate(activity, R.layout.activity_popup_regist_mitra, null)

            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            view.Arsitek.setOnClickListener {
                findNavController().navigate(R.id.action_Daftar_to_RegisMitra)
                dialog.dismiss()
            }
            view.Mandor.setOnClickListener {
                findNavController().navigate(R.id.action_Daftar_to_RegisMitra)
                dialog.dismiss()
            }
        }
        view.bawahMasuk.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_PickRoleLogin)
        }
    }
}
