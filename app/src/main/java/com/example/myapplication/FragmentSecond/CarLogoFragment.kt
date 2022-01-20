package com.example.myapplication.FragmentSecond

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FragmentSecond.Adapter.RecyclerViewCarAdapter
import com.example.myapplication.CarData
import com.example.myapplication.ExactCarActivity
import com.example.myapplication.R

class CarLogoFragment: Fragment(R.layout.fragment_carlogo) {

//    private lateinit var recyclerViewCarAdapter: RecyclerViewCarAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CarData>
    lateinit var imageId: Array<Int>
    lateinit var name: Array<String>
    lateinit var exactCarSedan: Array<String>
    lateinit var exactCarSedanImage: Array<Int>
    lateinit var exactCarSUV: Array<String>
    lateinit var exactCarSUVImage: Array<Int>

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

        exactCarSedan = arrayOf(
            "Volkswagen Jetta",
            "Audi A5",
            "BMW 330i",
            "Ford Mustang S550",
            "Toyota Camry"


        )

        exactCarSedanImage = arrayOf(
            R.drawable.volkswagen_jetta,
            R.drawable.audi_a,
            R.drawable.bmw_i,
            R.drawable.ford_mustang_s,
            R.drawable.toyota_camry

        )

        exactCarSUV = arrayOf(
            "Volkswagen Atlas",
            "Audi Q5",
            "BMW X5",
            "Ford Mustang Mach-E",
            "Toyota RAV4"
        )

        exactCarSUVImage = arrayOf(
            R.drawable.volkswagen_atlas,
            R.drawable.audi_q,
            R.drawable.bmw_x,
            R.drawable.ford_mustang_mach,
            R.drawable.toyota_rav

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
                intent.putExtra("exactCarSedanImage",exactCarSedanImage[position])
                intent.putExtra("exactCarSUVImage",exactCarSUVImage[position])
                intent.putExtra("exactCarSedan",exactCarSedan[position])
                intent.putExtra("exactCarSUV",exactCarSUV[position])
                startActivity(intent)

            }


        })

    }
}