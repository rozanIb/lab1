package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private  var db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var array: ArrayList<User>
    private lateinit var add :TextView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        array = arrayListOf()

    db= FirebaseFirestore.getInstance()
        db.collection("Note").get().addOnSuccessListener {
            if (!it.isEmpty){
                for (data in it.documents){
                    val user: User? =data.toObject(User::class.java)
                    if (user !=null){
                    array.add(user)
                }}
            }
            recyclerView.adapter = adapter(array)
        }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }
        add=findViewById(R.id.txadd)
        add.setOnClickListener {
            var intent = Intent(this,data::class.java)
            startActivity(intent)
    }
}}