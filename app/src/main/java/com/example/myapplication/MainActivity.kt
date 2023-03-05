package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var add :TextView


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Firebase.firestore
        val userlist = ArrayList<User>()
        val adapter = adapter(this,userlist)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= adapter
        add=findViewById(R.id.txadd)
        add.setOnClickListener {
            var intent = Intent(this,data::class.java)
            startActivity(intent)
    }
        db.collection("Note").get().addOnSuccessListener {
           result->
            for(document in result ){
                userlist.add(
                    User(
                        document.getString("Name").toString(),
                        document.getString("desc").toString(),
                        document.getString("Number").toString(),
                    )
                )
                adapter.notifyDataSetChanged()
            }

        }
            .addOnFailureListener{
                Toast.makeText(this,"Failed ",Toast.LENGTH_SHORT).show()
            }
}}