package com.example.learnandroid

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val login = Login()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initControls()
        initEvents()
    }

    private fun initControls() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frLayout, login)
            commit()
        }
    }

    private fun initEvents() {

    }
}