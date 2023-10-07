package com.example.learnandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnShow: Button
    private lateinit var btnCallLog: Button
    private lateinit var btnMedia: Button
    private lateinit var btnBookMark: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        btnShow.setOnClickListener{
            val intent = Intent(this, ShowContact::class.java)
            startActivity(intent)
        }
    }

    private fun addControls() {
        btnShow = findViewById(R.id.btnShow)
        btnCallLog = findViewById(R.id.btnCallLog)
        btnBookMark = findViewById(R.id.btnBookmarks)
        btnMedia = findViewById(R.id.btnMedia)
    }
}