package com.example.b19

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var context:Context, var dd:ArrayList<Ds>): BaseAdapter() {

    class ViewHolder(row:View){
        var textViewten:TextView
        var imageDd:ImageView

        init{
            textViewten = row.findViewById(R.id.txtTen) as TextView
            imageDd = row.findViewById(R.id.imageHinh) as ImageView
        }
    }
    override fun getCount(): Int {
        return dd.size
    }

    override fun getItem(position: Int): Any {
        return dd.get(position)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(position: Int, convertview: View?, p2: ViewGroup?): View {
        var view: View?
        var vHolder: ViewHolder
        if(convertview == null){
            var lFlater:LayoutInflater = LayoutInflater.from(context)
            view = lFlater.inflate(R.layout.item, null)
            vHolder = ViewHolder(view)
            view.tag = vHolder
        }else{
            view = convertview
            vHolder = convertview.tag as ViewHolder
        }
        var ds: Ds = getItem(position) as Ds
        vHolder.textViewten.text = ds.ten
        vHolder.imageDd.setImageResource(ds.img)
        return view as View
    }

}