package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class detailAdapter (val context: Detail, val detaillist: ArrayList<detailData>): RecyclerView.Adapter<detailAdapter.MyViewHolder>() {
    private lateinit var mylistener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick (position: Int)
    }
    fun onItemClickListener(listener: onItemClickListener){
        mylistener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detailAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return detailAdapter.MyViewHolder(itemView, mylistener)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = detaillist[position]
        val img = data.Image
        Picasso.get().load(img).into(holder.Image)
        holder.Name.text = data.Name.toString()
        holder.desc.text = data.Desc.toString()
        holder.num.text =data.Number.toString()

    }

    override fun getItemCount(): Int {
        return detaillist.size
    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val Image: ImageView = itemView.findViewById(R.id.Image)
        val Name: TextView = itemView.findViewById(R.id.txName)
        val desc: TextView = itemView.findViewById(R.id.txDesc)
        val num: TextView = itemView.findViewById(R.id.txNum)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}