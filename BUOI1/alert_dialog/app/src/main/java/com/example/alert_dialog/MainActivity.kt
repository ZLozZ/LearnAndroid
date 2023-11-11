package com.example.alert_dialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAc: Button = findViewById(R.id.btnAccess)
        val btnEx: Button = findViewById(R.id.btnExit)
        btnAc.setOnClickListener(myOnClickListener)
        btnEx.setOnClickListener(myOnClickListener)
    }

    val myOnClickListener = View.OnClickListener { View->
        when(View.id) {
            R.id.btnAccess->{
                val check:CheckBox = findViewById(R.id.save)
                if(check.isChecked == true){
                    Toast.makeText(this, "chào mừng bạn đăng nhập hệ thống, bạn đã lưu thông tin", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "chào mừng bạn đăng nhập hệ thống, bạn không lưu thông tin", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnExit->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Question?")
                builder.setMessage("Are you sure you want to exit?")
                builder.apply {
                    setPositiveButton("No",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                    setNegativeButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            finish()
                        })
                }
                builder.create().show()
            }
        }
    }
}