package com.zainul.buildrrr.mainmitra.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.databinding.FragmentHomeMitraBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeMitraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeMitraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
