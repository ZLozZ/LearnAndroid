package com.example.b1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import java.util.Scanner

class MainActivity : AppCompatActivity() {
    lateinit var btnRead: Button
    lateinit var btnWrite: Button
    lateinit var editContent: EditText

    private var externalStorageDirectory = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_DOWNLOADS}"
    private var fileName = "mydata.txt"
    private var file = File(externalStorageDirectory, fileName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
    }

    private fun addEvents() {
//        btnWrite.setOnClickListener{
//            val content = editContent.text.toString()
//            try {
//                val outputStream = openFileOutput("data.txt", Context.MODE_PRIVATE)
//                outputStream.write(content.toByteArray())
//                outputStream.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        btnRead.setOnClickListener{
//            try {
//                val inputStream = openFileInput("data.txt")
//                val contentRead = StringBuilder()
//                val buffer = ByteArray(1024)
//                var bytesRead: Int
//
//                while ((inputStream.read(buffer).also { bytesRead = it}) != -1) {
//                    contentRead.append(String(buffer, 0, bytesRead))
//                }
//
//                inputStream.close()
//                editContent.setText(contentRead.toString())
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
        //External
        btnWrite.setOnClickListener{
            val content = editContent.text.toString()
            try {
                val outputStream = FileOutputStream(file)
                outputStream.write(content.toByteArray())
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnRead.setOnClickListener{
            try {
                val inputStream = FileInputStream(file)
                val length = file.length().toInt()
                val buffer = ByteArray(length)

                inputStream.read(buffer)
                inputStream.close()

                val readContent = String(buffer, charset("UTF-8"))
                editContent.setText(readContent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //cache
//        btnWrite.setOnClickListener {
//            val content = editContent.text.toString()
//            try {
//                val cacheFile = File(cacheDir, "mydata.txt")
//                val outputStream = FileOutputStream(cacheFile)
//                outputStream.write(content.toByteArray())
//                outputStream.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        btnRead.setOnClickListener {
//            val cacheFileName = "mydata.txt"
//            val cacheFile = File(cacheDir, cacheFileName)
//
//            try {
//                val inputStream = FileInputStream(cacheFile)
//                val length = cacheFile.length().toInt()
//                val buffer = ByteArray(length)
//
//                inputStream.read(buffer)
//                inputStream.close()
//
//                val cacheFileContent = String(buffer, charset("UTF-8"))
//                editContent.setText(cacheFileContent)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
    }


    private fun addControls() {
        btnRead = findViewById(R.id.btnRead)
        btnWrite = findViewById(R.id.btnWrite)
        editContent = findViewById(R.id.editConntent)

    }
}