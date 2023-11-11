package tranduythanh.com

import android.R
import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity(), View.OnClickListener {
    var btncreatedatabase: Button? = null
    var btndeletedatabase: Button? = null
    var btncreatetable: Button? = null
    var btninsertrecordtolop: Button? = null
    var btnupdaterowtolop: Button? = null
    var btnquerytbllop: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btncreatedatabase = findViewById<View>(R.id.btncreatedatabase) as Button
        btncreatedatabase!!.setOnClickListener(this)
        btndeletedatabase = findViewById<View>(R.id.btndeletedatabase) as Button
        btndeletedatabase!!.setOnClickListener(this)
        btncreatetable = findViewById<View>(R.id.btncreatetable) as Button
        btncreatetable!!.setOnClickListener(this)
        btninsertrecordtolop = findViewById<View>(R.id.btnInsertRowToTblLop) as Button
        btninsertrecordtolop!!.setOnClickListener(this)
        btnupdaterowtolop = findViewById<View>(R.id.btnupdaterowtotbllop) as Button
        btnupdaterowtolop!!.setOnClickListener(this)
        btnquerytbllop = findViewById<View>(R.id.btnquerylop) as Button
        btnquerytbllop!!.setOnClickListener(this)
        doCreateDb()
    }

    var database: SQLiteDatabase? = null
    fun doCreateDb() {
        database = openOrCreateDatabase(
            "qlsinhvien.db",
            MODE_PRIVATE,
            null
        )
    }

    fun doDeleteDb() {
        var msg = ""
        msg = if (deleteDatabase("qlsinhvien.db") == true) {
            "Delete database [qlsinhvien.db] is successful"
        } else {
            "Delete database [qlsinhvien.db] is failed"
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun doCreateTable() {
        doCreateDb()
        doCreateLopTable()
        doCreateSinhvienTable()
    }

    fun doCreateLopTable() {
        var sql = "CREATE TABLE tbllop ("
        sql += "malop TEXT primary key,"
        sql += "tenlop TEXT,"
        sql += "siso INTEGER)"
        database!!.execSQL(sql)
    }

    fun doCreateSinhvienTable() {
        val sql = "CREATE TABLE tblsinhvien (" +
                "masv TEXT PRIMARY KEY ," +
                "tensv TEXT," +
                "malop TEXT NOT NULL CONSTRAINT malop " +
                " REFERENCES tbllop(malop) ON DELETE CASCADE)"
        database!!.execSQL(sql)
    }

    fun doDeleteRecordTable() {
        database!!.delete("tbllop", null, null)
        val malop = "DHTH7C"
        database!!.delete(
            "tbllop",
            "malop=?", arrayOf(malop)
        )
    }

    fun loadalllop() {
        val c = database!!.query(
            "tbllop",
            null, null, null, null, null, null
        )
        c.moveToFirst()
        var data = ""
        while (c.isAfterLast == false) {
            data += c.getString(0) + "-" +
                    c.getString(1) + "-" + c.getString(2)
            data += "\n"
            c.moveToNext()
        }
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
        c.close()
    }

    fun doInsertRecord() {
        val values = ContentValues()
        values.put("malop", "DHTH7A")
        values.put("tenlop", "Dai hoc tin hoc 7a")
        values.put("siso", 20)
        var msg = ""
        msg = if (database!!.insert("tbllop", null, values) == -1L) {
            "Failed to insert record"
        } else {
            "insert record is successful"
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun updateLopName(malop: String, new_tenlop: String?) {
        val values = ContentValues()
        values.put("tenlop", new_tenlop)
        var msg = ""
        val ret = database!!.update(
            "tbllop", values,
            "malop=?", arrayOf(malop)
        )
        msg = if (ret == 0) {
            "Failed to update"
        } else {
            "updating is successful"
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onClick(arg0: View) {
        if (arg0 === btncreatedatabase) {
            doCreateDb()
        } else if (arg0 === btndeletedatabase) {
            doDeleteDb()
        } else if (arg0 === btncreatetable) {
            doCreateTable()
        } else if (arg0 === btninsertrecordtolop) {
            doInsertRecord()
        } else if (arg0 === btnupdaterowtolop) {
            updateLopName("DHTH7C", "DAI HOC XYZ")
        } else if (arg0 === btnquerytbllop) {
            loadalllop()
        }
    }
}