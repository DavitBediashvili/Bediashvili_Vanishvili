package com.example.myapplication

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TermsOfServiceDialog(context: Context): Dialog(context) {
    private lateinit var submitButtonTOS: Button

    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.termsofservice_dialog)
        submitButtonTOS = findViewById(R.id.submitButtonTOS)

        submitButtonTOS.setOnClickListener{
            dismiss()
        }



    }
}