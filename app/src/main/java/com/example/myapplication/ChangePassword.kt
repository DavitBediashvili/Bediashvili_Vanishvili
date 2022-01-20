package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.changepassword.*
import kotlinx.android.synthetic.main.fragment_registrationscreen.*

class ChangePassword : Fragment(R.layout.changepassword)

{
    private lateinit var currentPassword : EditText
    private lateinit var newPassword : EditText
    private lateinit var confrimPassword : EditText
    private lateinit var changePasswordButton : Button
    private lateinit var auth : FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        changePasswordButton.setOnClickListener {
            changePasswordFun()
        }

    }

    private fun init() {
        currentPassword = requireView().findViewById(R.id.currentPassword)
        newPassword = requireView().findViewById(R.id.newPassword)
        confrimPassword = requireView().findViewById(R.id.confrimPassword)
        changePasswordButton = requireView().findViewById(R.id.changePasswordButton)
        auth = FirebaseAuth.getInstance()

    }

    private fun changePasswordFun() {
        val readcurrent = currentPassword.text.toString()
        val readnew = currentPassword.text.toString()
        val readconfrim = currentPassword.text.toString()


        if (readcurrent.isNotEmpty()|| readnew.isNotEmpty() || readconfrim.isNotEmpty()){
            if (readnew.equals((readconfrim))){
                val user = auth.currentUser
                if (user !=null ){
                    val credential = EmailAuthProvider
                        .getCredential(registrationEmailAddress.text.toString(), registrationPassword.text.toString())

                    user.reauthenticate(credential)
                        .addOnCompleteListener { Log.d(TAG, "User re-authenticated.")
                        if (it.isSuccessful){
                            Toast.makeText(getActivity(), "Password has changed succesfully", Toast.LENGTH_SHORT).show()
                            user!!.updatePassword(readnew)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "User password updated.")
                                        auth.signOut()
                                        val controller = Navigation.findNavController(requireView())

                                        val action = ChangePasswordDirections.actionChangePassword2ToAuthScreenFragment()
                                        controller.navigate(action)
                                    }
                                }
                        }
                        }
                }

            }
        }
        else{
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show()
        }

    }


}