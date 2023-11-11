package com.example.bai_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //C1
    //short 2s trở xuống
    fun toastShort(view: View) {
        val t: Toast = Toast.makeText(this, "Short", Toast.LENGTH_SHORT)
        t.show()
    }
    //C2
    //Long hien thi trong vong 3.5s
    fun toastLong(view: View) {
        Toast.makeText(this, "Long", Toast.LENGTH_LONG).show()
    }
}