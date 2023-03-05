package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(val context: MainActivity, val userlist: ArrayList<User>): RecyclerView.Adapter<adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = userlist[position]
        holder.Name.text = data.Name
        holder.desc.text = data.desc.toString()
        holder.num.text =data.Number

    }

    override fun getItemCount(): Int {
        return userlist.size
    }


    class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val Name: TextView = itemView.findViewById(R.id.txName)
        val desc: TextView = itemView.findViewById(R.id.txDesc)
        val num: TextView = itemView.findViewById(R.id.txnum)
    }
}