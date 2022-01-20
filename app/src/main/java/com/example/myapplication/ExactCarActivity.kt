package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBar

class ExactCarActivity : AppCompatActivity() {

    private lateinit var CarImageViewActivity: ImageView
    private lateinit var CarImageView2: ImageView
    private lateinit var CarImageView3: ImageView
    private lateinit var CarNameActivity: TextView
    private lateinit var CarNameActivity2: TextView
    private lateinit var CarNameActivity3: TextView
    private lateinit var CarInfoSedanTextView: TextView
    private lateinit var CarInfoSUVTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exactcar)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        init()

        val bundle: Bundle?= intent.extras
        val name = bundle!!.getString("name")
        val imageId = bundle.getInt("imageId")
        val exactCarSedanImage = bundle.getInt("exactCarSedanImage")
        val exactCarSUVImage = bundle.getInt("exactCarSUVImage")
        val exactCarSedan = bundle.getString("exactCarSedan")
        val exactCarSUV = bundle.getString("exactCarSUV")
        val exactCarSedanPrice = bundle.getString("exactCarSedanPrice")
        val exactCarSUVPrice = bundle.getString("exactCarSUVPrice")




        CarNameActivity2.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("car","$exactCarSedan")
            intent.putExtra("carPhoto","$exactCarSedanImage")
            startActivity(intent)
            finish()
        }

        CarNameActivity3.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("car","$exactCarSUV")
            intent.putExtra("carPhoto","$exactCarSUVImage")

            startActivity(intent)
            finish()
        }







        CarNameActivity.text = name
        CarNameActivity2.text = exactCarSedan
        CarNameActivity3.text = exactCarSUV
        CarInfoSedanTextView.text = exactCarSedanPrice
        CarInfoSUVTextView.text = exactCarSUVPrice
        CarImageViewActivity.setImageResource(imageId)
        CarImageView2.setImageResource(exactCarSedanImage)
        CarImageView3.setImageResource(exactCarSUVImage)

    }

    private fun init(){
        CarImageViewActivity = findViewById(R.id.CarImageViewActivity)
        CarImageView2 = findViewById(R.id.CarImageView2)
        CarImageView3 = findViewById(R.id.CarImageView3)
        CarNameActivity = findViewById(R.id.CarNameActivity)
        CarNameActivity2 = findViewById(R.id.CarNameActivity2)
        CarNameActivity3 = findViewById(R.id.CarNameActivity3)
        CarInfoSedanTextView = findViewById(R.id.CarInfoSedanTextView)
        CarInfoSUVTextView = findViewById(R.id.CarInfoSUVTextView)

    }

}