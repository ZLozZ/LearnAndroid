package com.example.learnandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var itemAdapter:CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addViews()
        addEvents()

    }

    private fun addEvents() {
        itemAdapter.onItemClick = { c ->
            Toast.makeText(this, "$c", Toast.LENGTH_SHORT).show()

        }
    }

    private fun addViews() {
        val listText = listOf("app1", "app2", "app3", "app4")
        val listImg = listOf(
            androidx.constraintlayout.widget.R.drawable.abc_ab_share_pack_mtrl_alpha, com.google.android.material.R.drawable.ic_mtrl_checked_circle,
            androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material, com.google.android.material.R.drawable.abc_edit_text_material)
        binding.rcList.layoutManager = GridLayoutManager(this, 3)
        itemAdapter = CustomAdapter(listText, listImg)
        binding.rcList.adapter = itemAdapter
    }


}