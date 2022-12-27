package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainul.buildrrr.databinding.FragmentLocationBinding
import com.zainul.buildrrr.mainhome.Home
import kotlinx.android.synthetic.main.fragment_location.*

class LocationRead : AppCompatActivity() {
    private lateinit var binding: FragmentLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Mulai.setOnClickListener {
            val intent = Intent(this, Loading::class.java)
            startActivity(intent)
        }
    }
}