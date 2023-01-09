package com.zainul.buildrrr.mainmarketing.ui.cari

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zainul.buildrrr.databinding.FragmentCariMarketingBinding
import com.zainul.buildrrr.mainmitra.ui.cari.CariViewModel

class CariFragment : Fragment() {

    private var _binding: FragmentCariMarketingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[CariViewModel::class.java]

        _binding = FragmentCariMarketingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}