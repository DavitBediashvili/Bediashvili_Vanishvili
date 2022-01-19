package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth

class AccountSettings : AppCompatActivity() {
    private lateinit var logoutButton: Button
    private lateinit var backTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        init()
        logout()

        backTextView.setOnClickListener{
            val intent = Intent(this, MainActivityTwo::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun logout(){
        logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun init(){
        logoutButton = findViewById(R.id.logoutButton)
        backTextView = findViewById(R.id.backTextView)

    }
}