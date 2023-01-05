package com.zainul.buildrrr.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.databinding.ActivityPopupRegistMitraBinding

class PopupRegistMitra : Fragment() {


    private var _binding: ActivityPopupRegistMitraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = ActivityPopupRegistMitraBinding.inflate(inflater, container, false)
        return binding.root
    }
}