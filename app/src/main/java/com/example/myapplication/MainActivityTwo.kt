package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.FragmentsSecond.HomeFragment
import com.example.myapplication.FragmentsSecond.NotificationsFragment
import com.example.myapplication.FragmentsSecond.ProfileFragment
import com.example.myapplication.FragmentsSecond.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityTwo : AppCompatActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                moveToFragments(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                moveToFragments(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_add_post -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                moveToFragments(NotificationsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                moveToFragments(ProfileFragment())
                return@OnNavigationItemSelectedListener true


            }
        }


        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        moveToFragments(HomeFragment())

    }


    private fun moveToFragments(fragment: Fragment){
        val fragment_tranz =  supportFragmentManager.beginTransaction()
        fragment_tranz.replace(
            R.id.fragment_container ,
            fragment

        )
        fragment_tranz.commit()


    }

}