package com.example.myapplication.FragmentSecond

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.RecyclerViewCarAdapter
import com.example.myapplication.CarData
import com.example.myapplication.ExactCarActivity
import com.example.myapplication.R

class CarLogoFragment: Fragment(R.layout.fragment_carlogo) {

//    private lateinit var recyclerViewCarAdapter: RecyclerViewCarAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CarData>
    lateinit var imageId: Array<Int>
    lateinit var name: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageId = arrayOf(
            R.drawable.volkswagen,
            R.drawable.audi,
            R.drawable.bmw,
            R.drawable.mustang,
            R.drawable.toyota

        )

        name = arrayOf(
            "Volkswagen",
            "Audi",
            "BMW",
            "Mustang",
            "Toyota"
        )

        recyclerView = view.findViewById(R.id.recyclerViewCar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<CarData>()
        getUserdata()

    }

    private fun getUserdata() {

        for (i in imageId.indices){

            val carData = CarData(name[i], imageId[i])
            newArrayList.add(carData)

        }

        var adapter = RecyclerViewCarAdapter(newArrayList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : RecyclerViewCarAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

//                Toast.makeText(requireActivity(),"you clicked on $position", Toast.LENGTH_SHORT).show()

                val intent = Intent(activity, ExactCarActivity::class.java)
                intent.putExtra("name",newArrayList[position].name)
                intent.putExtra("imageId",newArrayList[position].image)
                startActivity(intent)

            }


        })

    }
}