package com.example.b27

data class Album(val stt:Int, var code: String, var name: String, var songs: MutableList<Song> = mutableListOf())