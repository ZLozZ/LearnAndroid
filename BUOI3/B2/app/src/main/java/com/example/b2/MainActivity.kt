package com.example.b2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.File
import java.io.FileInputStream


class MainActivity : AppCompatActivity() {
    private lateinit var lstFile:ListView
    private lateinit var btnDelAll:Button
    private lateinit var btnDel:Button

    private lateinit var listAdapter: ArrayAdapter<String>
    private var arrFile: MutableList<String> = mutableListOf()

    private var positionSelect:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
    }

    private fun addEvents() {
        btnDel.setOnClickListener{
            val cacheFile = File(cacheDir, arrFile[positionSelect])
            cacheFile.delete()
            arrFile.remove(arrFile[positionSelect])
            listAdapter.remove(arrFile[positionSelect])
            listAdapter.notifyDataSetChanged()
        }

        btnDelAll.setOnClickListener {
            if((arrFile.size)!=0) {
                while (true) {
                    var index = arrFile.size - 1
                    val cacheFile = File(cacheDir, arrFile[index])
                    cacheFile.delete()
                    listAdapter.remove(arrFile[index])
                    if (index == 0) {
                        break
                    }
                }
            }
            listAdapter.notifyDataSetChanged()
        }

        lstFile.setOnItemClickListener { _, _, position: Int, _ ->
            positionSelect = position
        }

        lstFile.setOnItemLongClickListener { _, _, position: Int, _ ->
            val cacheFile = File(cacheDir, arrFile[position])
            val inputStream = FileInputStream(cacheFile)
            val length = cacheFile.length().toInt()
            val buffer = ByteArray(length)

            inputStream.read(buffer)
            inputStream.close()

            val cacheFileContent = String(buffer, charset("UTF-8"))


            val builder = AlertDialog.Builder(this)
            builder.setTitle("Nội dung file ${arrFile[position]}")
            builder.setMessage("$cacheFileContent")

            builder.apply {
                setPositiveButton("Đóng") { dialog, id ->
                    dialog.dismiss()
                }
            }
            builder.create().show()
            true
        }
    }

    private fun addControls() {
        lstFile = findViewById(R.id.listFile)
        btnDel = findViewById(R.id.btnDl)
        btnDelAll = findViewById(R.id.btnDlAll)

        val cacheDir = this.cacheDir
        val cacheFiles = cacheDir.listFiles().toList()

        for (file in cacheFiles) {
            arrFile.add(file.name)
        }
        listAdapter=ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, arrFile)
        lstFile.adapter = listAdapter
    }

}


