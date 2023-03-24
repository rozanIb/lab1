package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Note : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note)
        val progressBar = findViewById<ProgressBar>(R.id.NprogressBar)
        progressBar.visibility= View.VISIBLE
        val db = Firebase.firestore
        val userlist = ArrayList<User>()
        val adapter = adapter(this,userlist)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= adapter

        db.collection("Note").get().addOnSuccessListener {
                result->
            for(document in result ){
                userlist.add(
                    User(
                        document.getString("image").toString(),
                        document.getString("name").toString(),

                    )
                )
                adapter.notifyDataSetChanged()
            }
            progressBar.visibility= View.GONE

        }
            .addOnFailureListener{
                Toast.makeText(this,"Failed ",Toast.LENGTH_SHORT).show()
                progressBar.visibility= View.GONE
            }
    }}