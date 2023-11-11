package com.example.b6

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.database.sqlite.SQLiteDatabase



class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd:Button
    private lateinit var btnShow:Button
    private lateinit var btnManager:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        btnAdd.setOnClickListener{
            addEventDialog(Gravity.CENTER)
        }

        btnShow.setOnClickListener{

        }

        btnManager.setOnClickListener{

        }
    }

    private fun addControls() {
        btnAdd = findViewById(R.id.btnAdd)
        btnShow = findViewById(R.id.btnShow)
        btnManager = findViewById(R.id.btnManager)
    }

    private fun addEventDialog(gravity: Int){
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add)

        var window: Window? = dialog.window ?: return

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var windowAttributes: WindowManager.LayoutParams = window!!.attributes
        windowAttributes.gravity = gravity
        if (window != null) {
            window.attributes = windowAttributes
        }

        dialog.setCancelable(true)

        val editDialogName: EditText = dialog.findViewById(R.id.editDialogName)
        val editDialogCode: EditText =  dialog.findViewById(R.id.editDialogCode)
        val btnDialogClear:Button = dialog.findViewById(R.id.btnDialogClear)
        val btnDialogSave:Button = dialog.findViewById(R.id.btnDialogSave)

        btnDialogClear.setOnClickListener{
            editDialogName.setText("")
            editDialogCode.setText("")
        }
        btnDialogSave.setOnClickListener{
            val name = editDialogName.text.toString()
            val code = editDialogCode.text.toString()
        }
        dialog.show()
    }

}