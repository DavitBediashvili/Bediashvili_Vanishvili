package com.example.myapplication.FragmentsSecond

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.UserAdapter
import com.example.myapplication.Model.User
import com.example.myapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_registrationscreen.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment()
{
    private var recyclerView: RecyclerView? = null
    private var userAdapter: UserAdapter? = null
    private var mUser: MutableList<com.example.myapplication.Model.User>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_search)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        mUser = ArrayList()
        userAdapter = context?.let { UserAdapter(it , mUser as ArrayList<User> , true)  }
        recyclerView?.adapter = userAdapter

        view.search_edittext.addTextChangedListener (object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (search_edittext.text.toString() == ""){
                    Toast.makeText(requireActivity(),"There is no username",Toast.LENGTH_SHORT)

                }
                else{
                    recyclerView?.visibility = View.VISIBLE

                    searchedUsers()
                    searchUsers(s.toString().toLowerCase())
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }



        })


        return view
    }
    fun searchUsers(input: String) {
        var letsSearch = FirebaseDatabase.getInstance().getReference()
            .child("Users")
            .orderByChild("usernameRead")
            .startAt(input)
            .endAt(input + "\uf0ff")
        letsSearch.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(Datasnapshot: DataSnapshot) {


                mUser?.clear()

                for (snapshot in Datasnapshot.children){
                    val user = snapshot.getValue(User::class.java)
                    if (user!=null){

                        mUser?.add(user)


                    }

                }
                userAdapter?.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun searchedUsers(){
        var users = FirebaseDatabase.getInstance().getReference()
            .child("Users")
        users.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(Datasnapshot: DataSnapshot) {
                mUser?.clear()

                if(view?.search_edittext?.text.toString() == ""){

                    for (snapshot in Datasnapshot.children){
                        val user = snapshot.getValue(User::class.java)
                        if (user!=null){

                            mUser?.add(user)


                        }

                    }
                    userAdapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

}

