package com.example.learnandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ManagerAuthor(context:Context): SQLiteOpenHelper(context, "MANAGER_AUTHOR", null, 1) {
    override fun onCreate(data: SQLiteDatabase?) {
        //tạo table, column, có autoicreament để tự tăng
        data?.execSQL("CREATE TABLE AUTHOR(id integer primary key autoincrement, Code TEXT, Name TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}