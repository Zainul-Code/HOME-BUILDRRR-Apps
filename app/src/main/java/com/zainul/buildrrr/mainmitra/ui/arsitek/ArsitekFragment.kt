package com.zainul.buildrrr.mainmitra.ui.arsitek

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.R
import com.zainul.buildrrr.arsitekprogres1
import com.zainul.buildrrr.databinding.FragmentArsitekMitraBinding


class ArsitekFragment : Fragment() {

    private var _binding: FragmentArsitekMitraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fragment = inflater.inflate(R.layout.fragment_arsitek_mitra, container, false)
        var progresklien = fragment.findViewById<CardView>(R.id.progres_klien)
        progresklien.setOnClickListener{
            val intent = Intent(this@ArsitekFragment.requireContext(), arsitekprogres1::class.java)
            startActivity(intent)
        }

        return fragment

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
