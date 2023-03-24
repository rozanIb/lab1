package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Detail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        val progressBar = findViewById<ProgressBar>(R.id.DprogressBar)
        progressBar.visibility= View.VISIBLE
        val db = Firebase.firestore
        val detaillist = ArrayList<detailData>()
        val adapter = detailAdapter(this,detaillist)
        db.collection("Home").get().addOnSuccessListener {
                result->
            for(document in result ){
                detaillist.add(
                    detailData(
                        document.getString("image").toString(),
                        document.getString("Name").toString(),
                        document.getString("Dece").toString(),
                        document.getString("Number").toString().toInt()
                        )
                )
                adapter.notifyDataSetChanged()
            }
            progressBar.visibility= View.GONE
        }
            .addOnFailureListener{
                Toast.makeText(this,"Failed ", Toast.LENGTH_SHORT).show()
                progressBar.visibility= View.GONE
            }
    }
}