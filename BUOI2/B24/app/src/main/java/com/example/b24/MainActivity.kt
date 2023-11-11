package com.example.b24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TabHost
import android.widget.TableLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var tabHost:TabHost
    lateinit var btnLogin:Button
    private lateinit var tab1:TabHost.TabSpec
    private lateinit var tab2:TabHost.TabSpec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControl()
        addEvents()
    }

    private fun addEvents() {
        tabHost.setOnTabChangedListener {s->
                if(s.equals("t1")){
                    Toast.makeText(this, "Bạn chọn tab1",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Bạn chọn tab2",Toast.LENGTH_SHORT).show()
                }
        }
        btnLogin.setOnClickListener{
            Toast.makeText(this, "Đã đăng nhập", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addControl() {
        tabHost = findViewById(R.id.tabHost)
        tabHost.setup()
        tab1 = tabHost.newTabSpec("t1")
        tab1.setContent(R.id.tab1)
        tab1.setIndicator("1-LOGIN")
        tabHost.addTab(tab1)

        tab2 = tabHost.newTabSpec("t2")
        tab2.setContent(R.id.tab2)
        tab2.setIndicator("2-HELP")
        tabHost.addTab(tab2)

        btnLogin = findViewById(R.id.btnLogin)
    }
}