package com.example.b16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val arr = arrayOf("Tí", "Tèo", "Bin", "Bo")
    lateinit var lView:ListView
    lateinit var result: TextView
    lateinit var lAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEven()
    }

    fun addEven(){
        lView = findViewById<ListView>(R.id.list1)
        result = findViewById(R.id.textView)
        lAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arr)
        lView.adapter = lAdapter
        lView.setOnItemClickListener{parent, view, position, id ->
            result.text = "position "+position+":"+"value ="+arr[position]
        }
    }
}

