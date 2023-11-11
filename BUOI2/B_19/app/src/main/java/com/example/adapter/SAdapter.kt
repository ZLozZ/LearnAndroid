package com.example.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.model.SanPham
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.b_19.R
import android.widget.*

class SAdapter(private val context: Activity, private val title: Array<String>, private val imgId: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.item, title) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item, null, true)

        val txtTen = rowView.findViewById(R.id.txtTen) as TextView
        val imageView = rowView.findViewById(R.id.imageHinh) as ImageView
        txtTen.text = title[position]
        imageView.setImageResource(imgId[position])
//        var dem:Int = 0
//        for(i in title[position]){
//            dem++
//        }
//        if(dem<=3){
//            imageView.setImageResource(imgId[2])
//        }
//        else{
//            imageView.setImageResource(imgId[1])
//        }
        return rowView
    }
}