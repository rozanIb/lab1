package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.currentCoroutineContext
import java.util.jar.Attributes.Name

class HomeAdapter(val context: MainActivity, val notelist: ArrayList<NoteData>): RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = notelist[position]
        val img = data.image
        Picasso.get().load(img).into(holder.image)
        holder.Name.text = data.name
        }





    override fun getItemCount(): Int {
        return notelist.size
    }


    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val Name: TextView = itemView.findViewById(R.id.name)

    }
}
