package com.example.learnandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class ShowContact : AppCompatActivity() {
    private lateinit var btnBack: Button
    private lateinit var listContact:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_contact)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        btnBack.setOnClickListener{
            finish()
        }
    }

    private fun addControls() {
        btnBack = findViewById(R.id.btnBack)
        listContact = findViewById(R.id.Contact)
    }
}