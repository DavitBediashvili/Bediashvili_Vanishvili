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

class RegistrationScreenFragment: Fragment(R.layout.fragment_registrationscreen) {
    private lateinit var registrationEmailAddress: EditText
    private lateinit var registrationPassword: EditText
    private lateinit var registrationButton: Button
    private lateinit var registrationPasswordRepeat: EditText
    private lateinit var termsOfServiceCheckBox: CheckBox
    private lateinit var termsOfService: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationEmailAddress = view.findViewById(R.id.registrationEmailAddress)
        registrationPassword = view.findViewById(R.id.registrationPassword)
        registrationButton = view.findViewById(R.id.registrationButton)
        registrationPasswordRepeat = view.findViewById(R.id.registrationPasswordRepeat)
        termsOfServiceCheckBox = view.findViewById(R.id.termsOfServiceCheckBox)
        termsOfService = view.findViewById(R.id.termsOfService)

        val controller = Navigation.findNavController(view)

        registrationButton.setOnClickListener {
            var mailInput = registrationEmailAddress.text.toString()
            var passwordInput = registrationPassword.text.toString()
            var confirmPasswordInput = registrationPasswordRepeat.text.toString()

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
}
