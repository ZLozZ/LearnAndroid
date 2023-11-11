package com.example.b22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListAdapter

class CustomAdapter(private var context: MainActivity, private var itemCt: ArrayList<MdGrid>):BaseAdapter(), ListAdapter{

    lateinit var imagePic: ImageView
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return itemCt.size
    }

    override fun getItem(posistion: Int): Any {
        return itemCt[posistion]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, p2: ViewGroup?): View? {
        var convertView = view
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.custom_gridview, null)
        }

        imagePic = convertView!!.findViewById(R.id.imgPicture)
        imagePic.setImageResource(itemCt[position].img)

        return convertView
    }
}