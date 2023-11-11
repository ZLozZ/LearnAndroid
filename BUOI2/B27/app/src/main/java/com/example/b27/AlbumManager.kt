package com.example.b27

import com.example.b27.AlbumManager.listAlbum

object AlbumManager{
    var songList = mutableListOf<Song>()
    var listAlbum = mutableListOf<Album>()
    fun getSizeSong(position: Int):Int{
        return listAlbum[position].songs.size
    }
    fun getSize(): Int {
        return listAlbum.size
    }
    fun addAlbum(album: Album) {
        listAlbum.add(album)
    }

    fun removeAlbum(album: Album?) {
        listAlbum.remove(album)
    }
    fun addSong(songs: Song, position: Int){
        listAlbum[position].songs.add(songs)
    }
}