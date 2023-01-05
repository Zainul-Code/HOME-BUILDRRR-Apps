package com.zainul.buildrrr


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.zainul.buildrrr.databinding.ActivityPickRoleUserLoginBinding
import kotlinx.android.synthetic.main.activity_pick_role_user.view.*
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.*
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role1
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role2
import kotlinx.android.synthetic.main.activity_pick_role_user_login.view.Role3
import kotlinx.android.synthetic.main.activity_popup_regis_dev.view.*
import kotlinx.android.synthetic.main.activity_popup_regist_mitra.view.*

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
            val view = View.inflate(activity, R.layout.activity_popup_regis_dev, null)

            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            view.developer.setOnClickListener {
                findNavController().navigate(R.id.action_Daftar_to_LoginDeveloper)
                dialog.dismiss()
            }
            view.marketing.setOnClickListener {
                findNavController().navigate(R.id.action_masuk_to_loginmarketing)
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
                    findNavController().navigate(R.id.action_masuk_to_loginmitra)
                    dialog.dismiss()
                }
                view.Mandor.setOnClickListener {
                    findNavController().navigate(R.id.action_masuk_to_loginmitra)
                    dialog.dismiss()
                }
            }

            view.bawahdaftar.setOnClickListener {
                findNavController().navigate(R.id.action_Daftar_to_PickRole)
            }
        }
    }