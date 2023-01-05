package com.zainul.buildrrr.maindev.ui.arsitek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.databinding.FragmentArsitekDevBinding


class ArsitekFragment : Fragment() {

    private var _binding: FragmentArsitekDevBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArsitekDevBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
