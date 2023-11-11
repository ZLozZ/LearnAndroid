package com.example.b21

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity() {

    private lateinit var spnSp: Spinner
    private lateinit var autoText: AutoCompleteTextView
    private lateinit var gd:GridView
    private lateinit var btnIn:Button

    private val arrSp = arrayOf("Điện thoai", "Máy Tính", "Đồng hồ")
    private var lookText = mutableListOf<String>("Iphone", "Nokia", "LG")
    private var brandDt = mutableListOf<String>("Ipad","New Ipad", "SamSung", "Nokia", "Sony Ericson", "LG", "Q-Mobile")
    private val brandDh = mutableListOf<String>("Philippe", "Rolex", "Tag Heuer")
    private val brandMt = mutableListOf<String>("Casio", "Vinacal")

    lateinit var arrDt:ArrayAdapter<String>
    lateinit var arrDh:ArrayAdapter<String>
    lateinit var arrMt:ArrayAdapter<String>
    private lateinit var spninerAdapter: ArrayAdapter<String>
    private lateinit var adapterAuto:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEventGd()
        addEventBtn()
        addEventGrib()
    }

    private fun addEventGrib(){
        gd.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val builder = AlertDialog.Builder(this)
                val title = builder.setTitle("Thông tin")
                builder.setMessage(
                    "Position: $position Value: " +when(spnSp.selectedItemId){
                    0L->arrDt.getItem(position)
                    1L->brandMt[position]
                    else->brandDh[position]
                })
                builder.apply {
                    setNegativeButton("Đóng") { dialog, _ ->
                        dialog.dismiss()
                    }
                }
                builder.create().show()
        }
    }

    private fun addEventBtn() {
        btnIn.setOnClickListener{
            var textToAuto = autoText.text.toString()
//            Toast.makeText(this, textToAuto, Toast.LENGTH_SHORT).show()
            adapterAuto.add(textToAuto)
            adapterAuto.notifyDataSetChanged()

            when(spnSp.selectedItemId){
                0L->{
                    brandDt.add(textToAuto)
                    arrDt.notifyDataSetChanged()
                }
                1L->{
                    brandMt.add(textToAuto)
                    arrMt.notifyDataSetChanged()
                }
                else->{
                    brandDh.add(textToAuto)
                    arrDh.notifyDataSetChanged()
                }
            }
        }
    }

    private fun addEventGd() {
        spnSp.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                gd.adapter = when (arrSp[position]) {
                    arrSp[0] -> arrDt
                    arrSp[1] -> arrMt
                    else -> arrDh
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    fun addControls(){
        spnSp = findViewById(R.id.spinner)
        spninerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrSp)
        spninerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spnSp.adapter = spninerAdapter

        autoText = findViewById(R.id.autoText)
        autoText.addTextChangedListener();
        adapterAuto = ArrayAdapter(this, android.R.layout.simple_list_item_1, lookText)
        autoText.setAdapter(adapterAuto)


        gd = findViewById(R.id.Gd)

        arrDt = ArrayAdapter(this, android.R.layout.simple_list_item_1, brandDt)
        arrDh = ArrayAdapter(this, android.R.layout.simple_list_item_1, brandDh)
        arrMt = ArrayAdapter(this, android.R.layout.simple_list_item_1, brandMt)

        btnIn = findViewById(R.id.btnInput)
    }
}

