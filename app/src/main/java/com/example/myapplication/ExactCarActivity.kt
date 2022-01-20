package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class ExactCarActivity : AppCompatActivity() {

    private lateinit var CarImageViewActivity: ImageView
    private lateinit var CarNameActivity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exact_car)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        CarImageViewActivity = findViewById(R.id.CarImageViewActivity)
        CarNameActivity = findViewById(R.id.CarNameActivity)


        val bundle: Bundle?= intent.extras
        val name = bundle!!.getString("name")
        val imageId = bundle.getInt("imageId")

        CarNameActivity.text = name
        CarImageViewActivity.setImageResource(imageId)

    }
}