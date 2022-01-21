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
    lateinit var exactCarSedanPrice: Array<String>
    lateinit var exactCarSUVPrice: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageId = arrayOf(
            R.drawable.volkswagen,
            R.drawable.audi,
            R.drawable.bmw,
            R.drawable.mustang,
            R.drawable.maserati

        )

        name = arrayOf(
            "Volkswagen",
            "Audi",
            "BMW",
            "Mustang",
            "Maserati"
        )

        exactCarSedan = arrayOf(
            "Volkswagen Jetta",
            "Audi A5",
            "BMW 330i",
            "Ford Mustang S550",
            "Maserati GranTurismo V8"


        )

        exactCarSedanImage = arrayOf(
            R.drawable.volkswagen_jetta,
            R.drawable.audi_a_new,
            R.drawable.bmw_i,
            R.drawable.ford_mustang_s,
            R.drawable.maserati_new_sedan

        )

        exactCarSUV = arrayOf(
            "Volkswagen Atlas",
            "Audi Q5",
            "BMW X5",
            "Ford Mustang Mach-E",
            "Maserati Levante"
        )

        exactCarSUVImage = arrayOf(
            R.drawable.volkswagen_atlas,
            R.drawable.audi_q,
            R.drawable.bmw_x,
            R.drawable.ford_mustang_mach,
            R.drawable.maserati_suv
        )

        exactCarSedanPrice = arrayOf(
            "From 47 € for 3 hours\n(Additional kilometer (>200 km)  €0.31/km)",
            "From 59 € for 3 hours\n(Additional kilometer (>200 km)  €0.42/km)",
            "From 49 € for 3 hours\n(Additional kilometer (>200 km)  €0.32/km)",
            "From 59 € for 3 hours\n(Additional kilometer (>200 km)  €0.42/km)",
            "From 80 € for 3 hours\n(Additional kilometer (>200 km)  €0.64/km)"
        )

        exactCarSUVPrice = arrayOf(
            "From 64 €,for 3 hours\n(Additional kilometer (>200 km)  €0.49/km)",
            "From 65 €,for 3 hours\n(Additional kilometer (>200 km)  €0.51/km)",
            "From 60 €,for 3 hours\n(Additional kilometer (>200 km)  €0.45/km)",
            "From 57 €,for 3 hours\n(Additional kilometer (>200 km)  €0.36/km)",
            "From 79 €,for 3 hours\n(Additional kilometer (>200 km)  €0.59/km)"
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
                intent.putExtra("exactCarSedanPrice",exactCarSedanPrice[position])
                intent.putExtra("exactCarSUVPrice",exactCarSUVPrice[position])
                startActivity(intent)

            }


        })

    }
}