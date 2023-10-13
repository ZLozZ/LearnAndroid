package com.example.learnandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EmployeesManager(context: Context): SQLiteOpenHelper(context, "MANAGER_AUTHOR", null, 1){
    override fun onCreate(data: SQLiteDatabase?) {
        //tạo table, column, có autoicreament để tự tăng
        data?.execSQL("CREATE TABLE EMPLOYEES(id integer primary key autoincrement, Code TEXT, Name TEXT, Age Int)")

        //ADD Data
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-111','Nguyễn Đại Nhân', 30)")
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-112','Trần Đại Nghĩa', 31)")
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-113','Hoàng Đại Lễ', 35)")
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-114','Phạm Đại Trí', 30)")
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-115','Trương Đại Tín', 31)")
        data?.execSQL("INSERT INTO EMPLOYEES(Code, Name, Age) VALUES ('NV-116','Hồ Đại Đức', 37)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}