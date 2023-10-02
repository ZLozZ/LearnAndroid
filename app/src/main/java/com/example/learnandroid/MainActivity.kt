package com.example.learnandroid

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnShow: Button
    private lateinit var btnManager: Button

    private lateinit var data: SQLiteDatabase
    private lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        btnAdd.setOnClickListener {
            addAuthorDialog()
        }

        btnShow.setOnClickListener {
            val intent = Intent(this, ShowAuthorList::class.java)
            startActivity(intent)
        }

        btnManager.setOnClickListener {

        }

    }

    private fun addControls() {
        btnAdd = findViewById(R.id.btnAdd)
        btnShow = findViewById(R.id.btnShow)
        btnManager = findViewById(R.id.btnManager)

        var helper = ManagerAuthor(applicationContext)
        data = helper.readableDatabase // cho ở chế độ đọc
        rs = data.rawQuery(
            "SELECT * FROM AUTHOR",
            null
        ) // để tương tác lệnh với database, bên trong là lệnh truy vấn
    }

    private fun addAuthorDialog() {
        var focusedEditText: AppCompatEditText? = null
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add)

        var window: Window? = dialog.window ?: return

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var windowAttributes: WindowManager.LayoutParams = window!!.attributes
        windowAttributes.gravity = Gravity.CENTER
        if (window != null) {
            window.attributes = windowAttributes
        }

        dialog.setCancelable(true)

        val editDialogName: EditText = dialog.findViewById(R.id.editDialogName)
        val editDialogCode: EditText = dialog.findViewById(R.id.editDialogCode)
        val btnDialogClear: Button = dialog.findViewById(R.id.btnDialogClear)
        val btnDialogSave: Button = dialog.findViewById(R.id.btnDialogSave)

        // Xác định EditText đang được focus
        editDialogName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                focusedEditText = editDialogName as AppCompatEditText
            }
        }

        editDialogCode.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                focusedEditText = editDialogCode as AppCompatEditText
            }
        }

        btnDialogClear.setOnClickListener {
            focusedEditText?.setText("")
        }

        btnDialogSave.setOnClickListener {
            val name = editDialogName.text.toString()
            val code = editDialogCode.text.toString()

            var cv = ContentValues() // sử dụng để truyền dữ liệu
            cv.put("Name", name)
            cv.put("Code", code)
            data.insert("AUTHOR", null, cv)// chèn vào table AUTHOR
            rs.requery()//update database

            dialog.dismiss()
        }
        dialog.show()
    }

}