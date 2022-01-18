package com.example.myapplication.Adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.User
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class UserAdapter(private var mcontext : Context,
                  private var  mUser : List<User> ,
                  private var isFragment : Boolean = false):RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {

        val view = LayoutInflater.from(mcontext).inflate(R.layout.user_info , parent ,false)

        return UserAdapter.ViewHolder(view)
    }




    override fun getItemCount(): Int {
        return mUser.size

    }
    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val readUser = mUser[position]
        holder.userNamee.text = readUser.getUserName()
        holder.fullname.text = readUser.getFullName()
        Picasso.get().load(readUser.getImage()).placeholder(R.drawable.profile).into(holder.image)

    }

    class ViewHolder(@NonNull itemView : View) : RecyclerView.ViewHolder(itemView){

         var userNamee : TextView = itemView.findViewById(R.id.username_searched)
         var fullname : TextView = itemView.findViewById(R.id.username_fullname)
         var image : CircleImageView = itemView.findViewById(R.id.user_image)
         var followButton : Button = itemView.findViewById(R.id.follow_button)

    }
}