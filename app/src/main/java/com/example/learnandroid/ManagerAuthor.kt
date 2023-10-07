package com.example.learnandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ManagerAuthor(context:Context): SQLiteOpenHelper(context, "MANAGER_AUTHOR", null, 1) {
    override fun onCreate(data: SQLiteDatabase?) {
        //tạo table, column, có autoicreament để tự tăng
        data?.execSQL("CREATE TABLE AUTHOR(id integer primary key autoincrement, Code TEXT, Name TEXT)")
        data?.execSQL("create table BOOK ("
                + "id integer primary key autoincrement,"
                + "name text, "
                + "dateadded date,"
                + "authorid integer not null constraint authorid references AUTHOR(id) on delete cascade)")
        //Cách tạo trigger khi nhập dữ liệu sai ràng buộc quan hệ
        data?.execSQL("create trigger fk_insert_book before insert on BOOK "
                + " for each row "
                + " begin "
                + " 	select raise(rollback,'them du lieu tren bang BOOK bi sai') "
                + " 	where (select id from AUTHOR where id=new.authorid) is null ;"
                + " end;")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}