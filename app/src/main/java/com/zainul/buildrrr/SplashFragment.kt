package com.zainul.buildrrr

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FirebaseAuth.getInstance().getCurrentUser()
        val user = FirebaseAuth.getInstance().currentUser
        Handler().postDelayed({
            //kondisi if masih belum aktif function onboardingfinsih belum valid
            if (user != null) {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
             //sementara ptoses yang pertama kali ter eksesuksi adalah kondisi else
            } else {
                findNavController().navigate(R.id.action_Daftar_to_PickRoleLogin)
            }
        }, 6000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onDetach() {
        super.onDetach()
 }
}