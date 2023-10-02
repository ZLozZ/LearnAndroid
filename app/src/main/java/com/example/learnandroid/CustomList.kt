package com.example.learnandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomList(private var context: Context, private var infor: MutableList<InforData>) : BaseAdapter() {

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
        return infor.size
    }

    override fun getItem(position: Int): Any {
        return infor[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        val view = convertview ?: LayoutInflater.from(context)
            .inflate(R.layout.custom_list, parent, false) //Khởi tạo view
        val viewHolder = ViewHolder(view)
        val ds: InforData = getItem(position) as InforData// Lấy author tại vị trí position trong danh sách author
        viewHolder.txtStt.text = ds.field1.toString()
        viewHolder.txtCode.text = ds.field2
        viewHolder.txtName.text = ds.field3
        return view as View
    }
}
