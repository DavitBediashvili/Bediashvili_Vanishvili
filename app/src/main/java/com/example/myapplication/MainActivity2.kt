package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val controller = findNavController(R.id.nav_host_fragment)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.profileFragment,
                R.id.carLogoFragment

            )
        )

        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)
    }

}
