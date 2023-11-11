package com.example.b27

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapterSong(var context: Context, private var song: MutableList<Song>):BaseAdapter(){
    class ViewHolder(row: View) {
        var txtStt: TextView
        var txtCode: TextView
        var txtName: TextView

        init {
            txtStt = row.findViewById(R.id.txtStt)
            txtCode = row.findViewById(R.id.txtCode)
            txtName = row.findViewById(R.id.txtName)
        }
    }

    override fun getCount(): Int {
        return song.size
    }

    override fun getItem(position: Int): Any {
        return song[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        val view = convertview ?: LayoutInflater.from(context)
            .inflate(R.layout.item, parent, false) //Khởi tạo view
        val viewHolder = ViewHolder(view)
        val ds: Song =
            getItem(position) as Song // Lấy album tại vị trí position trong danh sách album
        viewHolder.txtStt.text = ds.stt.toString()
        viewHolder.txtCode.text = ds.nameSong
        viewHolder.txtName.text = ds.date
        return view as View
    }

}