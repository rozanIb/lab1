package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.id.*
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes


class data : AppCompatActivity() {
    lateinit var edName: EditText
    lateinit var edDesc: EditText
    lateinit var edNum: EditText
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        edName=findViewById(R.id.name)
        edDesc=findViewById(R.id.desc)
        edNum=findViewById(R.id.num)
        db = FirebaseFirestore.getInstance()
        db.collection("Note").get()
        val btnadd = findViewById<Button>(R.id.add_btn)
        btnadd.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        val name = edName.text.toString()
        val desc = edDesc.text.toString()
        val num = edNum.text.toString()

        val notemap = hashMapOf(
            "Name" to name,
            "desc" to desc,
            "Number" to num
        )


        db.collection("Note").add(notemap)
            .addOnSuccessListener {
                Toast.makeText(this, "SuccessFull Added", Toast.LENGTH_SHORT).show()


            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed Added", Toast.LENGTH_SHORT).show()

            }
    }


}}