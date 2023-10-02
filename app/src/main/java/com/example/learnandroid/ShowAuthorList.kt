package com.example.learnandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ShowAuthorList : AppCompatActivity() {
    private lateinit var listAuthor: ListView
    private lateinit var authorAdapter: CustomList
    private var arrAuthor: MutableList<InforData> = mutableListOf()

    private lateinit var data: SQLiteDatabase
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_list)

        addControls()
        addEvents()
    }

    private fun addEvents() {
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

}