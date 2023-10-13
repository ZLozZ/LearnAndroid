package com.example.learnandroid

import android.app.Dialog
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    private lateinit var lstEmployees:ListView

    private lateinit var data: SQLiteDatabase
    private var arrEmployees: MutableList<String> = mutableListOf()
    private var arrEmployeesUp: MutableList<InforEmployee> = mutableListOf()
    private lateinit var employeesAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        lstEmployees.setOnItemLongClickListener { _, _, i, _ ->
            showUpdateDialog(i)
            true
        }

    }


    private fun addControls() {
        lstEmployees = findViewById(R.id.listEmployees)
        var helper = EmployeesManager(applicationContext)
        data = helper.readableDatabase

        if(data!=null){
            val cursor: Cursor = data.query("EMPLOYEES", null, null, null, null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                arrEmployees.add(cursor.getString(2))
                arrEmployeesUp.add(InforEmployee(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)))
                cursor.moveToNext()
            }
        }
        employeesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrEmployees)
        lstEmployees.adapter = employeesAdapter
    }

    private fun showUpdateDialog(position: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog)

        val window: Window? = dialog.window ?: return

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes: WindowManager.LayoutParams = window!!.attributes
        windowAttributes.gravity = Gravity.CENTER
        if (window != null) {
            window.attributes = windowAttributes
        }

        dialog.setCancelable(true)

        val editDialogName: EditText = dialog.findViewById(R.id.editName)
        val editDialogAge: EditText = dialog.findViewById(R.id.editAge)
        val editDialogCode: EditText = dialog.findViewById(R.id.editCode)
        val btnDialogClear: Button = dialog.findViewById(R.id.btnClear)
        val btnDialogBack: Button = dialog.findViewById(R.id.btnBack)

        editDialogName.setText(arrEmployeesUp[position].name)
        editDialogCode.setText(arrEmployeesUp[position].code)
        editDialogAge.setText(arrEmployeesUp[position].age.toString())

        btnDialogClear.setOnClickListener {
            data.delete("EMPLOYEES", "id=?", arrayOf(arrEmployeesUp[position].id.toString()))
            arrEmployeesUp[position]
            arrEmployees.removeAt(position)
            employeesAdapter.notifyDataSetChanged()
        }

        btnDialogBack.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}