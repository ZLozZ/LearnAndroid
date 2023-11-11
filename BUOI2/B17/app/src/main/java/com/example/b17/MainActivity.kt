package com.example.b17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var arr = mutableListOf<String>()
    lateinit var listView:ListView
    lateinit var btnInput:Button
    lateinit var name:EditText
    lateinit var result:TextView
    lateinit var lAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        addEven()
        evenBtn()
        clickItem()
    }
    fun initView(){
        listView = findViewById(R.id.userList)
        btnInput = findViewById(R.id.btnInput)
        name = findViewById(R.id.editInput)
        result = findViewById(R.id.result)
    }

    fun evenBtn(){
        btnInput.setOnClickListener{
            val n = name.text.toString()
            arr.add(n)
            lAdapter.notifyDataSetChanged()
        }
    }

    fun addEven(){
        lAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arr)
        listView.adapter = lAdapter

    }

    fun clickItem(){
        listView.setOnItemClickListener{_, _, position, _ ->
            result.text = "position "+position+":"+"value ="+arr[position]
        }
    }
}