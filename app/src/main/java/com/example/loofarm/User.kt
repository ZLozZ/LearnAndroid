package com.example.loofarm

data class User(
    var userId: String? = "",
    var email: String? = "",
    var username: String? = "",
    var device: Device? = null
)