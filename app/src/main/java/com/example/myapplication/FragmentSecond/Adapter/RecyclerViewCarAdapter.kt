package com.example.myapplication.FragmentSecond.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.CarData
import com.example.myapplication.R

class RecyclerViewCarAdapter(private val list: ArrayList<CarData>): RecyclerView.Adapter<RecyclerViewCarAdapter.CarViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    class CarViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val CarImageView: ImageView
        val CarName: TextView

        init{
            CarImageView = itemView.findViewById(R.id.CarImageView)
            CarName = itemView.findViewById(R.id.CarName)

            itemView.setOnClickListener{

                listener.onItemClick(adapterPosition)

            }
        }

        fun setData(carData: CarData){
            Glide.with(itemView).load(carData.image).into(CarImageView)
            CarName.text = carData.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        var car = list[position]
        holder.CarImageView.setImageResource(car.image)
        holder.CarName.text =car.name
    }

    override fun getItemCount() = list.size
}