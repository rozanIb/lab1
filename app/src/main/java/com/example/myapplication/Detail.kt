package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Detail : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
       /* var image: ImageView =findViewById(R.id.Image)
        val name: TextView =findViewById(R.id.name)
        val desc:TextView=findViewById(R.id.desc)
        val num:TextView=findViewById(R.id.num)*/
        val progressBar = findViewById<ProgressBar>(R.id.DprogressBar)
        progressBar.visibility= View.VISIBLE
        val db = Firebase.firestore
        val detaillist = ArrayList<detailData>()
        val adapter = detailAdapter(this,detaillist)
        adapter.onItemClickListener(object : detailAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Detail, Note::class.java)
                intent.putExtra("image", detaillist[position].Image)
                intent.putExtra("name", detaillist[position].Name)
                intent.putExtra("Desc", detaillist[position].Desc)
                intent.putExtra("Number", detaillist[position].Number)

                startActivity(intent)
            }

        })
        db.collection("/Note/Note/Assignment/Done/detail").get().addOnSuccessListener {
                result->
            for(document in result ){
                detaillist.add(
                    detailData(
                        document.getString("image").toString(),
                        document.getString("Name").toString(),
                        document.getString("Desc").toString(),
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
        analytics = Firebase.analytics
        screenTraack("Detail", "detail")
        cintent("3","Detail","cardView")
    }
    fun cintent(id: String, name: String, contentType: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        }
    }
    fun screenTraack(screenClass: String, screenName: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }


}