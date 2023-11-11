package com.example.b_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.adapter.SAdapter
import com.example.b_19.R

class MainActivity : AppCompatActivity() {
    val dd = arrayOf("Hà Nội", "huế", "hpa", "côn sơn", "vũng tàu", "đà nẵng")

    val img = arrayOf<Int>(R.drawable.images, R.drawable.images_1)
    lateinit var lvDD: ListView
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvDD = findViewById(R.id.lvSP)
        result = findViewById(R.id.result)
        val myListAdapter = SAdapter(this,dd,img)

        lvDD.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }
    }
}