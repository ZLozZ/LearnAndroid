package com.example.b27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast

class SongActivity : AppCompatActivity() {

    private lateinit var spnAlbum:Spinner
    private lateinit var btnAddSong:Button
    private lateinit var editName:EditText
    private lateinit var editDate:EditText
    private lateinit var listSong:ListView

    private var arrAlbum: MutableList<String> = mutableListOf()
    private lateinit var spninerAdapter: ArrayAdapter<String>

    private lateinit var songAdapter:CustomAdapterSong

    var pos:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_song)
        addControls()
        addEvents()
    }

    private fun addEvents(){
        spnAlbum.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                songAdapter= CustomAdapterSong(this@SongActivity, AlbumManager.listAlbum[position].songs)
                pos = position
                listSong.adapter = songAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        btnAddSong.setOnClickListener{
            val name = editName.text.toString()
            val date = editDate.text.toString()
            var songInput = Song(AlbumManager.getSizeSong(pos)+1, name, date)
            AlbumManager.addSong(songInput, pos)
            songAdapter.notifyDataSetChanged()
        }
    }
    private fun addControls(){
        this.spnAlbum = findViewById(R.id.spinnerSp)
        btnAddSong = findViewById(R.id.btnAddSong)
        editDate = findViewById(R.id.editDate)
        editName = findViewById(R.id.editName)
        listSong = findViewById(R.id.listSong)
//        Toast.makeText(this, "${AlbumManager.listAlbum}",Toast.LENGTH_SHORT).show()
        for(i in 0..(AlbumManager.listAlbum.size-1)){
            arrAlbum.add(AlbumManager.listAlbum[i].stt.toString()+" "+AlbumManager.listAlbum[i].code+" "+AlbumManager.listAlbum[i].name)
        }
//        Toast.makeText(this, "$arrAlbum",Toast.LENGTH_SHORT).show()
        spninerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrAlbum)
        spninerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spnAlbum.adapter =spninerAdapter

    }
}


