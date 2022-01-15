package com.example.exam.Fragments

import android.content.ContentValues.TAG
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthScreenFragment: Fragment (R.layout.fragment_authscreen) {
    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var authorizationButton: Button
    private lateinit var registrationPageButton: Button
    private lateinit var passwordReset: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextEmailAddress = view.findViewById(R.id.editTextEmailAddress)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        authorizationButton = view.findViewById(R.id.authorizationButton)
        registrationPageButton = view.findViewById(R.id.registrationPageButton)
        passwordReset = view.findViewById(R.id.passwordReset)

        val controller = Navigation.findNavController(view)

        registrationPageButton.setOnClickListener {

            val action =
                AuthScreenFragmentDirections.actionHomeFragmentToRegistrationscreenFragment()

            controller.navigate(action)
        }

        authorizationButton.setOnClickListener {
            var mailInput = editTextEmailAddress.text.toString()
            var passwordInput = editTextPassword.text.toString()

            if (mailInput.isEmpty() || mailInput.length < 8 || !mailInput.contains("@")) {
                editTextEmailAddress.error = "Please write correct mail"
            } else if (passwordInput.isEmpty() || (passwordInput.length <= 9) || !(passwordInput.matches(
                    ".*[0-9].*".toRegex()
                )) || !(passwordInput.matches(".*[a-z].*".toRegex()))
            ) {
                editTextPassword.error = "Please write correct password"
            } else
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                getActivity(),
                                "Authorization Complete",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else
                            Toast.makeText(
                                getActivity(),
                                "During Authorization Problem Occurred ",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
        }
        passwordReset.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (view is TextView) {
                    var mailinput = editTextEmailAddress.text.toString()
                    Firebase.auth.sendPasswordResetEmail(mailinput)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "Email Sent")
                                Toast.makeText(
                                    getActivity(),
                                    "Instructions for resetting password were sent successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else
                                Toast.makeText(getActivity(), "Problem Occurred", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        })
    }
}
