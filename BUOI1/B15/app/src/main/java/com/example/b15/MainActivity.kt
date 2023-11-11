package com.example.b15

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var client = mutableListOf<String>()
    var clientVip = mutableListOf<String>()
    var revenue:Int = 0
    var index:Int = 0
    var indexVip:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        caculator()
    }

    public fun caculator(){
        val fullName:EditText = findViewById(R.id.editName)
        val number:EditText = findViewById(R.id.editNumber)
        val vip:CheckBox = findViewById(R.id.checkVip)
        val result:TextView = findViewById(R.id.textResult)
        val btnTT:Button = findViewById(R.id.btnTT)
        val btnNext:Button = findViewById(R.id.btnNext)
        val btnTK:Button = findViewById(R.id.btnStc)
        val tkh:EditText = findViewById(R.id.editNumberCL)
        val tkhV:EditText = findViewById(R.id.editNumberCLVip)
        val tdt:EditText = findViewById(R.id.editSumMN)
        val btnExit:Button= findViewById(R.id.btnExit)
        var money:Int
        btnTT.setOnClickListener(){
            var name = fullName.text.toString()
            var sl:Int
            if(number.text.toString().isNotEmpty()) {
                sl = number.text.toString().toInt()
            }else sl = 0
            var tt:Int = (sl*20000)
            var discout:Int = (tt*10)/100
            if(vip.isChecked){
                money = (tt - discout)
                client.add(name)
                clientVip.add(name)
                revenue+=money
                tt = money
            }
            else
            {
                client.add(name)
                revenue+=tt
            }
            result.text = tt.toString()
            tt = 0
        }
        btnNext.setOnClickListener(){
            fullName.setText("")
            number.setText("")
            result.text = ""
        }
        btnTK.setOnClickListener(){
            tkh.setText(client.size.toString())
            tkhV.setText((clientVip.size.toString()))
            tdt.setText(revenue.toString())
        }
        btnExit.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thoát?")
            builder.setMessage("Bạn có chắc chắn muốn thoát không?")
            builder.apply {
                setPositiveButton("Không",
                    { dialog, id ->
                        dialog.dismiss()
                    })
                setNegativeButton("Có",
                    { dialog, id ->
                        finish()
                    })
            }
            builder.create().show()
        }
    }
}