package com.example.b25

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editCV: EditText
    private lateinit var editND: EditText
    private lateinit var txtDate: TextView
    private lateinit var txtTime: TextView
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var btnAdd: Button
    private lateinit var listCv: ListView

    private lateinit var lAdapter: ArrayAdapter<String>
    private var arrJob = mutableListOf<String>()

    private lateinit var dateFinish:String
    private lateinit var timeFinish:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControl()
        addEventBtnDate()
        addEventBtnTime()
        addEventBtnAdd()
        registerForContextMenu(listCv)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option1 -> Toast.makeText(this, "Sửa rồi", Toast.LENGTH_SHORT).show()
            R.id.option2 -> Toast.makeText(this, "Xóa rồi", Toast.LENGTH_SHORT).show()
            R.id.option3 -> Toast.makeText(this, "${arrJob.size}", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //add(int groupId, int itemId, int order, charSequence title)
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.finish -> Toast.makeText(this, "${0}", Toast.LENGTH_SHORT).show()
            R.id.unfinish-> Toast.makeText(this, "${arrJob.size}", Toast.LENGTH_SHORT).show()
            R.id.allDelete -> {
                arrJob.clear()
                lAdapter.notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
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