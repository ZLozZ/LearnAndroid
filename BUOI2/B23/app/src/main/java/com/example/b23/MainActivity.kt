package com.example.b23

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editCV:EditText
    private lateinit var editND:EditText
    private lateinit var txtDate:TextView
    private lateinit var txtTime:TextView
    private lateinit var btnDate:Button
    private lateinit var btnTime:Button
    private lateinit var btnAdd:Button
    private lateinit var listCv:ListView

    private lateinit var lAdapter: ArrayAdapter<String>
    private var arrJob = mutableListOf<String>()

    lateinit var dateFinish:String
    lateinit var timeFinish:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControl()
        addEventBtnDate()
        addEventBtnTime()
        addEventBtnAdd()
    }

    private fun addEventBtnAdd() {
        btnDate.setOnClickListener{
            DatePickerDialog(this, {_,i1,i2,i3->
                dateFinish = "$i3/${i2+1}/$i1"
                txtDate.text = dateFinish
            },2017,2,2).show()
        }
    }

    private fun addEventBtnTime() {
        btnTime.setOnClickListener(){
            TimePickerDialog(this,{ _, i, i2->
                val ampm:String
                var hour = i
                if(i>12){
                    hour-=12
                    ampm = "PM"
                }
                else{
                    ampm = "AM"
                }
                timeFinish = "$hour:$i2 $ampm"
                txtTime.text = timeFinish
            },0,0, true).show()
        }
    }

    private fun addEventBtnDate() {
        btnAdd.setOnClickListener{
            val cv = editCV.text.toString()
            arrJob.add("$cv ${editND.text.toString() ?: ""} - $dateFinish - $timeFinish")
            lAdapter.notifyDataSetChanged()
        }
    }

    private fun addControl() {
        editCV = findViewById(R.id.edtCv)
        editND = findViewById(R.id.edtNd)
        txtDate = findViewById(R.id.textDate)
        txtTime = findViewById(R.id.textTime)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)
        btnAdd = findViewById(R.id.btnAdd)
        listCv = findViewById(R.id.listCv)

        lAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,arrJob)
        listCv.adapter = lAdapter
    }


}