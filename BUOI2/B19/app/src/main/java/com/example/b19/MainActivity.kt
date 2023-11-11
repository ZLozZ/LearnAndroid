package com.example.b19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val dd = arrayOf<String>("Hà Nội", "huế", "spa", "côn sơn", "vũng tàu", "đà nẵng")
    lateinit var lView:ListView
    lateinit var rT:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var arrDs: ArrayList<Ds> = ArrayList()
        for(i in dd){
            if(i.length <=3){
                arrDs.add(Ds(i, R.drawable.images_1))
            }else{
                arrDs.add(Ds(i, R.drawable.images))
            }
        }
        lView = findViewById(R.id.lvSP)
        lView.adapter = CustomAdapter(this@MainActivity, arrDs)
        rT = findViewById(R.id.result)

        lView.setOnItemClickListener{parent, view, position, id ->
            rT.text = dd[position]
        }
    }
}