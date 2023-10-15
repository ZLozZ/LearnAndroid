package com.example.learnandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.databinding.CustomBinding

class CustomAdapter(private val dataSet: List<String>, private val img: List<Int>) : RecyclerView.Adapter<ItemsViewHolder>() {

    var onItemClick: ((String)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = CustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.setDataItem(dataSet[position], img[position])
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
