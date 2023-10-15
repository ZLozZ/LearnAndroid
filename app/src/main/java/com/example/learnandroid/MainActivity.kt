package com.example.learnandroid

import android.content.pm.PackageManager
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
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val listApp = mutableListOf<App>()
        packages.forEach { appInfo ->
            println(appInfo.packageName)
            listApp.add(App(R.drawable.ic_launcher_foreground, appInfo.packageName))
        }
        binding.rvApp.layoutManager = GridLayoutManager(this, 3)
        itemAdapter = AppAdapter(listApp)
        binding.rvApp.adapter = itemAdapter
    }
}
