package com.zainul.buildrrr.onboarding.screens


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zainul.buildrrr.PickRoleUser
import com.zainul.buildrrr.R
import com.zainul.buildrrr.RegisterMitra
import kotlinx.android.synthetic.main.fragment_third_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        view.LoginClient2.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_PickRole)
            onBoardingFinished()
        }
        view.LoginMitra1.setOnClickListener {
            findNavController().navigate(R.id.action_Daftar_to_PickRoleLogin)
            onBoardingFinished()
        }
        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Daftar", true)
        editor.apply()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

}