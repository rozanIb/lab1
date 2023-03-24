package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapter(val context: Note, val userlist: ArrayList<User>): RecyclerView.Adapter<adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return adapter.MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = userlist[position]
        val img = data.image2
        Picasso.get().load(img).into(holder.image)
        holder.Name.text = data.name2.toString()
        holder.Name.setOnClickListener {
            val intent = Intent(context,Detail::class.java)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return userlist.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image2)
        val Name: TextView = itemView.findViewById(R.id.name2)

    }
}