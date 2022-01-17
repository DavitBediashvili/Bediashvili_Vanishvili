package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val controller = findNavController(R.id.nav_host_fragment)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.notificationsFragment,
                R.id.profileFragment,
                R.id.searchFragment
            )
        )
        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)
    }

}