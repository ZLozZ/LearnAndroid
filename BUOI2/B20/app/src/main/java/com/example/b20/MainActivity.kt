package com.example.b20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    lateinit var spnSp: Spinner
    lateinit var lSp: ListView
    val brandDt = arrayOf(
        "Iphone 5",
        "SamSung S III",
        "HTC",
        "Windows Phone Surface",
        "Nokia 1100",
        "Q-Mobile"
    )
    val brandDh = arrayOf("Philippe", "Rolex", "Tag Heuer")
    val brandMt = arrayOf("Casio", "Vinacal")
    val arrSp = arrayOf("Điện thoai", "Máy Tính", "Đồng hồ")
    val imgDt = arrayOf(
        R.drawable.iphone, R.drawable.samsung, R.drawable.htc,
        R.drawable.windown, R.drawable.nokia, R.drawable.qmobile
    )
    val imgSp = arrayOf(R.drawable.images, R.drawable.images_1)
    var arrDt: ArrayList<Ds> = ArrayList()
    var arrDh: ArrayList<Ds> = ArrayList()
    var arrMt: ArrayList<Ds> = ArrayList()
    private lateinit var spninerAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
        addEventList()
    }

    private fun addEventList() {
        lSp.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "position: " + position + "Value: " + when (spnSp.selectedItemId) {
                    0L -> "Điện thoại => ${arrDt[position].ten}"
                    1L -> "Máy tính => ${arrMt[position].ten}"
                    else -> "Đồng hồ => ${arrDh[position].ten}"
                }, Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun addEvents() {
        spnSp.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Log.d("Lhjhjk", "value arrSp-> ${arrSp[position]}")
                if (arrSp[position] == arrSp[0]) {
                    lSp.adapter = CustomAdapter(this@MainActivity, arrDt)
                } else if (arrSp[position] == arrSp[1]) {
                    lSp.adapter = CustomAdapter(this@MainActivity, arrMt)
                } else {
                    lSp.adapter = CustomAdapter(this@MainActivity, arrDh)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    fun addControls() {
        spnSp = findViewById(R.id.spinnerSp)
        spninerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, arrSp
        )
        spninerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spnSp.adapter = spninerAdapter

        lSp = findViewById(R.id.list)

        for (i in 0..(brandDt.size - 1)) {
            arrDt.add(Ds(brandDt[i], imgDt[i]))
        }
        Toast.makeText(this, "$arrDt", Toast.LENGTH_SHORT).show()
        for (i in 0..(brandDh.size - 1)) {
            arrDh.add(Ds(brandDh[i], imgSp[0]))
        }

        for (i in 0..(brandMt.size - 1)) {
            arrMt.add(Ds(brandMt[i], imgSp[1]))
        }
    }
}
