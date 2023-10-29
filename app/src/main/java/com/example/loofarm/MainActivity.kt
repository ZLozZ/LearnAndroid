package com.example.loofarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loofarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val login = Login()
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