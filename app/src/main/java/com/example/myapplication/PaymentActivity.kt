package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar


class PaymentActivity: AppCompatActivity() {

    private lateinit var CarNumberEditText: EditText
    private lateinit var MonthEditText: EditText
    private lateinit var YearEditText: EditText
    private lateinit var CvvEditText: EditText
    private lateinit var submitButtonP: Button
    private lateinit var backImageView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        init()

        submitButtonP.setOnClickListener{
            var cardnumber = CarNumberEditText.text.toString()
            var month = MonthEditText.text.toString()
            var year = YearEditText.text.toString()
            var cvv = CvvEditText.text.toString()

            if (cardnumber.isEmpty() || cardnumber.length != 16 || !cardnumber.matches(".*[0-9].*".toRegex())) {
                CarNumberEditText.error = "Please write correct cardnumber"
            }
            else if (month.isEmpty() || month.length != 2 || !month.matches(".*[0-9].*".toRegex()) || !(month.toInt() < 13)){
                MonthEditText.error = "Please write correct month"
            }
            else if (year.isEmpty() || year.length != 4 || !year.matches(".*[0-9].*".toRegex())){
                YearEditText.error = "Please write correct year"
            }
            else if (cvv.isEmpty() || cvv.length != 3 || !cvv.matches(".*[0-9].*".toRegex())){
                CvvEditText.error = "Please write correct CVV"
            }
            else{
                val carName = intent.getStringExtra("car")
                Toast.makeText(this,"$carName will be delivered soon",Toast.LENGTH_SHORT).show()

                Handler().postDelayed({
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }, 2000)


        }

    }
        backImageView.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun init() {
        CarNumberEditText = findViewById(R.id.CarNumberEditText)
        MonthEditText = findViewById(R.id.MonthEditText)
        YearEditText = findViewById(R.id.YearEditText)
        CvvEditText = findViewById(R.id.CvvEditText)
        submitButtonP = findViewById(R.id.submitButtonP)
        backImageView = findViewById(R.id.backImageView)

    }
}