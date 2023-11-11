package com.example.b27

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class AlbumActivity : AppCompatActivity() {
    private lateinit var listMusic: ListView
    private lateinit var albumAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show)

        addControls()
        addEvents()
    }

    private fun addEvents() {
        listMusic.setOnItemClickListener { _, _, position, _ ->
            showUpdateAlbumDialog(position)
        }
        listMusic.setOnItemLongClickListener { adapterView, view, i, l ->
            val builder = AlertDialog.Builder(this)
            val album = AlbumManager.listAlbum[i]
            builder.setMessage("Bạn muốn xoá album ${album.name}?")
                .setPositiveButton("Xoá") { _, _ ->

                    AlbumManager.removeAlbum(album)
                    // Xoá xong cập nhật lại list album
                    albumAdapter.notifyDataSetChanged()

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
        listMusic = findViewById(R.id.listMusic)
        albumAdapter = CustomAdapter(this@AlbumActivity, AlbumManager.listAlbum)
        listMusic.adapter = albumAdapter
    }

    private fun showUpdateAlbumDialog(position: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_update)

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

        val editDialogName: EditText = dialog.findViewById(R.id.editDialogName)
        val editDialogCode: EditText = dialog.findViewById(R.id.editDialogCode)
        val btnDialogClear: Button = dialog.findViewById(R.id.btnClear)
        val btnDialogUpdate: Button = dialog.findViewById(R.id.btnDialogUpdate)

        editDialogName.setText(AlbumManager.listAlbum[position].name)
        editDialogCode.setText(AlbumManager.listAlbum[position].code)

        btnDialogClear.setOnClickListener {
            editDialogName.setText("")
            editDialogCode.setText("")
        }
        btnDialogUpdate.setOnClickListener {
            AlbumManager.listAlbum[position].name = editDialogName.text.toString()
            AlbumManager.listAlbum[position].code = editDialogCode.text.toString()
            albumAdapter.notifyDataSetChanged()
        }
        dialog.show()
    }

}