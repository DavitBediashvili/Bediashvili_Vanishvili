package com.example.exam.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class RegistrationScreenFragment: Fragment(R.layout.fragment_registrationscreen) {
    private lateinit var registrationEmailAddress: EditText
    private lateinit var registrationPassword: EditText
    private lateinit var registrationButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationEmailAddress = view.findViewById(R.id.registrationEmailAddress)
        registrationPassword = view.findViewById(R.id.registrationPassword)
        registrationButton = view.findViewById(R.id.registrationButton)

        val controller = Navigation.findNavController(view)

        registrationButton.setOnClickListener {
            var mailInput = registrationEmailAddress.text.toString()
            var passwordInput = registrationPassword.text.toString()

            if (mailInput.isEmpty() || mailInput.length < 8 || !mailInput.contains("@")) {
                registrationEmailAddress.error = "Please write correct mail"
            }
            else if (passwordInput.isEmpty() || (passwordInput.length <= 9) || !(passwordInput.matches(".*[0-9].*".toRegex())) || !(passwordInput.matches(".*[a-z].*".toRegex()))){
                registrationPassword.error = "Please write correct password"
            }
            else
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            Toast.makeText(getActivity(), "Registration Complete", Toast.LENGTH_SHORT).show()
                            val action = RegistrationScreenFragmentDirections.actionRegistrationscreenFragmentToAuthscreeFragment()
                            controller.navigate(action)
                        }
                        else
                            Toast.makeText(getActivity(), "During Registration Problem Occurred ", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}
