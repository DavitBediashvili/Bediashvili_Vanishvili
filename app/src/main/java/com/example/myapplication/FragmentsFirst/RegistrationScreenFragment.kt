package com.example.exam.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.ResetPasswordDialog
import com.example.myapplication.TermsOfServiceDialog
import com.example.myapplication.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_registrationscreen.*
import java.util.*
import kotlin.collections.HashMap

class RegistrationScreenFragment: Fragment(R.layout.fragment_registrationscreen) {
    private lateinit var registrationEmailAddress: EditText
    private lateinit var registrationPassword: EditText
    private lateinit var registrationButton: Button
    private lateinit var username: EditText
    private lateinit var registrationPasswordRepeat: EditText
    private lateinit var termsOfServiceCheckBox: CheckBox
    private lateinit var termsOfService: TextView
    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UsernameReadd")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        registration()


        termsOfService.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                TermsOfServiceDialog(requireContext()).show()

            }
        })
    }

//    private fun saveUserInfo(usernameReadd: String, mailInput: String) {
//        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
//        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
//
//        val userMap = HashMap<String, Any>()
//        userMap["uid"] = currentUserID
//        userMap["usernameRead"] = usernameReadd
//        userMap["mailInput"] = mailInput
//        userMap["image"] = "theapk-4cf05-default-rtdb"
//
//        usersRef.child(currentUserID).setValue(userMap)
//            .addOnCompleteListener{task ->
//                if (task.isSuccessful){
//                    Toast.makeText(getActivity(), "Big Success ", Toast.LENGTH_SHORT)
//                }
//                else{
//                    Toast.makeText(getActivity(), "Fail ", Toast.LENGTH_SHORT)
//                }
//
//            }
//
//
//    }
    private fun registration(){
        registrationButton.setOnClickListener {
            var mailInput = registrationEmailAddress.text.toString()
            var passwordInput = registrationPassword.text.toString()
            var confirmPasswordInput = registrationPasswordRepeat.text.toString()
            var usernameReadd = username.text.toString()


            val controller = Navigation.findNavController(requireView())


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
                            val userInfo = UserInfo(usernameReadd)
                            dbUserInfo.child(auth.currentUser?.uid!!).setValue(userInfo)
                            Toast.makeText(getActivity(), "Registration Complete", Toast.LENGTH_SHORT).show()
                            val action = RegistrationScreenFragmentDirections.actionRegistrationscreenFragmentToFirstpageFragment()
                            controller.navigate(action)
                        }
                        else
                            Toast.makeText(getActivity(), "During Registration Problem Occurred ", Toast.LENGTH_SHORT).show()
                    }
        }
    }


    private fun init(){
        registrationEmailAddress = requireView().findViewById(R.id.registrationEmailAddress)
        registrationPassword = requireView().findViewById(R.id.registrationPassword)
        registrationButton = requireView().findViewById(R.id.registrationButton)
        username = requireView().findViewById(R.id.username)
        registrationPasswordRepeat = requireView().findViewById(R.id.registrationPasswordRepeat)
        termsOfServiceCheckBox = requireView().findViewById(R.id.termsOfServiceCheckBox)
        termsOfService = requireView().findViewById(R.id.termsOfService)
    }
}
