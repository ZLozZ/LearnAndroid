package com.example.b4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnAccess: Button
    private lateinit var editUser: EditText
    private lateinit var editPass: EditText
    private lateinit var cbSave: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvent()
    }

    private fun addEvent() {
        val sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        btnAccess.setOnClickListener {
            if (cbSave.isChecked) {
                val user = editUser.text.toString()
                val pass = editPass.text.toString()
                editor.putString("user", user)
                editor.putString("pass", pass)
                editor.apply()
            } else {
                editor.clear()
            }
            editor.commit()
            finish()
        }
    }

    private fun addControls() {
        btnAccess = findViewById(R.id.btnAccess)
        cbSave = findViewById(R.id.cbSave)
        editPass = findViewById(R.id.editPass)
        editUser = findViewById(R.id.editUser)

        val sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        editUser.setText(sharedPref.getString("user", ""))
        editPass.setText(sharedPref.getString("pass", ""))
    }
}