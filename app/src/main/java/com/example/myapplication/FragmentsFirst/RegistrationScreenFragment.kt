package com.example.exam.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.ResetPasswordDialog
import com.example.myapplication.TermsOfServiceDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_registrationscreen.*

class RegistrationScreenFragment: Fragment(R.layout.fragment_registrationscreen) {
    private lateinit var registrationEmailAddress: EditText
    private lateinit var registrationPassword: EditText
    private lateinit var registrationButton: Button
    private lateinit var username: EditText
    private lateinit var registrationPasswordRepeat: EditText
    private lateinit var termsOfServiceCheckBox: CheckBox
    private lateinit var termsOfService: TextView
    private val auth = FirebaseAuth.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationEmailAddress = view.findViewById(R.id.registrationEmailAddress)
        registrationPassword = view.findViewById(R.id.registrationPassword)
        registrationButton = view.findViewById(R.id.registrationButton)
        username = view.findViewById(R.id.username)
        registrationPasswordRepeat = view.findViewById(R.id.registrationPasswordRepeat)
        termsOfServiceCheckBox = view.findViewById(R.id.termsOfServiceCheckBox)
        termsOfService = view.findViewById(R.id.termsOfService)

        val controller = Navigation.findNavController(view)

        registrationButton.setOnClickListener {
            var mailInput = registrationEmailAddress.text.toString()
            var passwordInput = registrationPassword.text.toString()
            var confirmPasswordInput = registrationPasswordRepeat.text.toString()
            var usernameRead = username.text.toString().toLowerCase()


            if (mailInput.isEmpty() || mailInput.length < 8 || !mailInput.contains("@")) {
                registrationEmailAddress.error = "Please write correct mail"
            }
            else if (passwordInput.isEmpty() || (passwordInput.length <= 9) || !(passwordInput.matches(".*[0-9].*".toRegex())) || !(passwordInput.matches(".*[a-z].*".toRegex()))){
                registrationPassword.error = "Password should contain more than 8 symbols and numbers"
            }
            else if (confirmPasswordInput != passwordInput){
                registrationPasswordRepeat.error = "Please repeat password correctly"
            }
            else if (!termsOfServiceCheckBox.isChecked){
                registrationPasswordRepeat.error = "Please read Terms of Service"
            }
            else
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            saveUserInfo(usernameRead)
                            Toast.makeText(getActivity(), "Registration Complete", Toast.LENGTH_SHORT).show()
                            val action = RegistrationScreenFragmentDirections.actionRegistrationscreenFragmentToFirstpageFragment()
                            controller.navigate(action)
                        }
                        else
                            Toast.makeText(getActivity(), "During Registration Problem Occurred ", Toast.LENGTH_SHORT).show()
                    }
        }

        termsOfService.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                TermsOfServiceDialog(requireContext()).show()

            }
        })
    }

    private fun saveUserInfo(username: String) {
        val currentUser =FirebaseAuth.getInstance().currentUser!!.uid
        val users: DatabaseReference  = FirebaseDatabase.getInstance().getReference().child("Username")
        val usermap = HashMap<String , Any>()
        usermap["username"] = currentUser
        users.child(currentUser).setValue(usermap)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Toast.makeText(getActivity(), "Account has been created succesfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show()
                }
            }


    }
}
