package com.example.learnandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.databinding.ItemAppBinding
import com.example.learnandroid.model.App

class AppAdapter(private val dataSet: List<App>) : RecyclerView.Adapter<ItemViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setDataItem(dataSet[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(dataSet[position].name)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
