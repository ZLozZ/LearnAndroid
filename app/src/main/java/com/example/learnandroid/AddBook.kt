package com.example.learnandroid

import android.app.DatePickerDialog
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class AddBook : AppCompatActivity() {

    private lateinit var spnAlbum:Spinner
    private lateinit var btnAddSong:Button
    private lateinit var btnDate:Button
    private lateinit var editName:EditText
    private lateinit var editDate:EditText
    private lateinit var listBook:ListView

    private var arrAuthor: MutableList<InforData> = mutableListOf()
    private var arrAuthorSpin: MutableList<String> = mutableListOf()
    private lateinit var spninerAdapter: ArrayAdapter<String>
    private lateinit var bookAdapter: CustomList
    private var managerBook: MutableList<InforData> = mutableListOf()

    private var pos:Int = 0
    private var indexManagerBook:Int = 0

    private lateinit var dateFinish:String

    private lateinit var data: SQLiteDatabase
    private lateinit var cursor: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book)

        addControls()
        addEvents()
    }

    private fun addEvents(){
        spnAlbum.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                indexManagerBook = 0
                cursor = data.query(
                    "BOOK",
                    null,
                    "authorid=?",
                    arrayOf<String>(arrAuthor[position].field1.toString()),
                    null,
                    null,
                    null
                )
                cursor.moveToFirst()
                var arrBook: MutableList<InforData> = mutableListOf()
                while (!cursor.isAfterLast) {
                    indexManagerBook+=1
                    arrBook.add(InforData(indexManagerBook, cursor.getString(1), cursor.getString(2)))
                    cursor.moveToNext()
                }
                cursor.close()
                pos = position
                managerBook = arrBook
                bookAdapter = CustomList(this@AddBook, managerBook)
                listBook.adapter =bookAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        btnAddSong.setOnClickListener{
            val name = editName.text.toString()
            val date = editDate.text.toString()

            indexManagerBook+=1

            var cv = ContentValues() // sử dụng để truyền dữ liệu
            cv.put("name", name)
            cv.put("dateadded", date)
            cv.put("authorid", arrAuthor[pos].field1)
            data.insert("BOOK", null, cv)// chèn vào table BOOK
            managerBook.add(InforData(indexManagerBook, name, date))
            bookAdapter.notifyDataSetChanged()
            cursor.requery()//update database
        }

        btnDate.setOnClickListener{
            DatePickerDialog(this, {_,i1,i2,i3->
                dateFinish = "$i3-${i2+1}-$i1"
                editDate.setText(dateFinish)
            },2023,10,6).show()
        }
    }
    private fun addControls(){
        this.spnAlbum = findViewById(R.id.spinnerSp)
        btnAddSong = findViewById(R.id.btnAddBook)
        editDate = findViewById(R.id.editDate)
        editName = findViewById(R.id.editName)
        btnDate = findViewById(R.id.btnDate)
        listBook = findViewById(R.id.listBook)

        var helper = ManagerAuthor(applicationContext)
        data = helper.readableDatabase
        if(data!=null){
            val cursor: Cursor = data.query("AUTHOR", null, null, null, null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                arrAuthor.add(InforData(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
                arrAuthorSpin.add("${cursor.getInt(0)}" +"       "+ cursor.getString(1) +"       "+ cursor.getString(2))
                cursor.moveToNext()
            }
        }
        spninerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrAuthorSpin)
        spninerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spnAlbum.adapter =spninerAdapter
    }
}


