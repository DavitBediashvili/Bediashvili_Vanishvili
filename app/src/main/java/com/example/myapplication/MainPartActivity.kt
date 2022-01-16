package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainPartBinding

class MainPartActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                textView.setText("HOME")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                textView.setText("SEARCH")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add_post -> {
                textView.setText("ADD_POST")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                textView.setText("NOTIFICATIONS")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                textView.setText("PROFILE")
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_part)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        textView = findViewById(R.id.massage)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



    }
}