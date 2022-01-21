package com.example.myapplication.FragmentSecond

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_registrationscreen.*
import java.util.*

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var username: TextView
    private lateinit var lastRentalCar: TextView
    private lateinit var showAge: TextView
    private lateinit var editTextImage: EditText
    private lateinit var changePassword: Button
    private lateinit var logoutButton: Button
    private lateinit var imageView: ImageView
    private lateinit var clickImage: Button


    private var auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("UsernameReadd")
    private val dbUserInfo2: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("gela")
    private val dbUserInfo3: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("photo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        clickImage.setOnClickListener {
            val editText = editTextImage.text.toString()
            val userInfo3 = UserInfo(editText)
            dbUserInfo3.child(auth.currentUser?.uid!!).setValue(userInfo3)
            dbUserInfo3.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userInfo = snapshot.getValue(UserInfo :: class.java)?: return
                    Glide.with(requireActivity()).load(userInfo.usernameRead).placeholder(R.drawable.toyota_rav).into(imageView)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        changePassword.setOnClickListener {

            dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userinfo = snapshot.getValue(UserInfo::class.java) ?: return

                    Firebase.auth.sendPasswordResetEmail(userinfo.mail)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Log.d(ContentValues.TAG, "Email Sent")
                                Toast.makeText(requireActivity(), "instructions were sent to your mail", Toast.LENGTH_SHORT).show()
                            } else
                                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()

                        }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        }

        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }



        dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val userinfo = snapshot.getValue(UserInfo::class.java) ?: return
                username.text = userinfo.usernameRead

                val ragac = Calendar.getInstance().get(Calendar.YEAR)
                val ragac2 = getLastNCharsOfString(userinfo.age,4)
                val ageCount = (ragac - ragac2!!.toInt()).toString()

                showAge.text = ageCount




            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        dbUserInfo2.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userinfo2 = snapshot.getValue(UserInfo::class.java) ?: return
                lastRentalCar.text = userinfo2.history


            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }

    private fun init() {
        username = requireView().findViewById(R.id.username_profile)
        lastRentalCar = requireView().findViewById(R.id.lastRentalCar)
        changePassword = requireView().findViewById(R.id.changePassword)
        logoutButton = requireView().findViewById(R.id.logoutButton)
        showAge = requireView().findViewById(R.id.showAge)
        imageView = requireView().findViewById(R.id.imageView)
        clickImage = requireView().findViewById(R.id.clickImage)
        editTextImage = requireView().findViewById(R.id.editTextImage)
    }

    fun getLastNCharsOfString(str: String, n: Int): String? {
        var lastnChars = str
        if (lastnChars.length > n) {
            lastnChars = lastnChars.substring(lastnChars.length - n, lastnChars.length)
        }
        return lastnChars
    }
}


