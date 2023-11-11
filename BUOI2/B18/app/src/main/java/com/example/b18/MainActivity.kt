package com.example.b18

import android.app.ListActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : ListActivity() {
    var arr = arrayOf("Intel","SamSung", "Nokia","Simen","AMD", "KIC","ECD")
    lateinit var lAdapter: ArrayAdapter<String>
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Thiết lập Data Source cho Adapter
        lAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        //Gán Adapter vào ListView
        //Nhớ là phải đặt id cho ListView theo đúng quy tắc
        listAdapter =lAdapter
        result = findViewById(R.id.selection)

    }
    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        var txt:String = "postion = "+position +"; value ="+arr[position]
        result.text = txt
    }
}
