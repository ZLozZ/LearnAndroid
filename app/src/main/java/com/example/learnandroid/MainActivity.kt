package com.example.learnandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnandroid.adapter.AppAdapter
import com.example.learnandroid.databinding.ActivityMainBinding
import com.example.learnandroid.model.App

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addViews()
        addEvents()
    }

    private fun addEvents() {
        itemAdapter.onItemClick = { appName ->
            Toast.makeText(this, "$appName", Toast.LENGTH_SHORT).show()

        }
    }

    private fun addViews() {
        val listApp = listOf(
            App(androidx.constraintlayout.widget.R.drawable.abc_ab_share_pack_mtrl_alpha, "app1"),
            App(com.google.android.material.R.drawable.ic_mtrl_checked_circle, "app2"),
            App(androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material, "app3"),
            App(com.google.android.material.R.drawable.abc_edit_text_material, "app4"),
        )
        binding.rvApp.layoutManager = GridLayoutManager(this, 3)
        itemAdapter = AppAdapter(listApp)
        binding.rvApp.adapter = itemAdapter
    }
}
