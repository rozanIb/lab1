package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.id.*
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class data : AppCompatActivity() {
    lateinit var edName:EditText
    lateinit var edDesc : EditText
    lateinit var edNum:EditText
    private  var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        db= FirebaseFirestore.getInstance()
        db.collection("Note").get()
        val add_btn = findViewById<Button>(R.id.add_btn)
        add_btn.setOnClickListener {
            edName.text.toString()
            edDesc.text.toString()
            edNum.text.toString()
        }
    }
  }