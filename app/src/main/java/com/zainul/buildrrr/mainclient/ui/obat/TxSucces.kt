package com.zainul.buildrrr.mainclient.ui.obat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zainul.buildrrr.databinding.ActivityTxSuccesBinding
import com.zainul.buildrrr.mainclient.Home

class TxSucces : AppCompatActivity() {

    private lateinit var binding: ActivityTxSuccesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTxSuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener {
            val home = Intent(this@TxSucces, Home::class.java)
            startActivity(home)
        }
        binding.riwayat.setOnClickListener {
            val home = Intent(this@TxSucces, Pembayaran::class.java)
            startActivity(home)
        }
    }
}