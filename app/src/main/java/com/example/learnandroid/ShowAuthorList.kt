package com.example.learnandroid

import android.app.Dialog
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText

class ShowAuthorList : AppCompatActivity() {
    private lateinit var listAuthor: ListView
    private lateinit var authorAdapter: CustomList
    private var arrAuthor: MutableList<InforData> = mutableListOf()

    private lateinit var data: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_list)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        listAuthor.setOnItemClickListener { _, _, position, _ ->
            showUpdateAlbumDialog(position)
        }
        listAuthor.setOnItemLongClickListener { _, _, i, _ ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Remove")
            val message = "[${arrAuthor[i].field2}' - '${arrAuthor[i].field3}]]"
            builder.setMessage("Bạn muốn xoá album $message hả?")
                .setPositiveButton("Xoá") { _, _ ->
                    data.delete("AUTHOR", "id=?", arrayOf(arrAuthor[i].field1.toString()))

                    arrAuthor.removeAt(i)
                    authorAdapter.notifyDataSetChanged()
                }.setNegativeButton("Không") { dialog, _ ->
                    dialog.dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
            true
        }
    }

    private fun addControls() {
        listAuthor = findViewById(R.id.listMusic)

        var helper = ManagerAuthor(applicationContext)
        data = helper.readableDatabase

        if(data!=null){
            val cursor: Cursor = data.query("AUTHOR", null, null, null, null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                arrAuthor.add(InforData(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
                cursor.moveToNext()
            }
        }
        authorAdapter = CustomList(this@ShowAuthorList, arrAuthor)
        listAuthor.adapter = authorAdapter
    }

    private fun showUpdateAlbumDialog(position: Int) {
        var focusedEditText: AppCompatEditText? = null
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_author)

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

        val editDialogName: EditText = dialog.findViewById(R.id.editNameUpdate)
        val editDialogCode: EditText = dialog.findViewById(R.id.editCodeUpCode)
        val btnDialogClear: Button = dialog.findViewById(R.id.btnClear)
        val btnDialogUpdate: Button = dialog.findViewById(R.id.btnDialogUpdate)

        editDialogName.setText(arrAuthor[position].field3)
        editDialogCode.setText(arrAuthor[position].field2)

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

        btnDialogUpdate.setOnClickListener {
            var cv = ContentValues()
            cv.put("Code", editDialogCode.text.toString())
            cv.put("Name", editDialogName.text.toString())
            data.update("AUTHOR", cv, "id=?",
                arrayOf(arrAuthor[position].field1.toString())
            )
            arrAuthor[position].field2 = editDialogCode.text.toString()
            arrAuthor[position].field3 = editDialogName.text.toString()
            authorAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

}