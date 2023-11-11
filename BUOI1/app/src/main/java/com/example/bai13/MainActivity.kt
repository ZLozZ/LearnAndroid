package com.example.bai13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        change()
    }

    public fun change(){
        val can = arrayOf("Canh", "Tân", "Nhân", "Quý", "Giá", "Ất", "Bính", "Đinh", "Mậu", "Ký")
        val chi = arrayOf("Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi")
        val editDuong:EditText= findViewById(R.id.editText)

        val textAm:TextView = findViewById(R.id.result)
        val change:Button = findViewById(R.id.btnchange)
        change.setOnClickListener(){
            var nam = editDuong.text.toString().toIntOrNull()?:0
            var can1:String = can[nam%10]
            var chi1:String = chi[nam%12]
            textAm.text = can1+' '+chi1
        }
    }


}