package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainul.buildrrr.databinding.ActivityLocationReadClientBinding
import com.zainul.buildrrr.mainhome.Home
import kotlinx.android.synthetic.main.activity_location_read_client.view.*

class LocationReadClient : Fragment(R.layout.activity_location_read_client) {
    private var _binding: ActivityLocationReadClientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = ActivityLocationReadClientBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.Mulai.setOnClickListener {
            val intent = Intent(activity, Loading::class.java)
            startActivity(intent)
        }
    }
}