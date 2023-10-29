package com.example.loofarm

object DataUser {
    private var user = mutableListOf<User>()

    fun setUserId(id: String, position: Int){
        user[position].userId = id
    }

    fun addUser(user: User){

    }

    fun setEmail(){

    }

    fun setUserName(){

    }

    fun setDevice(){

    }
}

//var userId: Long? = 0,
//var email: String? = "",
//var username: String? = "",
//var device: Device? = null