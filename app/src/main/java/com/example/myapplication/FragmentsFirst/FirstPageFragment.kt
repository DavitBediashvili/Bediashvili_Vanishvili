package com.example.myapplication.FragmentsFirst

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.R
import android.graphics.Typeface




class FirstPageFragment: Fragment(R.layout.fragment_firstpage){
    private lateinit var transferToRegScreenButton: TextView
    private lateinit var transferToAuthScreenButton: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        val controller = Navigation.findNavController(view)

        transferToRegScreenButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val action1 = FirstPageFragmentDirections.actionFirstpageFragmentToRegistrationscreenFragment()
                controller.navigate(action1)

            }
        })

        transferToAuthScreenButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val action2 = FirstPageFragmentDirections.actionFirstpageFragmentToAuthscreeFragment()
                controller.navigate(action2)

            }
        })


    }
    private fun init(){
        transferToRegScreenButton = requireView().findViewById(R.id.transferToRegScreenButton)
        transferToAuthScreenButton = requireView().findViewById(R.id.transferToAuthScreenButton)
    }
}