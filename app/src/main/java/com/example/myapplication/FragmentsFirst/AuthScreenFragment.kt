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
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import com.example.myapplication.ResetPasswordDialog
import com.example.myapplication.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class AuthScreenFragment: Fragment (R.layout.fragment_authscreen) {
    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var username_text: EditText
    private lateinit var authorizationButton: Button
    private lateinit var passwordReset: TextView
    private lateinit var fromAuthScreenToRegScreen: TextView
    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UsernameReadd")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        authorization()

        val controller = Navigation.findNavController(view)

        fromAuthScreenToRegScreen.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val action3 = AuthScreenFragmentDirections.actionAuthscreeFragmentToRegistrationscreenFragment()
                controller.navigate(action3)

            }
        })

        passwordReset.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                ResetPasswordDialog(requireContext()).show()

            }
        })

    }
    private fun authorization(){
        authorizationButton.setOnClickListener {
            var mailInput = editTextEmailAddress.text.toString()
            var passwordInput = editTextPassword.text.toString()
            val usernameInput = username_text.text.toString()

            if (mailInput.isEmpty() || mailInput.length < 8 || !mailInput.contains("@")) {
                editTextEmailAddress.error = "Please write correct mail"
            } else if (passwordInput.isEmpty() || (passwordInput.length <= 9) || !(passwordInput.matches(".*[0-9].*".toRegex())) || !(passwordInput.matches(".*[a-z].*".toRegex()))) {
                editTextPassword.error = "Password should contain more than 8 symbols and numbers"
            } else if (usernameInput.isEmpty() || usernameInput.length<2){
                username_text.error = "Invalid username"
            }
            else
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(getActivity(), "Authorization Complete", Toast.LENGTH_SHORT).show()
                            val userInfo = UserInfo(usernameInput)
                            dbUserInfo.child(auth.currentUser?.uid!!).setValue(userInfo)
                            val intent = Intent(activity, MainActivity2::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        } else
                            Toast.makeText(
                                getActivity(),
                                "During Authorization Problem Occurred ",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
        }
    }


    private fun init(){
        editTextEmailAddress = requireView().findViewById(R.id.editTextEmailAddress)
        editTextPassword = requireView().findViewById(R.id.editTextPassword)
        authorizationButton = requireView().findViewById(R.id.authorizationButton)
        passwordReset = requireView().findViewById(R.id.passwordReset)
        username_text = requireView().findViewById(R.id.username_text)
        fromAuthScreenToRegScreen = requireView().findViewById(R.id.fromAuthScreenToRegScreen)
        val userInfo = UserInfo(usernameRead = "")
    }
}
