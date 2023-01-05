package com.zainul.buildrrr.mainclient

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zainul.buildrrr.R
import com.zainul.buildrrr.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_Obat, R.id.navigation_Donasi, R.id.navigation_dokter, R.id.navigation_personal
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

override fun onBackPressed() {
    // Create the object of AlertDialog Builder class
    val builder = AlertDialog.Builder(this)

    // Set the message show for the Alert time
    builder.setMessage("Yakin nih mau keluar ?")

    // Set Alert Title
    builder.setTitle("Yah !")

    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
    builder.setCancelable(false)

    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
    builder.setPositiveButton("Yes") {
        // When the user click yes button then app will close
            dialog, which -> finish()
    }

    // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
    builder.setNegativeButton("No") {
        // If user click no then dialog box is canceled.
            dialog, which -> dialog.cancel()
    }

    // Create the Alert dialog
    val alertDialog = builder.create()
    // Show the Alert Dialog box
    alertDialog.show()
}
}