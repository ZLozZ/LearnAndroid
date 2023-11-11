package com.example.b22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var gd:GridView
    private lateinit var textPosition:TextView
    private lateinit var img: ImageView
    private lateinit var btnBack: Button

    private var arrAdapter: ArrayList<MdGrid> = ArrayList()
    private val arrImg = arrayOf(dges_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4,
        R.drawable.image_5, R.drawable.image_6, R.drawable.image_7)

    private var myBackupBundle: android.os.Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        myBackupBundle=savedInstanceState
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEventGrid()
    }

    private fun addEventGrid() {
        gd.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            setContentView(R.layout.solo)
            textPosition = findViewById(R.id.tP)
            img = findViewById(R.id.imgSolo)
            btnBack = findViewById(R.id.btnBack)
            //textPosition.setText("Image at" + position.toString())
            img.setImageResource(arrImg[position])
            btnBack.setOnClickListener(View.OnClickListener(){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })
        }
    }


    private fun addControls() {
        gd = findViewById(R.id.gd)
        gd.adapter = CustomAdapter(this, arrAdapter)
        for(i in 0..(arrImg.size-1)){
            arrAdapter.add(MdGrid(arrImg[i]))
        }

    }

}

