package com.example.myapplication.FragmentsSecond

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.AccountSettings
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlin.time.Duration.Companion.milliseconds

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.

 */
class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {

        val view =inflater.inflate(R.layout.fragment_profile, container, false)
        view.edit_profile_settings_button.setOnClickListener{
            val intent = Intent(context , AccountSettings::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return view


    }

}
