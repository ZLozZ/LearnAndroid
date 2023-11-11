package com.example.b6

data class Album(val stt:Int, var code: String, var name: String, var songs: MutableList<Song> = mutableListOf())