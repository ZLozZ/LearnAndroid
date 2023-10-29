package com.example.loofarm

import androidx.recyclerview.widget.RecyclerView
import com.example.loofarm.databinding.ItemFarmBinding

class ItemViewRV(private val binding: ItemFarmBinding): RecyclerView.ViewHolder(binding.root) {
    fun setDataItem(txt: String) {
        binding.txtItem.text = txt
    }
}