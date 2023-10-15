package com.example.learnandroid

import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.databinding.CustomBinding

class ItemsViewHolder(private val binding: CustomBinding): RecyclerView.ViewHolder(binding.root){
    fun setDataItem(txt: String, img: Int){
        binding.tvItem.text = txt
        binding.imgItem.setImageResource(img)
    }

}