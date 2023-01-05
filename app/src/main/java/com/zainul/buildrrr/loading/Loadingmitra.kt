package com.zainul.buildrrr.loading

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.zainul.buildrrr.R
import com.zainul.buildrrr.mainmitra.UploadActivity

class Loadingmitra : AppCompatActivity() {
    private val waktu_loading = 4000

    //4000=4 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_mitra)
        Handler().postDelayed({ //setelah loading maka akan langsung berpindah ke home activity
            val home = Intent(this@Loadingmitra, UploadActivity::class.java)
            startActivity(home)
            finish()
        }, waktu_loading.toLong())
    }
}