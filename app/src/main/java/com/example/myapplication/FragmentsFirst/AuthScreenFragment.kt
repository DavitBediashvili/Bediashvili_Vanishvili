package com.example.exam.Fragments

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.FragmentsFirst.FirstPageFragmentDirections
import com.example.myapplication.MainActivityTwo
import com.example.myapplication.R
import com.example.myapplication.ResetPasswordDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthScreenFragment: Fragment (R.layout.fragment_authscreen) {
    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var authorizationButton: Button
    private lateinit var passwordReset: TextView
    private lateinit var fromAuthScreenToRegScreen: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextEmailAddress = view.findViewById(R.id.editTextEmailAddress)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        authorizationButton = view.findViewById(R.id.authorizationButton)
        passwordReset = view.findViewById(R.id.passwordReset)
        fromAuthScreenToRegScreen = view.findViewById(R.id.fromAuthScreenToRegScreen)

        val controller = Navigation.findNavController(view)

        fromAuthScreenToRegScreen.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val action3 = AuthScreenFragmentDirections.actionAuthscreeFragmentToRegistrationscreenFragment()
                controller.navigate(action3)

            }
        })

        authorizationButton.setOnClickListener {
            var mailInput = editTextEmailAddress.text.toString()
            var passwordInput = editTextPassword.text.toString()

            if (mailInput.isEmpty() || mailInput.length < 8 || !mailInput.contains("@")) {
                editTextEmailAddress.error = "Please write correct mail"
            } else if (passwordInput.isEmpty() || (passwordInput.length <= 9) || !(passwordInput.matches(".*[0-9].*".toRegex())) || !(passwordInput.matches(".*[a-z].*".toRegex()))) {
                editTextPassword.error = "Password should contain more than 8 symbols and numbers"
            } else
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(getActivity(), "Authorization Complete", Toast.LENGTH_SHORT).show()
                            val intent = Intent(activity, MainActivityTwo::class.java)
                            startActivity(intent)
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
                ResetPasswordDialog(requireContext()).show()

            }
        })

    }
}
