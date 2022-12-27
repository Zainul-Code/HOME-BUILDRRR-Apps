package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.zainul.buildrrr.mainhome.Home


class Loading : AppCompatActivity() {
    private val waktu_loading = 4000

    //4000=4 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        Handler().postDelayed({ //setelah loading maka akan langsung berpindah ke home activity
            val home = Intent(this@Loading, Home::class.java)
            startActivity(home)
            finish()
        }, waktu_loading.toLong())
    }
}