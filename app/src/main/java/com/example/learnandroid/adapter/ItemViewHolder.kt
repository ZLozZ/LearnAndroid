package com.example.learnandroid.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.R
import com.example.learnandroid.databinding.ItemAppBinding
import com.example.learnandroid.model.App

class ItemViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setDataItem(app: App) {
        binding.tvItem.text = app.name
        binding.imgItem.setImageResource(app.icon ?: R.drawable.ic_launcher_foreground)
    }
}
