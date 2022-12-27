package com.zainul.buildrrr.mainhome.ui.dokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zainul.buildrrr.mainhome.ui.dokter.DokterViewModel
import com.zainul.buildrrr.databinding.FragmentDokterBinding

class DokterFragment : Fragment() {

    private var _binding: FragmentDokterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DokterViewModel::class.java)

        _binding = FragmentDokterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}