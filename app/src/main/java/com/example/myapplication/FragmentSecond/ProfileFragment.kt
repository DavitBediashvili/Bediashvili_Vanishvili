package com.example.myapplication.FragmentSecond

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment(R.layout.fragment_profile)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = Navigation.findNavController(requireView())

        history.setOnClickListener {

            val action =ProfileFragmentDirections.actionProfileFragmentToHistoryFragment()
            controller.navigate(action)
        }

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val action =ProfileFragmentDirections.actionProfileFragmentToRegistrationScreenFragment()
            controller.navigate(action)
        }

        changePassword.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToChangePassword2()
            controller.navigate(action)

        }
    }
}