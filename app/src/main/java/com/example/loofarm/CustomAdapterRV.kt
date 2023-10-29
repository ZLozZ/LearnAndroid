package com.example.loofarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loofarm.databinding.ItemFarmBinding

class CustomAdapterRV(private val dataSet: List<String>): RecyclerView.Adapter<ItemViewRV>() {

    private var onItemClick: ((String)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewRV {
        val view = ItemFarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewRV(view)
    }

    override fun onBindViewHolder(holder: ItemViewRV, position: Int) {
        holder.setDataItem(dataSet[position])
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}