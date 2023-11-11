package com.example.b5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var btnSetting:Button
    private lateinit var txtColor:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()

    }

    private fun addEvents() {
        btnSetting.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val context:Context = applicationContext
        val pre:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        pre.registerOnSharedPreferenceChangeListener(this)
    }

    private fun addControls() {
        btnSetting = findViewById(R.id.btnSetting)
        txtColor = findViewById(R.id.textColor)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        val value = p0?.getBoolean(p1, false)
        if(value == true){
            txtColor.setBackgroundColor(getColor(R.color.red))
        }else{
            txtColor.setBackgroundColor(getColor(R.color.blue))
        }
    }
}