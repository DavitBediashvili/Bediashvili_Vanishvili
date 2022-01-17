package com.example.myapplication

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPasswordDialog(context: Context): Dialog(context) {
    private lateinit var submitButtonRP: Button
    private lateinit var editTextEmailAddressDialog: EditText

    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resetpassword_dialog)
        submitButtonRP = findViewById(R.id.submitButtonRP)
        editTextEmailAddressDialog = findViewById(R.id.editTextEmailAddressDialog)

        submitButtonRP.setOnClickListener{
                var mailinput = editTextEmailAddressDialog.text.toString()
                Firebase.auth.sendPasswordResetEmail(mailinput)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "Email Sent")
                            dismiss()
                        } else
                            dismiss()
                    }

        }


    }
}