package com.example.model

import java.io.Serializable

class SanPham(picture:Int, position: String){
    private var picture:Int= 0
    private var position:String = ""


    fun getPicture():Int{
        return picture
    }
    fun setPicture(picture:Int){
        this.picture = picture
    }

    fun getPosition():String{
        return position
    }
    fun setPositione(position:String){
        this.position = position
    }

}