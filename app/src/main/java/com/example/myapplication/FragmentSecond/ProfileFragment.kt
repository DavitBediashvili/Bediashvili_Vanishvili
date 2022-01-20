package com.example.myapplication.FragmentSecond

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var username: TextView
    private var auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val controller = Navigation.findNavController(requireView())
        username = view.findViewById(R.id.username_profile)
        dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userinfo = snapshot.getValue(UserInfo::class.java) ?: return
                username.text = userinfo.usernameRead


            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }
}


